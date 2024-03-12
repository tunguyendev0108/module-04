package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService{
    @Autowired
    IBlogRepository iBlogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return iBlogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return iBlogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        iBlogRepository.deleteById(id);
    }


    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return iBlogRepository.findAll((java.awt.print.Pageable) pageable);
    }

    @Override
    public Iterable<Blog> findAllSorted(Sort sort) {
        return iBlogRepository.findAll(sort);
    }

    @Override
    public Iterable<Blog> findByNameContaining(String name) {
        return iBlogRepository.findByNameContaining(name);
    }

    @Override
    public Page<Blog> findByCategory(Category category, Pageable pageable) {
        return iBlogRepository.findByCategory(category, (java.awt.print.Pageable) pageable);
    }

    @Override
    public Page<Blog> findByCategory(Optional<Category> category, Pageable pageable) {
        return iBlogRepository.findByCategory(category, (java.awt.print.Pageable) pageable);
    }
}
