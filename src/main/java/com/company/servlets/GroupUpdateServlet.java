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

@WebServlet(urlPatterns = { "/editGroup" })
public class GroupUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public GroupUpdateServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
		try {
			HttpSession session = request.getSession();
			conn = MySQLUtils.getMySQLConnection(MyUtils.getLoginedUser(session));
			String idStr = (String) request.getParameter("id");
	        int id = 0;
	        try{
	        	id = Integer.parseInt(idStr);
	        } catch (Exception e) {
	        }
	 
	        Group Group = null;
	 
	        String errorString = null;
	 
	        List<Curriculum> curriculums = null;
	        List<Department> departments = null;
	        List<Specialty> specialties = null;
	        try {
	            Group = MyUtils.findGroup(conn, id);
	            curriculums = MyUtils.listCurriculums(conn);
	            departments = MyUtils.listDepartments(conn);
	            specialties = MyUtils.listSpecialties(conn);
	            Group.setCurriculum(MyUtils.findCurriculumByGroup(conn, Group.getId()));
	            Group.setDepartment(MyUtils.findDepartmentByGroup(conn, Group.getId()));
	            Group.setSpecialty(MyUtils.findSpecialtyByGroup(conn, Group.getId()));
	        } catch (SQLException e) {
	        	////errorString = e.getMessage();
                errorString = MyUtils.ERROR_MESSAGE;
	        }
	        if (errorString != null && Group == null) {
	            response.sendRedirect(request.getServletPath() + "/GroupList");
	            return;
	        }
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("Group", Group);
	        request.setAttribute("CurriculumList", curriculums);
	        request.setAttribute("DepartmentList", departments);
	        request.setAttribute("SpecialtyList", specialties);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/GroupUpdateView.jsp");
        dispatcher.forward(request, response);
 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
		try {
			 HttpSession session = request.getSession();
			conn = MySQLUtils.getMySQLConnection(MyUtils.getLoginedUser(session));
			String idStr = (String) request.getParameter("id");
			String code = (String) request.getParameter("code");
			String name = (String) request.getParameter("name");
			String yearStr = (String) request.getParameter("year");
			String c_idStr = (String) request.getParameter("curriculum_id");
			String d_idStr = (String) request.getParameter("department_id");
			String s_idStr = (String) request.getParameter("specialty_id");
	        int id = 0;
	        int year = 0;
	        int c_id = 0;
	        int d_id = 0;
	        int s_id = 0;
	        try {
	        	id = Integer.parseInt(idStr);
	        	year = Integer.parseInt(yearStr);
	            c_id = Integer.parseInt(c_idStr);
	            d_id = Integer.parseInt(d_idStr);
	            s_id = Integer.parseInt(s_idStr);
	        } catch (Exception e) {
	        }
	        Group Group = new Group(id, code, name, year, c_id, d_id, s_id);
	 
	        String errorString = null;
	   	 
	        if (errorString == null && (MyUtils.getLoginedUser(session).getUser_type() == 1 || MyUtils.getLoginedUser(session).getUser_type() == 2)) {
	            try {
	                MyUtils.updateGroup(conn, Group);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	            }
	        }
	 
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("Group", Group);
	        if (errorString != null) {
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/GroupUpdateView.jsp");
	            dispatcher.forward(request, response);
	        }
	        else {
	            response.sendRedirect(request.getContextPath() + "/GroupList");
	        }
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

    }
 
}