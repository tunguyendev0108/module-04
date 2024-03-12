package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.repository.IBlogRepository;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<Blog>> getAllBlogs(){
        Iterable<Blog> blogs = blogService.findAll();
        return new ResponseEntity<>(blogs,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogDetails(@PathVariable Long id) {
        Optional<Blog> blog = blogService.findById(id);
        return blog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
