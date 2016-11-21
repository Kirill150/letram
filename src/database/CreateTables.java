package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Papai on 2016.11.01..
 */
public class CreateTables {

    private final String DRIVERS_TABLE=  "CREATE TABLE IF NOT EXISTS Drivers"
                                            + "(ID INT NOT NULL,"
                                            + "NAME VARCHAR NOT NULL, "
                                            + "SURNAME VARCHAR NOT NULL)";

    private final String PLANNING_DATE_TABLE =  "CREATE TABLE IF NOT EXISTS plan_date "
                                                 + "(month INT NOT NULL, "
                                                 +  "year INT NOT NULL)";

    private final String MAIN_DATE_TABLE =  "CREATE TABLE IF NOT EXISTS main_date "
                                             + "(day INTEGER NOT NULL,"
                                             + " month INTEGER NOT NULL, year "
                                             + "INTEGER NOT NULL) ";

    private final String PLANNING_TABLE = "CREATE TABLE IF NOT EXISTS PLAN "
                                          + "(Day INTEGER NOT NULL, "
                                          + "Month INTEGER NOT NULL, "
                                          + "Year INTEGER NOT NULL, "
                                          + "Driver_id VARCHAR NOT NULL,"
                                          + "Tram_id VARCHAR NOT NULL, "
                                          + "Hours VARCHAR NOT NULL, "
                                          + "Shift VARCHAR NOT NULL, "
                                          + "holiday VARCHAR) ";

    private final String TRAM_TABLE = "CREATE TABLE IF NOT EXISTS Trams "
                                      + "(Number INT NOT NULL, "
                                      + "Id int NOT NULL, "
                                      + "Color varchar NOT NULL)";



    public  void createTable(String sql) {
        try {
            Connection c = GetConnection.getConnection();
            Statement stmt = null;

            try {
                stmt = c.createStatement();
            } catch (SQLException ex) {
                System.out.print("error executing sql query : "+sql);
            }
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName());

        }
    }

    public void createTables(){

        createTable(DRIVERS_TABLE);
        createTable(PLANNING_DATE_TABLE);
        createTable(MAIN_DATE_TABLE);
        createTable(PLANNING_TABLE);
        createTable(TRAM_TABLE);

    }

}
