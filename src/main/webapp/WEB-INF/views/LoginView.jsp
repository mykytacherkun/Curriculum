<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE xhtml>
<html>
  
   <head>
      <meta charset="UTF-8">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/stool.css" title="default" />
      <link rel="alternate stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/stool_light.css" title="alternate" />
      <script src="https://kit.fontawesome.com/982c488da8.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/styles/styleswitcher.js"></script>
      <script src="https://kit.fontawesome.com/982c488da8.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/styles/styleswitcher.js"></script>
      <title>Login</title>
   </head>
   <body>
      <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
      <h1>Вхід в систему</h1>
      <p style="color: red;">${errorString}</p>
 
 	<div class="form-login">
      <form method="POST" action="${pageContext.request.contextPath}/login">
            <input type="text" name="user_name" value= "${user.user_name}" placeholder="Username"/>
            <input type="password" name="user_password" value= "${user.user_password}" placeholder="Password"/> 
            <input type="checkbox" name="rememberMe" value= "Y" /> Remember me
                  <div class="button-wrapper"><div class="button-center"><input type="submit" value= "Submit" /></div>
                  <div class="button-center"><input type="button" value= "Cancel" onClick="document.location.href='${pageContext.request.contextPath}/'" />
         		  </div></div>
            Or <a href="${pageContext.request.contextPath}/register">register now</a>
      </form>
      </div>
 
      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>