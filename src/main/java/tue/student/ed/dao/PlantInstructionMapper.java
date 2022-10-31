package tue.student.ed.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.ed.module.HourlyLog;
import tue.student.ed.module.MoistureLog;
import tue.student.ed.module.PlantInstruction;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlantInstructionMapper {

    List<PlantInstruction> find(int type);
}
