package com.example.onlineschool.mapper;

import com.example.onlineschool.controller.vm.NewsVm;
import com.example.onlineschool.dto.news.CreateNews;
import com.example.onlineschool.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(imports = {LocalDateTime.class})
public interface NewsMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "content", source = "dto.content")
    @Mapping(target = "imgUrl", source = "imgUrl")
    @Mapping(target = "time", expression = "java(LocalDateTime.now())")
    News asNewNews(CreateNews dto, String imgUrl);

    NewsVm asNewsVm(News news);

    List<NewsVm> asNewsList(List<News> newsList);

}
