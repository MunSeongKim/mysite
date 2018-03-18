package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDAO;
import com.cafe24.mysite.vo.BoardVO;

public class ModifyAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	Long no = Long.parseLong(request.getParameter( "no" ));
	String title = request.getParameter( "title" );
	String content = request.getParameter( "content" );
	BoardVO boardVo = new BoardVO();

	boardVo.setNo( no );
	boardVo.setTitle( title );
	boardVo.setContent( content );

	BoardDAO dao = new BoardDAO();
	dao.update( boardVo );
	
	BoardVO vo = dao.read(no);
	request.setAttribute( "result", vo );
	WebUtil.forward( request, response, "/WEB-INF/views/board/view.jsp" );
    }

}
