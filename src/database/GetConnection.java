package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GetConnection {


    private static Connection c = null;

    private GetConnection(){}

    public static Connection getConnection(){

            c = createConnection();
        return c;
    }

    private static Connection createConnection() {

        try {


            try {
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/letram", "tram", "pass");

            } catch (ClassNotFoundException e) {
                System.out.print("ClassNotFoundException");
            } catch (SQLException e) {
                System.out.print("SQLException");
            }

        } catch(Exception e){
            System.out.print(e.getLocalizedMessage());
        }

        return c;

    }
}