package com.example.onlineschool.service;

import com.example.onlineschool.dto.news.CreateNews;
import com.example.onlineschool.entity.News;
import com.example.onlineschool.exception.NewsExistException;
import com.example.onlineschool.exception.NewsNotFoundException;
import com.example.onlineschool.exception.PictureNotFoundException;
import com.example.onlineschool.mapper.NewsMapper;
import com.example.onlineschool.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository repository;
    private final NewsMapper mapper;

    public News create(CreateNews dto) throws IOException {
        if (repository.existsByTitle(dto.title())) {
            throw new NewsExistException();
        }
        String imgUrl = saveImage(dto.image());
        News news = mapper.asNewNews(dto, imgUrl);
        return repository.save(news);
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

    public List<News> getAll(Pageable pageable) {
//        return repository.findAll(pageable).getContent();
        List<News> news = new ArrayList<>(List.copyOf(repository.findAll(pageable).getContent()));
        news.sort(Comparator.comparing(News::getTime).reversed());
        return news;
    }

    public News getById(Long id) {
        return repository.findById(id).orElseThrow(NewsNotFoundException::new);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
