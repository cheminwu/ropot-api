package tue.student.ed.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.ed.module.HourlyLog;
import tue.student.ed.module.MoistureLog;
import tue.student.ed.module.TemperatureLog;

import java.util.List;
import java.util.Map;

@Mapper
public interface TemperatureLogMapper {

    void save(TemperatureLog log);

    List<TemperatureLog> find(Map map);

    TemperatureLog getLast(Integer potId);

    List<HourlyLog> getHourly(Map map);
}
