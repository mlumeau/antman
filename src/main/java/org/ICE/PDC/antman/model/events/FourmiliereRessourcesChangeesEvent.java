/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Fourmiliere;


public class FourmiliereRessourcesChangeesEvent extends FourmiliereEvent {


	private final int old_ressources;
	
	public FourmiliereRessourcesChangeesEvent(int tour, Date datetime,Fourmiliere fourmiliere,int old_ressources) {
		super(tour, datetime, fourmiliere);
		this.old_ressources = old_ressources;
	}

	/** 
	 * @return old_ressources
	 */
	public int getOld_ressources() {
		return old_ressources;
	}

}