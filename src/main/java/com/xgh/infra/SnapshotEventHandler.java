package com.xgh.infra;

import com.xgh.buildingblocks.Event;
import com.xgh.buildingblocks.EventHandler;

public class SnapshotEventHandler implements EventHandler {

	@Override
	public boolean isSubscribedTo(Event<?> event) {
		return false;
	}

	@Override
	public void execute(Event<?> event) {
		// TODO Auto-generated method stub
		
	}

}
