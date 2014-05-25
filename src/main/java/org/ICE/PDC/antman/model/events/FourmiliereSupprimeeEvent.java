package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmiliere;

/**
 * Suppression d'une fourmilière
 */
public class FourmiliereSupprimeeEvent extends FourmiliereEvent implements Serializable {

	private static final long serialVersionUID = 1523187413636827777L;

	public FourmiliereSupprimeeEvent(int tour, Date datetime,Fourmiliere fourmiliere) {
		super(tour, datetime, fourmiliere);
	}
	
}