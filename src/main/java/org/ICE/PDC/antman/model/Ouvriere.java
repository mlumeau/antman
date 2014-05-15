package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.ICE.PDC.antman.model.events.FourmiEtatChangeEvent;
import org.ICE.PDC.antman.model.events.RessourceQuantiteChangeeEvent;
import org.apache.log4j.Logger;

/** 
 * Une fourmi ouvriere :
 * -Cherche des ressources en suivant des pistes de phéromones
 * -Ramasse les ressources
 * -Retourne à la fourmiliere en déposant des phéromones sur son passage
 */
public class Ouvriere extends Fourmi implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Ouvriere.class);
	
	public enum States {
		SUIVRE_PHEROMONES,
		RETOUR,
		RECOLTER
	}
	
	private final int charge_max;
	private List<Case> chemin_retour;
	private int capacite;
	private int charge;
	private States etat;
	
	/** 
	 * @param fourmiliere
	 */
	public Ouvriere(Fourmiliere fourmiliere) {
		super(fourmiliere);
		setEtat(States.SUIVRE_PHEROMONES);
		this.charge_max = 20;
		this.chemin_retour = null;
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 */
	public Ouvriere(Fourmiliere fourmiliere, Case _case) {
		super(fourmiliere,_case);
		setEtat(States.SUIVRE_PHEROMONES);
		this.charge_max = 20;
		this.chemin_retour = null;
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 * @param etat
	 */
	public Ouvriere(Fourmiliere fourmiliere, Case _case, States etat) {
		super(fourmiliere,_case);
		setEtat( etat);
		this.charge_max = 20;
		this.chemin_retour = null;
	}

	public void poserPheromones() {
		this.get_case().ajouterPheromone(new Pheromone(this.getFourmiliere(),5));
	}

	public void recolterNouriture() {
		Set<Ressource> ressources = new HashSet<Ressource>(this.get_case().getRessources());		
		for(Ressource r : ressources) {
			Integer old = null;			
			if(this.getCharge() < this.getCharge_max()) {			
				if(r.getQuantite() <= this.getCharge_max()-this.getCharge()) {
					//Tout prendre
					old = r.getQuantite();
					this.setCharge(this.getCharge()+r.getQuantite());
					r.diminuerQuantite(r.getQuantite());
					this.get_case().supprimerRessource(r); //Suppression de la ressource					
				} else {
					old = r.getQuantite();
					//Prendre le maximum possible
					r.diminuerQuantite(this.getCharge_max()-this.getCharge());	
					this.setCharge(this.getCharge_max());
				}			
			}
			if(old != null) {
				//Ajout de l'évennement RessourceQuantiteChangeeEvent
				RessourceQuantiteChangeeEvent e = new RessourceQuantiteChangeeEvent(this.getFourmiliere().getMonde().getTour(), new Date(), r, old);
				this.getFourmiliere().getMonde().getEvents().get(this.getFourmiliere().getMonde().getTour()).add(e);
			}
		}
	}

	public void deposerNouriture() {
		this.getFourmiliere().augmenterRessources(this.charge);
		this.charge = 0;
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
				logger.info("L'ouvriere ("+this.hashCode()+") mange");
			}
			
			if(this.getAge() >= this.getEsperance_de_vie() || this.getSante() <= 0) {
				//Mort de la fourmi
				this.mourir();
				logger.info("L'ouvriere ("+this.hashCode()+") est morte");
				
			} else {
				
				switch(this.getEtat()) {
				
					case SUIVRE_PHEROMONES:
						
						if(this.get_case().getRessources().size() > 0) {
							this.setEtat(States.RECOLTER);
							logger.info("L'Ouvriere ("+this.hashCode()+") a trouvé des ressources et commence sa recolte");
						
						} else {
						
							//Recherche des phéromones
							List<Case> adjacentes = this.get_case().getCasesInRadius(1);
							adjacentes.remove(this.getLast_position()); //On éxclu la derniere case de la recherche
							List<Case> cases_with_max_ph = new ArrayList<Case>();
							int puissance_max = 0;
							
							for(Case c : adjacentes) {
								
								int puissance = 0;
								
								for(Pheromone ph : c.getPheromones()) {
									
									if(ph.getFourmiliere().equals(this.getFourmiliere())) {
										puissance += ph.getPuissance();
									}
									
								}
								
								if(puissance > puissance_max ) {
									cases_with_max_ph.clear();
									puissance_max = puissance;
								}
								
								if(puissance == puissance_max) {
									cases_with_max_ph.add(c);
								}
								
							}
							
							if(cases_with_max_ph.size() > 0) {
								//Deplacement dans une des cases adjacentes ayant le plus de phéromones
								int index = new Random().nextInt(cases_with_max_ph.size());
								this.seDeplacer(cases_with_max_ph.get(index));
							} else {
								//Si aucune case n'est trouvée, la fourmi effectue un déplacement aléatoire
								this.seDeplacerAlea();
							}
						
						}
						
					break;
					
					case RECOLTER:
						this.recolterNouriture();
						this.setEtat(States.RETOUR);
						logger.info("L'Ouvriere ("+this.hashCode()+") a fini de récolter et commene son retour vers la fourmiliere");
					break;
					
					case RETOUR:
						//Calcul du chemin de retour optimisé (effectué une seule fois)
						if(this.chemin_retour == null) {
							this.setChemin_retour(this.get_case().getPathTo(this.getFourmiliere().get_case()));
						}
						
						if(this.chemin_retour.size() > 0) {
							
							//Poser des phéromones
							this.poserPheromones();
							logger.info("L'Ouvriere ("+this.hashCode()+") pose des phéromones");
							
							//Se déplacer sur la prochaine case
							this.seDeplacer(this.getChemin_retour().get(0));
							this.getChemin_retour().remove(0);
							logger.info("L'Ouvriere ("+this.hashCode()+") se déplace en ("+this.get_case().getX()+","+this.get_case().getY()+")");			
						
						} else {
							//Déposer ressources
							this.deposerNouriture();
							logger.info("L'Ouvriere ("+this.hashCode()+") dépose sa nouriture dans la fourmiliere");
							//Retourner dans l'état suivre phéromones
							this.setEtat(States.SUIVRE_PHEROMONES);
							logger.info("L'Ouvriere ("+this.hashCode()+") part de la fourmiliere à la recherche de phéromones");
						}
						
					break;
						
				}

			}
			
	}
	
	/**
	 * @return charge_max
	 */
	public int getCharge_max() {
		return charge_max;
	}
	
	/**
	 * @return chemin_retour
	 */
	public List<Case> getChemin_retour() {
		return chemin_retour;
	}
	
	/**
	 * @param chemin_retour
	 */
	public void setChemin_retour(List<Case> chemin_retour) {
		this.chemin_retour = chemin_retour;
	}

	/** 
	 * @return capacite
	 */
	public int getCapacite() {
		return capacite;
	}

	/** 
	 * @param capacite capacite à définir
	 */
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	/** 
	 * @return charge
	 */
	public int getCharge() {
		return charge;
	}

	/** 
	 * @param charge charge à définir
	 */
	public void setCharge(int charge) {
		this.charge = charge;
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
		
		//Ajout de l'évennement FourmiEtatChangeEvent
		this.getFourmiliere().getMonde().fireEvent(new FourmiEtatChangeEvent(getFourmiliere().getMonde().getTour(), new Date(),this, old));
		
	}

}