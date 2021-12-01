package com.company.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.company.model.*;
import com.company.utils.*;

@WebServlet(urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public RegisterServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/RegisterView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
		try {
			conn = MySQLUtils.getSQLiteConnection(request, response);
			String user_name = request.getParameter("user_name");
			String user_email = request.getParameter("user_email");
			String user_password = request.getParameter("user_password");
	        UserAccount ua = new UserAccount(user_email, user_password, user_name);
			List<UserAccount> test = new ArrayList<UserAccount>();
	        String errorString = null;
	        try {
				test = MyUtils.checkIfUserExists(conn, user_name, user_email);
			} catch (SQLException e1) {
			}
			if (test.size() < 1) {
					
	        if (errorString == null) {
	            try {
	                MyUtils.insertUser(conn, ua);
	            } catch (SQLException e) {
	                e.printStackTrace();
	                //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	            }
	        }
	 
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("user", ua);}
			else {
				errorString = "User name or email is already in use";
			}
	        if (errorString != null) {
	            request.setAttribute("errorString", errorString);
	            RequestDispatcher dispatcher = request.getServletContext()
	                    .getRequestDispatcher("/WEB-INF/views/RegisterView.jsp");
	            dispatcher.forward(request, response);
	        }
	        else {
	            response.sendRedirect(request.getContextPath() + "/login");
	        }
	        
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

    }
 
}