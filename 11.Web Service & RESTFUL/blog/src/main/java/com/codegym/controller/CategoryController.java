package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.GeneratedValue;
import java.awt.print.Pageable;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final ICategoryService iCategoryService;

    private final IBlogService iBlogService;
    @Autowired
    public CategoryController(ICategoryService iCategoryService,IBlogService iBlogService){
        this.iCategoryService = iCategoryService;
        this.iBlogService = iBlogService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Category>> getAllCategories() {
        Iterable<Category> categories = iCategoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<Blog>> getBlogsByCategory(@PathVariable Long id, Pageable pageable) {
        Optional<Category> category = iCategoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Iterable<Blog> blogs = iBlogService.findByCategory(category.get(), (org.springframework.data.domain.Pageable) pageable);
            return new ResponseEntity<>(blogs,HttpStatus.OK);
        }
    }
}
