package kr.co.frenchlinedev.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringConfigClass implements WebApplicationInitializer {

	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		// Spring MVC 프로젝트 설정을 위해 작성하는 클래스의 객체를 생성한다.
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		
		// 요청 발생 시 요청을 처리하는 서블릿을 DispatcherServlet으로 설정해준다.
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", dispatcherServlet);
		
		// 부가 설정
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		
		// 파일 업로드를 위한 설정
		MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
		servlet.setMultipartConfig(config1);
		
		// Bean을 정의하는 클래스를 지정한다.
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);
		
		ContextLoaderListener listener = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listener);
		
		//파라미터 인코딩 설정
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
	}

}

//public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
//	// DispatcherServlet에 매핑할 요청 주소를 셋팅한다.
//	@Override
//	protected String[] getServletMappings() {
//		// TODO Auto-generated method stub
//		return new String[] {"/"};
//	}
//	
//	// Spring MVC 프로젝트 설정을 위한 클래스를 지정한다.
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		// TODO Auto-generated method stub
//		return new Class[] {ServletAppContext.class};
//	}
//	
//	// 프로젝트에서 사용할 Bean들을 정의하기 위한 클래스를 지정한다.
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		// TODO Auto-generated method stub
//		return new Class[] {RootAppContext.class};
//	}
//	
//	// 파라미터 인코딩 필터 설정
//	@Override
//	protected Filter[] getServletFilters() {
//		// TODO Auto-generated method stub
//		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
//		encodingFilter.setEncoding("UTF-8");
//		
//		return new Filter[] {encodingFilter};
//	}
//	
//	// 파일 업로드를 위한 설정
//	@Override
//	protected void customizeRegistration(Dynamic registration) {
//		// TODO Auto-generated method stub
//		super.customizeRegistration(registration);
//		// 총 4개의 매개변수를 설정한다
//		// 1. location(String) : 클라이언트가 보낸 파일 데이터를 저장해놓는 임시파일의 경로를 지정한다
//		// -> null로 지정하는 경우, Spring MVC는 서블릿 컨테이너가 정한 임시 폴더로 셋팅한다
//		// 2. maxFileSize(long) : 업로드 파일의 최대 용량을 셋팅한다
//		// -> 50MB = 52428800 Byte = 1024 * 1024 * 50 (Byte)
//		// 3. maxRequestSize(long) : 파일 데이터를 포함한 전체 요청 정보의 최대 용량을 셋팅한다
//		// -> 파일 데이터 뿐만 아니라 사용자가 입력한 데이터를 포함한 전체 용량
//		// 4. fileSizeThreshold(int) : 파일의 임계값
//		// -> 0으로 주면 데이터를 받아서 알아서 저장을 하도록 설정
//		MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
//		registration.setMultipartConfig(config1);
//	}
//}
