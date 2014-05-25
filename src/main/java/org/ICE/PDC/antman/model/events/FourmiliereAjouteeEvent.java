package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmiliere;

/**
 * Ajout d'une fourmilière sur la carte
 */
public class FourmiliereAjouteeEvent extends FourmiliereEvent implements Serializable {

	private static final long serialVersionUID = 6485715024480003788L;

	public FourmiliereAjouteeEvent(int tour, Date datetime,Fourmiliere fourmiliere) {
		super(tour, datetime, fourmiliere);
	}
	
}