package tue.student.ed.module;

import java.util.Date;

public class TemperatureLog {
    private Integer id;
    private Integer potId;
    private Integer temperature;
    private Date datetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPotId() {
        return potId;
    }

    public void setPotId(Integer potId) {
        this.potId = potId;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
