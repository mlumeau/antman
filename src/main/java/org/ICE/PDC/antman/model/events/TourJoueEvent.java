package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Monde;

public class TourJoueEvent extends MapEvent implements Serializable {

	private static final long serialVersionUID = -7871211977361241629L;
	private final Monde monde;
	
	public TourJoueEvent(int tour, Date datetime,Monde monde) {
		super(tour, datetime);
		this.monde = monde;
	}

	public Monde getMonde() {
		return monde;
	}

}
