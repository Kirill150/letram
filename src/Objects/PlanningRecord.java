package Objects;


import java.util.Map;

/**
 * Created by Papai on 2016.08.17..
 */
public class PlanningRecord {

    private String driverId;

    private Map<String, TramIdShiftHours> hoursPerDaymap;

    private int totalHours;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Map<String, TramIdShiftHours> getHoursPerDaymap() {
        return hoursPerDaymap;
    }

    public void setHoursPerDaymap(Map<String, TramIdShiftHours> map) {
        this.hoursPerDaymap = map;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }
}
