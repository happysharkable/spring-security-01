package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest(classes = Cart.class)
public class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void init() {
        Product p1 = new Product();
        p1.setId(1L);
        Product p2 = new Product();
        p2.setId(2L);

        Mockito.doReturn(Optional.of(p1)).when(productService).findById(1L);
        Mockito.doReturn(Optional.of(p2)).when(productService).findById(2L);
        Mockito.doThrow(new ResourceNotFoundException("Test exception")).when(productService).findById(3L);
    }

    @Test
    public void addAndRemoveAvailableProductTest() {
        Assertions.assertAll(
                () -> {
                    cart.addOrIncrement(1L);
                    cart.addOrIncrement(2L);
                    Assertions.assertEquals(2, cart.getItems().size());
                },
                () -> {
                    cart.decrementOrRemove(2L);
                    Assertions.assertEquals(1, cart.getItems().size());
                },
                () -> {
                    cart.clear();
                    Assertions.assertEquals(0, cart.getItems().size());
                });
    }

    @Test
    public void addProductWithWrongIdTest() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> cart.addOrIncrement(3L));
    }
}
