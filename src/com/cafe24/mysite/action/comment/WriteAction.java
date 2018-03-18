package com.cafe24.mysite.action.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.CommentDAO;
import com.cafe24.mysite.vo.CommentVO;
import com.cafe24.mysite.vo.UserVO;

public class WriteAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	String content = request.getParameter( "content" );
	String bno = request.getParameter( "bno" );
	String pageNo = request.getParameter( "p" );
	if( bno == null ) {
	    WebUtil.redirect( request, response, "/mysite/board" );
	    return ;
	}
	
	if( "".equals(content) ){
	    WebUtil.redirect( request, response, "/mysite/board?a=view&no=" + bno + "&p=" + pageNo );
	    return ;
	}
	
	Long userNo = ((UserVO)request.getSession().getAttribute( "authUser" )).getNo();
	Long boardNo = Long.parseLong(bno);

	CommentVO vo = new CommentVO();
	vo.setContent( content );
	vo.setBoardNo( boardNo );
	vo.setUserNo( userNo );
	
	new CommentDAO().create( vo );
	
	WebUtil.redirect( request, response, "/mysite/board?a=view&no=" + bno + "&p=" + pageNo );
    }

}
