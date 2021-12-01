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

@WebServlet(urlPatterns = { "/searchGroup" })
public class GroupSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public GroupSearchServlet() {
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
	        List<Group> list = null;
	        List<Group> list1 = null;
	        try {
	            list1 = MyUtils.searchGroups(conn, search);
	            list = MyUtils.searchGroupsPage(conn, search, (page-1)*recordsPerPage, recordsPerPage);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
	        }
	        for(Group g : list) {
	            g.setCurriculum(MyUtils.findCurriculumByGroup(conn, g.getId()));
	            g.setDepartment(MyUtils.findDepartmentByGroup(conn, g.getId()));
	            g.setSpecialty(MyUtils.findSpecialtyByGroup(conn, g.getId()));}
	        int noOfRecords = list1.size();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        request.setAttribute("errorString", errorString);
	        request.setAttribute("GroupList", list);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/GroupListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
