package kr.co.frenchlinedev.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.frenchlinedev.beans.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean)target;
		
		String beanName = errors.getObjectName();

		// 회원 가입 시 or 정보 수정 시
		if (beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
			// 비밀번호와 비밀번호 확인 일치여부
			if (!userBean.getUser_pw().equals(userBean.getUser_pw2())) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
		}
		
		// 회원 가입 시
		if (beanName.equals("joinUserBean")) {
			// 아이디 중복 확인 
			if (!userBean.isUserIdExist()) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
		}
		
	}

	
}
