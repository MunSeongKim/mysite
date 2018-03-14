package com.cafe24.mysite.action.user;

import com.cafe24.mvc.action.AbstractActionFactory;
import com.cafe24.mvc.action.Action;

public class UserActionFactory extends AbstractActionFactory {

    @Override
    public Action getAction( String actionName ) {
	Action action = null;

	if ( "joinform".equals( actionName ) ) {
	    action = new JoinFormAction();
	} else if ( "loginform".equals( actionName ) ) {
	    action = new LoginFormAction();
	} else {
	    //action = new IndexAction();
	    action = new DefaultAction();
	}

	return action;
    }

}
