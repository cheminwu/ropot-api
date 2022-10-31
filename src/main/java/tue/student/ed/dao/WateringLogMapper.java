package tue.student.ed.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.ed.module.MoistureLog;
import tue.student.ed.module.WateringLog;

import java.util.List;
import java.util.Map;

@Mapper
public interface WateringLogMapper {

    void save(WateringLog log);

    List<WateringLog> find(Map map);
}
