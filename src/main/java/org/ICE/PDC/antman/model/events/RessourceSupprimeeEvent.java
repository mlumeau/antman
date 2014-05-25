package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Ressource;

/**
 * Suppression d'une ressource
 */
public class RessourceSupprimeeEvent extends RessourceEvent implements Serializable {

	private static final long serialVersionUID = -293447497966096284L;

	public RessourceSupprimeeEvent(int tour, Date datetime, Ressource ressource) {
		super(tour, datetime, ressource);
		// TODO Auto-generated constructor stub
	}
}