/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;


public class FourmiPositionChangeeEvent extends FourmiEvent {

	private final int old_position_x;
	private final int old_position_y;
	
	public FourmiPositionChangeeEvent(int tour, Date datetime, Fourmi fourmi,int old_position_x,int old_position_y) {
		super(tour, datetime, fourmi);
		this.old_position_x = old_position_x;
		this.old_position_y = old_position_y;
	}

	/** 
	 * @return old_position_x
	 */
	public int getOld_position_x() {
		return old_position_x;
	}

	/** 
	 * @return old_position_y
	 */
	public int getOld_position_y() {
		return old_position_y;
	}

}