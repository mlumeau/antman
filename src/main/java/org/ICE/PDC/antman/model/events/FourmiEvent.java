package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

/**
 * Évènement abstrait SuperClass des évènements concernant les fourmis
 */
public abstract class FourmiEvent extends MapEvent implements Serializable {

	private static final long serialVersionUID = 6890248323954519724L;
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