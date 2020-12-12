package kr.co.frenchlinedev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final String VIEW_PATH = "user/";
	
	@GetMapping("/login")
	public String login() {
		return VIEW_PATH + "login";
	}
	
	@GetMapping("/join")
	public String join() {
		return VIEW_PATH + "join";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return VIEW_PATH + "modify";
	}

	@GetMapping("/logout")
	public String logout() {
		return VIEW_PATH + "logout";
	}
	

}
