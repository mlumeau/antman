package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.ICE.PDC.antman.ConfigurationLoader;
import org.ICE.PDC.antman.model.events.FourmiEtatChangeEvent;
import org.apache.log4j.Logger;

/** 
 * Une fourmi éclaireuse :<br/>
 * -Cherche des ressources en se déplaçant aléatoirement<br/>
 * -Retourne à la fourmilière en déposant des phéromones sur son passage
 */
public class Eclaireuse extends Fourmi implements Serializable  {
	
	private static final long serialVersionUID = -3767003284851778910L;
	private static Logger logger = Logger.getLogger(Eclaireuse.class);
	
	/**
	 * États de la fourmi<br/>
	 * CHERCHER_RESSOURCES : Cherche des ressources en se déplaçant aléatoirement<br/>
	 * RETOUR : Retourne à la fourmilière en déposant des phéromones sur son passage
	 */
	public enum States {
		CHERCHER_RESSOURCES,
		RETOUR
	}
	
	/**Permet de stocker le chemin de retour de la fourmi vers sa fourmilière une fois celui-ci calculé*/
	private List<Case> chemin_retour;
	
	/**État courant de la fourmi*/
	private States etat;
	
	/** 
	 * @param fourmiliere
	 */
	public Eclaireuse(Fourmiliere fourmiliere) {
		super(fourmiliere);
		setEtat(States.CHERCHER_RESSOURCES);
		this.chemin_retour = null;
		this.setSante(ConfigurationLoader.SANTE_ECLAIREUSE);
		this.setSante_max(ConfigurationLoader.SANTE_MAX_ECLAIREUSE);
		this.setEsperance_de_vie(ConfigurationLoader.ESPERANCE_VIE_ECLAIREUSE);
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 */
	public Eclaireuse(Fourmiliere fourmiliere, Case _case) {
		super(fourmiliere,_case);
		setEtat( States.CHERCHER_RESSOURCES);
		this.chemin_retour = null;
		this.setSante(ConfigurationLoader.SANTE_ECLAIREUSE);
		this.setSante_max(ConfigurationLoader.SANTE_MAX_ECLAIREUSE);
		this.setEsperance_de_vie(ConfigurationLoader.ESPERANCE_VIE_ECLAIREUSE);
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
		this.setSante(ConfigurationLoader.SANTE_ECLAIREUSE);
		this.setSante_max(ConfigurationLoader.SANTE_MAX_ECLAIREUSE);
		this.setEsperance_de_vie(ConfigurationLoader.ESPERANCE_VIE_ECLAIREUSE);
	}
	
	/**
	 * Dépose des phéromones sur la case courante de la fourmi
	 */
	public void poserPheromones() {
		this.get_case().ajouterPheromone(new Pheromone(this.getFourmiliere(),ConfigurationLoader.PHEROMONES_ECLAIREUSES));
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
	 * Fire l'event FourmiEtatChangeEvent
	 * @param etat état à définir
	 */
	public void setEtat(States etat) {
		States old = this.etat;
		this.etat = etat;
		
		//Ajout de l'évennement FourmiEtatChangeEvent
		this.getFourmiliere().getMonde().fireEvent(new FourmiEtatChangeEvent(getFourmiliere().getMonde().getTour(), new Date(),this, old));
		
	}

	
}