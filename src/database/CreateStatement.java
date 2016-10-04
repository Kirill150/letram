package database;

import Objects.Driver;
import Objects.PlanningRecord;
import Objects.Tram;

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

        String sql =
                "CREATE TABLE IF NOT EXISTS PLAN "
                + "(Date VARCHAR NOT NULL, "
                + "Driver_id VARCHAR NOT NULL,"
                + "Tram_id VARCHAR NOT NULL, "
                + "Hours VARCHAR NOT NULL, "
                + "Shift VARCHAR NOT NULL, "
                + "holiday BOOLEAN) ";
        return sql;
    }
    public static String getPlanningRecordsId(PlanningRecord record){

        String sql=
                "Select from plan " +
                "Where id = ";

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
    public static String insertStatementForPlaningRecord(String driver, String tram,String day,String hours,String shift,Boolean holiday){

        String sql = "INSERT INTO plan"
                + "(date,driver_id,tram_id, hours, shift, holiday)"
                + "VALUES"+"("+"'"+day+"'"+","+"'"+driver+"'"+","+"'"+tram+"'"+","
                +"'"+hours+"'"+","+"'"+shift+"',"+"'"+holiday+"')";

         return sql;

    }
    public static String updateStatementForPlanningTable(String day, String driver, String oldHours, String oldTram,
                                                         String oldShift, String newTram, String newHours, String newShift) {

    String sql = "UPDATE plan " +
            "SET tram_id =  "+newTram+", hours = "+newHours+", shift = "+newShift+
            " WHERE driver_id = '"+driver+"' and date='"+day+"' and hours='"+oldHours+"' and tram_id='"+oldTram+"' and shift='"+oldShift+"'";

        return sql;
    }
    public static String selectHoursStatementFromPlaningTable(String driver, String date){

        String sql = "select hours from plan " +
                "where driver_id = '"+driver+"'"+
                " and date = '"+date+"'";

        return sql;
    }
    public static String deleteFromPlanning(String name, String date){
        String sql = "delete from plan where driver_id = "+name+" and date = "+date;

        return sql;
    }
    public static String createTramTable(){

        String sql = "CREATE TABLE IF NOT EXISTS Trams "
                + "(Number INT NOT NULL, "
                + "Id int NOT NULL, "
                + "Color varchar NOT NULL)";

        return  sql;

    }
    public static String insertTram(Tram tram){

        String sql = "INSERT INTO trams "
                + "(Number,Id,Color) "
                + "VALUES"+"("+"'"+tram.getNumber()+"'"+","+"'"+tram.getId()+"'"+","+"'"+tram.getColor()+"'"+")";

        return sql;

    }
    public static String updateTram(Tram t, String id){

        String sql = "Update trams " +
                "SET Number='"+t.getNumber()+"',"+
                "id='"+t.getId()+"',"+
                "surname='"+t.getColor()+"' "+
                "Where id ='"+id+"'";

        return sql;
    }
    public static String deleteFromTram(String id){

        String sql = "Delete from trams where id = "+id;

        return sql;

    }
    public static String selectTram(String id){

        String sql = "select color from trams where id="+id;
        return sql;

    }
    public static String findTram(String id){



        return null;
    }
}


