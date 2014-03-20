/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Ressource;

public class RessourceAjouteeEvent extends RessourceEvent {

	public RessourceAjouteeEvent(int tour, Date datetime, Ressource ressource) {
		super(tour, datetime, ressource);
	}
}