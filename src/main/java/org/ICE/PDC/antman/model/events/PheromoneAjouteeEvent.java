package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Pheromone;

/**
 * Ajout d'une phéromone sur la carte
 */
public class PheromoneAjouteeEvent extends PheromoneEvent implements Serializable {

	private static final long serialVersionUID = -3737558789533447451L;

	public PheromoneAjouteeEvent(int tour, Date datetime, Pheromone pheromone) {
		super(tour, datetime, pheromone);
	}
}