package tue.student.ed.service;

import org.springframework.stereotype.Service;
import tue.student.ed.dao.*;
import tue.student.ed.module.*;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class DeviceService {

    @Resource
    ScheduleMapper scheduleMapper;

    @Resource
    MoistureLogMapper moistureLogMapper;
    @Resource
    TemperatureLogMapper temperatureLogMapper;
    @Resource
    public WateringLogMapper wateringLogMapper;

    public static Integer WATER_LEVEL = 1;

    public Schedule findLastTask(int potId){

        Schedule task = scheduleMapper.findLastTask(potId);
        return task;
    }


    public void recordMoisture(Integer potId, Integer moistureDegree){
        MoistureLog log = new MoistureLog();
        log.setPotId(potId);
        log.setMoistureDegree(moistureDegree);
        log.setDatetime(new Date());
        moistureLogMapper.save(log);
    }

    public void recordTemperature(Integer potId, Integer temperature){
        TemperatureLog log = new TemperatureLog();
        log.setPotId(potId);
        log.setTemperature(temperature);
        log.setDatetime(new Date());
        temperatureLogMapper.save(log);
    }

    public void recordWatering(Integer potId, Integer scheduleId, Date wateringTime, Integer wateringSeconds){
        WateringLog log = new WateringLog();
        log.setPotId(potId);
        log.setScheduleId(scheduleId);
        log.setWateringTime(wateringTime);
        log.setWateringSeconds(wateringSeconds);
        wateringLogMapper.save(log);
    }

    public void updateSchedule(Integer scheduleId, String status){
        scheduleMapper.update(new Schedule(scheduleId, status));
    }

    public void updateScheduleBefore(Integer potId, Date time, String status){
        Schedule schedule = new Schedule();
        schedule.setPotId(potId);
        schedule.setWateringTime(time);
        schedule.setStatus(status);
        scheduleMapper.updateBefore(schedule);
    }

}
