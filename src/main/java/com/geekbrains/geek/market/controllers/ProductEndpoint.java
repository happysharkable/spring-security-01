package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.products.Category;
import com.geekbrains.geek.market.products.GetProductsRequest;
import com.geekbrains.geek.market.products.GetProductsResponse;
import com.geekbrains.geek.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/spring/ws/products";

    private ProductRepository productRepository;

    @Autowired
    public ProductEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProducts(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            com.geekbrains.geek.market.products.Product xmlProduct = new com.geekbrains.geek.market.products.Product();

            Category xmlCategory = new Category();
            xmlCategory.setTitle(p.getCategory().getTitle());

            xmlProduct.setTitle(p.getTitle());
            xmlProduct.setPrice(p.getPrice());
            xmlProduct.setCategory(xmlCategory);

            response.getProducts().add(xmlProduct);
        }
        return response;
    }
}
