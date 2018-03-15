package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.vo.UserVO;
import com.cafe24.mysite.dao.UserDAO;

public class ModifyAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	Long no = Long.parseLong(request.getParameter( "no" ));
	String name = request.getParameter( "name" );
	String password = request.getParameter( "password" );
	String gender = request.getParameter( "gender" );
	
	UserVO vo = new UserVO();
	vo.setNo( no );
	vo.setName( name );
	vo.setPassword( password );
	vo.setGender( gender );
	
	new UserDAO().update(vo);
	
	vo.setPassword(null);
	vo.setGender(null);
	
	request.getSession().setAttribute( "authUser", vo );
	WebUtil.redirect( request, response, "/mysite/user?a=modifyform" );
    }

}
