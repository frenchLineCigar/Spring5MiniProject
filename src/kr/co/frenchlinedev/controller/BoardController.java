package kr.co.frenchlinedev.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.frenchlinedev.beans.ContentBean;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final String VIEW_PATH = "board/";
	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
						Model model) {
	
		model.addAttribute("board_info_idx", board_info_idx);
		
		return VIEW_PATH + "main";
	} 
	
	@GetMapping("/read")
	public String read() {
		return VIEW_PATH + "read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean) {
		return VIEW_PATH + "write";
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean, BindingResult result) {
		
		if (result.hasErrors()) {
			return "board/write";
		}
		
		return "board/write_success";
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
