package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Monde;

public class TourJouerEvent extends MapEvent {


	private final Monde monde;
	
	public TourJouerEvent(int tour, Date datetime,Monde monde) {
		super(tour, datetime);
		this.monde = monde;
	}

	public Monde getMonde() {
		return monde;
	}

}
