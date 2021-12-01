package com.company.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.company.model.*;
import com.company.utils.*;

@WebServlet(urlPatterns = { "/searchDisciplineCurriculum" })
public class DisciplineCurriculumSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DisciplineCurriculumSearchServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
		try {
			int page = 1;
			int recordsPerPage = 50;
			if (request.getParameter("page") != null)
				page = Integer.parseInt(request.getParameter("page"));
			HttpSession session = request.getSession();
			if(MyUtils.getLoginedUser(session) == null) {
				conn = MySQLUtils.getMySQLConnection(new UserAccount(0, "", "", "", 0));
			}
			else {
				conn = MySQLUtils.getMySQLConnection(MyUtils.getLoginedUser(session));
			}
			String search = (String) request.getParameter("search");
			String[] semesters = (String[]) request.getParameterValues("semesters");
			String g = (String) request.getParameter("group");
			String errorString = null;
	        List<DisciplineCurriculum> list = null;
	        List<DisciplineCurriculum> list1 = null;
	        try {
	            list1 = MyUtils.searchDisciplineCurriculums(conn, search, semesters);
	            list = MyUtils.searchDisciplineCurriculumsPage(conn, search, (page-1)*recordsPerPage, recordsPerPage, semesters);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	        }
	        for(DisciplineCurriculum dc : list) {
	            dc.setDiscipline(MyUtils.findDisciplineDC(conn, dc.getDiscipline_id(), dc.getCurriculum_id()));
	            dc.setCurriculum(MyUtils.findCurriculumDC(conn, dc.getDiscipline_id(), dc.getCurriculum_id()));}
	        int noOfRecords = list1.size();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("DisciplineCurriculumList", list);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        request.setAttribute("search", search);
	        request.setAttribute("groupName", g);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/DisciplineCurriculumListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
