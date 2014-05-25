package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.ICE.PDC.antman.model.events.FourmiAjouteeEvent;
import org.ICE.PDC.antman.model.events.FourmiPositionChangeeEvent;
import org.ICE.PDC.antman.model.events.FourmiSupprimeeEvent;
import org.apache.log4j.Logger;

/** 
 * Superclass pour tout les types de fourmis
 */
public abstract class Fourmi implements Serializable {
	
	private static final long serialVersionUID = -8157920655219441612L;
	private static Logger logger = Logger.getLogger(Fourmi.class);
	
	/**Dernière position connue de la fourmi*/
	private Case last_position;
	/**Case courant de la fourmi*/
	private Case _case;
	/**Fourmilière d'appartenance de la fourmi*/
	private Fourmiliere fourmiliere;
	/**Santé maximum de la fourmi (au delà de ce niveau de santé la fourmi ne regagnera plus de vie en mangeant)*/
	private int sante_max;
	/**Espérance de vie de la fourmi (nombre maximal de tour avant que la fourmi ne meurt)*/
	private int esperance_de_vie;
	/**Age de la fourmi*/
	private int age;
	/**Santé de la fourmi*/
	private int sante;
	
	/**
	 * Effectue une action dépendant du type de la Fourmi et de son état actuel
	 */
	public abstract void agir();

	/** 
	 * Fire l'event FourmiAjouteeEvent
	 * @param fourmiliere
	 * @param _case
	 */
	public Fourmi(Fourmiliere fourmiliere, Case _case) {
		this.age = 0; 
		this.esperance_de_vie = 20; 
		this.sante = 20;
		this.sante_max = this.sante; 
		this._case = _case;
		this.fourmiliere = fourmiliere;
		logger.debug("Fourmi crée : "+this);
		_case.ajouterFourmi(this); //Lie la case à la Fourmi
		fourmiliere.ajouterFourmi(this); //Lie la fourmilière à la Fourmi
		
		//Ajout de l'évennement FourmiAjouteeEvent
		this.getFourmiliere().getMonde().fireEvent(new FourmiAjouteeEvent(fourmiliere.getMonde().getTour(), new Date(), this));
	}

	/** 
	 * @param fourmiliere
	 */
	public Fourmi(Fourmiliere fourmiliere) {
		this(fourmiliere,fourmiliere.get_case());//On initialise la case à celle de la fourmilière
	}
	

	@Override
	public String toString() {
		return "(Fourmi) - "+this.hashCode()+" -> { Fourmiliere : "+this.getFourmiliere().hashCode()+
				" , Case : "+this.get_case().hashCode()+
				" , Age : "+this.getAge()+
				" , Espérance de vie : "+this.getEsperance_de_vie()+
				" , Santé : "+this.getSante()+"}";
	}
	
	/**
	 * Fait mourir la fourmi et la supprime du modèle<br/>
	 * Fire l'event FourmiSupprimeeEvent
	 */
	public void mourir() {
		this.get_case().supprimerFourmi(this);
		//Ajout de l'évennement FourmiSupprimeeEvent
		this.getFourmiliere().getMonde().fireEvent(new FourmiSupprimeeEvent(fourmiliere.getMonde().getTour(), new Date(),this));
		//Suppression de la fourmi de la fourmilière
		this.getFourmiliere().supprimerFourmi(this);
		
	}
	
	/**
	 * Déplace aléatoirement la fourmi sur l'une des cases libres adjacentes
	 */
	public void seDeplacerAlea() {
		List<Case> cases = this.get_case().getCasesInRadius(1);
		if(cases.size() > 1) {
			cases.remove(this.getLast_position());
		}
		
		int index = new Random().nextInt(cases.size());
		Case focus = cases.get(index);
		this.set_case(focus);
		logger.debug(this.getClass()+" ("+this.hashCode()+") : Déplacement Aléatoire en "+focus);
	}
	

	/**
	 * Déplace arbitrairement la fourmi sur l'une des cases du monde
	 * @param c
	 */
	public void seDeplacer(Case c) {
		this.set_case(c);
		logger.debug(this.getClass()+" ("+this.hashCode()+") : Déplacement en "+c);
	}
	
	/**
	 * Fait manger la fourmi à partir des ressources contenues dans la fourmilière de celle-ci<br/>
	 * Chaque point de ressource permet de récupérer un point de santé (si celle-ci est inférieure à la santé maximale de la fourmi)
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
	 * @param sante sante_max à définir
	 */
	public void setSante_max(int sante_max) {
		this.sante_max = sante_max;
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
	 * @param sante santé à définir
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
	 * Change la fourmi de case<br/>
	 * Fire l'event FourmiPositionChangeeEvent
	 * @param _case _case à définir
	 */
	public void set_case(Case _case) {	
		Case old = this.get_case();
		if(this._case != null) {
			
			this.setLast_position(this._case);
			this._case.supprimerFourmi(this);
		}
		
		this._case = _case;
		_case.ajouterFourmi(this);
		if(old != null) {
			//Ajout de l'évennement FourmiPositionChangeeEvent
			this.getFourmiliere().getMonde().fireEvent(new FourmiPositionChangeeEvent(this.fourmiliere.getMonde().getTour(), new Date(),this,old.getX(),old.getY()));
		}
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