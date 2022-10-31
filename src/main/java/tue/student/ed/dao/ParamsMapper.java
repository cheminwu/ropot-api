package tue.student.ed.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.ed.module.Params;
import tue.student.ed.module.User;

import java.util.List;

@Mapper
public interface ParamsMapper {

    void update(Params params);

    Params get();
}
