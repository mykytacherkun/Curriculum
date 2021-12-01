package com.company.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.company.model.*;

public class MyUtils {

    public static final String ERROR_MESSAGE = "Помилка виконання запиту";
    public static final String SUCCESS = "Успіх";
    public static final String FAILURE = "Помилка: ";

    public static final String ATT_NAME_CONNECTION = "CONNECTION";
    private static final String ATT_NAME_USER_NAME = "USERNAME";
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        session.setAttribute("loginedUser", loginedUser);
    }
 
    public static UserAccount getLoginedUser(HttpSession session) {
        UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
        return loginedUser;
    }
    public static List<UserAccount> listUser(Connection conn) throws SQLException {
        String sql = "select * from user_account";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<UserAccount> list = new ArrayList<UserAccount>();
        while (rs.next()) {
        	int user_id = rs.getInt("user_id");
            String user_email = rs.getString("user_email");
            String user_password = rs.getString("user_password");
            String user_name = rs.getString("user_name");
            int user_type = rs.getInt("user_type");
            UserAccount u = new UserAccount(user_id, user_email, user_password, user_name, user_type);
            list.add(u);
        }
        return list;
    }
    public static List<UserAccount> listUserPage(Connection conn, int offset, int noOfRecords) throws SQLException {
        String sql = "select * from user_account limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<UserAccount> list = new ArrayList<UserAccount>();
        while (rs.next()) {
        	int user_id = rs.getInt("user_id");
            String user_email = rs.getString("user_email");
            String user_password = rs.getString("user_password");
            String user_name = rs.getString("user_name");
            int user_type = rs.getInt("user_type");
            UserAccount u = new UserAccount(user_id, user_email, user_password, user_name, user_type);
            list.add(u);
        }
        return list;
    }
    public static UserAccount findUser(Connection conn, String name, String password) throws SQLException {
        String sql = "select * from user_account where (user_name=? and user_password=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        pstm.setString(2, password);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int user_id = rs.getInt("user_id");
            String user_email = rs.getString("user_email");
            String user_password = rs.getString("user_password");
            String user_name = rs.getString("user_name");
            int user_type = rs.getInt("user_type");
            UserAccount u = new UserAccount(user_id, user_email, user_password, user_name, user_type);
            return u;
        }
        return null;
    }
    public static UserAccount findUser(Connection conn, String name) throws SQLException {
        String sql = "select * from user_account where user_name=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int user_id = rs.getInt("user_id");
            String user_email = rs.getString("user_email");
            String user_password = rs.getString("user_password");
            String user_name = rs.getString("user_name");
            int user_type = rs.getInt("user_type");
            UserAccount u = new UserAccount(user_id, user_email, user_password, user_name, user_type);
            return u;
        }
        return null;
    }
    public static UserAccount findUser(Connection conn, int id) throws SQLException {
        String sql = "select * from user_account where user_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int user_id = rs.getInt("user_id");
            String user_email = rs.getString("user_email");
            String user_password = rs.getString("user_password");
            String user_name = rs.getString("user_name");
            int user_type = rs.getInt("user_type");
            UserAccount u = new UserAccount(user_id, user_email, user_password, user_name, user_type);
            return u;
        }
        return null;
    }
    public static List<UserAccount> searchUsers(Connection conn, String search) throws SQLException {
        String sql = "select * from user_account where user_id like '%"+search+"%' or user_email like '%"+search+"%' or user_name like '%"+search+"%'";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<UserAccount> list = new ArrayList<UserAccount>();
        while (rs.next()) {
            int id = rs.getInt("user_id");
            String email = rs.getString("user_email");
            String password = rs.getString("user_email");
            String name = rs.getString("user_name");
            int type = rs.getInt("user_type");
            UserAccount u = new UserAccount(id, email, password, name, type);
            list.add(u);
        }
        return list;
    }
    
    public static List<UserAccount> searchUsersPage(Connection conn, String search, int offset, int noOfRecords) throws SQLException {
        String sql = "select * from user_account where user_id like '%"+search+"%' or user_email like '%"+search+"%' or user_name like '%"+search+"%' limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<UserAccount> list = new ArrayList<UserAccount>();
        while (rs.next()) {
            int id = rs.getInt("user_id");
            String email = rs.getString("user_email");
            String password = rs.getString("user_email");
            String name = rs.getString("user_name");
            int type = rs.getInt("user_type");
            UserAccount u = new UserAccount(id, email, password, name, type);
            list.add(u);
        }
        return list;
    }
 
    public static List<UserAccount> checkIfUserExists(Connection conn, String name, String email) throws SQLException {
        String sql = "select * from user_account where user_name=? or user_email=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, name);
        pstm.setString(2, email);
 
        ResultSet rs = pstm.executeQuery();
        List<UserAccount> list = new ArrayList<UserAccount>();
        while (rs.next()) {
        	int user_id = rs.getInt("user_id");
            String user_email = rs.getString("user_email");
            String user_password = rs.getString("user_password");
            String user_name = rs.getString("user_name");
            int user_type = rs.getInt("user_type");
            UserAccount u = new UserAccount(user_id, user_email, user_password, user_name, user_type);
            list.add(u);
        }
        return list;
    }
 
    public static void insertUser(Connection conn, UserAccount ua) throws SQLException {
        String sql = "insert into user_account(user_email, user_password, user_name, user_type) values (?,?,?,0)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, ua.getUser_email());
        pstm.setString(2, ua.getUser_password());
        pstm.setString(3, ua.getUser_name());
 
        pstm.executeUpdate();
    }
    
    public static void updateUser(Connection conn, UserAccount ua) throws SQLException {
        String sql = "update user_account set user_email=?, user_password=?, user_name=?, user_type=? where user_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, ua.getUser_email());
        pstm.setString(2, ua.getUser_password());
        pstm.setString(3, ua.getUser_name());
        pstm.setInt(4, ua.getUser_type());
        pstm.setInt(5, ua.getUser_id());
        pstm.executeUpdate();
    }
    
    public static void deleteUser(Connection conn, int id) throws SQLException {
        String sql = "delete from user_account where user_id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, id);
 
        pstm.executeUpdate();
    }
    
    public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUser_name());
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }
 
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }
    
    public static List<Curriculum> listCurriculums(Connection conn) throws SQLException {
        String sql = "select * from curriculums";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Curriculum> list = new ArrayList<Curriculum>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            String file_url = rs.getString("file_url");
            String approvement_url = rs.getString("approvement_url");
            int specialty_id = rs.getInt("specialty_id");
            Curriculum curriculum = new Curriculum(id, name, year, file_url, approvement_url, specialty_id);
            list.add(curriculum);
        }
        return list;
    }
 
    public static List<Curriculum> listCurriculumsPage(Connection conn, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from curriculums limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Curriculum> list = new ArrayList<Curriculum>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            String file_url = rs.getString("file_url");
            String approvement_url = rs.getString("approvement_url");
            int specialty_id = rs.getInt("specialty_id");
            Curriculum curriculum = new Curriculum(id, name, year, file_url, approvement_url, specialty_id);
            list.add(curriculum);
        }
        return list;
    }
    
    public static Curriculum findCurriculum(Connection conn, int id) throws SQLException {
        String sql = "select * from curriculums where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String name = rs.getString("name");
            int year = rs.getInt("year");
            String file_url = rs.getString("file_url");
            String approvement_url = rs.getString("approvement_url");
            int specialty_id = rs.getInt("specialty_id");
            Curriculum curriculum = new Curriculum(id, name, year, file_url, approvement_url, specialty_id);
            return curriculum;
        }
        return null;
    }
    
    public static List<Curriculum> searchCurriculums(Connection conn, String search) throws SQLException {
        String sql = "select * from curriculums where id like '%"+search+"%' or name like '%"+search+"%' or year like '%"+search+"%'";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Curriculum> list = new ArrayList<Curriculum>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            String file_url = rs.getString("file_url");
            String approvement_url = rs.getString("approvement_url");
            int specialty_id = rs.getInt("specialty_id");
            Curriculum curriculum = new Curriculum(id, name, year, file_url, approvement_url, specialty_id);
            list.add(curriculum);
        }
        return list;
    }
    
    public static List<Curriculum> searchCurriculumsPage(Connection conn, String search, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from curriculums where id like '%"+search+"%' or name like '%"+search+"%' or year like '%"+search+"%' limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Curriculum> list = new ArrayList<Curriculum>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            String file_url = rs.getString("file_url");
            String approvement_url = rs.getString("approvement_url");
            int specialty_id = rs.getInt("specialty_id");
            Curriculum curriculum = new Curriculum(id, name, year, file_url, approvement_url, specialty_id);
            list.add(curriculum);
        }
        return list;
    }
    
    public static void updateCurriculum(Connection conn, Curriculum curriculum) throws SQLException {
        String sql = "update curriculums set name=?, year=?, file_url=?, approvement_url=? where id=? ";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, curriculum.getName());
        pstm.setInt(2, curriculum.getYear());
        pstm.setString(3, curriculum.getFile_url());
        pstm.setString(4, curriculum.getApprovement_url());
        pstm.setInt(5, curriculum.getId());
        pstm.executeUpdate();
    }
 
    public static void insertCurriculum(Connection conn, Curriculum curriculum) throws SQLException {
        String sql = "insert into curriculums(name, year, file_url, approvement_url) values (?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, curriculum.getName());
        pstm.setInt(2, curriculum.getYear());
        pstm.setString(3, curriculum.getFile_url());
        pstm.setString(4, curriculum.getApprovement_url());
        pstm.executeUpdate();
    }
 
    public static void deleteCurriculum(Connection conn, int id) throws SQLException {
        String sql = "delete from curriculums where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, id);
 
        pstm.executeUpdate();
    }
    
    public static List<Department> listDepartments(Connection conn) throws SQLException {
        String sql = "select * from departments";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Department> list = new ArrayList<Department>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            int faculty_id = rs.getInt("faculty_id");
            Department department = new Department(id, code, name, short_name, faculty_id);
            list.add(department);
        }
        return list;
    }
    
    public static List<Department> listDepartmentsPage(Connection conn, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from departments limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Department> list = new ArrayList<Department>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            int faculty_id = rs.getInt("faculty_id");
            Department department = new Department(id, code, name, short_name, faculty_id);
            list.add(department);
        }
        return list;
    }
    
    public static List<Department> searchDepartments(Connection conn, String search) throws SQLException {
        String sql = "select * from departments where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%' or short_name like '%"+search+"%' or faculty_id=(select id from faculties where name like '%"+search+"%')";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Department> list = new ArrayList<Department>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            int faculty_id = rs.getInt("faculty_id");
            Department department = new Department(id, code, name, short_name, faculty_id);
            list.add(department);
        }
        return list;
    }
    
    public static List<Department> searchDepartmentsPage(Connection conn, String search, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from departments where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%' or short_name like '%"+search+"%' or faculty_id=(select id from faculties where name like '%"+search+"%') limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Department> list = new ArrayList<Department>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            int faculty_id = rs.getInt("faculty_id");
            Department department = new Department(id, code, name, short_name, faculty_id);
            list.add(department);
        }
        return list;
    }
 
    public static Department findDepartment(Connection conn, int id) throws SQLException {
        String sql = "select * from departments where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int d_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            int faculty_id = rs.getInt("faculty_id");
            Department department = new Department(d_id, code, name, short_name, faculty_id);
            return department;
        }
        return null;
    }
    
    public static void updateDepartment(Connection conn, Department department) throws SQLException {
        String sql = "update departments set code=?, name=?, short_name=?, faculty_id=? where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, department.getCode());
        pstm.setString(2, department.getName());
        pstm.setString(3, department.getShort_name());
        pstm.setInt(4, department.getFaculty_id());
        pstm.setInt(5, department.getId());
        pstm.executeUpdate();
    }
 
    public static void insertDepartment(Connection conn, Department department) throws SQLException {
        String sql = "insert into departments(code, name, short_name, faculty_id) values (?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, department.getCode());
        pstm.setString(2, department.getName());
        pstm.setString(3, department.getShort_name());
        pstm.setInt(4, department.getFaculty_id());
        pstm.executeUpdate();
    }
 
    public static void deleteDepartment(Connection conn, int id) throws SQLException {
        String sql = "delete from departments where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, id);
 
        pstm.executeUpdate();
    }
    
    public static List<Discipline> listDisciplines(Connection conn) throws SQLException {
        String sql = "select * from disciplines";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Discipline> list = new ArrayList<Discipline>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            Discipline discipline = new Discipline(id, code, name, short_name);
            list.add(discipline);
        }
        return list;
    }
    
    public static List<Discipline> listDisciplinesPage(Connection conn, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from disciplines limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Discipline> list = new ArrayList<Discipline>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            Discipline discipline = new Discipline(id, code, name, short_name);
            list.add(discipline);
        }
        return list;
    }
    
    public static List<Discipline> searchDisciplines(Connection conn, String search) throws SQLException {
        String sql = "select * from disciplines where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%' or short_name like '%"+search+"%'";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Discipline> list = new ArrayList<Discipline>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            Discipline discipline = new Discipline(id, code, name, short_name);
            list.add(discipline);
        }
        return list;
    }
    
    public static List<Discipline> searchDisciplinesPage(Connection conn, String search, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from disciplines where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%' or short_name like '%"+search+"%' limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Discipline> list = new ArrayList<Discipline>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            Discipline discipline = new Discipline(id, code, name, short_name);
            list.add(discipline);
        }
        return list;
    }
 
    public static Discipline findDiscipline(Connection conn, int id) throws SQLException {
        String sql = "select * from disciplines where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int d_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            Discipline discipline = new Discipline(d_id, code, name, short_name);
            return discipline;
        }
        return null;
    }
    
    public static void updateDiscipline(Connection conn, Discipline discipline) throws SQLException {
        String sql = "update disciplines set code=?, name=?, short_name=? where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        System.out.println(discipline.getCode()+" "+discipline.getName());
        pstm.setString(1, discipline.getCode());
        pstm.setString(2, discipline.getName());
        pstm.setString(3, discipline.getShort_name());
        pstm.setInt(4, discipline.getId());
        pstm.executeUpdate();
    }
 
    public static void insertDiscipline(Connection conn, Discipline discipline) throws SQLException {
        String sql = "insert into disciplines(code, name, short_name) values (?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, discipline.getCode());
        pstm.setString(2, discipline.getName());
        pstm.setString(3, discipline.getShort_name());
        pstm.executeUpdate();
    }
 
    public static void deleteDiscipline(Connection conn, int id) throws SQLException {
        String sql = "delete from disciplines where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, id);
 
        pstm.executeUpdate();
    }
    
    public static List<DisciplineCurriculum> listDisciplineCurriculums(Connection conn) throws SQLException {
        String sql = "select * from discipline_curriculums";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<DisciplineCurriculum> list = new ArrayList<DisciplineCurriculum>();
        while (rs.next()) {
            int d_id = rs.getInt("discipline_id");
            int c_id = rs.getInt("curriculum_id");
            int hours = rs.getInt("hours");
            int audit_hours = rs.getInt("audit_hours");
            int lab_hours = rs.getInt("lab_hours");
            int lec_hours = rs.getInt("lec_hours");
            int practice_hours = rs.getInt("practice_hours");
            int independent_work_hours = rs.getInt("independent_work_hours");
            int credits = rs.getInt("credits");
            boolean has_exam = rs.getBoolean("has_exam");
            boolean has_credit = rs.getBoolean("has_credit");
            String individual_task_type = rs.getString("individual_task_type");
            int semester = rs.getInt("semester");
            String block = rs.getString("block");
            String file_url = rs.getString("file_url");
            DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
            		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
            		has_exam, has_credit, individual_task_type, semester, block, file_url);
            list.add(disciplineCurriculum);
        }
        return list;
    }
    
    public static List<DisciplineCurriculum> listDisciplineCurriculumsPage(Connection conn, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from discipline_curriculums limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<DisciplineCurriculum> list = new ArrayList<DisciplineCurriculum>();
        while (rs.next()) {
        	int d_id = rs.getInt("discipline_id");
            int c_id = rs.getInt("curriculum_id");
            int hours = rs.getInt("hours");
            int audit_hours = rs.getInt("audit_hours");
            int lab_hours = rs.getInt("lab_hours");
            int lec_hours = rs.getInt("lec_hours");
            int practice_hours = rs.getInt("practice_hours");
            int independent_work_hours = rs.getInt("independent_work_hours");
            int credits = rs.getInt("credits");
            boolean has_exam = rs.getBoolean("has_exam");
            boolean has_credit = rs.getBoolean("has_credit");
            String individual_task_type = rs.getString("individual_task_type");
            int semester = rs.getInt("semester");
            String block = rs.getString("block");
            String file_url = rs.getString("file_url");
            DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
            		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
            		has_exam, has_credit, individual_task_type, semester, block, file_url);
            list.add(disciplineCurriculum);
        }
        return list;
    }
    
    public static List<DisciplineCurriculum> searchDisciplineCurriculums(Connection conn, String search, String[] semesters) throws SQLException {
        String sql = "select * from discipline_curriculums";


        PreparedStatement pstm = conn.prepareStatement(sql);

        System.out.println(search);
        //pstm.setString(1, search);
        List<DisciplineCurriculum> list = new ArrayList<DisciplineCurriculum>();
        if(semesters != null) {
        	if(!search.equals("") && !search.equals(null) && search != null && search != "") {
        	System.out.println("Search with semester with id");
        for(String s : semesters) {
        sql = "select * from discipline_curriculums where curriculum_id=? and semester=?";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, search);
        pstm.setString(2, s);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int d_id = rs.getInt("discipline_id");
            int c_id = rs.getInt("curriculum_id");
            int hours = rs.getInt("hours");
            int audit_hours = rs.getInt("audit_hours");
            int lab_hours = rs.getInt("lab_hours");
            int lec_hours = rs.getInt("lec_hours");
            int practice_hours = rs.getInt("practice_hours");
            int independent_work_hours = rs.getInt("independent_work_hours");
            int credits = rs.getInt("credits");
            boolean has_exam = rs.getBoolean("has_exam");
            boolean has_credit = rs.getBoolean("has_credit");
            String individual_task_type = rs.getString("individual_task_type");
            int semester = rs.getInt("semester");
            String block = rs.getString("block");
            String file_url = rs.getString("file_url");
            DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
            		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
            		has_exam, has_credit, individual_task_type, semester, block, file_url);
            list.add(disciplineCurriculum);
        }
        }
        	}
        	if(search.equals("") || search.equals(null) || search == null || search == "") {
            	System.out.println("Search with semester without id");
                for(String s : semesters) {
                sql = "select * from discipline_curriculums where semester=?";
                pstm = conn.prepareStatement(sql);
                //pstm.setString(1, search);
                pstm.setString(1, s);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    int d_id = rs.getInt("discipline_id");
                    int c_id = rs.getInt("curriculum_id");
                    int hours = rs.getInt("hours");
                    int audit_hours = rs.getInt("audit_hours");
                    int lab_hours = rs.getInt("lab_hours");
                    int lec_hours = rs.getInt("lec_hours");
                    int practice_hours = rs.getInt("practice_hours");
                    int independent_work_hours = rs.getInt("independent_work_hours");
                    int credits = rs.getInt("credits");
                    boolean has_exam = rs.getBoolean("has_exam");
                    boolean has_credit = rs.getBoolean("has_credit");
                    String individual_task_type = rs.getString("individual_task_type");
                    int semester = rs.getInt("semester");
                    String block = rs.getString("block");
                    String file_url = rs.getString("file_url");
                    DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
                    		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
                    		has_exam, has_credit, individual_task_type, semester, block, file_url);
                    list.add(disciplineCurriculum);
                }
            }
        }
        
        }
        else {
        	ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int d_id = rs.getInt("discipline_id");
                int c_id = rs.getInt("curriculum_id");
                int hours = rs.getInt("hours");
                int audit_hours = rs.getInt("audit_hours");
                int lab_hours = rs.getInt("lab_hours");
                int lec_hours = rs.getInt("lec_hours");
                int practice_hours = rs.getInt("practice_hours");
                int independent_work_hours = rs.getInt("independent_work_hours");
                int credits = rs.getInt("credits");
                boolean has_exam = rs.getBoolean("has_exam");
                boolean has_credit = rs.getBoolean("has_credit");
                String individual_task_type = rs.getString("individual_task_type");
                int semester = rs.getInt("semester");
                String block = rs.getString("block");
                String file_url = rs.getString("file_url");
                DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
                		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
                		has_exam, has_credit, individual_task_type, semester, block, file_url);
                list.add(disciplineCurriculum);
            }
        }
        return list;
    }
    
    public static List<DisciplineCurriculum> searchDisciplineCurriculumsPage(Connection conn, String search, int offset, int noOfRecords, String[] semesters) throws SQLException {
    	String sql = "select * from discipline_curriculums";


        PreparedStatement pstm = conn.prepareStatement(sql);

        System.out.println(search);
        //pstm.setString(1, search);
        List<DisciplineCurriculum> list = new ArrayList<DisciplineCurriculum>();
        if(semesters != null) {
        	if(!search.equals("") && !search.equals(null) && search != null && search != "") {
        for(String s : semesters) {
        sql = "select * from discipline_curriculums where curriculum_id=? and semester=?";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, search);
        pstm.setString(2, s);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            int d_id = rs.getInt("discipline_id");
            int c_id = rs.getInt("curriculum_id");
            int hours = rs.getInt("hours");
            int audit_hours = rs.getInt("audit_hours");
            int lab_hours = rs.getInt("lab_hours");
            int lec_hours = rs.getInt("lec_hours");
            int practice_hours = rs.getInt("practice_hours");
            int independent_work_hours = rs.getInt("independent_work_hours");
            int credits = rs.getInt("credits");
            boolean has_exam = rs.getBoolean("has_exam");
            boolean has_credit = rs.getBoolean("has_credit");
            String individual_task_type = rs.getString("individual_task_type");
            int semester = rs.getInt("semester");
            String block = rs.getString("block");
            String file_url = rs.getString("file_url");
            DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
            		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
            		has_exam, has_credit, individual_task_type, semester, block, file_url);
            list.add(disciplineCurriculum);
        }
        }
        	}
        	if(search.equals("") || search.equals(null) || search == null || search == "") {
                for(String s : semesters) {
                sql = "select * from discipline_curriculums where semester=?";
                pstm = conn.prepareStatement(sql);
                //pstm.setString(1, search);
                pstm.setString(1, s);
                ResultSet rs = pstm.executeQuery();
                while (rs.next()) {
                    int d_id = rs.getInt("discipline_id");
                    int c_id = rs.getInt("curriculum_id");
                    int hours = rs.getInt("hours");
                    int audit_hours = rs.getInt("audit_hours");
                    int lab_hours = rs.getInt("lab_hours");
                    int lec_hours = rs.getInt("lec_hours");
                    int practice_hours = rs.getInt("practice_hours");
                    int independent_work_hours = rs.getInt("independent_work_hours");
                    int credits = rs.getInt("credits");
                    boolean has_exam = rs.getBoolean("has_exam");
                    boolean has_credit = rs.getBoolean("has_credit");
                    String individual_task_type = rs.getString("individual_task_type");
                    int semester = rs.getInt("semester");
                    String block = rs.getString("block");
                    String file_url = rs.getString("file_url");
                    DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
                    		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
                    		has_exam, has_credit, individual_task_type, semester, block, file_url);
                    list.add(disciplineCurriculum);
                }
            }
        }
        
        }
        else {
        	ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int d_id = rs.getInt("discipline_id");
                int c_id = rs.getInt("curriculum_id");
                int hours = rs.getInt("hours");
                int audit_hours = rs.getInt("audit_hours");
                int lab_hours = rs.getInt("lab_hours");
                int lec_hours = rs.getInt("lec_hours");
                int practice_hours = rs.getInt("practice_hours");
                int independent_work_hours = rs.getInt("independent_work_hours");
                int credits = rs.getInt("credits");
                boolean has_exam = rs.getBoolean("has_exam");
                boolean has_credit = rs.getBoolean("has_credit");
                String individual_task_type = rs.getString("individual_task_type");
                int semester = rs.getInt("semester");
                String block = rs.getString("block");
                String file_url = rs.getString("file_url");
                DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
                		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
                		has_exam, has_credit, individual_task_type, semester, block, file_url);
                list.add(disciplineCurriculum);
            }
        }
        return list;
    }
 
    public static DisciplineCurriculum findDisciplineCurriculums(Connection conn, int d_id, int c_id) throws SQLException {
        String sql = "select * from discipline_curriculums where (discipline_id=? and curriculum_id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, d_id);
        pstm.setInt(2, c_id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	/*int d_id = rs.getInt("discipline_id");
            int c_id = rs.getInt("curriculum_id");*/
            int hours = rs.getInt("hours");
            int audit_hours = rs.getInt("audit_hours");
            int lab_hours = rs.getInt("lab_hours");
            int lec_hours = rs.getInt("lec_hours");
            int practice_hours = rs.getInt("practice_hours");
            int independent_work_hours = rs.getInt("independent_work_hours");
            int credits = rs.getInt("credits");
            boolean has_exam = rs.getBoolean("has_exam");
            boolean has_credit = rs.getBoolean("has_credit");
            String individual_task_type = rs.getString("individual_task_type");
            int semester = rs.getInt("semester");
            String block = rs.getString("block");
            String file_url = rs.getString("file_url");
            DisciplineCurriculum disciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours, 
            		audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours, credits,
            		has_exam, has_credit, individual_task_type, semester, block, file_url);
            return disciplineCurriculum;
        }
        return null;
    }
    
    public static void updateDisciplineCurriculum(Connection conn, DisciplineCurriculum disciplineCurriculum, int d_id, int c_id) throws SQLException {
        String sql = "update discipline_curriculums set discipline_id=?, curriculum_id=?, hours=?, audit_hours=?, lab_hours=?, lec_hours=?, " 
        			+ "practice_hours=?, independent_work_hours=?, credits=?, has_exam=?, has_credit=?, individual_task_type=?, "
        			+ "semester=?, block=?, file_url=? where (discipline_id=? and curriculum_id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, disciplineCurriculum.getDiscipline_id());
        pstm.setInt(2, disciplineCurriculum.getCurriculum_id());
        pstm.setInt(3, disciplineCurriculum.getHours());
        pstm.setInt(4, disciplineCurriculum.getAudit_hours());
        pstm.setInt(5, disciplineCurriculum.getLab_hours());
        pstm.setInt(6, disciplineCurriculum.getLec_hours());
        pstm.setInt(7, disciplineCurriculum.getPractice_hours());
        pstm.setInt(8, disciplineCurriculum.getIndependent_work_hours());
        pstm.setInt(9, disciplineCurriculum.getCredits());
        pstm.setBoolean(10, disciplineCurriculum.getHas_exam());
        pstm.setBoolean(11, disciplineCurriculum.getHas_credit());
        pstm.setString(12, disciplineCurriculum.getIndividual_task_type());
        pstm.setInt(13, disciplineCurriculum.getSemester());
        pstm.setString(14, disciplineCurriculum.getBlock());
        pstm.setString(15, disciplineCurriculum.getFile_url());
        pstm.setInt(16, d_id);
        pstm.setInt(17, c_id);
        /*pstm.setInt(16, disciplineCurriculum.getDiscipline_id());
        pstm.setInt(17, disciplineCurriculum.getCurriculum_id());*/
        pstm.executeUpdate();
    }
 
    public static void insertDisciplineCurriculum(Connection conn, DisciplineCurriculum disciplineCurriculum) throws SQLException {
        String sql = "insert into discipline_curriculums(discipline_id, curriculum_id, hours, audit_hours, "
        		+ " lab_hours, lec_hours, practice_hours, independent_work_hours, credits, has_exam, has_credit, "
        		+ " individual_task_type, semester, block, file_url) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setInt(1, disciplineCurriculum.getDiscipline_id());
        pstm.setInt(2, disciplineCurriculum.getCurriculum_id());
        pstm.setInt(3, disciplineCurriculum.getHours());
        pstm.setInt(4, disciplineCurriculum.getAudit_hours());
        pstm.setInt(5, disciplineCurriculum.getLab_hours());
        pstm.setInt(6, disciplineCurriculum.getLec_hours());
        pstm.setInt(7, disciplineCurriculum.getPractice_hours());
        pstm.setInt(8, disciplineCurriculum.getIndependent_work_hours());
        pstm.setInt(9, disciplineCurriculum.getCredits());
        pstm.setBoolean(10, disciplineCurriculum.getHas_exam());
        pstm.setBoolean(11, disciplineCurriculum.getHas_credit());
        pstm.setString(12, disciplineCurriculum.getIndividual_task_type());
        pstm.setInt(13, disciplineCurriculum.getSemester());
        pstm.setString(14, disciplineCurriculum.getBlock());
        pstm.setString(15, disciplineCurriculum.getFile_url());
        pstm.executeUpdate();
    }
 
    public static void deleteDisciplineCurriculum(Connection conn, int d_id, int c_id) throws SQLException {
        String sql = "delete from discipline_curriculums where (discipline_id=? and curriculum_id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, d_id);
        pstm.setInt(2, c_id);
 
        pstm.executeUpdate();
    }
    
    public static List<Faculty> listFaculties(Connection conn) throws SQLException {
        String sql = "select * from faculties";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Faculty> list = new ArrayList<Faculty>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            Faculty faculty = new Faculty(id, code, name);
            list.add(faculty);
        }
        return list;
    }
    
    public static List<Faculty> listFacultiesPage(Connection conn, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from faculties limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Faculty> list = new ArrayList<Faculty>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            Faculty faculty = new Faculty(id, code, name);
            list.add(faculty);
        }
        return list;
    }
    
    public static List<Faculty> searchFaculties(Connection conn, String search) throws SQLException {
        String sql = "select * from faculties where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%'";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Faculty> list = new ArrayList<Faculty>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            Faculty faculty = new Faculty(id, code, name);
            list.add(faculty);
        }
        return list;
    }
    
    public static List<Faculty> searchFacultiesPage(Connection conn, String search, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from faculties where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%' limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Faculty> list = new ArrayList<Faculty>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            Faculty faculty = new Faculty(id, code, name);
            list.add(faculty);
        }
        return list;
    }
 
    public static Faculty findFaculty(Connection conn, int id) throws SQLException {
        String sql = "select * from faculties where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int f_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");
            Faculty faculty = new Faculty(f_id, code, name);
            return faculty;
        }
        return null;
    }
    
    public static void updateFaculty(Connection conn, Faculty faculty) throws SQLException {
        String sql = "update faculties set code=?, name=? where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, faculty.getCode());
        pstm.setString(2, faculty.getName());
        pstm.setInt(3, faculty.getId());
        pstm.executeUpdate();
    }
 
    public static void insertFaculty(Connection conn, Faculty faculty) throws SQLException {
        String sql = "insert into faculties(code, name) values (?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, faculty.getCode());
        pstm.setString(2, faculty.getName());
        pstm.executeUpdate();
    }
 
    public static void deleteFaculty(Connection conn, int id) throws SQLException {
        String sql = "delete from faculties where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, id);
 
        pstm.executeUpdate();
    }
    
    public static List<Group> listGroups(Connection conn) throws SQLException {
        String sql = "select * from groups";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Group> list = new ArrayList<Group>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            int c_id = rs.getInt("curriculum_id");
            int d_id = rs.getInt("department_id");
            int s_id = rs.getInt("specialty_id");
            Group group = new Group(id, code, name, year, c_id, d_id, s_id);
            list.add(group);
        }
        return list;
    }
    
    public static List<Group> listGroupsPage(Connection conn, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from groups limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Group> list = new ArrayList<Group>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            int c_id = rs.getInt("curriculum_id");
            int d_id = rs.getInt("department_id");
            int s_id = rs.getInt("specialty_id");
            Group group = new Group(id, code, name, year, c_id, d_id, s_id);
            list.add(group);
        }
        return list;
    }
    
    public static List<Group> searchGroups(Connection conn, String search) throws SQLException {
        String sql = "select * from groups where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%' or year like '%"+search+"%' or curriculum_id=(select id from curriculums where name like '%"+search+"%') or department_id=(select id from departments where name like '%"+search+"%') or specialty_id=(select id from specialities where name like '%"+search+"%')";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Group> list = new ArrayList<Group>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            int c_id = rs.getInt("curriculum_id");
            int d_id = rs.getInt("department_id");
            int s_id = rs.getInt("specialty_id");
            Group group = new Group(id, code, name, year, c_id, d_id, s_id);
            list.add(group);
        }
        return list;
    }
    
    public static List<Group> searchGroupsPage(Connection conn, String search, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from groups where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%' or year like '%"+search+"%' or curriculum_id=(select id from curriculums where name like '%"+search+"%') or department_id=(select id from departments where name like '%"+search+"%') or specialty_id=(select id from specialities where name like '%"+search+"%') limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Group> list = new ArrayList<Group>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            int c_id = rs.getInt("curriculum_id");
            int d_id = rs.getInt("department_id");
            int s_id = rs.getInt("specialty_id");
            Group group = new Group(id, code, name, year, c_id, d_id, s_id);
            list.add(group);
        }
        return list;
    }
 
    public static Group findGroup(Connection conn, int id) throws SQLException {
        String sql = "select * from groups where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int g_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            int c_id = rs.getInt("curriculum_id");
            int d_id = rs.getInt("department_id");
            int s_id = rs.getInt("specialty_id");
            Group group = new Group(g_id, code, name, year, c_id, d_id, s_id);
            return group;
        }
        return null;
    }
    
    public static void updateGroup(Connection conn, Group group) throws SQLException {
        String sql = "update groups set code=?, name=?, year=?, curriculum_id=?, department_id=?, specialty_id=? where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, group.getCode());
        pstm.setString(2, group.getName());
        pstm.setInt(3, group.getYear());
        pstm.setInt(4, group.getCurriculum_id());
        pstm.setInt(5, group.getDepartment_id());
        pstm.setInt(6, group.getSpecialty_id());
        pstm.setInt(7, group.getId());
        pstm.executeUpdate();
    }
 
    public static void insertGroup(Connection conn, Group group) throws SQLException {
        String sql = "insert into groups(code, name, year, curriculum_id, department_id, specialty_id) values (?,?,?,?,?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, group.getCode());
        pstm.setString(2, group.getName());
        pstm.setInt(3, group.getYear());
        pstm.setInt(4, group.getCurriculum_id());
        pstm.setInt(5, group.getDepartment_id());
        pstm.setInt(6, group.getSpecialty_id());
        pstm.executeUpdate();
    }
 
    public static void deleteGroup(Connection conn, int id) throws SQLException {
        String sql = "delete from groups where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, id);
 
        pstm.executeUpdate();
    }
    
    public static List<Specialty> listSpecialties(Connection conn) throws SQLException {
        String sql = "select * from specialities";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Specialty> list = new ArrayList<Specialty>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            Specialty specialty = new Specialty(id, code, name);
            list.add(specialty);
        }
        return list;
    }
    
    public static List<Specialty> listSpecialtiesPage(Connection conn, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from specialities limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Specialty> list = new ArrayList<Specialty>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            Specialty specialty = new Specialty(id, code, name);
            list.add(specialty);
        }
        return list;
    }
    
    public static List<Specialty> searchSpecialties(Connection conn, String search) throws SQLException {
        String sql = "select * from specialities where id like '%"+search+"%' or code like '%"+search+"%' or name like '%\"+search+\"%'";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Specialty> list = new ArrayList<Specialty>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            Specialty specialty = new Specialty(id, code, name);
            list.add(specialty);
        }
        return list;
    }
    
    public static List<Specialty> searchSpecialtiesPage(Connection conn, String search, int offset, int noOfRecords) throws SQLException {
        String sql = "select SQL_CALC_FOUND_ROWS * from specialities where id like '%"+search+"%' or code like '%"+search+"%' or name like '%"+search+"%' limit " + offset + ", " + noOfRecords;
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        ResultSet rs = pstm.executeQuery();
        List<Specialty> list = new ArrayList<Specialty>();
        while (rs.next()) {
        	int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            Specialty specialty = new Specialty(id, code, name);
            list.add(specialty);
        }
        return list;
    }
 
    public static Specialty findSpecialty(Connection conn, int id) throws SQLException {
        String sql = "select * from specialities where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int s_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");
            Specialty specialty = new Specialty(s_id, code, name);
            return specialty;
        }
        return null;
    }
    
    public static void updateSpecialty(Connection conn, Specialty specialty) throws SQLException {
        String sql = "update specialities set code=?, name=? where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setString(1, specialty.getCode());
        pstm.setString(2, specialty.getName());
        pstm.setInt(3, specialty.getId());
        pstm.executeUpdate();
    }
 
    public static void insertSpecialty(Connection conn, Specialty specialty) throws SQLException {
        String sql = "insert into specialities(code, name) values (?,?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        
        pstm.setString(1, specialty.getCode());
        pstm.setString(2, specialty.getName());
        pstm.executeUpdate();
    }
 
    public static void deleteSpecialty(Connection conn, int id) throws SQLException {
        String sql = "delete from specialities where id=?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
 
        pstm.setInt(1, id);
 
        pstm.executeUpdate();
    }
    
    public static Curriculum findCurriculumByGroup(Connection conn, int id) throws SQLException {
        String sql = "select * from curriculums where id=(select curriculum_id from groups where id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int c_id = rs.getInt("id");
        	String name = rs.getString("name");
            int year = rs.getInt("year");
            String file_url = rs.getString("file_url");
            String approvement_url = rs.getString("approvement_url");
            int specialty_id = rs.getInt("specialty_id");
            Curriculum curriculum = new Curriculum(c_id, name, year, file_url, approvement_url, specialty_id);
            return curriculum;
        }
        return null;
    }
    public static Department findDepartmentByGroup(Connection conn, int id) throws SQLException {
        String sql = "select * from departments where id=(select department_id from groups where id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int d_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");
        	String short_name = rs.getString("short_name");
            int f_id = rs.getInt("faculty_id");
            Department department = new Department(d_id, code, name, short_name, f_id);
            return department;
        }
        return null;
    }
    public static Specialty findSpecialtyByGroup(Connection conn, int id) throws SQLException {
        String sql = "select * from specialities where id=(select specialty_id from groups where id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int s_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");
            Specialty specialty = new Specialty(s_id, code, name);
            return specialty;
        }
        return null;
    }
    public static Faculty findFacultyByDepartment(Connection conn, int id) throws SQLException {
        String sql = "select * from faculties where id=(select faculty_id from departments where id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int d_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");;
            Faculty faculty = new Faculty(d_id, code, name);
            return faculty;
        }
        return null;
    }
    public static Specialty findSpecialtyByCurriculum(Connection conn, int id) throws SQLException {
        String sql = "select * from specialities where id=(select specialty_id from curriculums where id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int d_id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");;
            Specialty specialty = new Specialty(d_id, code, name);
            return specialty;
        }
        return null;
    }
    public static Discipline findDisciplineDC(Connection conn, int d_id, int c_id) throws SQLException {
        String sql = "select * from disciplines where id=(select discipline_id from discipline_curriculums where (discipline_id=? and curriculum_id=?))";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, d_id);
        pstm.setInt(2, c_id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int id = rs.getInt("id");
        	String code = rs.getString("code");
            String name = rs.getString("name");
            String short_name = rs.getString("short_name");
            Discipline discipline = new Discipline(id, code, name, short_name);
            return discipline;
        }
        return null;
    }
    public static Curriculum findCurriculumDC(Connection conn, int d_id, int c_id) throws SQLException {
        String sql = "select * from curriculums where id=(select curriculum_id from discipline_curriculums where (discipline_id=? and curriculum_id=?))";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, d_id);
        pstm.setInt(2, c_id);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
        	int id = rs.getInt("id");
        	String name = rs.getString("name");
            int year = rs.getInt("year");
            String file_url = rs.getString("file_url");
            String approvement_url = rs.getString("approvement_url");
            int specialty_id = rs.getInt("specialty_id");
            Curriculum curriculum = new Curriculum(id, name, year, file_url, approvement_url, specialty_id);
            return curriculum;
        }
        return null;
    }
    public static List<Group> findGroupsByDiscipline(Connection conn, int discipline_id) throws SQLException {
        String sql = "select * from groups where curriculum_id=(select curriculum_id from discipline_curriculums where discipline_id=?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, discipline_id);
 
        ResultSet rs = pstm.executeQuery();
        List<Group> list = new ArrayList<Group>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String code = rs.getString("code");
            String name = rs.getString("name");
            int year = rs.getInt("year");
            int c_id = rs.getInt("curriculum_id");
            int d_id = rs.getInt("department_id");
            int s_id = rs.getInt("specialty_id");
            Group group = new Group(id, code, name, year, c_id, d_id, s_id);
            list.add(group);
        }
        return null;
    }
    
}