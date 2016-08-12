package database;

import Objects.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}