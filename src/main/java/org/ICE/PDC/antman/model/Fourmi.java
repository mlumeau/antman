package org.ICE.PDC.antman.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.ICE.PDC.antman.model.events.FourmiAjouteeEvent;
import org.ICE.PDC.antman.model.events.FourmiPositionChangeeEvent;
import org.ICE.PDC.antman.model.events.FourmiSupprimeeEvent;
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
	private Case _case;
	private Fourmiliere fourmiliere;
	private final int sante_max;
	private int esperance_de_vie;
	private int age;
	private int sante;
	
	public abstract void agir();

	/** 
	 * @param fourmiliere
	 * @param _case
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Fourmi(Fourmiliere fourmiliere, Case _case) {
		this.age = 0; //DEFAULT
		this.esperance_de_vie = 20; //DEFAULT
		this.sante = 20; //DEFAULT 
		this.sante_max = this.sante; //DEFAULT 
		this._case = _case;
		this.fourmiliere = fourmiliere;
		logger.debug("Fourmi crée : "+this);
		_case.ajouterFourmi(this); //Lie la case à la Fourmi
		fourmiliere.ajouterFourmi(this); //Lie la fourmiliere à la Fourmi
		fourmiliere.getMonde().getEvents().get(fourmiliere.getMonde().getTour()).add(new FourmiAjouteeEvent(fourmiliere.getMonde().getTour(), new Date(), this));
	}

	/** 
	 * @param fourmiliere
	 */
	public Fourmi(Fourmiliere fourmiliere) {
		this(fourmiliere,fourmiliere.get_case());//On initialise la case à celle de la fourmiliere
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
		fourmiliere.getMonde().getEvents().get(fourmiliere.getMonde().getTour()).add(new FourmiSupprimeeEvent(fourmiliere.getMonde().getTour(), new Date(),this));
		this.get_case().supprimerFourmi(this);
		this.getFourmiliere().supprimerFourmi(this);
	}
	

	public void seDeplacerAlea() {
		List<Case> cases = this.get_case().getCasesInRadius(1);
		if(cases.size() > 1) {
			cases.remove(this.getLast_position());
		}
		
		int index = new Random().nextInt(cases.size());
		Case focus = cases.get(index);
		this.set_case(focus);
		logger.debug(this.getClass()+" ("+this.hashCode()+") : Deplacement Aleatoire en "+focus);
	}
	

	/**
	 * @param c
	 */
	public void seDeplacer(Case c) {
		this.set_case(c);
		logger.debug(this.getClass()+" ("+this.hashCode()+") : Deplacement en "+c);
	}
	

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
	
	/**
	 * @return last_position
	 */
	public Case getLast_position() {
		return last_position;
	}

	/**
	 * @param last_position
	 */
	public void setLast_position(Case last_position) {
		this.last_position = last_position;
	}

	/**
	 * @return sante_max
	 */
	public int getSante_max() {
		return this.sante_max;
	}
	
	/** 
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/** 
	 * @param age age à définir
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/** 
	 * @return sante
	 */
	public int getSante() {
		return sante;
	}

	/** 
	 * @param sante sante à définir
	 */
	public void setSante(int sante) {
		this.sante = sante;
	}

	/** 
	 * @return _case
	 */
	public Case get_case() {
		return _case;
	}

	/** 
	 * @param _case _case à définir
	 */
	public void set_case(Case _case) {		
		if(this._case != null) {
			fourmiliere.getMonde().getEvents().get(fourmiliere.getMonde().getTour()).add(new FourmiPositionChangeeEvent(fourmiliere.getMonde().getTour(), new Date(),this,this.get_case().getX(),this.get_case().getY()));
			this.setLast_position(this._case);
			this._case.supprimerFourmi(this);
		}
		
		this._case = _case;
		_case.ajouterFourmi(this);
	}

	/** 
	 * @return esperance_de_vie
	 */
	public int getEsperance_de_vie() {
		return esperance_de_vie;
	}
	
	
	/**
	 * @param esperance_de_vie
	 */
	public void setEsperance_de_vie(int esperance_de_vie) {
		this.esperance_de_vie = esperance_de_vie;
	}
	
	
	/**
	 * @return fourmiliere
	 */
	public Fourmiliere getFourmiliere() {
		return this.fourmiliere;
	}
	
	/**
	 * @param fourmiliere
	 */
	public void setFourmiliere(Fourmiliere fourmiliere) {
		
		if(this.fourmiliere != null) {
			this.fourmiliere.supprimerFourmi(this);
		}
		
		this.fourmiliere = fourmiliere;
		fourmiliere.ajouterFourmi(this);
		
	}

}