package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDAO;
import com.cafe24.mysite.dto.BoardDTO;

public class ModifyFormAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	String tmpNo = request.getParameter( "no" );
	if ( tmpNo == null ) {
	    WebUtil.redirect( request, response, "/mysite/board" );
	    return;
	}
	
	Long no = Long.parseLong( tmpNo );
	BoardDTO dto = new BoardDAO().read( no );
	request.setAttribute( "result", dto );
	request.setAttribute( "action", "modify" );
	WebUtil.forward( request, response, "/WEB-INF/views/board/write.jsp" );
    }

}
