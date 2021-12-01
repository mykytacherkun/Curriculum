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
import com.company.model.*;
import com.company.utils.*;

@WebServlet(urlPatterns = { "/editUserAccount" })
public class UserAccountUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserAccountUpdateServlet() {
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
	        try{
	        	id = Integer.parseInt(idStr);
	        } catch (Exception e) {
	        }
	 
	        UserAccount UserAccount = null;
	 
	        String errorString = null;
	 
	        try {
	            UserAccount = MyUtils.findUser(conn, id);
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	        }
	        if (errorString != null && UserAccount == null) {
	            response.sendRedirect(request.getServletPath() + "/UserAccountList");
	            return;
	        }
	        
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("UserAccount", UserAccount);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/UserAccountUpdateView.jsp");
        dispatcher.forward(request, response);
 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
		try {
			 //conn = MySQLUtils.getMySQLConnection(MyUtils.getLoginedUser(session));
			conn = MySQLUtils.getSQLiteConnection(request, response);
			String idStr = (String) request.getParameter("id");
			String email = (String) request.getParameter("email");
			String password = (String) request.getParameter("password");
			String name = (String) request.getParameter("name");
			String typeStr = (String) request.getParameter("type");
			int id = 0;
			int type = 0;
	        try {
	        	id = Integer.parseInt(idStr);
	        	type = Integer.parseInt(typeStr);
	        } catch (Exception e) {
	        }
	        UserAccount UserAccount = new UserAccount(id, email, password, name, type);
	 
	        String errorString = null;
	   	 
	        if (errorString == null) {
	            try {
	                MyUtils.updateUser(conn, UserAccount);
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	                //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	            }
	        }
	 
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("UserAccount", UserAccount);
	        if (errorString != null) {
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/UserAccountUpdateView.jsp");
	            dispatcher.forward(request, response);
	        }
	        else {
	            response.sendRedirect(request.getContextPath() + "/UserAccountList");
	        }
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();

    }
    }
}