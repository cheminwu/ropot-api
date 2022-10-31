package tue.student.ed.potapi.restful;

import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tue.student.ed.Util;
import tue.student.ed.module.*;
import tue.student.ed.service.AppService;
import tue.student.ed.service.DeviceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "app")
public class AppController {

    @Autowired
    AppService appService;

    @Autowired
    DeviceService deviceService;

    @PostMapping(
            value = "/water.do",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String water(@RequestBody Map params) {
        Object potId = params.get("potId");
        Object seconds = params.get("seconds");
        appService.wateringNow((int) potId, seconds != null ? (int) seconds : null);
        System.out.println("command was delivered");

        Map response = new HashMap();
        response.put("code", 0);
        response.put("message", "command was delivered");
        return JSON.toJSONString(response);
    }

    @GetMapping(
            value = "/water-log.display",
            produces = "text/plain")
    @ResponseBody
    public String waterLog(
            @RequestParam Integer potId) {

        Map params = new HashMap();
        params.put("potId", potId);
        List<WateringLog> data = appService.findWateringLog(params);

        String response  = "time, duration/s \n";
        for(WateringLog log: data){
            response += Util.formatter.format(log.getWateringTime()) + ", " + log.getWateringSeconds() + "\n";
        }
        return response;
    }

    @GetMapping(
            value = "/schedule.display",
            produces = "text/plain")
    @ResponseBody
    public String schedule(
            @RequestParam Integer potId) {

        Map params = new HashMap();
        params.put("potId", potId);
        PageHelper.startPage(0, 10);
        List<Schedule> data = appService.findSchedule(params);

        String response  = "time, status \n";
        for(Schedule schedule: data){
            response += Util.formatter.format(schedule.getWateringTime()) + ", " + schedule.getStatus() + "\n";
        }
        return response;
    }

    @GetMapping(
            value = "/moisture-log.display",
            produces = "text/plain")
    @ResponseBody
    public String moistureLog(
            @RequestParam Integer potId) {

        Map params = new HashMap();
        params.put("potId", potId);
        List data = appService.findMoistureLog(params);

        Map response = new HashMap();
        response.put("code", 0);
        response.put("data", data);
        return JSON.toJSONString(response);
    }

    @GetMapping(
            value = "/temperature-log.display",
            produces = "text/plain")
    @ResponseBody
    public String temperatureLog(
            @RequestParam Integer potId) {

        Map params = new HashMap();
        params.put("potId", potId);
        List data = appService.findTemperatureLog(params);

        Map response = new HashMap();
        response.put("code", 0);
        response.put("data", data);
        return JSON.toJSONString(response);
    }

    @GetMapping(
            value = "/params.display",
            produces = "application/json")
    @ResponseBody
    public String params() {

        Params params = appService.getParams();
        return JSON.toJSONString(params);
    }

    @PostMapping(
            value = "/set-params.do",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public String setParams(@RequestBody Map params) {
        Object scheduleTimeO = params.get("scheduleTime");
        Object refMoistureO = params.get("refMoisture");
        Object autoMoistureO = params.get("autoMoisture");

        String scheduleTime = scheduleTimeO != null ? scheduleTimeO.toString() : null;
        Integer refMoisture = (refMoistureO != null) ? ("".equals(refMoistureO.toString()) ? null : Integer.valueOf(refMoistureO.toString())) : null;
        Integer autoMoisture = (autoMoistureO != null) ? ("".equals(autoMoistureO.toString()) ? null : Integer.valueOf(autoMoistureO.toString())) : null;

        appService.updateParams(new Params(scheduleTime, refMoisture, autoMoisture));
        System.out.println("params were updated");

        Map response = new HashMap();
        response.put("code", 0);
        response.put("message", "params were updated");
        return JSON.toJSONString(response);
    }

    @GetMapping(
            value = "/get-moisture.display",
            produces = "text/plain")
    @ResponseBody
    public String getMoisture(
            @RequestParam Integer potId) {

        MoistureLog log = appService.getLastMoistureLog(potId);
        String response = "";
        if(log == null){
            return response;
        }else{
            return response + log.getMoistureDegree().toString() + "%";
        }
    }

    @GetMapping(
            value = "/get-temperature.display",
            produces = "text/plain")
    @ResponseBody
    public String getTemperature(
            @RequestParam Integer potId) {

        TemperatureLog log = appService.getLastTemperatureLog(potId);
        String response = "";
        if(log == null){
            return response;
        }else{
            return response + log.getTemperature().toString() + "℃";
        }
    }

    @GetMapping(
            value = "/temperature-hourly-chart.display",
            produces = "text/plain")
    @ResponseBody
    public String temperatureHourlyChartByDay(
            @RequestParam Integer potId,
            @RequestParam String date) {
        Map params = new HashMap();
        params.put("potId", potId);
        params.put("date", date);
        List<HourlyLog> data = appService.getTemperatureHourly(params);
        String response = "";
        for(int i = 0; i < 24; i++){
            Double number = null;
            for(int j = 0; j < data.size(); j++){
                if(data.get(j).getHour() == i) {
                    number = data.get(j).getNumber();
                    response = response + data.get(j).getNumber() + ",";
                    break;
                }
            }
            if(number == null){
                response = response + ",";
            }
        }

        return response.substring(0, response.length() - 1);
    }

    @GetMapping(
            value = "/moisture-hourly-chart.display",
            produces = "text/plain")
    @ResponseBody
    public String moistureHourlyChartByDay(
            @RequestParam Integer potId,
            @RequestParam String date) {
        Map params = new HashMap();
        params.put("potId", potId);
        params.put("date", date);
        List<HourlyLog> data = appService.getMoistureHourly(params);
        String response = "";
        for(int i = 0; i < 24; i++){
            Double number = null;
            for(int j = 0; j < data.size(); j++){
                if(data.get(j).getHour() == i) {
                    number = data.get(j).getNumber();
                    response = response + data.get(j).getNumber() + ",";
                    break;
                }
            }
            if(number == null){
                response = response + ",";
            }
        }
        return response.substring(0, response.length() - 1);
    }

    @GetMapping(
            value = "/references.display",
            produces = "text/plain")
    @ResponseBody
    public String references(@RequestParam Integer type) {

        PageHelper.startPage(0, 10);
        List<PlantInstruction> data = appService.findPlantInstruction(type);

        String response  = "name, temperature, moisture \t";
        for(PlantInstruction instruction: data){
            response += instruction.getName() + ", " + instruction.getTemperature().replaceAll(" -","℃ -").concat("℃") + ", " + instruction.getMoisture().concat("%") + "\t";
        }
        return response;
    }


    @GetMapping(
            value = "/water-level.display",
            produces = "text/plain")
    @ResponseBody
    public String waterLevel(
            @RequestParam Integer potId) {
        return String.valueOf(deviceService.WATER_LEVEL);
    }


    @GetMapping(
            value = "/water-status.display",
            produces = "text/plain")
    @ResponseBody
    public String getWaterStatus(
            @RequestParam Integer potId) {
        Schedule task = deviceService.findLastTask(potId);
        if (task == null) {
            return "1";
        } else {
            if ("OVERDUE".equals(task.getStatus()) || "WATERED".equals(task.getStatus())) {
                return "1";
            } else {
                return "0";
            }
        }
    }
}
