<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/lightbox.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/product.css" rel="stylesheet" type="text/css">

<script src="https://code.jquery.com/jquery-1.12.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/assets/js/lightbox.js" type="text/javascript"></script>


<c:if test="${ not empty authUser }">
   <script type="text/javascript">
      $(function(){
         // 대화상자 (다이얼로그)
         var dialogUpload = $('#dialog-upload-form').dialog({
            autoOpen : false,
            height : 400,
            width : 300,
            modal : true,
            buttons : {
               '업로드' : function(){
                  $('#dialog-upload-form form').submit();
               },
               '취소' : function(){
                  $(this).dialog('close');
               }
            },
            close : function(){
               $('#dialog-upload-form form').get(0).reset();
            }
         });
         
         $('#upload-image').click(function(event){
            event.preventDefault();
            dialogUpload.dialog('open');
         });
         
      });
   </script>
</c:if>

</head>
<body>
   <div id="container">
      <!-- 페이지 상단(header) -->
      <c:import url="/WEB-INF/views/includes/header.jsp"/>
      <!-- 페이지 내용(content) -->
      <div id="content">
         <div id="gallery">
         <div>
            <h1>상품 목록</h1>
            <c:if test="${ authUser.type == 'A' }">
               <a href="" id="upload-image">상품 올리기</a>
            </c:if>
         </div>
         <ul>
            <c:forEach items="${list}" var="vo">
               <li>
                  <a href="${pageContext.request.contextPath}${baseURL}/${vo.saveFileName}"
                   data-lightbox="gallery" class="image" style="background-image:url('${pageContext.request.contextPath}${baseURL}/${vo.saveFileName}')">
                  &nbsp;
                  </a>
               </li>
                  <table class="tbl-ex" border="1" style="background-color:'blue'">
                  	<c:if test="${authUser.type == 'U'}">
                  		<tr>
                  			<th>번호</th>
                  			<td>${ vo.no }</td>
                  			<td rowspan="4">
                  				<a href="${pageContext.request.contextPath}/order/order?no=${authUser.no}&pname=${vo.pname}&price=${vo.price}&userName=${authUser.name}">
                  					주문
                  				</a>
                  			</td>
                  		</tr>
                  		<tr>
                  			<th>상품명</th>
                  			<td>${ vo.pname }</td>
                  		</tr>
                  		<tr>
                  			<th>상세 설명</th>
                  			<td>${ vo.pcomment }</td>
                  		</tr>
                  		<tr>
                  			<th>가격</th>
                  			<td>${ vo.price }</td>
                  		</tr>
                  	</c:if>
                  	<c:if test="${authUser.type == 'A'}">
                  		<tr>
                  			<th>번호</th>
                  			<td>${ vo.no }</td>
                  			<td rowspan="2">
                  				<a href="${pageContext.request.contextPath}/product/updateform?no=${vo.no}">
                  					수정
                  				</a>
                  			</td>
                  		</tr>
                  		<tr>
                  			<th>상품명</th>
                  			<td>${ vo.pname }</td>
                  		</tr>
                  		<tr>
                  			<th>상세 설명</th>
                  			<td>${ vo.pcomment }</td>
                  			<td rowspan="2">
                  				<a href="${pageContext.request.contextPath}/product/delete/${vo.no}">
                  					삭제
                  				</a>
                  			</td>
                  		</tr>
                  		<tr>
                  			<th>가격</th>
                  			<td>${ vo.price }</td>
                  		</tr>
                  	</c:if>
                  </table>
            </c:forEach>
         </ul>
         </div>
         <c:if test="${ not empty authUser }">
            <div id="dialog-upload-form" title="상품 등록" style="display:none;">
               <p class="validateTips normal">상품을 등록 하세요.</p>
               <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/product/upload">
               	  <label>물품 명</label>
               	  <input type="text" id="input-pname" name="pname" value="">
               	  <label>가격</label>
               	  <input type="text" id="input-price" name="price" value="">
                  <label>세부 설명</label>
                  <input type="text" id="input-pcomment" name="pcomment" value="">
                  <label>이미지</label>
                  <input type="file" id="input-file" name="file">
                  <input type="submit" tabindex="-1" style="position: absolute; top: -1000px;">
                </form>
            </div>
         </c:if>
      </div>
      
      <!-- 페이지 메뉴 (navigation) -->
      <c:import url="/WEB-INF/views/includes/navigation.jsp">
         <c:param name="menu" value="product"/>
      </c:import>
      <!-- 페이지 하단(footer) -->
      <c:import url="/WEB-INF/views/includes/footer.jsp"/>
   </div>
</body>
</html>