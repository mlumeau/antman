/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import org.ICE.PDC.antman.model.Fourmi;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class FourmiEvent {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Fourmi fourmi;

	/** 
	 * @return fourmi
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Fourmi getFourmi() {
		// begin-user-code
		return fourmi;
		// end-user-code
	}

	/** 
	 * @param fourmi fourmi � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setFourmi(Fourmi fourmi) {
		// begin-user-code
		this.fourmi = fourmi;
		// end-user-code
	}
}