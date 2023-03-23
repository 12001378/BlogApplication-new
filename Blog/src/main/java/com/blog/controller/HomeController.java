package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.entity.User;
import com.blog.repository.UserRepo;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/home")
	public String home() {
		return "home";

	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/signin")
	public String login(Model model) {
		
		model.addAttribute("user");
		return "login";
	}

	@PostMapping("/do_register")
	public String userRegister(@ModelAttribute("user") User user) {

		user.setRole("ROLE_USER");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User u = this.userRepo.save(user);
		System.out.println(u);
		return "login";
	}

}
