package com.company.utils;


import com.company.model.UserAccount;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

   public static Connection getConnection() 
             throws ClassNotFoundException, SQLException {
       return MySQLUtils.getMySQLConnection(new UserAccount(0, "", "", "", 0));
   }
    
   public static void closeQuietly(Connection conn) {
       try {
           conn.close();
       } catch (Exception e) {
       }
   }

   public static void rollbackQuietly(Connection conn) {
       try {
           conn.rollback();
       } catch (Exception e) {
       }
   }
}