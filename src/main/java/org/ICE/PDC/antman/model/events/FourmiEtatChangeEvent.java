package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

/**
 * Changement d'état d'une fourmi
 */
public class FourmiEtatChangeEvent extends FourmiEvent implements Serializable {

	private static final long serialVersionUID = -4814932261492896325L;
	private final Object old_etat;
	
	public FourmiEtatChangeEvent(int tour, Date datetime, Fourmi fourmi,Object old_etat) {
		super(tour, datetime, fourmi);
		this.old_etat = old_etat;
	}

	/** 
	 * @return old_etat
	 */
	public Object getOld_etat() {
		return old_etat;
	}

}