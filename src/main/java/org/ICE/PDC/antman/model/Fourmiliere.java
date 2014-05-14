package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.ICE.PDC.antman.model.events.FourmiliereAjouteeEvent;
import org.ICE.PDC.antman.model.events.FourmiliereRessourcesChangeesEvent;
import org.ICE.PDC.antman.model.events.FourmiliereSupprimeeEvent;
import org.apache.log4j.Logger;


public class Fourmiliere implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Fourmiliere.class);
	private Monde monde;
	private Set<Fourmi> fourmi;
	private int fecondite;
	private int ressources;
	private int taille_max;
	private Case _case;
	private int tauxEclaireuses;
	
	/**
	 * @return monde
	 */
	public Monde getMonde() {
		return monde;
	}

	/**
	 * @param monde
	 */
	public void setMonde(Monde monde) {
		monde.ajouterFourmiliere(this);
		this.monde = monde;
	}

	public Fourmiliere(Monde monde, Case c, int fecondite,int taille_max, int ressources,int tauxEclaireuses) {
		this.fecondite = fecondite;
		this.taille_max = taille_max;
		this.ressources = ressources;
		this.fourmi = new HashSet<Fourmi>();
		this._case = c;
		this.monde = monde;
		this.tauxEclaireuses = tauxEclaireuses;		
		logger.debug("Fourmiliere crée : "+this);
		_case.setFourmiliere(this); //Lie la case à la fourmiliere
		monde.ajouterFourmiliere(this); //Lie le monde à la fourmiliere
		monde.getEvents().get(monde.getTour()).add(new FourmiliereAjouteeEvent(monde.getTour(), new Date(), this));
	}
	
	@Override
	public String toString() {
		return "(Fourmiliere) - "+this.hashCode()+" -> {Case : "+this.get_case().hashCode()+
				" , Fecondite : "+this.getFecondite()+
				" , Taille_max : "+this.getTaille_max()+
				" , Ressources : "+this.getRessources()+"}";
	}
	
	/** 
	 * @param f
	 */
	public void ajouterFourmi(Fourmi f) {
		this.fourmi.add(f);
		logger.debug("Fourmiliere ("+this.hashCode()+") Ajout de la Fourmi : "+f+" ");
	}

	/** 
	 * @param f
	 */
	public void supprimerFourmi(Fourmi f) {
		this.fourmi.remove(f);
		
		if(this.fourmi.size() == 0) {
			this._case.setFourmiliere(null);
			monde.getEvents().get(monde.getTour()).add(new FourmiliereSupprimeeEvent(monde.getTour(), new Date(), this));
			this.getMonde().supprimerFourmiliere(this);
		}
		
		logger.debug("Fourmiliere ("+this.hashCode()+") Suppression de la Fourmi : "+f+" ");
	}

	/** 
	 * @return le nombre de fourmis dans la fourmiliere
	 */
	public int getTotalFourmis() {
		return this.getFourmi().size();
	}

	/** 
	 * @param quantite
	 */
	public void diminuerRessources(int quantite) {
		this.setRessources(this.getRessources()-quantite);
	}

	/** 
	 * @param quantite
	 */
	public void augmenterRessources(int quantite) {
		this.setRessources(this.getRessources()+quantite);
	}

	/** 
	 * @return fourmi
	 */
	public Set<Fourmi> getFourmi() {
		return fourmi;
	}

	/** 
	 * @param fourmi fourmi à définir
	 */
	public void setFourmi(Set<Fourmi> fourmi) {
		this.fourmi = fourmi;
	}

	/** 
	 * @return fecondite
	 */
	public int getFecondite() {
		return fecondite;
	}

	/** 
	 * @param fecondite fecondite à définir
	 */
	public void setFecondite(int fecondite) {
		this.fecondite = fecondite;
	}

	/** 
	 * @return ressources
	 */
	public int getRessources() {
		return ressources;
	}

	/** 
	 * @param ressources ressources à définir
	 */
	public void setRessources(int ressources) {
		int old = this.ressources;		
		this.ressources = ressources;
		monde.getEvents().get(monde.getTour()).add(new FourmiliereRessourcesChangeesEvent(monde.getTour(), new Date(), this,old));
	}

	/** 
	 * @return taille_max
	 */
	public int getTaille_max() {
		return taille_max;
	}

	/** 
	 * @param taille_max taille_max à définir
	 */
	public void setTaille_max(int taille_max) {
		this.taille_max = taille_max;
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
		this._case = _case;
		_case.setFourmiliere(this);
	}
	
	/** 
	 * @return tauxEclaireuses
	 */
	public int get_tauxEclaireuses() {
		return tauxEclaireuses;
	}

}