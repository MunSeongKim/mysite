package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.Pager;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDAO;
import com.cafe24.mysite.dto.BoardDTO;

public class SearchAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	BoardDAO dao = new BoardDAO();
	List<BoardDTO> list = null;
	Pager pager = (Pager)request.getSession().getAttribute( "pager" );
	
	String keyword = request.getParameter( "kwd" );
	if ( keyword == null ) {
	    WebUtil.redirect( request, response, "/mysite/board" );
	    return ;
	}
	
	
		
	list = dao.readAll( keyword, pager );
	request.setAttribute( "list", list );
	request.setAttribute( "key", keyword );
	WebUtil.forward( request, response, "/WEB-INF/views/board/list.jsp" );
    }

}
