package com.example.onlineschool.service;

import com.example.onlineschool.dto.faculty.CreateFaculty;
import com.example.onlineschool.dto.faculty.UpdateFaculty;
import com.example.onlineschool.entity.Faculty;
import com.example.onlineschool.entity.Subject;
import com.example.onlineschool.exception.FacultyExistException;
import com.example.onlineschool.exception.FacultyNotFoundException;
import com.example.onlineschool.mapper.FacultyMapper;
import com.example.onlineschool.repository.FacultyRepository;
import com.example.onlineschool.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository repository;
    private final FacultyMapper mapper;
    private final SubjectRepository subjectRepository;

    public Faculty create(CreateFaculty dto) {
        if (repository.existsByName(dto.name())) {
            throw new FacultyExistException();
        }
        List<Subject> subjects = subjectRepository.findAllByIdIn(dto.subjectIds());

        Faculty faculty = mapper.asNewFaculty(dto, subjects);
        return repository.save(faculty);
    }

    public Faculty getById(Long id) {
        return repository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    public List<Faculty> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(Long id, UpdateFaculty dto) {
        Faculty faculty = getById(id);

        if (repository.existsByNameAndIdNot(dto.name(), id)) {
            throw new FacultyExistException();
        }

        List<Subject> subjects = subjectRepository.findAllByIdIn(dto.subjectIds());

        mapper.asUpdateFaculty(dto, subjects, faculty);

        repository.save(faculty);
    }
}
