/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

public class FourmiSupprimeeEvent extends FourmiEvent {

	public FourmiSupprimeeEvent(int tour, Date datetime, Fourmi fourmi) {
		super(tour, datetime, fourmi);
	}
}