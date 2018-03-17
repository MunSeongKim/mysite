package com.cafe24.mvc.util;

import com.cafe24.mysite.dao.BoardDAO;

public class PagerUtil {
    public static Pager setUpPager( Pager pager, BoardDAO dao, int pageNo, String keyword ) {
	int count = dao.readCountBySearch( keyword );
		
	pager = setCurrentPageNumber( pager, pageNo );
	pager = setStartPostNumber( pager, dao, count );
	pager = setTotalPageCount( pager, dao, count );
	pager = setNavigator( pager );
	return pager;
    }
    
    public static Pager setUpPager( Pager pager, BoardDAO dao, int pageNo ) {
	int count = dao.readCount();
	
	pager = setCurrentPageNumber( pager, pageNo );
	pager = setStartPostNumber( pager, dao, count );
	pager = setTotalPageCount( pager, dao, count );
	pager = setNavigator( pager );
	return pager;
    }

    private static Pager setCurrentPageNumber( Pager pager, int pageNo ) {
	int pageCount = pager.getPageCount();

	int diff = ((pageNo - 1) % pageCount);

	pager.setStartPageNumber( pageNo - diff );
	pager.setEndPageNumber( pageNo + (pageCount - diff - 1) );

	pager.setCurrentPageNumber( pageNo );
	return pager;
    }
    
    private static Pager setStartPostNumber( Pager pager, BoardDAO dao, int count ) {
	int currentPage = pager.getCurrentPageNumber();
	int postCount = pager.getPostCount();

	pager.setStartPostNumber( count - ((currentPage - 1) * postCount) );
	return pager;
    }

    private static Pager setTotalPageCount( Pager pager, BoardDAO dao, int count ) {
	int postCount = pager.getPostCount();

	if ( (count % postCount) == 0 ) {
	    pager.setTotalPageCount( count / postCount );
	    return pager;
	}
	pager.setTotalPageCount( (count / postCount) + 1 );
	return pager;
    }

    private static Pager setNavigator( Pager pager ) {
	int startPageNumber = pager.getStartPageNumber();
	int endPageNumber = pager.getEndPageNumber();
	int totalPageCount = pager.getTotalPageCount();
	int pageCount = pager.getPageCount();

	pager.setLeftNavigator( false );
	pager.setRightNavigator( false );

	if ( endPageNumber < totalPageCount ) {
	    pager.setRightNavigator( true );
	}
	if ( startPageNumber > pageCount ) {
	    pager.setLeftNavigator( true );
	}

	return pager;
    }
}
