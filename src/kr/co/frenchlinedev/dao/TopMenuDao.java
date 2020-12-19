package kr.co.frenchlinedev.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.frenchlinedev.beans.BoardInfoBean;
import kr.co.frenchlinedev.mapper.TopMenuMapper;

@Repository
public class TopMenuDao {

	@Autowired
	private TopMenuMapper topMenuMapper;
	
	// 게시판 전체 리스트
	public List<BoardInfoBean> getTopMenuList() {
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
		return topMenuList;
	}
}
