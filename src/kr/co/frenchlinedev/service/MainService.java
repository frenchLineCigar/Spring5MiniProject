package kr.co.frenchlinedev.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.frenchlinedev.beans.ContentBean;
import kr.co.frenchlinedev.dao.BoardDao;

@Service
public class MainService {
	
	@Autowired
	private BoardDao boardDao;
	
	private static final int OFFSET = 0;
	private static final int LIMIT = 5;
	
	public List<ContentBean> getMainList(int board_info_idx) {
		
		RowBounds rowBounds = new RowBounds(OFFSET, LIMIT);
		return boardDao.getContentList(board_info_idx, rowBounds);
	}

}
