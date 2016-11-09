package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Papai on 2016.11.01..
 */
public class MainDateRepo {

    private final String SELECT_MAIN_DATE = "SELECT * from main_date";

    private final String UPDATE_MAIN_DATE = "UPDATE main_date SET day = '%d', month='%d', year = '%d'";

    private final String INSERT_MAIN_DATE = "INSERT INTO main_date (day,month,year) VALUES ('%d','%d','%d')";


    public void insert(Integer day,Integer month, Integer year) {

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_MAIN_DATE);
            if(rs.next()){
                  stmt.executeQuery(String.format(UPDATE_MAIN_DATE,day, month,year));
            }else{
                stmt.executeQuery(String.format(INSERT_MAIN_DATE,day, month,year));
            }
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Insert main date SQL exception");
        }

    }


}
