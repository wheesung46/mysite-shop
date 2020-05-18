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
         	<form id="search-form" method="get" action="${pageContext.request.contextPath}/qna">
               <input type="text" id="kwd" name="kwd" value="${ map.keyword }">
               <input type="submit" value="검색">
            </form>
            
            <table class="tbl-ex">
               <tr>
                  <th>번호</th>
                  <th>제목</th>
                  <th>글쓴이</th>
                  <th>작성일</th>
                  <th>&nbsp;</th>
               </tr>
               <c:forEach items="${ map.list }" var="vo" varStatus="status">
                  <tr>
                  	<td>${ vo.no }</td>
                  	<td>
                  		<c:if test="${vo.chk == null || authUser.no == vo.userNo || userNo == 1 }">
                  			<a href="${ pageContext.request.contextPath }/qna/view?no=${vo.no}">${vo.title}</a>
                  		</c:if>
                  		<c:if test="${vo.userNo != authUser.no && vo.chk == 1 }">
                  			비공개 글 입니다.
                  		</c:if>
                  	</td>
                  	<td>${ vo.userName }</td>
                  	<td>${ vo.regDate }</td>
                  	<td>
                  		<c:choose>
                    		<c:when test="${ not empty authUser && authUser.no == vo.userNo || authUser.type == 'A' }">
                    			<a href="${ pageContext.request.contextPath }/qna/delete?no=${vo.no}&kwd=${map.keyword}" class="del">
                                       	삭제
                                </a>
                        	</c:when>
                        	<c:otherwise>
                        		&nbsp;
                        	</c:otherwise>
                    	</c:choose>
                  	</td>
                  </tr>
               </c:forEach>
            </table>
            <div class="bottom">
               <c:if test="${ not empty authUser }">
                  <a href="${ pageContext.request.contextPath }/qna/write" id="new-book"> 
                     	글쓰기
                  </a>
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