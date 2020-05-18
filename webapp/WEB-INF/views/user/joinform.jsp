<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script>
	$(function(){
		$('#btn-chkemail').click(function(){
			var email = $('#email').val();
			if(email == '') return; // 비어있다면 종료
			
			// jQuery ajax를 활용한 이메일 중복 체크
			$.ajax({
				url : '${pageContext.request.contextPath}/user/api/checkemail?email=' + email,
				type : 'get',
				dataType : 'json',
				data : '',
				success : function(response){
					if(response.result == 'fail'){
						console.log(response.message);
						return;
					}
				
					// success
					if(response.data == 'exist'){
						alert('이미 존재합니다. 다른 이메일을 사용해 주세요.');
						$('#email').val('').focus();
						return;
					}
					
					// 중복되지 않는다면,
					$('#img-chkemail').show();
					$('#btn-chkemail').hide();
				},
				error : function(jqXHR, status, e){
					console.log(jqXHR.status, status);
					// jqXHR (jQuery XML Http Request)
				}
			});
		});
		
		$('#join-form').submit(function(){
			if($('#img-chkemail').is(':visible') == false){
				alert('이메일 중복 체크를 하셔야 합니다.');
				return false;
			}
			
			if($("#agree-prov").is(':checked') == false){
				alert('약관에 동의하셔야 합니다.');
				return false;
			}
		
		});
	});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<!-- form:form : 데이터 바인딩 및 에러 처리 관련 스프링 프레임워크 태그 제공 -->
				<form:form modelAttribute="userVo" id="join-form"
					name="joinform" method="post"
					action="${pageContext.request.contextPath}/user/join">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" />
					<p style="text-align:left; color:red">
						<form:errors path="name"/>
					</p>
					
					<label class="block-label" for="email">이메일</label>
					<form:input path="email" />
					<!-- 사용가능한 이메일이라면 이미지 보여주기 -->
					<img id="img-chkemail" align="top" style="width:16px; display:none"
						src="${pageContext.request.contextPath}/assets/images/check.png">
					<input id="btn-chkemail" type="button" value="중복체크">
					<p style="text-align:left; color:red">
						<form:errors path="email"/>
					</p>
					
					<label class="block-label">패스워드</label>
					<form:password path="password"/>
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('password')}">
							<p style="test-align:left; color:red">
								<spring:message
									code="${errors.getFieldError('password').codes[0]}"
									text="${errors.getFieldError('password').defaultMessage}"
								/>
							</p>
						</c:if>
					</spring:hasBindErrors>
					
					<fieldset>
						<legend>성별</legend>
						<lable>남</lable><input type="radio" name="gender" value="male" checked="checked">
						<lable>여</lable><input type="radio" name="gender" value="female">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
				</form:form>
			</div>
		</div>
		<!-- 
			navigation의 메뉴가 아닌 header의 메뉴에서 접근하는 페이지
			navigation의 선택된 메뉴를 식별하ㅣ 위한 파라미터를 전달하지 않겠다.
		-->
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>