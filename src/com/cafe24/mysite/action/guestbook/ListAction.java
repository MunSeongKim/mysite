package com.cafe24.mysite.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.GuestbookDAO;
import com.cafe24.mysite.vo.GuestbookVO;

public class ListAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	GuestbookDAO dao = new GuestbookDAO();
	List<GuestbookVO> list = dao.readAll();
	
	request.setAttribute( "list", list );
	WebUtil.forward( request, response, "/WEB-INF/views/guestbook/list.jsp" );
    }

}
