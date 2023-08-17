package tests.jdbc_tests.day02;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jdbc4_DynamicList {
    String dbUrl="jdbc:postgresql://localhost:5432/postgres";
    String dbUserName="postgres";
    String dbPassword="oslem2008";

    @Test
    public void test1() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        Statement statement= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet= statement.executeQuery("select firstname,lastname,salary,jobid from employees");
        //get the result set object metadata
        ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
        //list for keeping all rows in a list
        List<Map<String,Object>> queryData=new ArrayList<>();

        int columnCount = resultSetMetaData.getColumnCount();
        while (resultSet.next()){
            Map<String,Object>row =new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(resultSetMetaData.getColumnName(i),resultSet.getString(i));
            }
            queryData.add(row);
        }
        System.out.println(queryData);
        System.out.println("queryData.get(3).get(\"firstname\") = " + queryData.get(3).get("firstname"));
        System.out.println("queryData.get(5).get(\"lastname\") = " + queryData.get(5).get("lastname"));
        System.out.println("queryData.get(7).get(\"salary\") = " + queryData.get(7).get("salary"));
        System.out.println("queryData.get(9).get(\"jobid\") = " + queryData.get(9).get("jobid"));


        resultSet.close();
        statement.close();
        connection.close();


    }
}
