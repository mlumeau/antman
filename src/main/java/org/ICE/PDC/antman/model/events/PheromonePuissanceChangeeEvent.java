/**
 * 
 */
package org.ICE.PDC.antman.model.events;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class PheromonePuissanceChangeeEvent extends PheromoneEvent {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int old_puissance;

	/** 
	 * @return old_puissance
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getOld_puissance() {
		// begin-user-code
		return old_puissance;
		// end-user-code
	}

	/** 
	 * @param old_puissance old_puissance � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setOld_puissance(int old_puissance) {
		// begin-user-code
		this.old_puissance = old_puissance;
		// end-user-code
	}
}