package com.example.onlineschool.mapper;

import com.example.onlineschool.controller.vm.FacultyVm;
import com.example.onlineschool.dto.faculty.CreateFaculty;
import com.example.onlineschool.dto.faculty.UpdateFaculty;
import com.example.onlineschool.entity.Faculty;
import com.example.onlineschool.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = {SubjectMapper.class})
public interface FacultyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "subjects", source = "subjects")
    Faculty asNewFaculty(CreateFaculty dto, List<Subject> subjects);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "subjects", source = "subjects")
    void asUpdateFaculty(UpdateFaculty dto, List<Subject> subjects, @MappingTarget Faculty faculty);

    FacultyVm asFacultyVm(Faculty faculty);

    List<FacultyVm> asFacultyVmList(List<Faculty> faculties);
}
