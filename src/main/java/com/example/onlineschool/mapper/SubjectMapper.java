package com.example.onlineschool.mapper;

import com.example.onlineschool.controller.vm.SubjectVm;
import com.example.onlineschool.dto.subject.CreateSubject;
import com.example.onlineschool.dto.subject.UpdateSubject;
import com.example.onlineschool.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface SubjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    Subject asNewSubject(CreateSubject dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    void asUpdateSubject(UpdateSubject dto, @MappingTarget Subject subject);

    SubjectVm asSubjectVm(Subject subject);

    List<SubjectVm> asSubjectVmList(List<Subject> subjects);
}
