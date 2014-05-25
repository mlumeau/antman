package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

/**
 * Suppression d'une fourmi
 */
public class FourmiSupprimeeEvent extends FourmiEvent implements Serializable {

	private static final long serialVersionUID = -2489921704997204630L;

	public FourmiSupprimeeEvent(int tour, Date datetime, Fourmi fourmi) {
		super(tour, datetime, fourmi);
	}
}