package kroryi.pro07.DAO;

import kroryi.pro07.DTO.Board;
import kroryi.pro07.DTO.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDAO {
    Context initContext = new InitialContext();
    Context envContext = (Context) initContext.lookup("java:comp/env");
    DataSource dataSource = (DataSource) envContext.lookup("jdbc/MyDB");

    public UserDAO() throws NamingException {
    }

    public List<User> selectList() {
        Connection con;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        LinkedList<User> userList = new LinkedList<>();
        String sql = " SELECT * "
                + " FROM User "
                + " ORDER BY uid desc ";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                User user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUserid(rs.getString("userid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                userList.add(user);
            }
        } catch (Exception e) {
            System.out.println("게시글 목록 조회시 예외 발생");
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userList;
    }

    public int insert(User user) {
        Connection con;
        PreparedStatement psmt = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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
        } finally {
            try {
                if(psmt != null) {
                    psmt.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public User select(String uid){
        Connection con;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        User user = new User();
        String sql = "SELECT * "
                + " FROM User"
                + " WHERE uid = ?";

        try{
            psmt = con.prepareStatement(sql);
            psmt.setString(1, uid);
            rs = psmt.executeQuery();

            if(rs.next()) {
                user.setUid(Integer.parseInt(rs.getString("uid")));
                user.setUserid(rs.getString("userid"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            } else {
                System.out.println(uid + " 사용자가 없습니다.");
                uid=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(psmt != null) {
                    psmt.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public int update(User user){
        Connection con;
        PreparedStatement psmt = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int result = 0;
        String sql = " UPDATE User "
                    + " SET username = ? "
                    + " WHERE userid = ? ";

        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("UpdateSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getUserid());
            result = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch (SQLException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                System.out.println("회원 정보 수정시 예외 발생");
                e1.printStackTrace();
            }

            e.printStackTrace();
        } finally {
            try {
                if(psmt != null) {
                    psmt.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public int delete(int uid){
        Connection con;
        PreparedStatement psmt = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int result = 0;
        String sql = " DELETE FROM User "
                + " WHERE uid = ? ";

        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("DeleteSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setInt(1, uid);

            result  = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch (SQLException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                System.out.println("회원 삭제시 예외 발생");
                e1.printStackTrace();
            }

            e.printStackTrace();
        }  finally {
            try {
                if(psmt != null) {
                    psmt.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}