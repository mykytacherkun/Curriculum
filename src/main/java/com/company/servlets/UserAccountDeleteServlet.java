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
import com.company.utils.*;

@WebServlet(urlPatterns = { "/deleteUserAccount" })
public class UserAccountDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserAccountDeleteServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
		try {
			conn = MySQLUtils.getSQLiteConnection(request, response);
			String idStr = (String) request.getParameter("id");
	        int id = 0;
	        try {
	        	id = Integer.parseInt(idStr);
	        } catch(Exception e) {
	        }
	 
	        String errorString = null;
	 
	        try {
	            MyUtils.deleteUser(conn, id);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	        } 
	        if (errorString != null) {
	            request.setAttribute("errorString", errorString);
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/UserAccountDeleteView.jsp");
	            dispatcher.forward(request, response);
	        }
	        else {
	            response.sendRedirect(request.getContextPath() + "/UserAccountList");
	        }
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}