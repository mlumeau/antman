package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.ICE.PDC.antman.model.events.FourmiEtatChangeEvent;
import org.apache.log4j.Logger;

/** 
 * Une fourmi éclaireuse :
 * -Cherche des ressources en se déplacant aleatoirement
 * -Retourne à la fourmiliere en déposant des phéromones sur son passage
 */
public class Eclaireuse extends Fourmi implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Eclaireuse.class);
	
	public enum States {
		CHERCHER_RESSOURCES,
		RETOUR
	}
	
	private List<Case> chemin_retour;
	private States etat;
	
	public void poserPheromones() {
		this.get_case().ajouterPheromone(new Pheromone(this.getFourmiliere(),10));
	}

	/** 
	 * @param fourmiliere
	 */
	public Eclaireuse(Fourmiliere fourmiliere) {
		super(fourmiliere);
		setEtat(States.CHERCHER_RESSOURCES);
		this.chemin_retour = null;
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 */
	public Eclaireuse(Fourmiliere fourmiliere, Case _case) {
		super(fourmiliere,_case);
		setEtat( States.CHERCHER_RESSOURCES);
		this.chemin_retour = null;
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 * @param etat
	 */
	public Eclaireuse(Fourmiliere fourmiliere, Case _case, States etat) {
		super(fourmiliere,_case);
		setEtat(etat);
		this.chemin_retour = null;
	}

	/** 
	 * @see Fourmi#agir()
	 */
	public void agir() {
		this.setAge(this.getAge()+1);
		this.setSante(this.getSante()-1);
		
			if(this.get_case().equals(this.getFourmiliere().get_case())) {
				//Manger :)
				this.manger();
				logger.info("L'éclaireuse ("+this.hashCode()+") mange");
			}
			
			if(this.getAge() >= this.getEsperance_de_vie() || this.getSante() <= 0) {
				//Mort de la fourmi
				this.mourir();
				logger.info("L'éclaireuse ("+this.hashCode()+") est morte");
				
			} else {
				
				//Action
				switch(this.getEtat()) {
				
					case CHERCHER_RESSOURCES :
							 //Si la case possède des ressources : Passage dans l'état RETOUR
							 //Sinon : Déplacement aléatoire
							if(this.get_case().getRessources().size() > 0) {
								this.setEtat(States.RETOUR);
								logger.info("L'éclaireuse ("+this.hashCode()+") à trouvé des ressources et commence son retour vers la fourmiliere");
							} else {
								this.seDeplacerAlea();
								logger.info("L'éclaireuse ("+this.hashCode()+") se déplace en ("+this.get_case().getX()+","+this.get_case().getY()+")");
							}
						
					break;
					
					case RETOUR :
						
						//Calcul du chemin de retour optimisé (effectué une seule fois)
						if(this.chemin_retour == null) {
							this.setChemin_retour(this.get_case().getPathTo(this.getFourmiliere().get_case()));
						}
						
						if(this.chemin_retour.size() > 0) {
							//Poser des phéromones
							this.poserPheromones();
							logger.info("L'éclaireuse ("+this.hashCode()+") pose des phéromones");
							//Se déplacer sur la prochaine case
							this.seDeplacer(this.getChemin_retour().get(0));
							this.getChemin_retour().remove(0);
							logger.info("L'éclaireuse ("+this.hashCode()+") se déplace en ("+this.get_case().getX()+","+this.get_case().getY()+")");

						} else {
							//Retourner dans l'état recherche
							this.setEtat(States.CHERCHER_RESSOURCES);
							logger.info("L'éclaireuse ("+this.hashCode()+") part de la fourmiliere à la recherche de ressources");
						}
						
					break;
				
				}
				
			}
		
	}
	
	
	/**
	 * @return chemin_retour
	 */
	public List<Case> getChemin_retour() {
		return this.chemin_retour;
	}
	
	/**
	 * @param chemin_retour
	 */
	public void setChemin_retour(List<Case> chemin_retour) {
		this.chemin_retour = chemin_retour;
	}
	
	/** 
	 * @return etat
	 */
	public States getEtat() {
		return etat;
	}

	/** 
	 * @param etat etat à définir
	 */
	public void setEtat(States etat) {
		States old = this.etat;
		this.etat = etat;
		getFourmiliere().getMonde().getEvents().get(getFourmiliere().getMonde().getTour()).add(new FourmiEtatChangeEvent(getFourmiliere().getMonde().getTour(), new Date(),this, old));
		
	}

	
}