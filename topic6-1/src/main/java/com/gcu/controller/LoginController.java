package com.gcu.controller;

import com.gcu.business.OrdersBusinessInterface;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.gcu.business.SecurityBusinessService;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private SecurityBusinessService security;

    // Inject your OrdersBusinessService
    @Autowired
    private OrdersBusinessInterface service;

    // Show login form
    @GetMapping("/")
    public String display(Model model) {
        model.addAttribute("title", "Login Form");
        model.addAttribute("loginModel", new LoginModel());
        return "login"; // loads login.html
    }

    // Handle form submission
    @PostMapping("/doLogin")
    public String doLogin(@Valid @ModelAttribute("loginModel") LoginModel loginModel,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Login Form");
            return "login";
        }

        // Authenticate user
        security.authenticate(loginModel.getUsername(), loginModel.getPassword());
        
        // Now call test() and getOrders() from this service
        service.test();
        model.addAttribute("title", "My Orders");
        model.addAttribute("orders", service.getOrders());

        return "orders";
    }
}