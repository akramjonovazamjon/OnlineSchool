package com.example.onlineschool.controller;

import com.example.onlineschool.controller.vm.NewsVm;
import com.example.onlineschool.dto.ResponseData;
import com.example.onlineschool.dto.news.CreateNews;
import com.example.onlineschool.entity.News;
import com.example.onlineschool.mapper.NewsMapper;
import com.example.onlineschool.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private final NewsService service;
    private final NewsMapper mapper;

    @PostMapping
    public ResponseData<NewsVm> create(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "option") String option
    ) throws IOException {
        News news = service.create(new CreateNews(title, content, file, option));
        return ResponseData.of(mapper.asNewsVm(news));
    }

    @GetMapping
    public ResponseData<List<NewsVm>> getAll(Pageable pageable) {
        List<News> newsList = service.getAll(pageable);
        return ResponseData.of(mapper.asNewsList(newsList));
    }

    @GetMapping("/{id}")
    public ResponseData<NewsVm> getById(@PathVariable Long id) {
        News news = service.getById(id);
        return ResponseData.of(mapper.asNewsVm(news));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
