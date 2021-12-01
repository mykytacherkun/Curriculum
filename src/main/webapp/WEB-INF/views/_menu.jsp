<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
	  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!-- <table class="container">
	<tbody>
	<tr>
		<td onClick="document.location.href='${pageContext.request.contextPath}/'">Home</td>
		<td onClick="document.location.href='${pageContext.request.contextPath}/userInfo'">Account</td>
		<c:if test="${user.user_type=='1'}" >
		<td onClick="document.location.href='${pageContext.request.contextPath}/UserAccountList'">Users</td>
		</c:if>
		<c:if test="${user.user_type=='1' || user.user_type=='0'}" >
		<td onClick="document.location.href='${pageContext.request.contextPath}/CurriculumList'">Навчальні плани</td>
		<td onClick="document.location.href='${pageContext.request.contextPath}/DepartmentList'">Кафедри</td>
		<td onClick="document.location.href='${pageContext.request.contextPath}/DisciplineList'">Дисципліни</td>
		<td onClick="document.location.href='${pageContext.request.contextPath}/FacultyList'">Факультети</td>
		<td onClick="document.location.href='${pageContext.request.contextPath}/GroupList'">Групи</td>
		<td onClick="document.location.href='${pageContext.request.contextPath}/SpecialtyList'">Спеціальності</td>
		<td onClick="document.location.href='${pageContext.request.contextPath}/DisciplineCurriculumList'">Дисципліни плану</td>
		</c:if>		
	</tr>
	</tbody>
	</table> -->
	<div class="menu">
	<ul>
	<c:if test="${loginedUser.user_type=='2'}" >
		<li><a href="${pageContext.request.contextPath}/UserAccountList"><i class="fa fa-user"></i> Users</a>
	</c:if>
	<c:if test="${loginedUser.user_type=='2' || loginedUser.user_type=='1' || loginedUser.user_type=='0' || loginedUser.user_type==null}" >
		<li><a href="${pageContext.request.contextPath}/CurriculumList">Навчальні плани</a></li>
		<li><a href="${pageContext.request.contextPath}/FacultyList">Факультети</a></li>
		<li><a href="${pageContext.request.contextPath}/DepartmentList">Кафедри</a></li>
		<li><a href="${pageContext.request.contextPath}/SpecialtyList">Спеціальності</a></li><li>
		<a href="${pageContext.request.contextPath}/GroupList">Групи</a></li><li>
		<a href="${pageContext.request.contextPath}/DisciplineList">Дисципліни</a></li>
		<li><a href="${pageContext.request.contextPath}/DisciplineCurriculumList">Дисципліни плану</a></li>
	</c:if>
	</ul>
	</div>
  