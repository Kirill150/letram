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

/**
 * Created by Papai on 2016.11.01..
 */
public class DriverRepo {

    private final String INSERT_DRIVER = "INSERT INTO drivers"
                                         + "(ID,NAME,SURNAME) "
                                         + "VALUES"+"('%s','%s','%s')";

    private final String DELETE_DRIVER = "DELETE FROM drivers WHERE name='%s')";

    private final String UPDATE_DRIVER = "Update drivers "  +
                                         "id='%s', "        +
                                         "SET name='%s', "  +
                                         "surname='%s' "    +
                                         "Where name ='%s'";

    private final String SELECT_DRIVER = "SELECT * FROM drivers WHERE name='%s'";

    private final String SELECT_ALL_DRIVERS = "SELECT * FROM drivers";

    public void insert(String id,String name, String surname) {

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(String.format(INSERT_DRIVER,id, name, surname));
            stmt.close();
            c.close();
        } catch (SQLException ex) {
           System.out.println("Insert driver SQL exception");
        }

    }

    public void update(String id,String name, String surname, String oldName) {

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(String.format(UPDATE_DRIVER,id, name, surname, oldName));
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Update driver SQL exception");
        }

    }

    public void delete(String name){

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(String.format(DELETE_DRIVER, name));
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Delete driver SQL exception");
        }

    }

    public Driver findDriver(String name) {

        Driver driver = new Driver();
        Connection c = GetConnection.getConnection();

        try {
            Statement  stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(SELECT_DRIVER,name));

            while(rs.next()) {

                driver.setCode(rs.getString("id"));
                driver.setName(rs.getString("name"));
                driver.setSurname(rs.getString("surname"));

            }

        } catch (SQLException e) {
            System.out.println("Find driver SQL exception");
        }

        return driver;
    }

    public ObservableList<Driver> getDrivers() {

        Driver driver = new Driver();
        List<Driver> drivers = new LinkedList<>();
        Connection c = GetConnection.getConnection();

        try {

             Statement stmt  = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_DRIVERS);

            while(rs.next()) {
                driver.setCode(rs.getString("id"));
                driver.setName(rs.getString("name"));
                driver.setSurname(rs.getString("surname"));
                drivers.add(driver);
                driver = new Driver();
            }
        } catch (SQLException e) {
            System.out.println("Get drivers sql exception");
        }
        return FXCollections.observableList(drivers);
    }
}
