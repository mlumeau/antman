/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Ressource;

public class RessourceSupprimeeEvent extends RessourceEvent {

	public RessourceSupprimeeEvent(int tour, Date datetime, Ressource ressource) {
		super(tour, datetime, ressource);
		// TODO Auto-generated constructor stub
	}
}