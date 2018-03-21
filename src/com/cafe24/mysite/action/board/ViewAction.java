package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDAO;
import com.cafe24.mysite.dao.CommentDAO;
import com.cafe24.mysite.dto.CommentDTO;
import com.cafe24.mysite.vo.BoardVO;

public class ViewAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	String tmpNo = request.getParameter( "no" );
	if ( tmpNo == null ) {
	    WebUtil.redirect( request, response, "/mysite/board" );
	    return;
	}

	Long no = Long.parseLong( tmpNo );
	BoardDAO boardDao = new BoardDAO();
	
	// increse hit count
	boardDao.update( no );
	BoardVO boardVo = boardDao.read( no );
	
	List<CommentDTO> commentList = new CommentDAO().readAll( no );
	
	request.setAttribute( "board", boardVo );
	request.setAttribute( "commentList", commentList ); 
	WebUtil.forward( request, response, "/WEB-INF/views/board/view.jsp" );
    }
}
