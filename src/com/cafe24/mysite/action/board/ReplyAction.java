package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDAO;
import com.cafe24.mysite.dto.BoardDTO;
import com.cafe24.mysite.vo.BoardVO;
import com.cafe24.mysite.vo.UserVO;

public class ReplyAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	BoardVO vo = new BoardVO();
	BoardDAO dao = new BoardDAO();
	Long groupNo = Long.parseLong( request.getParameter( "group-no" ) );
	Long orderNo = Long.parseLong( request.getParameter( "order-no" ) );
	Long depth = Long.parseLong( request.getParameter( "depth" ) );
	String title = request.getParameter( "title" );
	String content = request.getParameter( "content" );
	UserVO authUser = (UserVO) request.getSession().getAttribute( "authUser" );

	vo.setTitle( title );
	vo.setContent( content );

	dao.create( vo, authUser.getNo(), groupNo, orderNo +1, depth +1 );

	BoardDTO dto = dao.readAtLast();
	request.setAttribute( "result", dto );
	WebUtil.forward( request, response, "/WEB-INF/views/board/view.jsp" );

    }

}
