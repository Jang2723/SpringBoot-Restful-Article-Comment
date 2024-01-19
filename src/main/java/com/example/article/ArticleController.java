package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
CRUD를 위한 URL
POST /articles -> create()
GET /articles -> readAll()
GET /articles/{id} -> read()
PUT /articles/{id} -> update()
DELETE /articles/{id} -> delete()
*/
@Slf4j
@RestController // view가 없음
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;

    @PostMapping
    public ArticleDto create(
            @RequestBody
            ArticleDto dto
    ) {
        return service.create(dto);
    }

   /* @GetMapping
    public List<ArticleDto> readAll() {
        return service.readAll();
    }*/

    /*@GetMapping
    public List<ArticleDto> readAllPaged(
            @RequestParam(value = "page", defaultValue = "1")
            Integer page,
            @RequestParam(value = "limit", defaultValue = "20")
            Integer limit
    ){
        return service.readAllPaged(page, limit);
    }*/

    @GetMapping
    public Page<ArticleDto> readAllPagination(
            @RequestParam(value = "page", defaultValue = "1")
            Integer page,
            @RequestParam(value = "limit", defaultValue = "20")
            Integer limit
    ) {
        return service.readAllPagination(page, limit);
    }

    @GetMapping("/{id}")
    public ArticleDto read(
        @PathVariable("id")
        Long id
    ) {
        return service.readOne(id);
    }

    @PutMapping("/{id}")
    public ArticleDto update(
            @PathVariable("id")
            Long id,
            @RequestBody
            ArticleDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id")
            Long id
    ) {
        service.delete(id);
    }
}
