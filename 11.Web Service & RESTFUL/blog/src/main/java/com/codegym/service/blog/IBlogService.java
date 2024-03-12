package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IGenerateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface IBlogService extends IGenerateService<Blog> {
    Page<Blog> findAll(Pageable pageable);
    Iterable<Blog> findAllSorted(Sort sort);
    Iterable<Blog> findByNameContaining(String name);
    Page<Blog> findByCategory(Category category, Pageable pageable);
    Page<Blog> findByCategory(Optional<Category> category, Pageable pageable);
}
