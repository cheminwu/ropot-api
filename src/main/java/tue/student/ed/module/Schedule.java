package tue.student.ed.module;

import java.util.Date;

public class Schedule {
    private Integer id;
    private Integer potId;
    private Date wateringTime;
    private Integer wateringDuration = 10;
    private String status;

    public Schedule() {}

    public Schedule(Integer id, Integer potId, Date wateringTime, Integer wateringDuration, String status) {
        this.id = id;
        this.potId = potId;
        this.wateringTime = wateringTime;
        this.wateringDuration = wateringDuration;
        this.status = status;
    }

    public Schedule(Integer potId, Date wateringTime, Integer wateringDuration) {
        this.potId = potId;
        this.wateringTime = wateringTime;
        this.wateringDuration = wateringDuration;
    }

    public Schedule(Integer potId, Date wateringTime) {
        this.potId = potId;
        this.wateringTime = wateringTime;
    }

    public Schedule(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

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

    public Date getWateringTime() {
        return wateringTime;
    }

    public void setWateringTime(Date wateringTime) {
        this.wateringTime = wateringTime;
    }

    public Integer getWateringDuration() {
        return wateringDuration;
    }

    public void setWateringDuration(Integer wateringDuration) {
        this.wateringDuration = wateringDuration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
