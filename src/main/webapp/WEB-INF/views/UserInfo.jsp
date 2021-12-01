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
    <title>User Info</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
 
    <h1>Вітаю, ${loginedUser.user_name}</h1>
 
    <p>User Name: <b>${loginedUser.user_name}</b></p>
    <p>E-mail: <b>${loginedUser.user_email}</b></p>
    <p>Rank: <b>${loginedUser.type()}</b></p>
    <p><a href="${pageContext.request.contextPath}/logout">logout</a></p>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>