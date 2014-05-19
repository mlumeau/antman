/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Ressource;

public class RessourceQuantiteChangeeEvent extends RessourceEvent implements Serializable {

	private static final long serialVersionUID = 3890545665466339677L;
	private final int old_quantite;
	
	
	public RessourceQuantiteChangeeEvent(int tour, Date datetime,Ressource ressource,int old_quantite) {
		super(tour, datetime, ressource);
		this.old_quantite = old_quantite;
	}

	/** 
	 * @return old_quantite
	 */
	public int getOld_quantite() {
		return old_quantite;
	}

}