package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.events.PheromonePuissanceChangeeEvent;
import org.apache.log4j.Logger;

public class Pheromone implements Serializable  {
	
	private static final long serialVersionUID = 7338644874597093851L;
	private static Logger logger = Logger.getLogger(Pheromone.class);

	/**Fourmiliere de la fourmi ayant émis la phéromone*/
	private Fourmiliere fourmiliere;
	/**Puissance de la phéromone (celle-ci diminue à chaque tour*/
	private int puissance;
	
	/**
	 * @param fourmiliere
	 * @param puissance
	 */
	public Pheromone(Fourmiliere fourmiliere,int puissance) {
		this.fourmiliere = fourmiliere;
		this.puissance = puissance;
		logger.debug("Phéromone crée : "+this);
	}
	
	@Override
	public String toString() {
		return "(Phéromone) - "+this.hashCode()+" -> { Fourmiliere : "+this.getFourmiliere().hashCode()+
				" , Puissance : "+this.getPuissance()+"}";
	}
	
	/**
	 * Augmente la puissance d'une phéromone 
	 * @param puissance
	 */
	public void augmenterPuissance(int puissance) {
		this.setPuissance(this.getPuissance()+puissance);
	}

	/** 
	 * Diminue la puissance d'une phéromone - Fire l'event PheromonePuissanceChangeeEvent
	 * @param puissance
	 */
	public void diminuerPuissance(int puissance) {
		int old = this.getPuissance();
		this.setPuissance(this.getPuissance()-puissance);
		//Ajout de l'évennement PheromonePuissanceChangeeEvent
		this.getFourmiliere().getMonde().fireEvent(new PheromonePuissanceChangeeEvent(this.fourmiliere.getMonde().getTour(), new Date(),this,old));
	}
	
	/** 
	 * @return fourmiliere
	 */
	public Fourmiliere getFourmiliere() {
		return fourmiliere;
	}

	/** 
	 * @param fourmiliere fourmiliere à définir
	 */
	public void setFourmiliere(Fourmiliere fourmiliere) {
		this.fourmiliere = fourmiliere;
	}

	/** 
	 * @return puissance
	 */
	public int getPuissance() {
		return puissance;
	}

	/** 
	 * @param puissance puissance à définir
	 */
	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

}