/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class FourmiEtatChangeEvent extends FourmiEvent {

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