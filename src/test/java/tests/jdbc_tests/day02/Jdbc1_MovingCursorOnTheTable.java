package tests.jdbc_tests.day02;

import org.testng.annotations.Test;

import java.sql.*;

public class Jdbc1_MovingCursorOnTheTable {
    String dbUrl="jdbc:postgresql://localhost:5432/postgres";
    String dbUserName="postgres";
    String dbPassword="oslem2008";

    @Test
    public void test1() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet= statement.executeQuery("select * from employees");
        //how to find how many rows we have for the query
        resultSet.last();
        int row = resultSet.getRow();
        System.out.println(row);
//how to go first line
        resultSet.first();
        System.out.println(resultSet.getRow());
        //how to get "Mehmet" firstname directly
        resultSet.absolute(7);
        System.out.println(resultSet.getString(2));
//how to go 6th line
        resultSet.previous();
        System.out.println(resultSet.getRow());


        resultSet.close();
        statement.close();
        connection.close();

    }
}
