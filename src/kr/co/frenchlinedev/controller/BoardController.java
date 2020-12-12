package kr.co.frenchlinedev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final String VIEW_PATH = "board/";
	
	@GetMapping("/main")
	public String main() {
		return VIEW_PATH + "main";
	} 
	
	@GetMapping("/read")
	public String read() {
		return VIEW_PATH + "read";
	}
	
	@GetMapping("/write")
	public String write() {
		return VIEW_PATH + "write";
	}
	
	@GetMapping("/modify")
	public String modify() {
		return VIEW_PATH + "modify";
	}
	
	@GetMapping("/delete")
	public String delete() {
		return VIEW_PATH + "delete";
	}
}
