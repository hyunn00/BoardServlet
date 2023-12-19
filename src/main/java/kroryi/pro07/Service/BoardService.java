package kroryi.pro07.Service;

import kroryi.pro07.DTO.Board;


import java.util.List;

public interface BoardService {
    List<Board> list();
    Board select(int board_no);
    int insert(Board board);
    int update(Board board);
    int delete(int board_no);
    List<Board> pageList(int pageNo);
    int totalListCount();

}
