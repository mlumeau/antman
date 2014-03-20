/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Pheromone;


public class PheromoneSupprimeeEvent extends PheromoneEvent {

	public PheromoneSupprimeeEvent(int tour, Date datetime, Pheromone pheromone) {
		super(tour, datetime, pheromone);
	}
}