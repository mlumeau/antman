package org.ICE.PDC.antman.model.events;

import java.util.Date;

public abstract class MapEvent {
	
	private final Date time;
	
	public MapEvent(int tour,Date datetime) {
		this.time = datetime;
	}

	public Date getTime() {
		return time;
	}

}
