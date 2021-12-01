package com.company.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.company.utils.*;

@WebServlet(urlPatterns = { "/deleteDisciplineCurriculum" })
public class DisciplineCurriculumDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DisciplineCurriculumDeleteServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
		try {
			HttpSession session = request.getSession();
			conn = MySQLUtils.getMySQLConnection(MyUtils.getLoginedUser(session));
			String d_idStr = (String) request.getParameter("discipline_id");
			String c_idStr = (String) request.getParameter("curriculum_id");
	        int d_id = 0;
	        int c_id = 0;
	        try {
	        	d_id = Integer.parseInt(d_idStr);
	        	c_id = Integer.parseInt(c_idStr);
	        } catch(Exception e) {
	        }
	 
	        String errorString = null;
	 
	        try {
	            MyUtils.deleteDisciplineCurriculum(conn, d_id, c_id);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	        } 
	        if (errorString != null) {
	            request.setAttribute("errorString", errorString);
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/deleteDisciplineCurriculumErrorView.jsp");
	            dispatcher.forward(request, response);
	        }
	        else {
	            response.sendRedirect(request.getContextPath() + "/DisciplineCurriculumList");
	        }
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}