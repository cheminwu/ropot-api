package tue.student.ed.dao;

import org.apache.ibatis.annotations.Mapper;
import tue.student.ed.module.User;

import java.util.List;

@Mapper
public interface UserMapper {

    void save(User user);

    void update(User user);

    List<User> find(String name);
}
