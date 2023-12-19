package kroryi.pro07.DAO;

import kroryi.pro07.DTO.User;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Savepoint;

public class UserDAO extends JDBCConnection{

    public UserDAO() throws NamingException {
    }

    public int insert(User user){
        int result = 0;
        String sql = " INSERT INTO User(userid, username, password) "
                    + " VALUES(?, ?, ?) ";
        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("insertSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setString(1, user.getUserid());
            psmt.setString(2, user.getUsername());
            psmt.setString(3, user.getPassword());
            result = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch(SQLException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("회원 가입 시, 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    public User select(String userid){
        User user = new User();
        String sql = "SELECT * "
                + " FROM User"
                + " WHERE userid = ?";

        try{
            psmt = con.prepareStatement(sql);
            psmt.setString(1, userid);
            rs = psmt.executeQuery();

            if(rs.next()) {
                user.setUserid(rs.getString("userid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            } else {
                System.out.println(userid + " 사용자가 없습니다.");
                userid=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int update(User user){

        return 0;
    }

    public int delete(String userid){

        return 0;
    }
}