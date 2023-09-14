package com.example.onlineschool.service;

import com.example.onlineschool.dto.circle.CreateCircle;
import com.example.onlineschool.entity.Circle;
import com.example.onlineschool.exception.CircleExistException;
import com.example.onlineschool.exception.CircleNotFoundException;
import com.example.onlineschool.exception.PictureNotFoundException;
import com.example.onlineschool.mapper.CircleMapper;
import com.example.onlineschool.repository.CircleRepository;
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
public class CircleService {

    private final CircleRepository repository;
    private final CircleMapper mapper;

    public Circle create(CreateCircle dto) throws IOException {
        if (repository.existsByName(dto.name())) {
            throw new CircleExistException();
        }
        String image = saveImage(dto.file());
        Circle circle = mapper.asNewCircle(dto, image);
        return repository.save(circle);
    }

    private static final String BASE_IMAGE_PATH = "src/main/resources/";

    private String saveImage(MultipartFile image) throws IOException {
        if (Objects.isNull(image) || image.isEmpty()) {
            throw new PictureNotFoundException();
        }
        String imageUrl = String.format("images/%s.jpg", UUID.randomUUID());
        File file = new File(BASE_IMAGE_PATH + imageUrl);
        file.getParentFile().mkdirs();
        file.createNewFile();
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            byte[] mainContent = image.getBytes();
            outputStream.write(mainContent);
        }
        return imageUrl;
    }

    public Circle getById(Long id) {
        return repository.findById(id).orElseThrow(CircleNotFoundException::new);
    }

    public List<Circle> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
