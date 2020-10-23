package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> findAll() { return categoryRepository.findAll(); }
}
