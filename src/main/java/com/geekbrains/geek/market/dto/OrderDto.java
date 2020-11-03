package com.geekbrains.geek.market.dto;

import com.geekbrains.geek.market.entities.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private List<OrderItemDto> items;
    private Integer price;

    public OrderDto(Order o) {
        this.id = o.getId();
        this.items = o.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = o.getPrice();
    }
}