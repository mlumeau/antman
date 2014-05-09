/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmiliere;

public class FourmiliereSupprimeeEvent extends FourmiliereEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	public FourmiliereSupprimeeEvent(int tour, Date datetime,Fourmiliere fourmiliere) {
		super(tour, datetime, fourmiliere);
	}
	
}