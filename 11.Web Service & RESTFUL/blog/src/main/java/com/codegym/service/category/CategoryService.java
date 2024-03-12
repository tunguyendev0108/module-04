package com.codegym.service.category;

import com.codegym.model.Category;
import com.codegym.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    private final ICategoryRepository iCategoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        this.iCategoryRepository = categoryRepository;
    }


    @Override
    public Iterable<Category> findAll() {
        return iCategoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return iCategoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        iCategoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        iCategoryRepository.deleteById(id);
    }
}
