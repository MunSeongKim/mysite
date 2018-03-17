package com.cafe24.mysite.dto;
import com.cafe24.mysite.vo.BoardVO;

public class BoardDTO {
    private BoardVO vo;
    private String username;
    
    public BoardVO getVo() {
        return vo;
    }
    public void setVo( BoardVO vo ) {
        this.vo = vo;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername( String username ) {
        this.username = username;
    }
    @Override
    public String toString() {
	return "BoardDTO [vo=" + vo.toString() + ", username=" + username + "]";
    }

}
