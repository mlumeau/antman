/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Fourmiliere;

public class FourmiliereSupprimeeEvent extends FourmiliereEvent {

	public FourmiliereSupprimeeEvent(int tour, Date datetime,Fourmiliere fourmiliere) {
		super(tour, datetime, fourmiliere);
	}
	
}