package tue.student.ed.module;

public class Params {
    private Integer id;
    private String scheduleTime;
    private Integer refMoisture;
    private Integer autoMoisture;

    public Params(String scheduleTime, Integer refMoisture, Integer autoMoisture) {
        this.id = 1;
        this.scheduleTime = scheduleTime;
        this.refMoisture = refMoisture;
        this.autoMoisture = autoMoisture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Integer getRefMoisture() {
        return refMoisture;
    }

    public void setRefMoisture(Integer refMoisture) {
        this.refMoisture = refMoisture;
    }

    public Integer getAutoMoisture() {
        return autoMoisture;
    }

    public void setAutoMoisture(Integer autoMoisture) {
        this.autoMoisture = autoMoisture;
    }
}
