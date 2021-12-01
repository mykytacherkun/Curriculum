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
      <title>Нова спеціальність</title>
   </head>
   <body>
       <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include> 
      <h1>Нова спеціальність</h1>
       
      <p style="color: red;">${errorString}</p>
      <div class="form-cu">
      <form method="POST" action="${pageContext.request.contextPath}/createSpecialty">
         <input type="hidden" name="id" value="0" />
               Шифр
               <input type="text" name="code" value="${Specialty.code}" />
               Назва
               <input type="text" name="name" value="${Specialty.name}" />
                   <input type="submit" value="Зберігти" />
                   <a href="javascript:history.back()">Відміна</a>
      </form>
      </div>
       <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>