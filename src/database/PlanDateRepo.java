package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Papai on 2016.11.01..
 */
public class PlanDateRepo {

    private final String SELECT_PLAN_DATE = "SELECT * from plan_date";

    private final String UPDATE_PLAN_DATE = "UPDATE plan_date SET month='%d', year = '%d'";

    private final String INSERT_PLAN_DATE = "INSERT INTO plan_date (month,year) VALUES ('%d','%d')";


    public void insert(Integer month, Integer year) {

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_PLAN_DATE);
            System.out.println("selected");
            if(rs.next()){
                System.out.println(String.format(UPDATE_PLAN_DATE, month,year));
                stmt.executeQuery(String.format(UPDATE_PLAN_DATE, month,year));
            }else{

                System.out.println(String.format(INSERT_PLAN_DATE, month,year));
                stmt.executeQuery(String.format(INSERT_PLAN_DATE, month,year));
            }
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Insert plan date SQL exception "+ex.getSQLState());
        }

    }
}
