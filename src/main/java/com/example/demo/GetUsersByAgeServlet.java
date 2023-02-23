package com.example.demo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/getUsersByAge")
public class GetUsersByAgeServlet extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String age = request.getParameter("age");
            List<Employee> employee;
            try {
                employee = EmployeeRepository.getUserByAge(Integer.parseInt(age));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            out.print(employee);
            out.close();
        }
}
