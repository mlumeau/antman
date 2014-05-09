/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmiliere;

public class FourmiliereEvent extends MapEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Fourmiliere fourmiliere;

	public FourmiliereEvent(int tour, Date datetime,Fourmiliere fourmiliere) {
		super(tour, datetime);
		this.fourmiliere = fourmiliere;
	}
	
	/** 
	 * @return fourmiliere
	 */
	public Fourmiliere getFourmiliere() {
		return fourmiliere;
	}

}