package com.company.servlets;

import com.company.model.UserAccount;
import com.company.utils.MySQLUtils;
import com.company.utils.MyUtils;
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

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/LoginView.jsp");
 
        dispatcher.forward(request, response);
 
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String user_password = request.getParameter("user_password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);
 
        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;
 
        if (user_name == null || user_password == null || user_name.length() == 0 || user_password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = null;
			try {
				conn = MySQLUtils.getSQLiteConnection(request, response);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
            try {
                user = MyUtils.findUser(conn, user_name, user_password);
                conn.close();
                if (user == null) {
                    hasError = true;
                    errorString = "Username or password is invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                //errorString = e.getMessage();
	                errorString = MyUtils.ERROR_MESSAGE;
            }
        }
        if (hasError) {
            user = new UserAccount();
            user.setUser_name(user_name);
            user.setUser_password(user_password);
 
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/LoginView.jsp");
 
            dispatcher.forward(request, response);
        }
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            else {
                MyUtils.deleteUserCookie(response);
            }
 
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }
 
}