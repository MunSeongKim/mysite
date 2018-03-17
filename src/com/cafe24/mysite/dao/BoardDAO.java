package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.dto.BoardDTO;
import com.cafe24.mysite.vo.BoardVO;

public class BoardDAO {
    public List<BoardDTO> readAll() {
	List<BoardDTO> list = new ArrayList<BoardDTO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "   SELECT board.no," + 
	    		 "	    board.title," + 
	    		 "          board.content," + 
	    		 "          board.hit_count," + 
	    		 "          DATE_FORMAT(board.reg_date, '%Y-%m-%d %h:%i:%s')," + 
	    		 "          board.group_no," + 
	    		 "          board.order_no," + 
	    		 "          board.depth," +
	    		 "          board.user_no," + 
	    		 "          users.name" + 
	    		 "     FROM board, users" + 
	    		 "    WHERE board.user_no = users.no" + 
	    		 " ORDER BY group_no DESC, order_no ASC";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();

	    while ( rs.next() ) {
		BoardVO vo = new BoardVO();
		vo.setNo( rs.getLong( 1 ) );
		vo.setTitle( rs.getString( 2 ) );
		vo.setContent( rs.getString( 3 ) );
		vo.setHitCount( rs.getLong( 4 ) );
		vo.setRegDate( rs.getString( 5 ) );
		vo.setGroupNo( rs.getLong( 6 ) );
		vo.setOrderNo( rs.getLong( 7 ) );
		vo.setDepth( rs.getLong( 8 ) );
		vo.setUserNo( rs.getLong( 9 ) );
		String username = rs.getString( 10 );

		BoardDTO dto = new BoardDTO();
		dto.setVo( vo );
		dto.setUsername( username );

		list.add( dto );
	    }
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( rs != null ) rs.close();
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}
	return list;
    }
    
    public List<BoardDTO> readAll(String keyword) {
	List<BoardDTO> list = new ArrayList<BoardDTO>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "   SELECT board.no," + 
	    		 "	    board.title," + 
	    		 "          board.content," + 
	    		 "          board.hit_count," + 
	    		 "          DATE_FORMAT(board.reg_date, '%Y-%m-%d %h:%i:%s')," +
	    		 "          board.group_no," + 
	    		 "          board.order_no," + 
	    		 "          board.depth," + 
	    		 "          board.user_no," +
	    		 "          users.name" + 
	    		 "     FROM board, users" + 
	    		 "    WHERE board.user_no = users.no" +
	    		 "      AND board.title LIKE '%" + keyword + "%'" +
	    		 " ORDER BY group_no DESC, order_no ASC";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();
	    
	    while ( rs.next() ) {
		BoardVO vo = new BoardVO();
		vo.setNo( rs.getLong( 1 ) );
		vo.setTitle( rs.getString( 2 ) );
		vo.setContent( rs.getString( 3 ) );
		vo.setHitCount( rs.getLong( 4 ) );
		vo.setRegDate( rs.getString( 5 ) );
		vo.setGroupNo( rs.getLong( 6 ) );
		vo.setOrderNo( rs.getLong( 7 ) );
		vo.setDepth( rs.getLong( 8 ) );
		vo.setUserNo( rs.getLong( 9 ) );
		String username = rs.getString( 10 );

		BoardDTO dto = new BoardDTO();
		dto.setVo( vo );
		dto.setUsername( username );

		list.add( dto );
	    }
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( rs != null ) rs.close();
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}
	return list;
    }
    
    public BoardDTO read( Long no ) {
	BoardDTO dto = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "   SELECT board.no," + 
	    		 "	    board.title," + 
	    		 "          board.content," + 
	    		 "          board.hit_count," + 
	    		 "          DATE_FORMAT(board.reg_date, '%Y-%m-%d %h:%i:%s')," +
	    		 "          board.group_no," + 
	    		 "          board.order_no," + 
	    		 "          board.depth," + 
	    		 "          board.user_no," +
	    		 "          users.name" + 
	    		 "     FROM board, users" + 
	    		 "    WHERE board.user_no = users.no" +
	    		 "      AND board.no = ?";
	    pstmt = conn.prepareStatement( sql );
	    pstmt.setLong( 1, no );
	    rs = pstmt.executeQuery();
	    
	    if ( rs.next() ) {
		BoardVO vo = new BoardVO();
		vo.setNo( rs.getLong( 1 ) );
		vo.setTitle( rs.getString( 2 ) );
		vo.setContent( rs.getString( 3 ) );
		vo.setHitCount( rs.getLong( 4 ) );
		vo.setRegDate( rs.getString( 5 ) );
		vo.setGroupNo( rs.getLong( 6 ) );
		vo.setOrderNo( rs.getLong( 7 ) );
		vo.setDepth( rs.getLong( 8 ) );
		vo.setUserNo( rs.getLong( 9 ) );
		String username = rs.getString( 10 );

		dto = new BoardDTO();
		dto.setVo( vo );
		dto.setUsername( username );
	    }
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( rs != null ) rs.close();
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}
	return dto;
    }
    
    public BoardDTO readAtLast( ) {
	BoardDTO dto = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
	    conn = getConnection();
	    String sql = "   SELECT board.no," + 
	    		 "	    board.title," + 
	    		 "          board.content," + 
	    		 "          board.hit_count," + 
	    		 "          DATE_FORMAT(board.reg_date, '%Y-%m-%d %h:%i:%s')," + 
	    		 "          board.group_no," + 
	    		 "          board.order_no," + 
	    		 "          board.depth," + 
	    		 "          users.name" + 
	    		 "     FROM board, users" + 
	    		 "    WHERE board.user_no = users.no" +
	    		 " ORDER BY board.no DESC" +
	    		 " LIMIT 1";
	    pstmt = conn.prepareStatement( sql );
	    rs = pstmt.executeQuery();
	    
	    if ( rs.next() ) {
		BoardVO vo = new BoardVO();
		vo.setNo( rs.getLong( 1 ) );
		vo.setTitle( rs.getString( 2 ) );
		vo.setContent( rs.getString( 3 ) );
		vo.setHitCount( rs.getLong( 4 ) );
		vo.setRegDate( rs.getString( 5 ) );
		vo.setGroupNo( rs.getLong( 6 ) );
		vo.setOrderNo( rs.getLong( 7 ) );
		vo.setDepth( rs.getLong( 8 ) );
		String username = rs.getString( 9 );

		dto = new BoardDTO();
		dto.setVo( vo );
		dto.setUsername( username );
	    }
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( rs != null ) rs.close();
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}
	return dto;
    }

    public boolean create( BoardVO vo, Long userNo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = " INSERT INTO board" + 
	    		 " VALUES(null, ?, ?, 0, now()," + 
	    		 "       (SELECT IFNULL(MAX(group_no), 0)+1 FROM board as max_no)," + 
	    		 "        1, 0, ?)";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setString( 1, vo.getTitle() );
	    pstmt.setString( 2, vo.getContent() );
	    pstmt.setLong( 3, userNo );

	    int count = pstmt.executeUpdate();
	    result = (count == 1);
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }
    
    public boolean create( BoardVO vo, Long userNo, Long groupNo, Long orderNo, Long depth ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	update(groupNo, orderNo);
	
	try {
	    conn = getConnection();
	    String sql = " INSERT INTO board" + 
	    		 " VALUES(null, ?, ?, 0, now()," + 
	    		 "        ?, ?, ?, ?)";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setString( 1, vo.getTitle() );
	    pstmt.setString( 2, vo.getContent() );
	    pstmt.setLong( 3, groupNo );
	    pstmt.setLong( 4, (orderNo + 1) );
	    pstmt.setLong( 5, (depth + 1) );
	    pstmt.setLong( 6, userNo );

	    int count = pstmt.executeUpdate();
	    result = (count == 1);
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }
    
    public boolean update( BoardVO vo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = " UPDATE board" + 
	    		 "    SET title = ?, content = ?" + 
	    		 "  WHERE no = ?";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setString( 1, vo.getTitle() );
	    pstmt.setString( 2, vo.getContent() );
	    pstmt.setLong( 3, vo.getNo() );

	    int count = pstmt.executeUpdate();
	    result = (count == 1) ? true : false;
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }
    
    public boolean update( Long groupNo, Long orderNo ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = " UPDATE board" + 
	    		 "    SET order_no = order_no + 1" + 
	    		 "  WHERE group_no = ? and order_no >= ?";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setLong( 1, groupNo );
	    pstmt.setLong( 2, orderNo );

	    int count = pstmt.executeUpdate();
	    result = (count == 1);
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }

    public boolean delete( Long no ) {
	boolean result = false;
	Connection conn = null;
	PreparedStatement pstmt = null;

	try {
	    conn = getConnection();
	    String sql = "DELETE FROM board where no = ?";
	    pstmt = conn.prepareStatement( sql );

	    pstmt.setLong( 1, no );

	    int count = pstmt.executeUpdate();
	    result = (count == 1);
	} catch ( SQLException e ) {
	    e.printStackTrace();
	} finally {
	    try {
		if ( pstmt != null ) pstmt.close();
		if ( conn != null ) conn.close();
	    } catch ( SQLException e ) {
		e.printStackTrace();
	    }
	}

	return result;
    }

    private Connection getConnection() throws SQLException {
	Connection conn = null;

	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	    String url = "jdbc:mysql://localhost:3306/webdb";
	    conn = DriverManager.getConnection( url, "webdb", "webdb" );
	} catch ( ClassNotFoundException e ) {
	    System.out.println( "Failed to load driver : " + e );
	}

	return conn;
    }
}
