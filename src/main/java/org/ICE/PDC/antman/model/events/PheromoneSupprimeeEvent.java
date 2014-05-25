package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Pheromone;

/**
 * Suppression d'une phéromone
 */
public class PheromoneSupprimeeEvent extends PheromoneEvent implements Serializable {

	private static final long serialVersionUID = -6295297511563861868L;

	public PheromoneSupprimeeEvent(int tour, Date datetime, Pheromone pheromone) {
		super(tour, datetime, pheromone);
	}
}