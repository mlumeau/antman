/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class Fourmi {
	
	private static Logger logger = Logger.getLogger(Fourmi.class);
	
	private Case last_position;
	
	public Case getLast_position() {
		return last_position;
	}

	public void setLast_position(Case last_position) {
		this.last_position = last_position;
	}

	private final int sante_max;
	
	public int getSante_max() {
		return this.sante_max;
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int age;

	/** 
	 * @return age
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getAge() {
		// begin-user-code
		return age;
		// end-user-code
	}

	/** 
	 * @param age age � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAge(int age) {
		// begin-user-code
		this.age = age;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int sante;

	/** 
	 * @return sante
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getSante() {
		// begin-user-code
		return sante;
		// end-user-code
	}

	/** 
	 * @param sante sante � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setSante(int sante) {
		// begin-user-code
		this.sante = sante;
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
		return _case;
	}

	/** 
	 * @param _case _case � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void set_case(Case _case) {
		
		if(this._case != null) {
			this.setLast_position(this._case);
			this._case.supprimerFourmi(this);
		}
		
		this._case = _case;
		_case.ajouterFourmi(this);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int esperance_de_vie;

	/** 
	 * @return esperance_de_vie
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getEsperance_de_vie() {
		// begin-user-code
		return esperance_de_vie;
		// end-user-code
	}

	/** 
	 * @param esperance_de_vie esperance_de_vie � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setEsperance_de_vie(int esperance_de_vie) {
		// begin-user-code
		this.esperance_de_vie = esperance_de_vie;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public abstract void agir();

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @param _case
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Fourmi(Fourmiliere fourmiliere, Case _case) {
		this.fourmiliere = fourmiliere;
		this._case = _case;
		this.age = 0; //DEFAULT
		this.esperance_de_vie = 20; //DEFAULT
		this.sante = 20; //DEFAULT 
		this.sante_max = this.sante; //DEFAULT 
		logger.debug("Fourmi crée : "+this);
		this.set_case(_case); //Lie la case à la Fourmi
		fourmiliere.ajouterFourmi(this); //Lie la fourmiliere à la Fourmi
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Fourmi(Fourmiliere fourmiliere) {
		this(fourmiliere,fourmiliere.get_case());//On initialise la case à celle de la fourmiliere
	}
	
	
	private Fourmiliere fourmiliere;
	
	public Fourmiliere getFourmiliere() {
		return this.fourmiliere;
	}
	
	public void setFourmiliere(Fourmiliere fourmiliere) {
		this.fourmiliere = fourmiliere;
	}
	
	@Override
	public String toString() {
		return "(Fourmi) - "+this.hashCode()+" -> { Fourmiliere : "+this.getFourmiliere().hashCode()+
				" , Case : "+this.get_case().hashCode()+
				" , Age : "+this.getAge()+
				" , Esperance de vie : "+this.getEsperance_de_vie()+
				" , Santée : "+this.getSante()+"}";
	}
	
	public void mourir() {
		this.get_case().supprimerFourmi(this);
		this.getFourmiliere().supprimerFourmi(this);
	}
	

	public void seDeplacerAlea() {
		List<Case> cases = this.get_case().getCasesInRadius(1);
		int index = new Random().nextInt(cases.size());
		Case focus = cases.get(index);
		this.set_case(focus);
		logger.debug(this.getClass()+" ("+this.hashCode()+") : Deplacement Aleatoire en "+focus);
	}
	

	public void seDeplacer(Case c) {
		this.set_case(c);
		logger.debug(this.getClass()+" ("+this.hashCode()+") : Deplacement en "+c);
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void manger() {
		int ressources_dispo = this.getFourmiliere().getRessources();
		int vie_perdue = this.getSante_max()-this.getSante();
		
			if(ressources_dispo < vie_perdue) {
				this.getFourmiliere().diminuerRessources(ressources_dispo);
				this.setSante(this.getSante()+ressources_dispo);
			} else {
				this.getFourmiliere().diminuerRessources(vie_perdue);
				this.setSante(this.getSante()+vie_perdue);
			}
			
	}


}