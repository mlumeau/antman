/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Ressource;

public class RessourceAjouteeEvent extends RessourceEvent implements Serializable {

	private static final long serialVersionUID = 1881542434787128703L;

	public RessourceAjouteeEvent(int tour, Date datetime, Ressource ressource) {
		super(tour, datetime, ressource);
	}
}