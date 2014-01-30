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
public class Pheromone {
	
	private static Logger logger = Logger.getLogger(Pheromone.class);
	
	
	public Pheromone(Fourmiliere fourmiliere,int puissance) {
		this.fourmiliere = fourmiliere;
		this.puissance = puissance;
		logger.debug("Pheromone crée : "+this);
	}
	
	public String toString() {
		return "(Pheromone) - "+this.hashCode()+" -> { Fourmiliere : "+this.getFourmiliere().hashCode()+
				" , Puissance : "+this.getPuissance()+"}";
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Fourmiliere fourmiliere;


	/** 
	 * @return fourmiliere
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Fourmiliere getFourmiliere() {
		// begin-user-code
		return fourmiliere;
		// end-user-code
	}

	/** 
	 * @param fourmiliere fourmiliere � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setFourmiliere(Fourmiliere fourmiliere) {
		// begin-user-code
		this.fourmiliere = fourmiliere;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int puissance;

	/** 
	 * @return puissance
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getPuissance() {
		// begin-user-code
		return puissance;
		// end-user-code
	}

	/** 
	 * @param puissance puissance � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPuissance(int puissance) {
		// begin-user-code
		this.puissance = puissance;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param puissance
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void augmenterPuissance(int puissance) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param puissance
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void diminuerPuissance(int puissance) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}
}