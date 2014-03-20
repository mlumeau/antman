/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Ressource;

public abstract class RessourceEvent extends MapEvent {


	private final Ressource ressource;
	
	public RessourceEvent(int tour, Date datetime,Ressource ressource) {
		super(tour, datetime);
		this.ressource = ressource;
	}

	/** 
	 * @return ressource
	 */
	public Ressource getRessource() {
		return ressource;
	}

}