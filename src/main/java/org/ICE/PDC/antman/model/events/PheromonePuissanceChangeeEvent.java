/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Pheromone;

public class PheromonePuissanceChangeeEvent extends PheromoneEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private final int old_puissance;

	
	public PheromonePuissanceChangeeEvent(int tour, Date datetime,Pheromone pheromone, int old_puissance) {
		super(tour, datetime, pheromone);
		this.old_puissance = old_puissance;
	}
	
	/** 
	 * @return old_puissance
	 */
	public int getOld_puissance() {
		return old_puissance;
	}

}