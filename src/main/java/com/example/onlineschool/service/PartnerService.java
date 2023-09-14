package com.example.onlineschool.service;

import com.example.onlineschool.dto.partner.CreatePartner;
import com.example.onlineschool.entity.Partner;
import com.example.onlineschool.exception.PartnerExistException;
import com.example.onlineschool.exception.PartnerNotFoundException;
import com.example.onlineschool.exception.PictureNotFoundException;
import com.example.onlineschool.mapper.PartnerMapper;
import com.example.onlineschool.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartnerService {
    private final PartnerRepository repository;
    private final PartnerMapper mapper;

    public Partner create(CreatePartner dto) throws IOException {
        if (repository.existsByName(dto.name())) {
            throw new PartnerExistException();
        }
        String imgUrl = saveImg(dto.file());
        Partner partner = mapper.asNewPartner(dto, imgUrl);
        return repository.save(partner);
    }

    private static final String BASE_IMAGE_PATH = "src/main/resources/";

    private String saveImg(MultipartFile imageFile) throws IOException {
        if (Objects.isNull(imageFile) || imageFile.isEmpty()) {
            throw new PictureNotFoundException();
        }
        String imageUrl = String.format("images/%s.jpg", UUID.randomUUID());
        File file = new File(BASE_IMAGE_PATH + imageUrl);
        file.getParentFile().mkdirs();
        file.createNewFile();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] mainContent = imageFile.getBytes();
            outputStream.write(mainContent);
        }
        return imageUrl;
    }

    public List<Partner> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public Partner getById(Long id) {
        return repository.findById(id).orElseThrow(PartnerNotFoundException::new);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
