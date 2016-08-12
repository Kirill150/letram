package Objects;

import java.util.LinkedList;

/**
 * Created by Papai on 2016.08.03..
 */
public class Driver {

    private String name;
    private String surname;
    private String code;
    private Integer TotalHours;
    private String Tram;
    private LinkedList<Date> Dates;

    public String getName() {
        return name;
    }

    public void setName(String vards) {
        this.name = vards;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String uzvards) {
        this.surname = uzvards;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getTotalHours() {
        return TotalHours;
    }

    public void setTotalHours(Integer total) {
        TotalHours = total;
    }

    public String getTram() {
        return Tram;
    }

    public void setTram(String tram) {
        Tram = tram;
    }

}
