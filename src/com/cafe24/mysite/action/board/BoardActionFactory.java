package com.cafe24.mysite.action.board;

import com.cafe24.mvc.action.AbstractActionFactory;
import com.cafe24.mvc.action.Action;

public class BoardActionFactory extends AbstractActionFactory {

    @Override
    public Action getAction( String actionName ) {
	Action action = null;

	if( "view".equals( actionName ) ) {
	    action = new ViewAction();
    	} else if( "writeform".equals( actionName ) ) {
	    action = new WriteFormAction();
    	} else if( "write".equals( actionName ) ) {
	    action = new WriteAction();
    	} else if( "modifyform".equals( actionName ) ) {
	    action = new ModifyFormAction();
    	} else if( "modify".equals( actionName ) ) {
	    action = new ModifyAction();
    	} else if( "delete".equals( actionName ) ) {
	    action = new DeleteAction();
    	} else if( "replyform".equals( actionName ) ) {
	    action = new ReplyFormAction();
    	} else if( "reply".equals( actionName ) ) {
	    action = new ReplyAction();
    	} else {
	    action = new ListAction();
	}

	return action;
    }

}
