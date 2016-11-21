package database;

import Objects.PlanningRecord;
import Objects.TramIdShiftHours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Papai on 2016.11.01..
 */
public class PlanningRepo {

    private final String INSERT = "INSERT INTO plan " +
                                                  "(day, month, year, driver_id, tram_id, hours, shift, holiday) " +
                                                  "VALUES('%d','%d','%d','%s','%s','%s','%s','%s')";

    private final String UPDATE = " UPDATE plan " +
                                  " SET tram_id = '%s', hours = '%s', shift = '%s' "+
                                  " WHERE driver_id = '%s' " +
                                  " and day='%d' and month = '%d' and year = '%d' "+
                                  " and tram_id = '%s' and hours='%s' and shift = '%s'";

    private final String GET_NAMES = "SELECT name FROM drivers";

    private final String GET_RECORDS = "SELECT * FROM plan where driver_id = '%s' and month = %d and year = %d";

    private final String GET_RECORD = "SELECT * FROM plan where day = %d AND month = %d AND year = %d AND driver_id = '%s' " +
            "AND tram_id = '%s' and hours = '%s' ";

    public  ObservableList<PlanningRecord> getRecords(Integer month, Integer year) {
        HashMap<String, TramIdShiftHours> map = new HashMap<>();
        PlanningRecord record = new PlanningRecord();
        List<PlanningRecord> records = new LinkedList<>();
        Connection c = GetConnection.getConnection();
        Statement stmt2 = null;

        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(GET_NAMES);
            while (rs.next()) {

                record.setDriverId(rs.getString("name"));
                stmt2 = c.createStatement();
                ResultSet rs2 = stmt2.executeQuery(String.format(GET_RECORDS, record.getDriverId(),month,year));

                while (rs2.next()) {
                    TramIdShiftHours rec = new TramIdShiftHours();
                    rec.setShift(rs2.getString("shift"));
                    rec.setHoliday(rs2.getString("holiday"));
                    rec.setHours(rs2.getString("hours"));
                    record.setTotalHours(record.getTotalHours() + Integer.parseInt(rs2.getString("hours")));
                    rec.setTramId(rs2.getString("tram_id"));
                    Integer s = rs2.getInt("day");
                    map.put(s.toString(), rec);
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

    public void updateRecord(Integer day, Integer month, Integer year,
                             String driver,
                             String oldTram, String oldHours, String oldShift,
                             String newTram, String newHours, String newShift){

        try {
            Connection c = GetConnection.getConnection();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(String.format(UPDATE,newTram, newHours, newShift, driver, day, month, year, oldTram, oldHours, oldShift));
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("update planning record SQL exception");
        }

    }

    public void insertRecord(Integer day, Integer month, Integer year,
                             String driver,
                             String tram, String hours, String shift,String holiday){

        try {
            Connection c = GetConnection.getConnection();
            Statement stmt = c.createStatement();
            stmt.executeUpdate(String.format(INSERT, day, month, year, driver, tram, hours, shift,holiday));
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            System.out.println("insert planning record SQL exception");
        }

    }

//    public PlanningRecord getRecord(Integer day, Integer month, Integer year,
//                                    String driver,
//                                    String tram, String hours){
//
//        PlanningRecord record = new PlanningRecord();
//        HashMap<String, TramIdShiftHours> map = new HashMap<>();
//        TramIdShiftHours rec = new TramIdShiftHours();
//        try {
//
//            Connection c = GetConnection.getConnection();
//            Statement stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery(String.format(GET_RECORD,day,month,year,driver,tram,hours));
//            while (rs.next()) {
//
//                record.setDriverId(rs.getString("name"));
//                rec.setTramId();
//            }
//        }catch(Exception e){
//            System.out.println("Get Record SQL ex");
//        }
//
//        return  record;
//    }
}
