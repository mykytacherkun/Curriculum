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
      <title>Редагування НП</title>
   </head>
   <body>
  <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
      <h1>Редагування навчального плану</h1>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty Curriculum}">
      <div class="form-cu">
         <form method="POST" action="${pageContext.request.contextPath}/editCurriculum" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${Curriculum.id}" />
            <input type="hidden" name="file_url1" value="${Curriculum.file_url}" />
            <input type="hidden" name="approvement_url1" value="${Curriculum.approvement_url}" />
                  <p>ID <span style="color:red">${Curriculum.id}</span></p>
                  Найменування
                  <input type="text" name="name" value="${Curriculum.name}" />
                  Рік
                  <input type="text" name="year" value="${Curriculum.year}" />
                  Спеціальність
                  <select name="specialty_id">
            	  <option selected value="${Curriculum.specialty.id}">${Curriculum.specialty.name}</option>
            	  <c:forEach items="${SpecialtyList}" var="Specialty" >
            	  <option value="${Specialty.id}">${Specialty.name}</option>
            	  </c:forEach>
            	  </select>
                  <p>Файл із планом</p>
                  <p><input type="file" name="file_url" id="file_1" class="inputfile inputfile-1" /><label for="file_1"><i class="fas fa-paperclip"></i> Змінити файл</label>
                  </p><p>Лист затвердження</p>
                  <p><input type="file" name="approvement_url" id="file_2" class="inputfile inputfile-1" /><label for="file_2"><i class="fas fa-paperclip"></i> Змінити файл</label>
                  </p>
                  <p><input type="submit" value="Зберігти" />
                  <a href="javascript:history.back()">Відміна</a></p>
         </form>
      </div>
      </c:if>
 <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>