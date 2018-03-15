package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDAO;
import com.cafe24.mysite.vo.UserVO;

public class ModifyFormAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	HttpSession session = request.getSession();
	if ( session != null && session.getAttribute( "authUser" ) != null ) {
	    UserVO authUser = (UserVO)session.getAttribute( "authUser" );
	    UserVO vo = new UserDAO().read(authUser.getNo());
		
	    request.setAttribute( "vo", vo );
	    WebUtil.forward( request, response, "/WEB-INF/views/user/modifyform.jsp" );
	    return ;
	}
	WebUtil.redirect( request, response, "/mysite/main" );
	
    }

}