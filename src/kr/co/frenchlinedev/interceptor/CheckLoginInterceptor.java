package kr.co.frenchlinedev.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.frenchlinedev.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor {
	
	private UserBean loginUserBean;

	public CheckLoginInterceptor(UserBean loginUserBean) {
		// TODO Auto-generated constructor stub
		this.loginUserBean = loginUserBean;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// 로그인 하지 않았을 경우
		if (loginUserBean.isUserLogin() == false) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/not_login");
			return false; //다음 단계로 이동하지 않고 종료
		}
		// 로그인 했을 경우
		return true;
	}
	
}
