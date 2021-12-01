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

@WebServlet(urlPatterns = {"/createDisciplineCurriculum"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DisciplineCurriculumCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String SAVE_DIRECTORY = "upload_files";

    public DisciplineCurriculumCreateServlet() {
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
            List<Discipline> disciplines = null;
            List<Curriculum> curriculums = null;
            try {
                disciplines = MyUtils.listDisciplines(conn);
                curriculums = MyUtils.listCurriculums(conn);
            } catch (SQLException e) {
                e.printStackTrace();
                //errorString = e.getMessage();
                errorString = MyUtils.ERROR_MESSAGE;
            }
            request.setAttribute("errorString", errorString);
            request.setAttribute("DisciplineList", disciplines);
            request.setAttribute("CurriculumList", curriculums);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/DisciplineCurriculumCreateView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn;
        try {
            HttpSession session = request.getSession();
            conn = MySQLUtils.getMySQLConnection(MyUtils.getLoginedUser(session));
            String d_idStr = (String) request.getParameter("discipline_id");
            String c_idStr = (String) request.getParameter("curriculum_id");
            String hoursStr = (String) request.getParameter("hours");
            String audit_hoursStr = (String) request.getParameter("audit_hours");
            String lab_hoursStr = (String) request.getParameter("lab_hours");
            String lec_hoursStr = (String) request.getParameter("lec_hours");
            String practice_hoursStr = (String) request.getParameter("practice_hours");
            String independent_work_hoursStr = (String) request.getParameter("independent_work_hours");
            String creditsStr = (String) request.getParameter("credits");
            String has_examStr = (String) request.getParameter("has_exam");
            String has_creditStr = (String) request.getParameter("has_credit");
            String individual_task_type = (String) request.getParameter("individual_task_type");
            String semesterStr = (String) request.getParameter("semester");
            String block = (String) request.getParameter("block");
            //String file_url = (String) request.getParameter("file_url");
            int d_id = 0;
            int c_id = 0;
            int hours = 0;
            int audit_hours = 0;
            int lab_hours = 0;
            int lec_hours = 0;
            int practice_hours = 0;
            int independent_work_hours = 0;
            int credits = 0;
            boolean has_exam = false;
            boolean has_credit = false;
            int semester = 0;
            try {
                d_id = Integer.parseInt(d_idStr);
                c_id = Integer.parseInt(c_idStr);
                hours = Integer.parseInt(hoursStr);
                audit_hours = Integer.parseInt(audit_hoursStr);
                lab_hours = Integer.parseInt(lab_hoursStr);
                lec_hours = Integer.parseInt(lec_hoursStr);
                practice_hours = Integer.parseInt(practice_hoursStr);
                independent_work_hours = Integer.parseInt(independent_work_hoursStr);
                credits = Integer.parseInt(creditsStr);
                has_exam = Boolean.parseBoolean(has_examStr);
                has_credit = Boolean.parseBoolean(has_creditStr);
                semester = Integer.parseInt(semesterStr);
            } catch (Exception e) {
            }

            String applicationPath = getServletContext().getRealPath(""),
                    uploadPath = applicationPath + File.separator + SAVE_DIRECTORY;

            File fileUploadDirectory = new File(uploadPath);
            if (!fileUploadDirectory.exists()) {
                fileUploadDirectory.mkdirs();
            }

            String fileName = "";
            UploadDetail details = null;
            List<UploadDetail> fileList = new ArrayList<UploadDetail>();

            for (Part part : request.getParts()) {
                fileName = extractFileName(part);
                details = new UploadDetail();
                details.setFileName(fileName);
                details.setFileSize(part.getSize() / 1024);
                try {
                    part.write(uploadPath + File.separator + fileName);
                    details.setUploadStatus(MyUtils.SUCCESS);
                } catch (IOException ioObj) {
                    details.setUploadStatus(MyUtils.FAILURE + ioObj.getMessage());
                }
                fileList.add(details);
            }

            request.setAttribute("uploadedFiles", fileList);
            DisciplineCurriculum DisciplineCurriculum = new DisciplineCurriculum(d_id, c_id, hours,
                    audit_hours, lab_hours, lec_hours, practice_hours, independent_work_hours,
                    credits, has_exam, has_credit, individual_task_type, semester, block, fileName);

            String errorString = null;

            if (errorString == null && (MyUtils.getLoginedUser(session).getUser_type() == 1 || MyUtils.getLoginedUser(session).getUser_type() == 2)) {
                try {
                    MyUtils.insertDisciplineCurriculum(conn, DisciplineCurriculum);
                } catch (SQLException e) {
                    e.printStackTrace();
                    //errorString = e.getMessage();
                    errorString = MyUtils.ERROR_MESSAGE;
                }
            }

            request.setAttribute("errorString", errorString);
            request.setAttribute("DisciplineCurriculum", DisciplineCurriculum);
            if (errorString != null) {
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/DisciplineCurriculumCreateView.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/DisciplineCurriculumList");
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