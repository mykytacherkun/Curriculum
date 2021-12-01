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
      <title>Update User account</title>
   </head>
   <body>
  <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
      <h1>Update User account</h1>
 
      <p style="color: red;">${errorString}</p>
 
      <c:if test="${not empty UserAccount}">
      <div class="form-cu">
         <form method="POST" action="${pageContext.request.contextPath}/editUserAccount">
            <input type="hidden" name="id" value="${UserAccount.user_id}" />
               <p>ID <span style="color:red;">${UserAccount.user_id}</span></p>
                  E-mail
                  <input type="text" name="email" value="${UserAccount.user_email}" />
                  Password
                  <input type="text" name="password" value="${UserAccount.user_password}" />
                  Name
                  <input type="text" name="name" value="${UserAccount.user_name}" />
            	  Type
            	  <select name="type">
            	  <option selected value="${UserAccount.user_type}">${UserAccount.type()}</option>
            	  <option value="0">Default user</option>
            	  <option value="1">Moderator</option>
            	  <option value="2">Administrator</option>
            	  </select>
                      <input type="submit" value="Зберігти" />
                      <a href="${pageContext.request.contextPath}/UserAccountList">Відміна</a>
         </form>
      </div>
      </c:if>
 <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>