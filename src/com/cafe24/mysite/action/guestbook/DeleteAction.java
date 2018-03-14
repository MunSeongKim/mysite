package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.GuestbookDAO;
import com.cafe24.mysite.vo.GuestbookVO;
import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;

public class DeleteAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	Long no = Long.parseLong( request.getParameter( "no" ) );
	String password = request.getParameter( "password" );

	GuestbookVO vo = new GuestbookVO();
	vo.setNo( no );
	vo.setPassword( password );

	GuestbookDAO dao = new GuestbookDAO();
	dao.delete( vo );

	WebUtil.redirect( request, response, "/mysite/guestbook" );
    }

}
