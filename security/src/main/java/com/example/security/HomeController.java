package com.example.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/")
	public String home() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.info("Authentication: {}", authentication);
		if (authentication != null && authentication.getAuthorities().stream()
			.anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "redirect:/admin";
		}
		return "redirect:/user";
	}

	@GetMapping("/user")
	public String userPage(Model model) {
		model.addAttribute("message", "Welcome User!");
		logger.info("user page");
		return "user";
	}

	@GetMapping("/admin")
	public String adminPage(Model model) {
		model.addAttribute("message", "Welcome Admin!");
		logger.info("admin page");
		return "admin";
	}
}