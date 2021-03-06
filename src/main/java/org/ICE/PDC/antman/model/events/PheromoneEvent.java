package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Pheromone;

/**
 * Évènement abstrait SuperClass des évènements concernant les phéromones
 */
public class PheromoneEvent extends MapEvent implements Serializable {

	private static final long serialVersionUID = 6148653462013142045L;
	private final Pheromone pheromone;
	
	public PheromoneEvent(int tour, Date datetime,Pheromone pheromone) {
		super(tour, datetime);
		this.pheromone = pheromone;
	}

	/** 
	 * @return pheromone
	 */
	public Pheromone getPheromone() {
		return pheromone;
	}

}