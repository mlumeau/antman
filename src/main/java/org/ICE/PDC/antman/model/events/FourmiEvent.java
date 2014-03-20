/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

public abstract class FourmiEvent extends MapEvent {

	private final Fourmi fourmi;
	
	public FourmiEvent(int tour, Date datetime, Fourmi fourmi) {
		super(tour, datetime);
		this.fourmi = fourmi;
	}

	/** 
	 * @return fourmi
	 */
	public Fourmi getFourmi() {
		return fourmi;
	}

}