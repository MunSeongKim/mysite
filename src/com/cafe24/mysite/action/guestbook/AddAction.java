package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.GuestbookDAO;
import com.cafe24.mysite.vo.GuestbookVO;
import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;

public class AddAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	String name = request.getParameter( "name" );
	String password = request.getParameter( "password" );
	String content = request.getParameter( "content" );

	GuestbookVO vo = new GuestbookVO();
	vo.setName( name );
	vo.setPassword( password );
	vo.setContent( content );

	if ( (name != "") && (password != "") && (content != "") ) {
	    GuestbookDAO dao = new GuestbookDAO();
	    dao.create( vo );
	}

	WebUtil.redirect( request, response, "/mysite/guestbook" );
    }
}
