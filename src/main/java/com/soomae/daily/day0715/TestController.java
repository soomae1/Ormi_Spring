package com.soomae.daily.day0715;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;


@Controller
public class TestController {

    @GetMapping("/index")
    private String test(Model model) {
        User user = new User("김수민", "soomae@gmail.com", false, "1234");

        model.addAttribute("user", user);
        model.addAttribute("test", "Hello, Thymeleaf!");

        return "index";
    }

    @GetMapping("/products")
    private String list(Model model) {

        List<Product> products = Arrays.asList(
                new Product(1L, "노트북", 1000000),
                new Product(2L, "아이폰", 800000),
                new Product(3L, "맥북", 500000)
        );

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/users")
    public String postForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    @PostMapping("/users")
    public String users(@ModelAttribute User user) {
        System.out.println("사용자 이름: " + user.getUsername());
        System.out.println("이메일: " + user.getEmail());
        System.out.println("비밀번호: " + user.getPassword());
        return "index";
    }

}
