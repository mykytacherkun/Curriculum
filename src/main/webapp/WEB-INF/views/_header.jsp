<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <script src="https://kit.fontawesome.com/982c488da8.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/styles/styleswitcher.js"></script>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="height: 55px; padding: 5px;">
  <div style="float: left">
     <h1><a href="${pageContext.request.contextPath}/">StudyPlans</a></h1>
  </div>
 
  <div style="float: right; padding: 10px; text-align: right;">
  <c:choose> 
  	<c:when test="${loginedUser.user_type=='0' || loginedUser.user_type=='1' || loginedUser.user_type =='2'}" >
	 	<b>Welcome, <a href="${pageContext.request.contextPath}/userInfo">${loginedUser.user_name}</a></b>
	 	<p><b><a href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i> log out</a></b></p>
 	</c:when>
 	<c:otherwise>
 		<b><a href="${pageContext.request.contextPath}/login"><i class="fas fa-sign-in-alt"></i> Log in</a></b></c:otherwise>
  </c:choose>
     
  	<p><a href="#" onclick="setActiveStyleSheet('default'); return false;"><i class="fas fa-moon">dark</i></a></p>
	<p><a href="#" onclick="setActiveStyleSheet('alternate'); return false;"><i class="far fa-moon">light</i></a></p>
 
  </div>
 
</div>