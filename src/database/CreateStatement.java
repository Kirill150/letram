package database;

import Objects.Driver;

/**
 * Created by Papai on 2016.08.07..
 */
public class CreateStatement {



    public static String CreateStatementForAllDriversTable(){

        String sql = "CREATE TABLE IF NOT EXISTS Drivers"
                + "(ID INT NOT NULL,"
                + "NAME VARCHAR NOT NULL, "
                + "SURNAME VARCHAR NOT NULL)";

        return  sql;
    }
    public static String CreateStatementForPlaningTable(){

        String sql = "CREATE TABLE IF NOT EXISTS PLAN"
                + "(Date VARCHAR NOT NULL, "
                + "Driver_id INT NOT NULL,"
                + "Tram_id INT NOT NULL, "
                + "Hours INT NOT NULL, "
                + "Shift INT NOT NULL, "
                + "holiday BOOLEAN)";
        return sql;
    }
    public static String insertStatementForAllDriversTable(Driver driver){

        String sql = "INSERT INTO drivers"
                + "(ID,NAME,SURNAME)"
                + "VALUES"+"("+"'"+driver.getCode()+"'"+","+"'"+driver.getName()+"'"+","+"'"+driver.getSurname()+"'"+")";

        return sql;
    }
    public static String deleteStatementForAllDriversTable(String driver){

        String sql = "DELETE FROM drivers" +
                " WHERE name="+"'"+driver+"'";

        return sql;
    }
    public static String selectStatementForAllDriversTable(String driver){

        String sql = "SELECT FROM drivers" +
                " WHERE name="+"'"+driver+"'";

               return sql;
    }
    public static String updateStatementForAllDriversTable(Driver driver, String name){

        String sql = "Update drivers " +
                "SET name='"+driver.getName()+"',"+
                "id='"+driver.getCode()+"',"+
                "surname='"+driver.getSurname()+"' "+
                "Where name ='"+name+"'";

        return sql;
    }
    public static void testStatement(){

        String sql = "CREATE TABLE IF NOT EXISTS PLAN"
                + "(Date VARCHAR NOT NULL, "
                + "Driver_id INT NOT NULL,"
                + "Tram_id INT NOT NULL, "
                + "Hours INT NOT NULL, "
                + "Shift INT NOT NULL, "
                + "holiday BOOLEAN)";
        System.out.println(sql);
    }
}


