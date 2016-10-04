package database;

import Objects.Driver;
import Objects.PlanningRecord;

import Objects.TramIdShiftHours;
import Objects.Tram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExecuteStatement {


    public static void createTable(String sql) {
        try {
            Connection c = GetConnection.getConnection();
            Statement stmt = null;

            try {
                stmt = c.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ExecuteStatement.class.getName()).log(Level.SEVERE, null, ex);
            }

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteStatement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void insert(String sql) {

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static  Driver findDriver(String driver) {

        Driver driverr = new Driver();
        Connection c = GetConnection.getConnection();
        Statement stmt = null;
        String query = "select id, name, surname" +
                " from " + "drivers WHERE name="+"'"+driver+"'";

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {

                driverr.setCode(rs.getString("id"));
                driverr.setName(rs.getString("name"));
                driverr.setSurname(rs.getString("surname"));

            }

        } catch (SQLException e) {
            System.out.println("sqlexception");
        }

        return driverr;
    }

    public static ObservableList<Driver> ParsingDrivers() {

        Driver driver = new Driver();
        List<Driver>  drivers = new LinkedList<Driver>();
        Connection c = GetConnection.getConnection();
        Statement stmt = null;
        String query = "select id, name, surname" +
                " from " + "drivers";

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                driver.setCode(rs.getString("id"));
                driver.setName(rs.getString("name"));
                driver.setSurname(rs.getString("surname"));
                drivers.add(driver);
                driver = new Driver();
            }
        } catch (SQLException e) {
            System.out.println("sqlexception");
        }

        return FXCollections.observableList(drivers);
    }

    public static void DeleteDriver(String sql){
        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Driver UpdateDriver(String sql){

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();
        } catch (SQLException ex) {

            System.out.println("ERROR");
        }



        return null;
    }

    public static String executeSelectStatement(String sql){

        Connection c = GetConnection.getConnection();
        Statement stmt = null;
        String s = "";
        String query = sql;
        System.out.println(query);
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);



            while(rs.next())s =rs.getString("hours");

        } catch (SQLException e) {
            System.out.println("sqlexception");
        }
        return s;
    }

    public static ObservableList<PlanningRecord> test(){
        HashMap<String, TramIdShiftHours> map = new HashMap<>();
        PlanningRecord record = new PlanningRecord();
        List<PlanningRecord>  records = new LinkedList<>();
        Connection c = GetConnection.getConnection();
        Statement stmt = null;
        Statement stmt2 = null;
        String query = "select name" +
                " from drivers";
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {

                record.setDriverId(rs.getString("name"));
                String query2 = "select date, hours, shift, tram_id, holiday from plan where driver_id = "+"'"+record.getDriverId()+"'";
                stmt2 = c.createStatement();
                ResultSet rs2 = stmt2.executeQuery(query2);

                while(rs2.next()) {
                    TramIdShiftHours rec = new TramIdShiftHours();
                    rec.setShift(rs2.getString("shift"));
                    rec.setHoliday(rs2.getBoolean("holiday"));
                    rec.setHours(rs2.getString("hours"));
                    rec.setTramId(rs2.getString("tram_id"));
                    map.put(rs2.getString("date"), rec);

                }


                record.setHoursPerDaymap(map);
                records.add(record);
                record = new PlanningRecord();
                map = new HashMap<>();

            }

        } catch (SQLException e) {

        }

        return FXCollections.observableList(records);

    }

    public static void insertInPlanningTable(String sql){

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExecuteStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean isRecordInTable(String sql){


    Connection c = GetConnection.getConnection();
    Statement stmt = null;

    try {
        stmt = c.createStatement();
        ResultSet rs =  stmt.executeQuery(sql);
        if(rs.next())return true;

    } catch (SQLException ex) {
        Logger.getLogger(ExecuteStatement.class.getName()).log(Level.SEVERE, null, ex);
    }


    return false;
}

    public static ObservableList<Tram> parsingTrams(){

        Tram tram = new Tram();
        List<Tram>  trams = new LinkedList<Tram>();
        Connection c = GetConnection.getConnection();
        Statement stmt = null;
        String query = "select number, id, color" +
                " from " + "trams";

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                tram.setNumber(rs.getString("number"));
                tram.setId(rs.getString("id"));
                tram.setColor(rs.getString("color"));
                trams.add(tram);
                tram = new Tram();
            }
        } catch (SQLException e) {
            System.out.println("sqlexception");
        }

        return FXCollections.observableList(trams);


    }
    public static void executeSql(String sql){


        Connection c = GetConnection.getConnection();
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("sqlexception");
        }
    }

    public static String getColorById(String sql){



        Connection c = GetConnection.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = c.createStatement();
             rs = stmt.executeQuery(sql);
            return rs.getString("color");
        }catch (SQLException e) {
                System.out.println("sqlexception");
            }
        return null;

    }


    public static  Tram findTram(String id) {

        Tram tram = new Tram();
        Connection c = GetConnection.getConnection();
        Statement stmt = null;
        String query = "select id, number, color" +
                " from " + "trams WHERE id="+"'"+id+"'";

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {

                tram.setId(rs.getString("id"));
               tram.setNumber(rs.getString("number"));
                tram.setColor(rs.getString("color"));

            }

        } catch (SQLException e) {
            System.out.println("sqlexception");
        }

        return tram;
    }
}

