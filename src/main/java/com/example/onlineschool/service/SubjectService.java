package com.example.onlineschool.service;

import com.example.onlineschool.dto.subject.CreateSubject;
import com.example.onlineschool.dto.subject.UpdateSubject;
import com.example.onlineschool.entity.Subject;
import com.example.onlineschool.exception.SubjectExistException;
import com.example.onlineschool.exception.SubjectNotFoundException;
import com.example.onlineschool.mapper.SubjectMapper;
import com.example.onlineschool.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository repository;
    private final SubjectMapper mapper;

    public Subject create(CreateSubject dto) {
        if (repository.existsByName(dto.name())) {
            throw new SubjectExistException();
        }
        Subject subject = mapper.asNewSubject(dto);
        return repository.save(subject);
    }

    public Subject getById(Long id) {
        return repository.findById(id).orElseThrow(SubjectNotFoundException::new);
    }

    public List<Subject> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(Long id, UpdateSubject dto) {
        Subject subject = getById(id);

        if (repository.existsByNameAndIdNot(dto.name(), id)) {
            throw new SubjectExistException();
        }
        mapper.asUpdateSubject(dto, subject);
        repository.save(subject);
    }
}
