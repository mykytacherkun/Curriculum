package com.company.servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.company.model.*;
import com.company.utils.*;

@WebServlet(urlPatterns = {"/createCurriculum"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CurriculumCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String SAVE_DIRECTORY = "upload_files";

    public CurriculumCreateServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn;
        try {
            HttpSession session = request.getSession();
            conn = MySQLUtils.getMySQLConnection(MyUtils.getLoginedUser(session));
            String errorString = null;
            List<Specialty> specialties = null;
            try {
                specialties = MyUtils.listSpecialties(conn);
            } catch (SQLException e) {
                e.printStackTrace();
                //errorString = e.getMessage();
                errorString = MyUtils.ERROR_MESSAGE;
            }
            request.setAttribute("errorString", errorString);
            request.setAttribute("SpecialtyList", specialties);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/CurriculumCreateView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
        try {
            HttpSession session = request.getSession();
            conn = MySQLUtils.getMySQLConnection(MyUtils.getLoginedUser(session));
            String idStr = request.getParameter("id");
            String name = request.getParameter("name");
            String yearStr = request.getParameter("year");
            //String file_url = request.getParameter("file_url");
            //String approvement_url = request.getParameter("approvement_url");
            String specialty_idStr = request.getParameter("specialty_id");
            int id = 0;
            int year = 0;
            int specialty_id = 0;
            try {
                id = Integer.parseInt(idStr);
                year = Integer.parseInt(yearStr);
                specialty_id = Integer.parseInt(specialty_idStr);
            } catch (Exception e) {
            }

            String applicationPath = getServletContext().getRealPath(""),
                    uploadPath = applicationPath + File.separator + SAVE_DIRECTORY;

            File fileUploadDirectory = new File(uploadPath);
            if (!fileUploadDirectory.exists()) {
                fileUploadDirectory.mkdirs();
            }

            String fileName;
            UploadDetail details;
            List<UploadDetail> fileList = new ArrayList<UploadDetail>();
            List<String> names = new ArrayList<String>();

            for (Part part : request.getParts()) {
                fileName = extractFileName(part);
                details = new UploadDetail();
                details.setFileName(fileName);
                details.setFileSize(part.getSize() / 1024);
                try {
                    part.write(uploadPath + File.separator + fileName);
                    names.add(fileName);
                    details.setUploadStatus(MyUtils.SUCCESS);
                } catch (IOException ioObj) {
                    details.setUploadStatus(MyUtils.FAILURE + ioObj.getMessage());
                }
                fileList.add(details);
            }

            request.setAttribute("uploadedFiles", fileList);
            Curriculum Curriculum = new Curriculum(id, name, year, names.get(0), names.get(1), specialty_id);

            String errorString = null;

            if (errorString == null && (MyUtils.getLoginedUser(session).getUser_type() == 1 || MyUtils.getLoginedUser(session).getUser_type() == 2)) {
                try {
                    MyUtils.insertCurriculum(conn, Curriculum);
                } catch (SQLException e) {
                    e.printStackTrace();
                    //errorString = e.getMessage();
                    errorString = MyUtils.ERROR_MESSAGE;
                }
            }

            request.setAttribute("errorString", errorString);
            request.setAttribute("Curriculum", Curriculum);
            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/CurriculumCreateView.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/CurriculumList");
            }

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    private String extractFileName(Part part) {
        String fileName = "",
                contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                fileName = item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return fileName;
    }

}