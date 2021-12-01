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
      <title>Нова дисципліна в НП</title>
   </head>
   <body>
       <jsp:include page="_header.jsp"></jsp:include>
      <jsp:include page="_menu.jsp"></jsp:include> 
      <h1>Нова дисципліна в НП</h1>
       
      <p style="color: red;">${errorString}</p>
      <div class="form-cu">
      <form method="POST" action="${pageContext.request.contextPath}/createDisciplineCurriculum" enctype="multipart/form-data">
         <input type="hidden" name="id" value="0" />
            	Дисципліна
            	<select name="discipline_id">
            	<c:forEach items="${DisciplineList}" var="Discipline" >
            	<option value="${Discipline.id}">${Discipline.name}</option>
            	</c:forEach>
            </select>
            	Навчальний план
            	<select name="curriculum_id">
            	<c:forEach items="${CurriculumList}" var="Curriculum" >
            	<option value="${Curriculum.id}">${Curriculum.name}</option>
            	</c:forEach>
            </select>
               Годин всього
               <input type="number" name="hours" value="${DisciplineCurriculum.hours}" />
               Годин ауд.
               <input type="number" name="audit_hours" value="${DisciplineCurriculum.audit_hours}" />
         Лекції
         <input type="number" name="lec_hours" value="${DisciplineCurriculum.lec_hours}" />
         ЛЗ
               <input type="number" name="lab_hours" value="${DisciplineCurriculum.lab_hours}" />
               ПЗ
               <input type="number" name="practice_hours" value="${DisciplineCurriculum.practice_hours}" />
               СРС
               <input type="number" name="independent_work_hours" value="${DisciplineCurriculum.independent_work_hours}" />
               Кредитів
               <input type="number" name="credits" value="${DisciplineCurriculum.credits}" />
               <p>Є екзамен
               <input type="radio" name="has_exam" value="true" >Yes
               <input type="radio" name="has_exam" value="false" >No
               <p>Є залік
               <input type="radio" name="has_credit" value="true" >Yes
               <input type="radio" name="has_credit" value="false" >No
               <p>Тип індивідуального завдання
               <input type="text" name="individual_task_type" value="${DisciplineCurriculum.individual_task_type}" />
               Семестр
               <input type="number" name="semester" value="${DisciplineCurriculum.semester}" />
               Блок
               <input type="text" name="block" value="${DisciplineCurriculum.block}" />
               File URL
               <p><input type="file" name="file_url" id="file_1" class="inputfile inputfile-1" /><label for="file_1"><i class="fas fa-paperclip"></i> Choose file</label>
                   </p><p><input type="submit" value="Зберігти" />
                   <a href="javascript:history.back()">Відміна</a>
      </form>
      </div>
       <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>