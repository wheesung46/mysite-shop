<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="join-form" name="joinform" method="post"
					action="${pageContext.request.contextPath}/product/update">
					<input id="no" name="no" type="hidden" value="${productVo.no}">
					<img src="${pageContext.request.contextPath}/upload/${productVo.saveFileName}" width="100">
					<label class="block-label" for="pname">상품명</label>
					<input id="pname" name="pname" type="text" value="${productVo.pname}">
					
					<label class="block-label" for="pcomment">상세설명</label>
					<input id="pcomment" name="pcomment" type="text" value="${productVo.pcomment}">
					
					<label class="block-label" for="price">가격</label>
					<input id="price" name="price" type="text" value="${productVo.price}">
					
					<input type="submit" value="수정하기">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>