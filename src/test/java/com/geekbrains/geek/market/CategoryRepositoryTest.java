package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void addNewCategoryTest() {
        Category c = new Category();
        c.setTitle("Boardgames");
        entityManager.persist(c);
        entityManager.flush();

        List<Category> categories = categoryRepository.findAll();
        Assertions.assertEquals("Boardgames", categories.get(categories.size() - 1).getTitle());
    }

    @Test
    public void getAllCategoriesTest() {
        List<Category> categories = categoryRepository.findAll();
        Assertions.assertEquals(3, categories.size());
    }
}
