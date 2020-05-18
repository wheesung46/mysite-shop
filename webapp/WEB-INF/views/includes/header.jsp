<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="header">
	<h1>My Shop</h1>
	<ul>
		<c:choose>
			<c:when test="${ empty authUser }">
				<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a>
				<li><a href="${pageContext.request.contextPath}/user/joinform">회원가입</a>
			</c:when>
			<c:when test="${ authUser.type == 'A' }">
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
				<li>${authUser.name}님 어서오세요.</li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/modifyform">정보수정</a>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
				<li>${authUser.name}님 어서오세요.</li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>