<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="navigation">
	<ul>
		<c:choose>
			<c:when test="${param.menu == 'main'}">
				<c:if test="${authUser.type == null }">
					<li class="selected"><a href="${pageContext.request.contextPath}">Home</a></li>
				</c:if>
				<c:if test="${authUser.type == 'A'}">
					<li class="selected"><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/product">상품 목록/등록</a></li>
					<li><a href="${pageContext.request.contextPath}/order">주문 현황</a></li>
					<li><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
				<c:if test="${authUser.type == 'U'}">
					<li class="selected"><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/product">상품 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/order/list?no=${authUser.no}">주문 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
			</c:when>
			<c:when test="${param.menu == 'product'}">
				<c:if test="${authUser.type == 'A'}">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/product">상품 목록/등록</a></li>
					<li><a href="${pageContext.request.contextPath}/order">주문 현황</a></li>
					<li><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
				<c:if test="${authUser.type == 'U'}">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/product">상품 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/order/list?no=${authUser.no}">주문 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
			</c:when>
			<c:when test="${param.menu == 'order'}">
				<c:if test="${authUser.type == 'A'}">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/product">상품 목록/등록</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/order">주문 현황</a></li>
					<li><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
				<c:if test="${authUser.type == 'U'}">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/product">상품 목록</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/order/list?no=${authUser.no}">주문 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
			</c:when>
			<c:when test="${param.menu == 'qna'}">
				<c:if test="${authUser.type == 'A'}">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/product">상품 목록/등록</a></li>
					<li><a href="${pageContext.request.contextPath}/order">주문 현황</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
				<c:if test="${authUser.type == 'U'}">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/product">상품 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/order/list?no=${authUser.no}">주문 목록</a></li>
					<li class="selected"><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${authUser.type == null }">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
				</c:if>
				<c:if test="${authUser.type == 'A'}">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/product">상품 목록/등록</a></li>
					<li><a href="${pageContext.request.contextPath}/order">주문 현황</a></li>
					<li><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
				<c:if test="${authUser.type == 'U'}">
					<li><a href="${pageContext.request.contextPath}">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/product">상품 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/order/list?no=${authUser.no}">주문 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/qna">문의</a></li>
				</c:if>
			</c:otherwise>
		</c:choose>
	</ul>
</div>