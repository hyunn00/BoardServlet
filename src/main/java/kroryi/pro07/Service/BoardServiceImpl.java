package kroryi.pro07.Service;

import kroryi.pro07.DAO.BoardDAO;
import kroryi.pro07.DTO.Board;

import javax.naming.NamingException;
import java.util.List;

public class BoardServiceImpl implements BoardService {
    private BoardDAO boardDAO = new BoardDAO();

    public BoardServiceImpl() throws NamingException {
    }

    public List<Board> list() {
        List<Board> boardList = (List<Board>)boardDAO.selectList();
        return boardList;
    }

    @Override
    public Board select(int board_no) {
        Board board = boardDAO.select(board_no);
        return board;
    }

    @Override
    public int insert(Board board) {
        int result = boardDAO.insert(board);
        return result;
    }

    @Override
    public int update(Board board) {
        int result = boardDAO.update(board);
        return result;
    }

    @Override
    public int delete(int board_no) {
        int result = boardDAO.delete(board_no);
        return result;
    }

    @Override
    public List<Board> pageList(int pageNo) {
        List<Board> boardList = boardDAO.selectPageList(pageNo);
        return boardList;
    }

    @Override
    public int totalListCount() {
        int count =boardDAO.selectTotalCount();
        return count;
    }
}
