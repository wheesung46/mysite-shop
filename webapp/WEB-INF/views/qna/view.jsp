<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% pageContext.setAttribute("newLine", "\n"); %>
<!-- 페이지 내에서만 newLine("\n") 처럼 활용하겠다! (영역 등록) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="write-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글 정보</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${qnaVo.title}</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							${ fn:replace(qnaVo.content, newLine, "<br>") }
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${pageContext.request.contextPath}/qna?
						kwd=${keyword}">글 목록</a>
					<c:if test="${ not empty authUser }">
						<c:if test="${ authUser.no == qnaVo.userNo }">
							<a href="${pageContext.request.contextPath}/qna/modify?
								no=${qnaVo.no}&kwd=${keyword}">글 수정</a>
						</c:if>
					</c:if>
				</div>
			</div>
		</div>
	<c:import url="/WEB-INF/views/includes/navigation.jsp">
		<c:param name="menu" value="qna"/>
	</c:import>
	<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>