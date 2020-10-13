package com.geekbrains.geek.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class GeekMarketApplication {
	// Домашнее задание:
	// 1. Добавьте отображение заказов только текущего пользователя (на странице orders)
	// * 2. Попробуйте реализовать регистрацию пользователей
	// Работаем пока с thymeleaf в ДЗ

	public static void main(String[] args) {
		SpringApplication.run(GeekMarketApplication.class, args);
	}
}
