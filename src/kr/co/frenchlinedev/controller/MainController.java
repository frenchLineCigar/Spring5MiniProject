package kr.co.frenchlinedev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.frenchlinedev.beans.BoardInfoBean;
import kr.co.frenchlinedev.beans.ContentBean;
import kr.co.frenchlinedev.service.MainService;
import kr.co.frenchlinedev.service.TopMenuService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private TopMenuService topMenuService;

	@GetMapping("/main")
	public String main(Model model) {
		
		// 각 게시판 별로 상위 5개 글 가져오기
		// 1(자유), 2(유머), 3(정치), 4(스포츠)
		ArrayList<List<ContentBean>> list = new ArrayList<List<ContentBean>>();
		
		for (int i = 1; i <= 4; i++) {
			List<ContentBean> subList = mainService.getMainList(i);			
			list.add(subList);
		}
		
		model.addAttribute("list", list);
		
		//각 게시판 정보 가져오기
		List<BoardInfoBean> board_list = topMenuService.getTopMenuList();
		model.addAttribute("board_list", board_list);
		
		return "main";
	}
}
