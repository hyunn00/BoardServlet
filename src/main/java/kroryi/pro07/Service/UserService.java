package kroryi.pro07.Service;

import kroryi.pro07.DTO.User;

import java.util.List;

public interface UserService {
    List<User> list();
    User select(String userid);
    int insert(User user);
    int update(User user);
    int delete(int uid);
}
