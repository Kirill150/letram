package Objects;

/**
 * Created by Papai on 2016.09.26..
 */
public class TramIdShiftHours {

    private String tramId;
    private String shift;
    private String hours;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    private boolean holiday;

    public String getTramId() {
        return tramId;
    }

    public void setTramId(String tramId) {
        this.tramId = tramId;
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
