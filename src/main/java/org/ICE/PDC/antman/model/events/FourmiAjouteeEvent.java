/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Fourmi;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class FourmiAjouteeEvent extends FourmiEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	public FourmiAjouteeEvent(int tour, Date datetime, Fourmi fourmi) {
		super(tour, datetime, fourmi);
	}
	
}