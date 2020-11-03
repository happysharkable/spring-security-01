package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.OrderDto;
import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

//    @GetMapping(produces = "application/json")
//    public List<OrderDto> showOrders() {
//        return orderService.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
//    }

    @GetMapping(produces = "application/json")
    public List<OrderDto> showOrdersById(@RequestParam(name = "username") String username) {
        User u = userService.findByUsername(username);
        return orderService.findOrdersByUserId(u.getId()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping(value="/create", produces = "application/json")
    public void createOrder(@RequestParam(name = "username") String username) {
        System.out.println("Username is " + username);
        Order o = new Order(userService.findByUsername(username), cart, null);
        orderService.save(o);
    }
//    @GetMapping("/create")
//    public String showOrderPage(Principal principal, Model model) {
//        model.addAttribute("username", principal.getName());
//        return "create_order";
//    }
//
//    @PostMapping("/confirm")
//    @ResponseBody
//    public String confirmOrder(Principal principal,
//                              @RequestParam(name = "address") String address,
//                              @RequestParam(name = "receiver_name") String receiverName,
//                              @RequestParam(name = "phone_number") String phone
//                              ) {
//        User user = userService.findByUsername(principal.getName());
//        Order order = new Order(user, cart, address);
//        order = orderService.save(order);
//        return "Ваш заказ #" + order.getId();
//    }
}
