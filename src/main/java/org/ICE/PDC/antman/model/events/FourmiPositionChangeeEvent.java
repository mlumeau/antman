package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

/**
 * Déplacement d'une fourmi
 */
public class FourmiPositionChangeeEvent extends FourmiEvent implements Serializable {

	private static final long serialVersionUID = 1730432913592456432L;
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