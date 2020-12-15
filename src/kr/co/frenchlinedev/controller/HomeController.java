package kr.co.frenchlinedev.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.frenchlinedev.beans.UserBean;
 
@Controller
public class HomeController {

//	@Resource(name = "loginUserBean")
//	private UserBean loginUserBean;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
//		System.out.println(loginUserBean);
		
		// 프로젝트 실제 배포 경로 : 프로젝트 소스코드가 실행할 수 있는 형태로 만들어진뒤 아래 경로로 배포가 되고 실행이 된다
		System.out.println(request.getServletContext().getRealPath("/"));
		
		return "redirect:/main";
	}
}
