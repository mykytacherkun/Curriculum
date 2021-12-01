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
    <script src="https://kit.fontawesome.com/982c488da8.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/styles/styleswitcher.js"></script>
    <title>Список дисціплін НП - StudyPlans</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>
<h1>Список дисціплін навчального плану</h1>
<div class="search">
    <form method="POST" action="${pageContext.request.contextPath}/searchDisciplineCurriculum?group=${groupName}">
        <ul>
            <li>
                <div class="link__block">
                    <c:if test="${loginedUser.user_type=='2' || loginedUser.user_type=='1'}">
                        <a href="${pageContext.request.contextPath}/createDisciplineCurriculum"><i
                                class="fas fa-plus-square"></i> Create Discipline Curriculum</a>
                    </c:if>
                    <a href="javascript:history.back()">Back</a>
                </div>
            </li>
            <li>
                <div class="search__block">
                    <div style="color: #FB667A;">
                        <input type="hidden" name="search" value="${search}"/>
                        Оберіть семестри 1<input type="checkbox" name="semesters" value="1">
                        2<input type="checkbox" name="semesters" value="2">
                        3<input type="checkbox" name="semesters" value="3">
                        4<input type="checkbox" name="semesters" value="4">
                        5<input type="checkbox" name="semesters" value="5">
                        6<input type="checkbox" name="semesters" value="6">
                        7<input type="checkbox" name="semesters" value="7">
                        8<input type="checkbox" name="semesters" value="8">
                    </div>

                    <!-- <input type="text" name="search" placeholder="Search..." /> -->
                    <button type="submit"><i class="fa fa-search"></i></button>
                </div>
            </li>
        </ul>
    </form>
</div>

<p style="color: red;">${errorString}</p>
<c:if test="${not empty groupName}"><p>Дисцпиліни НП для групи <span style="color: red;"> ${groupName}</span>
</p></c:if>

<table class="container">
    <thead>
    <tr>
        <th><h5>Найменування</h5></th>
        <th><h5>Навч.план</h5></th>
        <th><h5>Є екзамен?</h5></th>
        <th><h5>Є залік?</h5></th>
        <th><h5>Вид ІЗ</h5></th>
        <th><h5>Семестр</h5></th>
        <th><h5>Блок</h5></th>
        <th><h5>Файл</h5></th>
        <th><h5>Групи</h5></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${DisciplineCurriculumList}" var="DisciplineCurriculum" varStatus="loop">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/searchDiscipline?search=${DisciplineCurriculum.discipline.name}">${DisciplineCurriculum.discipline.name}</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/searchCurriculum?search=${DisciplineCurriculum.curriculum.name}">${DisciplineCurriculum.curriculum.name}</a>
            </td>
            <td>${DisciplineCurriculum.has_exam()}</td>
            <td>${DisciplineCurriculum.has_credit()}</td>
            <td>${DisciplineCurriculum.individual_task_type}</td>
            <td>${DisciplineCurriculum.semester}</td>
            <td>${DisciplineCurriculum.block}</td>
            <td><a href="${pageContext.request.contextPath}/download?file=${DisciplineCurriculum.file_url}"><i
                    class="fas fa-link"></i> Link</a>
            <td><a href="searchGroupByDiscipline?search=${DisciplineCurriculum.discipline.name}">Show groups</a></td>
            <c:if test="${loginedUser.user_type=='2' || loginedUser.user_type=='1'}">
                <td>
                    <a href="editDisciplineCurriculum?discipline_id=${DisciplineCurriculum.discipline.id}&curriculum_id=${DisciplineCurriculum.curriculum.id}"><i
                            class="far fa-edit"></i> Edit</a>
                </td>
                <td>
                    <a href="deleteDisciplineCurriculum?discipline_id=${DisciplineCurriculum.discipline.id}&curriculum_id=${DisciplineCurriculum.curriculum.id}"><i
                            class="far fa-trash-alt"></i> Delete</a>
                </td>
            </c:if>
        </tr>
        <tr>
            <td colspan="11" style="color: #A7A1AE;">Годин вього: ${DisciplineCurriculum.hours} |
                Годин ауд.: ${DisciplineCurriculum.audit_hours} |
                Лекції: ${DisciplineCurriculum.lec_hours} |
                ЛЗ: ${DisciplineCurriculum.lab_hours} |
                ПЗ: ${DisciplineCurriculum.practice_hours} |
                СРС: ${DisciplineCurriculum.independent_work_hours} |
                Кредитів: ${DisciplineCurriculum.credits}
            </td>

        </tr>
    </c:forEach>
    </tbody>

</table>
<div class="menu">
    <ul>
        <c:if test="${currentPage != 1}">
            <li>
                <a href="${pageContext.request.contextPath}/DisciplineCurriculumList?page=${currentPage - 1}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li><a class="active">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/DisciplineCurriculumList?page=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li><a href="${pageContext.request.contextPath}/DisciplineCurriculumList?page=${currentPage + 1}">Next</a>
            </li>
        </c:if>
    </ul>
</div>
<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>