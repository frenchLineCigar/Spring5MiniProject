<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="root" value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<script>
	function checkUserIdExist() {
		
		// 사용자가 입력한 user_id 값을 가져온다
		var user_id = $("#user_id").val();
		
		// user_id를 입력하지 않았을때 알림 팝업
		if (user_id.length == 0) {
			alert('아이디를 입력해주세요')
			return
		}
		
/*
		// 공백 금지
		//var blank_pattern = /^\s+|\s+$/g;(/\s/g
		var blank_pattern = /[\s]/g;
		if( blank_pattern.test(user_id) == true){
		    alert('공백은 사용할 수 없습니다');
		    return false;
		}
		// 특수 문자 금지
		var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
		if(special_pattern.test(user_id) == true){
		    alert('특수문자는 사용할 수 없습니다');
		    return false;
		}
		
		//한글 입력 제한
		var ko_pattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		if(ko_pattern.test(user_id) == true){
		    alert('아이디는 영문 대소문자, 숫자만 허용합니다');
		    return false;
		}
 */
		
		// ajax 통신으로 user_id 중복확인 요청
		$.ajax({
			// 요청할 페이지 주소,
			url : '${root}user/checkUserIdExist/' + user_id,
			// 요청 타입,
			type : 'get',
			// 응답 결과 타입,
			dataType : 'text', 
			// 성공 했을 때 호출될 함수
			success : function(result) {
				if (result.trim() == 'true') {
					alert('사용할 수 있는 아이디입니다')
					$("#userIdExist").val('true')
				} else {
					alert('사용할 수 없는 아이디입니다')
					$("userIdExist").val('false')
				}
			}
		})
	}
	
	//user_id 입력 칸에 키보드를 누르면 무조건 false로 리셋한다
	function resetUserIdExist() {
		$("#userIdExist").val('false')
	}
</script>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action="${root }user/join_pro" method="post" modelAttribute="joinUserBean">
						<!-- 사용자가 아이디 중복 유효성 검사를 통과했는지 서버에서 판단하는 값 -->
						<form:hidden path="userIdExist"/>
						<div class="form-group">
							<form:label path="user_name">이름</form:label>
							<form:input path="user_name" class="form-control"/>
							<form:errors path="user_name" style="color:red"/>
						</div>
						<div class="form-group">
							<form:label path="user_id">아이디</form:label>
							<div class="input-group">
								<form:input path="user_id" class="form-control" onkeypress="resetUserIdExist()"/>
								<div class="input-group-append">
									<button type="button" class="btn btn-primary" onclick="checkUserIdExist()">중복확인</button>
								</div>
							</div>
							<form:errors path="user_id" style="color:red"/>
						</div>
						<div class="form-group">
							<form:label path="user_pw">비밀번호</form:label>
							<form:password path="user_pw" class="form-control"/>
							<form:errors path="user_pw" style="color:red"/>
						</div>
						<div class="form-group">
							<form:label path="user_pw2">비밀번호 확인</form:label>
							<form:password path="user_pw2" class="form-control"/>
							<form:errors path="user_pw2" style="color:red"/>
						</div>
						<div class="form-group">
							<div class="text-right">
								<form:button class="btn btn-primary">회원가입</form:button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>