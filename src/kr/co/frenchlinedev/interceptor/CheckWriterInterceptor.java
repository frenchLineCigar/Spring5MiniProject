package kr.co.frenchlinedev.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.frenchlinedev.beans.ContentBean;
import kr.co.frenchlinedev.beans.UserBean;
import kr.co.frenchlinedev.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor{

	private UserBean loginUserBean;
	private BoardService boardService;
	
	public CheckWriterInterceptor(UserBean loginUserBean, BoardService boardService) {
		this.loginUserBean = loginUserBean;
		this.boardService = boardService;
	}
	
	// 게시글 수정/삭제에 대한 URL 직접 호출에 대한 권한 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		//현재 게시글 인덱스 추출
		String str1 = request.getParameter("content_idx");
		int content_idx = Integer.parseInt(str1);
		
		//현재 게시글 작성자 인덱스
		ContentBean currentContentBean = boardService.getContentInfo(content_idx);
		int content_writer_idx = currentContentBean.getContent_writer_idx();
		//현재 로그인 유저 인덱스
		int user_idx = loginUserBean.getUser_idx();
		
		//로그인 유저와 글 작성자가 같지 않을 경우
		if (content_writer_idx != user_idx) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			return false;
		}
		
		return true;
	}
	
}
