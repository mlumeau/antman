/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmiliere;


public class FourmiliereRessourcesChangeesEvent extends FourmiliereEvent implements Serializable {

	private static final long serialVersionUID = 1L;
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