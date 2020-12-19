package kr.co.frenchlinedev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.frenchlinedev.beans.BoardInfoBean;
import kr.co.frenchlinedev.dao.TopMenuDao;

@Service
public class TopMenuService {
	
	@Autowired
	private TopMenuDao topMenuDao;
	
	// 게시판 전체 리스트
	public List<BoardInfoBean> getTopMenuList(){
		List<BoardInfoBean> topMenuList = topMenuDao.getTopMenuList();
		return topMenuList;
	}
}
