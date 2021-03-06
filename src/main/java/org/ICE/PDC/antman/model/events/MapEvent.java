package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

/**
 * Évènement abstrait SuperClass de tout les évènements
 */
public abstract class MapEvent implements Serializable {
	
	private static final long serialVersionUID = -6753438529038374656L;
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
