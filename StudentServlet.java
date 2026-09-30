package com.student.controller;

import com.student.dao.StudentDAO;
import com.student.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO dao;

    @Override
    public void init() {
        dao = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("students", dao.getAllStudents());
            request.getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Unable to load students", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            if ("add".equals(action) || "update".equals(action)) {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String course = request.getParameter("course");
                if (name == null || name.isBlank() || email == null || email.isBlank()
                        || course == null || course.isBlank()) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
                    return;
                }
                if ("add".equals(action)) {
                    dao.addStudent(new Student(0, name.trim(), email.trim(), course.trim()));
                } else {
                    int id = Integer.parseInt(request.getParameter("id"));
                    dao.updateStudent(new Student(id, name.trim(), email.trim(), course.trim()));
                }
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                dao.deleteStudent(id);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action.");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/students");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Unable to process student request", e);
        }
    }
}
