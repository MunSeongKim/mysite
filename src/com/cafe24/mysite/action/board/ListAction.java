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
	BoardDAO dao = new BoardDAO();
	List<BoardDTO> list = null;
	int pageNo = 0;
	Pager pager = (Pager)request.getSession().getAttribute("pager");
	String tmpPageNo = request.getParameter( "p" );
	if( tmpPageNo == null ){
	    pageNo = 1;
	} else {
	    pageNo = Integer.parseInt(tmpPageNo);
	}
	
	// 처음 접속때 pager 생성
	if( pager == null ){
	    pager = new Pager();
	}
	
	pager = PagerUtil.setCurrentPageNumber( pager, pageNo );
	pager = PagerUtil.setStartPostNumber( pager, dao );
	pager = PagerUtil.setTotalPageCount( pager, dao );
	pager = PagerUtil.setNavigator(pager);
	
	list = dao.readAll(pager);

	request.getSession().setAttribute("pager", pager);
	request.setAttribute( "list", list );
	WebUtil.forward( request, response, "/WEB-INF/views/board/list.jsp" );
    }

}
