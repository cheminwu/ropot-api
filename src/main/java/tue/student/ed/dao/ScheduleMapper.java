package tue.student.ed.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.ed.module.Schedule;
import tue.student.ed.module.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScheduleMapper {

    void save(Schedule schedule);

    void update(Schedule schedule);

    List<Schedule> find(Map map);

    Schedule findLastTask(int potId);

    void updateBefore(Schedule schedule);
}
