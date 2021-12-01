<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE xhtml>
<html>
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/stool.css" title="default" />
      <link rel="alternate stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/stool_light.css" title="alternate" />
      <script src="https://kit.fontawesome.com/982c488da8.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/styles/styleswitcher.js"></script>
      <title>Редагування групи</title>
   </head>
   <body>
  <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
      <h1><span class="blue">Редагування групи</span></h1>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty Group}">
      <div class="form-cu">
         <form method="POST" action="${pageContext.request.contextPath}/editGroup">
            <input type="hidden" name="id" value="${Group.id}" />
               <p>ID <span style="color:red;">${Group.id}</span></p>
                  Код
                  <input type="text" name="code" value="${Group.code}" />
                  Назва
                  <input type="text" name="name" value="${Group.name}" />
                  Рік
                  <input type="text" name="year" value="${Group.year}" />
            	  Шифр НП
            	  <select name="curriculum_id">
            	  <option selected value="${Group.curriculum.id}">${Group.curriculum.name}</option>
            	  <c:forEach items="${CurriculumList}" var="Curriculum" >
            	  <option value="${Curriculum.id}">${Curriculum.name}</option>
            	  </c:forEach>
            	  </select>
            	  Спеціальність
            	  <select name="specialty_id">
            	  <option selected value="${Group.specialty.id}">${Group.specialty.name}</option>
            	  <c:forEach items="${SpecialtyList}" var="Specialty" >
            	  <option value="${Specialty.id}">${Specialty.name}</option>
            	  </c:forEach>
            	  </select>
            	  Факультет
            	  <select name="department_id">
            	  <option selected value="${Group.department.id}">${Group.department.name}</option>
            	  <c:forEach items="${DepartmentList}" var="Department" >
            	  <option value="${Department.id}">${Department.name}</option>
            	  </c:forEach>
            	  </select>
                      <input type="submit" value="Зберігти" />
                      <a href="javascript:history.back()">Відміна</a>
         </form>
      </div>
      </c:if>
 <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>