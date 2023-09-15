package com.example.onlineschool.service;

import com.example.onlineschool.dto.employee.CreateEmployee;
import com.example.onlineschool.entity.Employee;
import com.example.onlineschool.exception.EmployeeExistException;
import com.example.onlineschool.exception.EmployeeNotFoundException;
import com.example.onlineschool.exception.PictureNotFoundException;
import com.example.onlineschool.mapper.EmployeeMapper;
import com.example.onlineschool.repository.EmployeeRepository;
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
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public Employee create(CreateEmployee dto) throws IOException {
        if (repository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new EmployeeExistException();
        }
        String imgUrl = saveImage(dto.file());
        Employee employee = mapper.asNewEmployee(dto, imgUrl);
        return repository.save(employee);
    }

    private static final String BASE_IMAGE_PATH = "src/main/resources/";

    private String saveImage(MultipartFile imageFile) throws IOException {
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

    public Employee getById(Long id) {
        return repository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAll(Pageable pageable, String department) {
        return repository.findAllByDepartment(department, pageable).getContent();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
