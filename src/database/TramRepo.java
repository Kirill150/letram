package database;

import Objects.Tram;
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
public class TramRepo {

    private final String UPDATE = "Update trams " +
                                  "SET Number='%s', "+
                                  "id='%s', "+
                                  "color='%s' "+
                                  "Where id ='%s'";

    private final String INSERT = "INSERT INTO trams "
            + "(Number,Id,Color) "
            + "VALUES"+"('%s','%s','%s')";

    private final String DELETE  = "Delete from trams where id = '%s'";

    private final String GET_TRAMS = "SELECT * FROM trams";

    private final String GET_TRAM = "SELECT * FROM trams WHERE id = '%d'";

    public ObservableList<Tram> getTrams(){

        Tram tram = new Tram();
        List<Tram> trams = new LinkedList<>();
        Connection c = GetConnection.getConnection();

        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(GET_TRAMS);
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

    public Tram getTram(String id) {

        Tram tram = new Tram();
        Connection c = GetConnection.getConnection();
        Statement stmt = null;


        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(GET_TRAM,Integer.valueOf(id)));

            while(rs.next()) {

                tram.setId(String.valueOf(rs.getInt("id")));
                tram.setNumber(String.valueOf(rs.getString("number")));
                tram.setColor(rs.getString("color"));

            }

        } catch (SQLException e) {
            System.out.println("Get Tram sql exception");
        }

        return tram;
    }

    public void insert(String number,String id, String color){

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(String.format(INSERT,number, id, color));
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Insert Tram SQL exception");
        }

    }

    public void update(String number,String id,String color, String oldId){

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(String.format(UPDATE,number, id, color,oldId));
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Insert Tram SQL exception");
        }

    }

    public void delete(String id){

        Connection c = GetConnection.getConnection();
        Statement stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(String.format(DELETE, id));
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("Insert Tram SQL exception");
        }
    }



}
