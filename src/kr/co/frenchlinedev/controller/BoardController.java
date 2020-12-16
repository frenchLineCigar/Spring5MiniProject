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
	
	//글 목록
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
	
	//글 읽기
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
	
	//글 쓰기
	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
						@RequestParam("board_info_idx") int board_info_idx) {
		
		//게시판 구분 번호 셋팅
		writeContentBean.setContent_board_idx(board_info_idx);
		
		return VIEW_PATH + "write";
	}
	
	//글 쓰기 등록
	@PostMapping("/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean, BindingResult result) {
		
		if (result.hasErrors()) {
			return "board/write";
		}
		
		boardService.addContentInfo(writeContentBean);
		
		return "board/write_success";
	}
	
	//글 수정
	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx,
						 @RequestParam("content_idx") int content_idx,
						 @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
						 Model model) {
		
		//해당 게시글 조회
		ContentBean tempContentBean = boardService.getContentInfo(content_idx);
		
		modifyContentBean.setContent_writer_name(tempContentBean.getContent_writer_name());
		modifyContentBean.setContent_date(tempContentBean.getContent_date());
		modifyContentBean.setContent_subject(tempContentBean.getContent_subject());
		modifyContentBean.setContent_text(tempContentBean.getContent_text());
		modifyContentBean.setContent_file(tempContentBean.getContent_file());
		modifyContentBean.setContent_board_idx(board_info_idx);
		modifyContentBean.setContent_idx(content_idx);
		
//		model.addAttribute("board_info_idx", board_info_idx);
//		model.addAttribute("content_idx", content_idx);
		
		return VIEW_PATH + "modify";
	}
	
	//글 수정 등록
	@PostMapping("/modify_pro")
	public String modify_pro(Model model, @Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean, 
							 BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println(model.toString());
			return "board/modify";
		}
		
		boardService.modifyContentInfo(modifyContentBean);
		
		return "board/modify_success";
		
	}
	
	//글 삭제
	@GetMapping("/delete")
	public String delete() {
		return VIEW_PATH + "delete";
	}
	
	//수정/삭제 요청 시 인터셉터에서 로그인 유저와 글 작성자가 같지 않을 경우에 여기로 리다이렉트
	@GetMapping("/not_writer")
	public String not_writer() {
		return VIEW_PATH + "not_writer";
	}
}
