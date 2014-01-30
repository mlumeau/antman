/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.util.Random;

import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Reine extends Fourmi {
	
	private static Logger logger = Logger.getLogger(Reine.class);
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @author S219
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public enum States {
		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		INSTALEE,
		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		RECHERCHE_EMPLACEMENT
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private States etat;

	/** 
	 * @return etat
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public States getEtat() {
		// begin-user-code
		return etat;
		// end-user-code
	}

	/** 
	 * @param etat etat � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setEtat(States etat) {
		// begin-user-code
		this.etat = etat;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void pondre() {
		
		for(int i=0; i<this.getFourmiliere().getFecondite(); i++) {
			
			int rand = new Random().nextInt(10);
			
			if(rand < 8) {
				new Ouvriere(this.getFourmiliere());
			} else {
				new Eclaireuse(this.getFourmiliere());
			}

		}
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Reine(Fourmiliere fourmiliere) {
		super(fourmiliere);
		this.setEtat(States.INSTALEE);
		this.setEsperance_de_vie(99999);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @param _case
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Reine(Fourmiliere fourmiliere, Case _case) {
		super(fourmiliere,_case);
		this.setEtat(States.INSTALEE);
		this.setEsperance_de_vie(99999);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @param _case
	 * @param etat
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Reine(Fourmiliere fourmiliere, Case _case, States etat) {
		super(fourmiliere,_case);
		this.etat = etat;
		this.setEsperance_de_vie(99999);
	}

	/** 
	 * (non-Javadoc)
	 * @see Fourmi#agir()
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void agir() {
		this.setAge(this.getAge()+1);
		this.setSante(this.getSante()-1);
		
			if(this.get_case().equals(this.getFourmiliere().get_case())) {
				//Manger :)
				this.manger();
				logger.info("La Reine ("+this.hashCode()+") mange");
			}
			
			if(this.getAge() >= this.getEsperance_de_vie() || this.getSante() <= 0) {
				//Mort de la fourmi
				this.mourir();
				logger.info("La Reine ("+this.hashCode()+") est morte");
				
			} else {
				//Action
				switch(this.getEtat()) {
				
					case INSTALEE:
						this.pondre();
					break;
						
					case RECHERCHE_EMPLACEMENT:
						//TODO feed me :(
					break;
						
				}
				
			}
			
	}
}