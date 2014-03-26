package org.ICE.PDC.antman.model.events;

import java.util.Date;

public abstract class MapEvent {
	
	private final Date time;
	
	private final int tour;
	
	public MapEvent(int tour,Date datetime) {
		this.time = datetime;
		this.tour = tour;
	}

	public Date getTime() {
		return time;
	}

	public int getTour() {
		return tour;
	}

}
