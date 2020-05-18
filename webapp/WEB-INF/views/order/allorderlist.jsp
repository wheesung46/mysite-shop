<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
         <div id="board">
            <table class="tbl-ex">
               <tr>
                  <th>주문번호</th>
                  <th>상품명</th>
                  <th>가격</th>
                  <th>구매일</th>
                  <th>구매자</th>
               </tr>
               <c:forEach items="${ map.list }" var="vo" varStatus="status">
                  <tr>
                  	<td>${ vo.no }</td>
                  	<td>${ vo.pname }</td>
                  	<td>${ vo.price }</td>
                  	<td>${ vo.regDate }</td>
                  	<td>${ vo.userName }</td>
                  </tr>
               </c:forEach>
            </table>
         </div>
      </div>
      
      <c:import url="/WEB-INF/views/includes/navigation.jsp">
         <c:param name="menu" value="order"/>
      </c:import>
      <c:import url="/WEB-INF/views/includes/footer.jsp"/>
   </div>
</body>
</html>