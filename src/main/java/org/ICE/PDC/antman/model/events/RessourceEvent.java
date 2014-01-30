/**
 * 
 */
package org.ICE.PDC.antman.model.events;

import org.ICE.PDC.antman.model.Ressource;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class RessourceEvent {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Ressource ressource;

	/** 
	 * @return ressource
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Ressource getRessource() {
		// begin-user-code
		return ressource;
		// end-user-code
	}

	/** 
	 * @param ressource ressource � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setRessource(Ressource ressource) {
		// begin-user-code
		this.ressource = ressource;
		// end-user-code
	}
}