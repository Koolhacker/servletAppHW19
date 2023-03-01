package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    public static void main(String[] args) {
        getConnection();

        Employee employee = new Employee();

        employee.setName("Oleg Kaluzny111");
        employee.setEmail(" @111");
        employee.setCountry(" U111");
        save(employee);
    }

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/employee";
        String user = "postgres";
//        String password = "postgres";
        String password = "user";


        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int save(Employee employee) {
        int status = 0;
        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into users(name,email,country,age) values (?,?,?,?)");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCountry());
            ps.setInt(4,employee.getAge());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(Employee employee) {
        int status = 0;
        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update users set name=?,email=?,country=? where id=?");
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getCountry());
            ps.setInt(4, employee.getId());
            status = ps.executeUpdate();
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {
        int status = 0;
        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from users where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Employee getEmployeeById(int id) {
        Employee employee = new Employee();
        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setEmail(rs.getString(3));
                employee.setCountry(rs.getString(4));
                employee.setAge(rs.getInt(5));
            }
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> getAllEmployees() {
        List<Employee> listEmployees = new ArrayList<>();
        try {
            Connection connection = EmployeeRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setEmail(rs.getString(3));
                employee.setCountry(rs.getString(4));
                employee.setAge(rs.getInt(5));
                listEmployees.add(employee);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listEmployees;
    }

    /**
     * Lesson 24 hw 19
     **/

    public static final String query = "select * from users where country=?";
    public static final String queryUpdate = "update users set name=? where id=?";
    public static final String queryAge = "select * from users where age=?";


    public static List<Employee> getUsersByCountry(String country) throws SQLException {
        ResultSet rs = getSQLQuery(query, country);
        List<Employee> listOfEmployee = resultlist(rs);
        return listOfEmployee;
    }

    public static ResultSet getSQLQuery(String sqlQuery, String params) throws SQLException {
        Connection connection = EmployeeRepository.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ps.setString(1, params);
        ResultSet rs = ps.executeQuery();
        connection.close();
        return rs;
    }

    public static List<Employee> resultlist(ResultSet rs) throws SQLException {
        List<Employee> outputList = new ArrayList<>();
        while (rs.next()) {
            outputList.add(new Employee(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(4),
                    rs.getString(3),
                    rs.getInt(5)
            ));
        }
        return outputList;
    }

    public static void updateName(String name, int id) throws SQLException {
        getSQL(queryUpdate, name, id);
    }

    public static void getSQL(String sqlQuery, String name, int id ) throws SQLException {
        Connection connection = EmployeeRepository.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ps.setString(1, name);
        ps.setInt(2, id);
        ps.executeUpdate();
        connection.close();
    }

    public static List<Employee> getUserByAge (int age) throws SQLException {
        ResultSet rs = getSQLQueryAge(queryAge, age);
        List<Employee> listOfEmployee = resultlist(rs);
        return listOfEmployee;
    }

    public static ResultSet getSQLQueryAge(String sqlQuery, int age) throws SQLException {
        Connection connection = EmployeeRepository.getConnection();
        PreparedStatement ps = connection.prepareStatement(sqlQuery);
        ps.setInt(1, age);
        ResultSet rs = ps.executeQuery();
        connection.close();
        return rs;
    }
}
