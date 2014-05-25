package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

/**
 * Ajout d'une fourmi sur la carte
 */
public class FourmiAjouteeEvent extends FourmiEvent implements Serializable {

	private static final long serialVersionUID = -6064517677271907175L;

	public FourmiAjouteeEvent(int tour, Date datetime, Fourmi fourmi) {
		super(tour, datetime, fourmi);
	}
	
}