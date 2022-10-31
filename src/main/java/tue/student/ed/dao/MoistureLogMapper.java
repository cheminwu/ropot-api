package tue.student.ed.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.ed.module.HourlyLog;
import tue.student.ed.module.MoistureLog;
import tue.student.ed.module.Schedule;

import java.util.List;
import java.util.Map;

@Mapper
public interface MoistureLogMapper {

    void save(MoistureLog log);

    List<MoistureLog> find(Map map);

    MoistureLog getLast(Integer potId);

    List<HourlyLog> getHourly(Map map);
}
