package kroryi.pro07.DTO;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class Board {
    private  int board_no;
    private String title;
    private String writer;
    private String content;
    private File file;
    private InputStream isfile;
    private String reg_date;
    private String upd_date;

    public Board() {
        this("제목 없음", "작성자 없음", "내용 없음");
    }

    public Board(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public Board(String title, String writer, String content, File file) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.file = file;
    }

    public int getBoard_no() {
        return board_no;
    }

    public void setBoard_no(int board_no) {
        this.board_no = board_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public InputStream getIsfile() {
        return isfile;
    }

    public void setIsfile(InputStream isfile) {
        this.isfile = isfile;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getUpd_date() {
        return upd_date;
    }

    public void setUpd_date(String upd_date) {
        this.upd_date = upd_date;
    }


    @Override
    public String toString() {
        return "Board{" +
                "board_no=" + board_no +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", content='" + content + '\'' +
                ", reg_date=" + reg_date +
                ", upd_date=" + upd_date +
                '}';
    }
}
