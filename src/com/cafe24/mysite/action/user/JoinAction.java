package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDAO;
import com.cafe24.mysite.vo.UserVO;

public class JoinAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	UserVO vo = new UserVO();
	
	String name = request.getParameter( "name" );
	String email = request.getParameter( "email" );
	String password = request.getParameter( "password" );
	String gender = request.getParameter( "gender" );
	
	vo.setName( name );
	vo.setEmail( email );
	vo.setPassword( password );
	vo.setGender( gender );
	
	new UserDAO().create(vo);
	
	WebUtil.redirect( request, response, "/mysite/user?a=joinsuccess" );
    }
}
