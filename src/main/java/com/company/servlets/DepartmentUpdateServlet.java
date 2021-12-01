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

@WebServlet(urlPatterns = { "/editDepartment" })
public class DepartmentUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DepartmentUpdateServlet() {
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
	 
	        Department Department = null;
	 
	        String errorString = null;
	 
	        List<Faculty> list = null;
	        try {
	            Department = MyUtils.findDepartment(conn, id);
	            list = MyUtils.listFaculties(conn);
	            Department.setFaculty(MyUtils.findFacultyByDepartment(conn, Department.getId()));
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	        }
	        if (errorString != null && Department == null) {
	            response.sendRedirect(request.getServletPath() + "/DepartmentList");
	            return;
	        }
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("Department", Department);
	        request.setAttribute("FacultyList", list);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/DepartmentUpdateView.jsp");
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
			String short_name = (String) request.getParameter("short_name");
			String f_idStr = (String) request.getParameter("faculty_id");
	        int id = 0;
	        int f_id = 0;
	        try {
	        	id = Integer.parseInt(idStr);
	            f_id = Integer.parseInt(f_idStr);
	        } catch (Exception e) {
	        }
	        Department Department = new Department(id, code, name, short_name, f_id);
	 
	        String errorString = null;
	   	 
	        if (errorString == null && (MyUtils.getLoginedUser(session).getUser_type() == 1 || MyUtils.getLoginedUser(session).getUser_type() == 2)) {
	            try {
	                MyUtils.updateDepartment(conn, Department);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	            }
	        }
	 
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("Department", Department);
	        if (errorString != null) {
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/DepartmentUpdateView.jsp");
	            dispatcher.forward(request, response);
	        }
	        else {
	            response.sendRedirect(request.getContextPath() + "/DepartmentList");
	        }
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

    }
 
}