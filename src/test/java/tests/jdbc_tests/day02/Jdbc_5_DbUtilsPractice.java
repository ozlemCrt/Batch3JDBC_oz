package tests.jdbc_tests.day02;

import org.testng.annotations.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class Jdbc_5_DbUtilsPractice {


    @Test
    public void listOfMap() {
        DBUtils.createConnection();
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select * from employees");
        for (Map<String, Object> map : queryResultMap) {
            System.out.println("map = " + map);
        }







DBUtils.destroy();
    }

    @Test
    public void oneSingleRow() {
        DBUtils.createConnection();
        Map<String, Object> rowMap = DBUtils.getRowMap("select * from employees where firstname='Alperen'");
        System.out.println(rowMap);


        DBUtils.destroy();
    }
}
