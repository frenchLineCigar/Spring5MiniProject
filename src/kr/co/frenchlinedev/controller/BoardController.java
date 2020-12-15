 package kr.co.frenchlinedev.controller;

import java.util.List;

import javax.annotation.Resource;
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
import kr.co.frenchlinedev.beans.UserBean;
import kr.co.frenchlinedev.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final String VIEW_PATH = "board/";
	
	@Autowired
	private BoardService boardService;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
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
		
		/**
		 * board_info_idx
		 * 1. 목록보기 클릭 시 해당 글이 어느 게시판인지 목록 구분
		 * 2. 수정 후 해당 글로 돌아오기 위해 사용
		 * 
		 * content_idx
		 * 1. 수정/삭제 버튼 클릭 시 해당 글이 무엇인지
		 */
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);
		
		// 수정/삭제 버튼은 로그인한 사람과 작성한 사람이 같은 경우에만 노출
		model.addAttribute("loginUserBean", loginUserBean);
		
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
	
	@GetMapping("/not_writer")
	public String not_writer() {
		return VIEW_PATH + "not_writer";
	}
}
