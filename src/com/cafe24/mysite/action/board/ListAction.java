package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.Pager;
import com.cafe24.mvc.util.PagerUtil;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDAO;
import com.cafe24.mysite.dto.BoardDTO;

public class ListAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	String keyword = request.getParameter( "kwd" );
	if ( keyword == null ) {
	    keyword="";
	}
	
	int pageNo = 0;
	String tmpPageNo = request.getParameter( "p" );
	if( tmpPageNo == null ){
	    pageNo = 1;
	} else {
	    pageNo = Integer.parseInt(tmpPageNo);
	}
	
	Pager pager = (Pager)request.getSession().getAttribute("pager");
	// 처음 접속때 pager 생성
	if( pager == null ){
	    pager = new Pager();
	}
	
	BoardDAO dao = new BoardDAO();
	pager = PagerUtil.setUpPager( pager, dao, pageNo, keyword );
	request.getSession().setAttribute("pager", pager);
	
	List<BoardDTO> list = dao.readAll(keyword, pager);
	
	request.setAttribute( "list", list );
	request.setAttribute( "keyword", keyword );
	WebUtil.forward( request, response, "/WEB-INF/views/board/list.jsp" );
    }

}
