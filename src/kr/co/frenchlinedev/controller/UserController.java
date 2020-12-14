package kr.co.frenchlinedev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.frenchlinedev.beans.UserBean;
import kr.co.frenchlinedev.service.UserService;
import kr.co.frenchlinedev.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final String VIEW_PATH = "user/";
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return VIEW_PATH + "login";
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		
		return VIEW_PATH + "join";
	}
	
	@PostMapping("/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserBean joinUserBean, BindingResult result) {
		
		if (result.hasErrors()) {
			return "user/join";
		}
		
		userService.addUserInfo(joinUserBean);
		
		return "user/join_success";
	}
	
	
	@GetMapping("/modify")
	public String modify() {
		return VIEW_PATH + "modify";
	}

	@GetMapping("/logout")
	public String logout() {
		return VIEW_PATH + "logout";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
	

}
