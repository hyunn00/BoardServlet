package kroryi.pro07.Service;

import kroryi.pro07.DAO.UserDAO;
import kroryi.pro07.DTO.User;

import javax.naming.NamingException;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAO();

    public UserServiceImpl() throws NamingException {
    }

    @Override
    public User select(String userid) {
        User user = userDAO.select(userid);
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
    public int delete(String userid) {
        int result = userDAO.delete(userid);
        return result;
    }

}
