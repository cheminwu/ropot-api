package tue.student.ed.potapi.restful;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tue.student.ed.Util;
import tue.student.ed.module.Schedule;
import tue.student.ed.service.DeviceService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/pot")
public class PotController {

    @Autowired
    DeviceService deviceService;

    @PostMapping(
            value = "/moisture-degree.send",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String moistureDegree(@RequestBody Map params) {
        Map response = new HashMap();
        try {
            Object potId = params.get("potId");
            Object moistureDegree = params.get("moistureDegree");
            deviceService.recordMoisture((int) potId, (int) moistureDegree);
            response.put("code", 0);
            response.put("message", "data is delivered");
        } catch (Exception e) {
            response.put("code", -1);
            response.put("message", "runtime exception");
        } finally {
            return JSON.toJSONString(response);
        }
    }

    @PostMapping(
            value = "/temperature.send",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String temperature(@RequestBody Map params) {
        Map response = new HashMap();
        try {
            System.out.println("*************** temperature send ********************");
            Object potId = params.get("potId");
            Object temperature = params.get("temperature");
            //Object datetime = params.get("datetime");
            deviceService.recordTemperature((int) potId, (int) temperature);
            response.put("code", 0);
            response.put("message", "data is delivered");
        } catch (Exception e) {
            response.put("code", -1);
            response.put("message", "runtime exception");
        } finally {
            return JSON.toJSONString(response);
        }
    }


    @GetMapping(
            value = "/need-water.query",
            produces = "application/json")
    @ResponseBody
    public String needWater(@RequestParam Integer potId) {
        Map response = new HashMap();
        try {
            System.out.println("*************** need water query ********************");
            Schedule task = deviceService.findLastTask(potId);
            if (task == null) {
                response.put("code", 0);
            } else {
                if ("OVERDUE".equals(task.getStatus()) || "WATERED".equals(task.getStatus())){
                    response.put("code", 0);
                } else {

                    Date taskTime = task.getWateringTime();
                    long now = System.currentTimeMillis();
                    long timeLapse = now - taskTime.getTime();
                    if (timeLapse >= 0) {
                        if(timeLapse > 600000){
                            deviceService.updateScheduleBefore(potId, taskTime, "OVERDUE");
                        } else {
                            deviceService.updateSchedule(task.getId(), "WATERING");
                            response.put("code", 1);
                            response.put("taskId", task.getId());
                            response.put("seconds", task.getWateringDuration());
                        }
                    } else {
                        response.put("code", 0);
                    }
                }
            }
        } catch (Exception e) {
            response.put("code", -1);
            response.put("message", "runtime exception");
        } finally {
            return JSON.toJSONString(response);
        }
    }

    @PostMapping(
            value = "/water-log.send",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String waterLog(@RequestBody Map params) {
        Map response = new HashMap();
        try {
            System.out.println("*************** water log send ********************");
            Object potId = params.get("potId");
            Object taskId = params.get("taskId");
            Date datetime = new Date();
            //Object seconds = params.get("seconds");
            deviceService.recordWatering((int) potId, (int) taskId, datetime, 10);
            deviceService.updateSchedule((int) taskId, "WATERED");
            deviceService.updateScheduleBefore((int) potId, datetime, "WATERED");
            response.put("code", 0);
            response.put("message", "data is delivered");
        } catch (Exception e) {
            response.put("code", -1);
            response.put("message", "runtime exception");
        } finally {
            return JSON.toJSONString(response);
        }
    }

    @PostMapping(
            value = "/water-level.send",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String waterLevel(@RequestBody Map params) {
        Map response = new HashMap();
        try {
            System.out.println("*************** water level send ********************");
            Object potId = params.get("potId");
            deviceService.WATER_LEVEL = Integer.parseInt(params.get("waterLevel").toString());
            //Object datetime = params.get("datetime");
            response.put("code", 0);
            response.put("message", "data is delivered");
        } catch (Exception e) {
            response.put("code", -1);
            response.put("message", "runtime exception");
        } finally {
            return JSON.toJSONString(response);
        }
    }
}
