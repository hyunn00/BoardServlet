package kroryi.pro07.Service;

import kroryi.pro07.DAO.UserDAO;
import kroryi.pro07.DTO.User;

import javax.naming.NamingException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAO();

    public UserServiceImpl() throws NamingException {
    }

    @Override
    public List<User> list() {
        List<User> userList = (List<User>)userDAO.selectList();
        return userList;
    }
    @Override
    public User select(String uid) {
        User user = userDAO.select(uid);
        return user;
    }

    @Override
    public int insert(User user) {
        int result = userDAO.insert(user);
        return result;
    }

    @Override
    public int update(User user) {
        int result = userDAO.update(user);
        return result;
    }

    @Override
    public int delete(int uid) {
        int result = userDAO.delete(uid);
        return result;
    }

}
