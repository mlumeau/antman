/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Fourmiliere {
	
	private static Logger logger = Logger.getLogger(Fourmiliere.class);
	
	public Fourmiliere(Monde monde, Case c, int fecondite,int taille_max, int ressources) {
		this._case = c;
		this.fecondite = fecondite;
		this.taille_max = taille_max;
		this.ressources = ressources;
		this.fourmi = new HashSet<Fourmi>();
		logger.debug("Fourmiliere crée : "+this);
		c.setFourmiliere(this); //Lie la case à la fourmiliere
		monde.ajouterFourmiliere(this); //Lie le monde à la fourmiliere
	}
	
	@Override
	public String toString() {
		return "(Fourmiliere) - "+this.hashCode()+" -> {Case : "+this.get_case().hashCode()+
				" , Fecondite : "+this.getFecondite()+
				" , Taille_max : "+this.getTaille_max()+
				" , Ressources : "+this.getRessources()+"}";
	}
	
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Fourmi> fourmi;


	/** 
	 * @return fourmi
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Fourmi> getFourmi() {
		// begin-user-code
		return fourmi;
		// end-user-code
	}

	/** 
	 * @param fourmi fourmi � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setFourmi(Set<Fourmi> fourmi) {
		// begin-user-code
		this.fourmi = fourmi;
		// end-user-code
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int fecondite;

	/** 
	 * @return fecondite
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getFecondite() {
		// begin-user-code
		return fecondite;
		// end-user-code
	}

	/** 
	 * @param fecondite fecondite � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setFecondite(int fecondite) {
		// begin-user-code
		this.fecondite = fecondite;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int ressources;

	/** 
	 * @return ressources
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getRessources() {
		// begin-user-code
		return ressources;
		// end-user-code
	}

	/** 
	 * @param ressources ressources � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setRessources(int ressources) {
		// begin-user-code
		this.ressources = ressources;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int taille_max;

	/** 
	 * @return taille_max
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getTaille_max() {
		// begin-user-code
		return taille_max;
		// end-user-code
	}

	/** 
	 * @param taille_max taille_max � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setTaille_max(int taille_max) {
		// begin-user-code
		this.taille_max = taille_max;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Case _case;

	/** 
	 * @return _case
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Case get_case() {
		// begin-user-code
		return _case;
		// end-user-code
	}

	/** 
	 * @param _case _case � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void set_case(Case _case) {
		// begin-user-code
		this._case = _case;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param f
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void ajouterFourmi(Fourmi f) {
		this.fourmi.add(f);
		logger.debug("Fourmiliere ("+this.hashCode()+") Ajout de la Fourmi : "+f+" ");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param f
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void supprimerFourmi(Fourmi f) {
		this.fourmi.remove(f);
		logger.debug("Fourmiliere ("+this.hashCode()+") Suppression de la Fourmi : "+f+" ");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getTotalFourmis() {
		return this.getFourmi().size();
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param quantite
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void diminuerRessources(int quantite) {
		this.setRessources(this.getRessources()-quantite);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param quantite
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void augmenterRessources(int quantite) {
		this.setRessources(this.getRessources()+quantite);
	}


}