package com.cafe24.mysite.action.comment;

import com.cafe24.mvc.action.AbstractActionFactory;
import com.cafe24.mvc.action.Action;

public class CommentActionFactory extends AbstractActionFactory {

    @Override
    public Action getAction( String actionName ) {
	Action action = null;

	if ( "write".equals( actionName ) ) {
	    action = new WriteAction();
	} else if ( "delete".equals( actionName ) ) {
	    action = new DeleteAction();
	} else {
	    action = new ListAction();
	}

	return action;
    }

}
