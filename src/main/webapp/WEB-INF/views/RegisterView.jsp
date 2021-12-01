<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE xhtml>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/stool.css" title="default" />
      <link rel="alternate stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/stool_light.css" title="alternate" />
      <script src="https://kit.fontawesome.com/982c488da8.js"></script>
      <script type="text/javascript" src="${pageContext.request.contextPath}/styles/styleswitcher.js"></script>
<title>Register</title>
<script> 
function validate()
{ 
 var user_name = document.form.user_name.value;
 var user_email = document.form.user_email.value;
 var user_password = document.form.user_password.value;
 var conpassword= document.form.conpassword.value;
 
 if (user_name==null || user_name=="")
 { 
 alert("Full Name can't be blank"); 
 return false; 
 }
 else if (user_email==null || user_email=="")
 { 
 alert("Email can't be blank"); 
 return false; 
 }
 else if(user_password.length<6)
 { 
 alert("Password must be at least 6 characters long."); 
 return false; 
 } 
 else if (user_password!=conpassword)
 { 
 alert("Confirm Password should match with the Password"); 
 return false; 
 } 
 } 
</script> 
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include>
<h1><span class="blue">Register</span></h1>
<div class="form-login">
	<form name="form" action="register" method="post" onsubmit="return validate()">
	 Username
	 <input type="text" name="user_name" />
	 Email
	 <input type="text" name="user_email" />
	 Password
	 <input type="password" name="user_password" />
	 Confirm Password<input type="password" name="conpassword" />
	 <%=(request.getAttribute("errMessage") == null) ? ""
	 : request.getAttribute("errMessage")%><input type="submit" value="Реєстрація"></input>
	 <input type="button" value= "Відміна" onClick="document.location.href='${pageContext.request.contextPath}/'" />
	</form>
</div>
      <h3>${errorString}</h3>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>