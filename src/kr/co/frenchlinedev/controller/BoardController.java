 package kr.co.frenchlinedev.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.frenchlinedev.beans.ContentBean;
import kr.co.frenchlinedev.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final String VIEW_PATH = "board/";
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
						Model model) {
	
		model.addAttribute("board_info_idx", board_info_idx);
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName);
		
		List<ContentBean> contentList = boardService.getContentList(board_info_idx);
		model.addAttribute("contentList", contentList);
		
		return VIEW_PATH + "main";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx") int board_info_idx, 
					   @RequestParam("content_idx") int content_idx,
					   Model model) {
		
		// 목록보기 url의 파라미터에 전달할 board_info_idx를 담는다
		model.addAttribute("board_info_idx", board_info_idx);
		
		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		model.addAttribute("readContentBean", readContentBean);
		
		return VIEW_PATH + "read";
	}
	
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
						@RequestParam("board_info_idx") int board_info_idx) {
		
		//게시판 구분 번호 셋팅
		writeContentBean.setContent_board_idx(board_info_idx);
		
		return VIEW_PATH + "write";
	}
	
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean, BindingResult result) {
		
		if (result.hasErrors()) {
			return "board/write";
		}
		
		boardService.addContentInfo(writeContentBean);
		
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
