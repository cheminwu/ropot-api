package tue.student.ed.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tue.student.ed.Util;
import tue.student.ed.dao.MoistureLogMapper;
import tue.student.ed.dao.ParamsMapper;
import tue.student.ed.dao.ScheduleMapper;
import tue.student.ed.module.MoistureLog;
import tue.student.ed.module.Params;
import tue.student.ed.module.Schedule;

import javax.annotation.Resource;
import java.util.*;

@Component
public class ScheduleService {

    @Resource
    ParamsMapper paramsMapper;
    @Resource
    MoistureLogMapper moistureLogMapper;
    @Resource
    ScheduleMapper scheduleMapper;

    @Scheduled(cron = "*/5 * * * * ?")
    public void task1(){
        Params params = paramsMapper.get();
        if (params.getAutoMoisture() == 2){
            Integer refMoisture = params.getRefMoisture();
            MoistureLog lastLog = moistureLogMapper.getLast(1);
            Date logTime = lastLog.getDatetime();
            long now = System.currentTimeMillis();
            long timeLapse = now - logTime.getTime();
            if(timeLapse <= 600000){
                if(lastLog.getMoistureDegree() < refMoisture) {
                    addNewTask();
                }
            }
        }
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void task2(){
        Params params = paramsMapper.get();
        if (params.getAutoMoisture() == 3){
            String refTime = params.getScheduleTime();
            if(Util.formatter2.format(new Date()).equals(refTime)){
                addNewTask();
            }
        }
    }

    private void addNewTask(){
        Schedule lastSchedule = scheduleMapper.findLastTask(1);
        if("WATERED".equals(lastSchedule.getStatus()) || "COMPLETE".equals(lastSchedule.getStatus()) || "OVERDUE".equals(lastSchedule.getStatus())){
            System.out.println("---------add new task");
            Schedule schedule = new Schedule();
            schedule.setPotId(1);
            schedule.setWateringDuration(5);
            schedule.setWateringTime(new Date());
            schedule.setStatus("PENDING");
            scheduleMapper.save(schedule);
        }
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void task3(){
        Map param1 = new HashMap();
        param1.put("status","WATERING");
        List<Schedule> tasks1 = scheduleMapper.find(param1);
        Map param2 = new HashMap();
        param2.put("status","PENDING");
        List<Schedule> tasks2 = scheduleMapper.find(param2);

        List<Schedule> tasks = new ArrayList<>();
        tasks.addAll(tasks1);
        tasks.addAll(tasks2);
        for (int i = 0; i < tasks.size(); i++){
            Schedule task = tasks.get(i);
            Date taskTime = task.getWateringTime();
            long now = System.currentTimeMillis();
            long timeLapse = now - taskTime.getTime();
            if (timeLapse > 600000) {
                Schedule schedule = new Schedule();
                schedule.setId(task.getId());
                schedule.setStatus("OVERDUE");
                scheduleMapper.update(schedule);
            }
        }
    }
}
