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

@WebServlet(urlPatterns = { "/searchUserAccount" })
public class UserAccountSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserAccountSearchServlet() {
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
			String errorString = null;
	        List<UserAccount> list = null;
	        List<UserAccount> list1 = null;
	        try {
				conn = MySQLUtils.getSQLiteConnection(request, response);
	            list1 = MyUtils.searchUsers(conn, search);
	            list = MyUtils.searchUsersPage(conn, search, (page-1)*recordsPerPage, recordsPerPage);
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	        }
	        int noOfRecords = list1.size();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("UserAccountList", list);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/UserAccountListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
