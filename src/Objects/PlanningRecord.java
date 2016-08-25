package Objects;

/**
 * Created by Papai on 2016.08.17..
 */
public class PlanningRecord {

    private String driverId;
    private String tramId;
    private String datums;
    private String hours;
    private String shift;
    private boolean holiday;


    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getTramId() {
        return tramId;
    }

    public void setTramId(String tramId) {
        this.tramId = tramId;
    }

    public String getDatums() {
        return datums;
    }

    public void setDatums(String datums) {
        this.datums = datums;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }
}
