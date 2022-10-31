package tue.student.ed.module;

import java.util.Date;

public class MoistureLog {
    private Integer id;
    private Integer potId;
    private Integer moistureDegree;
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

    public Integer getMoistureDegree() {
        return moistureDegree;
    }

    public void setMoistureDegree(Integer moistureDegree) {
        this.moistureDegree = moistureDegree;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
