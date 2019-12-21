package com.treko.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.treko.exception.NotFoundException;
import com.treko.security.services.UserDetailsServiceImpl;

@ControllerAdvice
public class BaseControllerAdvice {

	private final UserDetailsServiceImpl userDetailsServiceImpl;

	public BaseControllerAdvice(UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	@ExceptionHandler(NotFoundException.class)
	public String handledNotFoundException(NotFoundException e, Model model) {
		model.addAttribute("status", 400);
		model.addAttribute("exception", e);

		return "common/error";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("status", 500);
		model.addAttribute("exception", e);

		return "common/error";
	}

	@ModelAttribute
	public void addCommonAttributes(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		if (userDetails != null) {
			UserDetails user = userDetailsServiceImpl.loadUserByUsername(userDetails.getUsername());
			model.addAttribute("user", user);
		}
	}
}