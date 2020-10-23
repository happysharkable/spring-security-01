package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.CategoryService;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @GetMapping(produces = "application/json") // /api/v1/products
    public HashMap<String, Object> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                                   @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 10);
        List<Category> categories = categoryService.findAll();

        HashMap<String, Object> content = new HashMap<>();
        content.put("products", products);
        content.put("categories", categories);
        return content;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productService.saveOrUpdate(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
