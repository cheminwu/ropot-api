package tue.student.ed.module;

import java.util.Date;

public class WateringLog {
    private Integer id;
    private Integer potId;
    private Integer scheduleId;
    private Date wateringTime;
    private Integer wateringSeconds;

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

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getWateringTime() {
        return wateringTime;
    }

    public void setWateringTime(Date wateringTime) {
        this.wateringTime = wateringTime;
    }

    public Integer getWateringSeconds() {
        return wateringSeconds;
    }

    public void setWateringSeconds(Integer wateringSeconds) {
        this.wateringSeconds = wateringSeconds;
    }
}
