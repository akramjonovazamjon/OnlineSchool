package com.example.onlineschool.mapper;

import com.example.onlineschool.controller.vm.CircleVm;
import com.example.onlineschool.dto.circle.CreateCircle;
import com.example.onlineschool.entity.Circle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CircleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "content", source = "dto.content")
    @Mapping(target = "imgUrl", source = "imgUrl")
    Circle asNewCircle(CreateCircle dto, String imgUrl);

    CircleVm asCircleVm(Circle circle);

    List<CircleVm> asCircleList(List<Circle> circleList);

}
