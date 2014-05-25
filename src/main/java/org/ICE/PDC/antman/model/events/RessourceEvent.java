package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Ressource;

/**
 * Évènement abstrait SuperClass des évènements concernant les ressources
 */
public abstract class RessourceEvent extends MapEvent implements Serializable {

	private static final long serialVersionUID = 2685784378674906335L;
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