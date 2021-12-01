<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE xhtml>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/stool.css" title="default"/>
    <link rel="alternate stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/stool_light.css"
          title="alternate"/>
    <script src="https://kit.fontawesome.com/982c488da8.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/styles/styleswitcher.js"></script>
    <title>Нова кафедра</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<h1>Нова кафедра</h1>

<p style="color: red;">${errorString}</p>
<div class="form-cu">
    <form method="POST" action="${pageContext.request.contextPath}/createDepartment">
        <input type="hidden" name="id" value="0"/>
        Код
        <input type="text" name="code" value="${Department.code}"/>
        Найменування
        <input type="text" name="name" value="${Department.name}"/>
        Скорочення
        <input type="text" name="short_name" value="${Department.short_name}"/>
        Факультет
        <select name="faculty_id">
            <c:forEach items="${FacultyList}" var="Faculty">
                <option value="${Faculty.id}">${Faculty.name} </option>
            </c:forEach>
        </select>
        <p><input type="submit" value="Зберігти"/>
            <a href="javascript:history.back()">Відміна</a></p>
    </form>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>