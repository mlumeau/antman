/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

public class FourmiSupprimeeEvent extends FourmiEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	public FourmiSupprimeeEvent(int tour, Date datetime, Fourmi fourmi) {
		super(tour, datetime, fourmi);
	}
}