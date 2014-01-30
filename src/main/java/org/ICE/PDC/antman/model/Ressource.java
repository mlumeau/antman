/**
 * 
 */
package org.ICE.PDC.antman.model;

import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Ressource {
	
	private static Logger logger = Logger.getLogger(Ressource.class);
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int quantite;

	public Ressource(int quantite) {
		this.setQuantite(quantite);
		logger.debug("Ressource crée : "+this);
	}
	
	@Override
	public String toString() {
		return "(Ressource) - "+this.hashCode()+" -> {Quantite : "+this.getQuantite()+"}";
	}

	/** 
	 * @return quantite
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getQuantite() {
		// begin-user-code
		return quantite;
		// end-user-code
	}

	/** 
	 * @param quantite quantite � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setQuantite(int quantite) {
		// begin-user-code
		this.quantite = quantite;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param quantite
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void diminuerQuantite(int quantite) {
		this.setQuantite(this.getQuantite()-quantite);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param quantite
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void augmenterQuantite(int quantite) {
		this.setQuantite(this.getQuantite()+quantite);
	}
}