package kroryi.pro07.DAO;

import kroryi.pro07.DTO.Board;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class BoardDAO {
    Context initContext = new InitialContext();
    Context envContext = (Context) initContext.lookup("java:comp/env");
    DataSource dataSource = (DataSource) envContext.lookup("jdbc/MyDB");
    private FileInputStream fis;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public BoardDAO() throws NamingException {
    }

    public int insert(Board board){
        Connection con;
        PreparedStatement psmt = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int result = 0;
        String sql;
        if(board.getFile() != null)
            sql = " INSERT INTO Board(title, writer, content, file) "
                    + " VALUES(?, ?, ?, ?) ";
        else
            sql = " INSERT INTO Board(title, writer, content) "
                    + " VALUES(?, ?, ?) ";
        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("insertSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setString(1, board.getTitle());
            psmt.setString(2, board.getWriter());
            psmt.setString(3, board.getContent());

            if(board.getFile() != null) {
                fis = new FileInputStream(board.getFile());
                psmt.setBinaryStream(4, (InputStream)fis, board.getFile().length());
            }
            result = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch(SQLException | FileNotFoundException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("게시글 등록 시, 예외 발생");
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

    public List<Board> selectList() {
        Connection con;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        LinkedList<Board> boardList = new LinkedList<>();
        String sql = " SELECT * "
                + " FROM Board "
                + " ORDER BY board_no desc ";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                Board board = new Board();
                board.setBoard_no(rs.getInt("board_no"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setReg_date(dateFormat.format(rs.getTimestamp("reg_date")));
                board.setUpd_date(dateFormat.format(rs.getTimestamp("upd_date")));
                boardList.add(board);
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


        return boardList;
    }

    public Board select(int board_no) {
        Connection con;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Board board = new Board();
        String sql = " SELECT * "
                + " FROM Board "
                + " WHERE board_no = ? ";

        try {
            psmt = con.prepareStatement(sql);
            psmt.setInt(1, board_no);
            rs = psmt.executeQuery();

            if(rs.next()) {
                board.setBoard_no(rs.getInt("board_no"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setReg_date(dateFormat.format(rs.getTimestamp("reg_date")));
                board.setUpd_date(dateFormat.format(rs.getTimestamp("upd_date")));
                InputStream is = rs.getBinaryStream("file");
                board.setIsfile(is);

//                OutputStream os = new FileOutputStream(new File("photo.jpg"));
//                byte[] fileBuffer = new byte[1024];
//                int size = 0;
//                while((size = is.read(fileBuffer)) != -1) {
//                    os.write(fileBuffer, 0, size);
//                }
//                os.close();
//                is.close();
//                File file = new File("file:photo.jpg");
//                board.setFile(file);

            } else {
                System.out.println(board_no + "번 게시물은 존재하지 않습니다.");
                board = null;
            }
        } catch(Exception e) {
            System.out.println("게시글 상세 조회시 예외 발생");
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

        return board;
    }

    public int update(Board board) {
        Connection con;
        PreparedStatement psmt = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int result = 0;
        String sql;
        if(board.getFile() != null) {
             sql = " UPDATE Board "
                    + " SET title = ? "
                    + " , writer = ? "
                    + " , content = ? "
                    + ", file = ? "
                    + " , upd_date = now() "
                    + " WHERE board_no = ? ";
        } else {
            sql = " UPDATE Board "
                    + " SET title = ? "
                    + " , writer = ? "
                    + " , content = ? "
                    + " , upd_date = now() "
                    + " WHERE board_no = ? ";
        }

        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("UpdateSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setString(1, board.getTitle());
            psmt.setString(2, board.getWriter());
            psmt.setString(3, board.getContent());
            if(board.getFile()!=null){
                fis = new FileInputStream(board.getFile());
                psmt.setBinaryStream(4, (InputStream)fis, board.getFile().length());
                psmt.setInt(5, board.getBoard_no());
            } else {
                psmt.setInt(4, board.getBoard_no());
            }
            result = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch (SQLException | FileNotFoundException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                System.out.println("게시글 수정시 예외 발생");
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

    public int delete(int board_no) {
        Connection con;
        PreparedStatement psmt = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int result = 0;
        String sql = " DELETE FROM Board "
                + " WHERE board_no = ? ";

        Savepoint savepoint = null;
        try {
            savepoint = con.setSavepoint("DeleteSavePoint");
            psmt = con.prepareStatement(sql);
            psmt.setInt(1, board_no);

            result  = psmt.executeUpdate();

            if(result > 0) {
                con.commit();
            }
        } catch (SQLException e) {
            try {
                con.rollback(savepoint);
            } catch (SQLException e1) {
                System.out.println("게시글 삭제시 예외 발생");
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

    public int selectTotalCount() {
        Connection con;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = " SELECT count(*) "
                + " FROM Board "
                + " ORDER BY board_no DESC ";
        int count = 0;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                count = rs.getInt(1);
            }
            System.out.println("총 게시물 : " + count);
        } catch (SQLException e) {
            System.out.println("총 게시물 개수 조회시 오류 발생");
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null) {
                    stmt.close();
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public List<Board> selectPageList(int pageNo) {
        Connection con;
        ResultSet rs = null;
        PreparedStatement psmt = null;
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        LinkedList<Board> boardList = new LinkedList<>();
        String sql = " SELECT * "
                + " FROM Board "
                + " ORDER BY board_no DESC LIMIT ? OFFSET ? ";
        try {
            psmt = con.prepareStatement(sql);
            rs = psmt.executeQuery();
            while(rs.next()) {
                Board board = new Board();
                board.setBoard_no(rs.getInt("board_no"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setReg_date(dateFormat.format(rs.getTimestamp("reg_date")));
                board.setUpd_date(dateFormat.format(rs.getTimestamp("upd_date")));
                boardList.add(board);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        return boardList;
    }
}

