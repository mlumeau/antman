/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import java.io.Serializable;
import java.util.Date;

import org.ICE.PDC.antman.model.Pheromone;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class PheromoneAjouteeEvent extends PheromoneEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	public PheromoneAjouteeEvent(int tour, Date datetime, Pheromone pheromone) {
		super(tour, datetime, pheromone);
	}
}