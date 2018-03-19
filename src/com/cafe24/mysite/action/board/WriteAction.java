package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDAO;
import com.cafe24.mysite.dao.CommentDAO;
import com.cafe24.mysite.dto.CommentDTO;
import com.cafe24.mysite.vo.BoardVO;
import com.cafe24.mysite.vo.UserVO;

public class WriteAction implements Action {

    @Override
    public void execute( HttpServletRequest request, HttpServletResponse response )
	    throws ServletException, IOException {
	String title = request.getParameter( "title" );
	if( "".equals(title) ){
	    WebUtil.forward( request, response, "/WEB-INF/views/board/write.jsp" );
	    return ;
	}
	String content = request.getParameter( "content" );
	UserVO userVo = (UserVO) request.getSession().getAttribute( "authUser" );
	BoardVO boardVo = new BoardVO();

	boardVo.setTitle( title );
	boardVo.setContent( content );

	BoardDAO dao = new BoardDAO();
	dao.create( boardVo, userVo.getNo() );
	
	BoardVO vo = dao.readAtLast();
	List<CommentDTO> commentList = new CommentDAO().readAll( vo.getNo() );
	
	request.setAttribute( "board", vo );
	request.setAttribute( "commentList", commentList );
	WebUtil.forward( request, response, "/WEB-INF/views/board/view.jsp" );
    }

}
