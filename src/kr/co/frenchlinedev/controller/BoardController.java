package kr.co.frenchlinedev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final String BOARD_VIEW_PATH = "board/";
	
	@GetMapping("/main")
	public String main() {
		return BOARD_VIEW_PATH + "main";
	}
}
