package tests.jdbc_tests.day02;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jdbc3_ListOfMapExample {
    String dbUrl="jdbc:postgresql://localhost:5432/postgres";
    String dbUserName="postgres";
    String dbPassword="oslem2008";

    @Test
    public void test1() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet= statement.executeQuery("select firstname,lastname,salary,jobid from employees");
        ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
        List<Map<String,Object>> queryData=new ArrayList<>();
        Map<String ,Object> row1=new HashMap<>();
        row1.put("firstname","Eren");
        row1.put("lastname","Çengel");
        row1.put("salary", 100000);
        row1.put("jobid","QA");
        System.out.println("row1 = " + row1);
        Map<String ,Object> row2=new HashMap<>();
        row2.put("firstname","Alperen");
        row2.put("lastname","Çengel");
        row2.put("salary", 120000);
        row1.put("jobid","Dev");
        System.out.println("row2 = " + row2);
        queryData.add(row1);
        queryData.add(row2);
        System.out.println(queryData);
//get the Eren's lastname directly from the list
        System.out.println(queryData.get(0).get("lastname"));
        //get the Alperen's salary
        System.out.println(queryData.get(1).get("salary"));
        //how to fill out a list of map with information that comes from database
        List<Map<String,Object>> queryData2=new ArrayList<>();
        resultSet.next();
        Map<String,Object>newRow1=new HashMap<>();
        newRow1.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        newRow1.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        newRow1.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        newRow1.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));
        System.out.println("newRow1 = " + newRow1);
        resultSet.next();
        Map<String,Object>newRow2=new HashMap<>();
        newRow2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        newRow2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        newRow2.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        newRow2.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));
        System.out.println("newRow2 = " + newRow2);



        resultSet.close();
        statement.close();
        connection.close();


    }
}
