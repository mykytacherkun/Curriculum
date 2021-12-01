package com.company.utils;

import com.company.model.UserAccount;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MySQLUtils {
  
 public static Connection getMySQLConnection(UserAccount user)
         throws ClassNotFoundException, SQLException {
	 String hostName = "sql7.freemysqlhosting.net";
	 String dbName = "sql7339540";
	 String userName = "sql7339540";
	 String password = "VI4dihTP2f";
     /*String hostName = "localhost";
     String dbName = "study_plan";
     String userName = "defaultuser";
     String password = "secretuserpassword";
     Integer ut = user.getUser_type();
     if(ut.equals(null)) {
		 userName = "defaultuser";
		 password = "secretuserpassword";
	 }
     else {
	 if(user.getUser_type() == 0) {
		 userName = "defaultuser";
		 password = "secretuserpassword";
	 }
	 if (user.getUser_type() == 1) {
		 userName = "moder";
		 password = "secretmoderatorpassword";		 
	 }
	 if (user.getUser_type() == 2) {
		 userName = "admini";
		 password = "secretadminpassword";
	 }}*/
     return getMySQLConnection(hostName, dbName, userName, password);
 }
 
 public static Connection getSQLiteConnection(ServletRequest request, ServletResponse response) throws ClassNotFoundException, IOException {
	 Class.forName("org.sqlite.JDBC");
     Connection conn = null;
     try {
    	 String url = "jdbc:sqlite:" + request.getServletContext().getRealPath("/") + "users.db";
         conn = DriverManager.getConnection(url);
         
         System.out.println("Connection to SQLite has been established.");
         return conn;
         
     } catch (SQLException e) {
         System.out.println(e.getMessage());
     } /*finally {
         try {
             if (conn != null) {
                 conn.close();
             }
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
     }*/
	return conn;
 }
  
 public static Connection getMySQLConnection(String hostName, String dbName,
         String userName, String password) throws SQLException,
         ClassNotFoundException {
    
     Class.forName("com.mysql.cj.jdbc.Driver");
  
     //String connectionURL = "jdbc:mysql://localhost/study_plan?serverTimezone=Europe/Moscow&useSSL=false&useUnicode=true&characterEncoding=UTF-8";
     String connectionURL = "jdbc:mysql://sql7.freemysqlhosting.net/sql7339540?useUnicode=true&characterEncoding=UTF-8";
  
     Connection conn = DriverManager.getConnection(connectionURL, userName,
             password);
     return conn;
 }
}