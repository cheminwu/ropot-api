package tue.student.ed.service;

import org.springframework.stereotype.Service;
import tue.student.ed.dao.*;
import tue.student.ed.module.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AppService {

    @Resource
    ScheduleMapper scheduleMapper;
    @Resource
    WateringLogMapper wateringLogMapper;
    @Resource
    MoistureLogMapper moistureLogMapper;
    @Resource
    TemperatureLogMapper temperatureLogMapper;
    @Resource
    ParamsMapper paramsMapper;
    @Resource
    PlantInstructionMapper plantInstructionMapper;

    public void wateringNow(Integer potId, Integer seconds){
        if(seconds == null) {
            scheduleMapper.save(new Schedule(potId, new Date()));
        } else {
            scheduleMapper.save(new Schedule(potId, new Date(), seconds));
        }
    }

    public void updateSchedule(Schedule schedule){
        scheduleMapper.update(schedule);
    }


    public List<Schedule> findSchedule(Map map){
        List<Schedule> schedules = scheduleMapper.find(map);
        return schedules;
    }
    public List<WateringLog> findWateringLog(Map map){
        List<WateringLog> logs = wateringLogMapper.find(map);
        return logs;
    }

    public List<MoistureLog> findMoistureLog(Map map){
        List<MoistureLog> logs = moistureLogMapper.find(map);
        return logs;
    }
    public List<TemperatureLog> findTemperatureLog(Map map){
        List<TemperatureLog> logs = temperatureLogMapper.find(map);
        return logs;
    }

    public Params getParams(){
        return paramsMapper.get();
    }

    public void updateParams(Params params){
        paramsMapper.update(params);
    }

    public MoistureLog getLastMoistureLog(Integer potId){
        return moistureLogMapper.getLast(potId);
    }
    public TemperatureLog getLastTemperatureLog(Integer potId){
        return temperatureLogMapper.getLast(potId);
    }

    public List<HourlyLog> getTemperatureHourly(Map map){
        return temperatureLogMapper.getHourly(map);
    }

    public List<HourlyLog> getMoistureHourly(Map map){
        return moistureLogMapper.getHourly(map);
    }

    public List<PlantInstruction> findPlantInstruction(int type){
        return plantInstructionMapper.find(type);
    }
}
