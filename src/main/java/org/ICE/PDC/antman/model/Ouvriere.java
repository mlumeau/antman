/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Ouvriere extends Fourmi {
	
	private static Logger logger = Logger.getLogger(Ouvriere.class);
	
	private final int charge_max;
	
	public int getCharge_max() {
		return charge_max;
	}
	
	private List<Case> chemin_retour;
	
	public List<Case> getChemin_retour() {
		return chemin_retour;
	}
	
	public void setChemin_retour(List<Case> chemin_retour) {
		this.chemin_retour = chemin_retour;
	}
	

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
		SUIVRE_PHEROMONES,
		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		RETOUR,
		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		RECOLTER
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int capacite;

	/** 
	 * @return capacite
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getCapacite() {
		// begin-user-code
		return capacite;
		// end-user-code
	}

	/** 
	 * @param capacite capacite � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setCapacite(int capacite) {
		// begin-user-code
		this.capacite = capacite;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int charge;

	/** 
	 * @return charge
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getCharge() {
		// begin-user-code
		return charge;
		// end-user-code
	}

	/** 
	 * @param charge charge � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setCharge(int charge) {
		// begin-user-code
		this.charge = charge;
		// end-user-code
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
	public void poserPheromones() {
		this.get_case().ajouterPheromone(new Pheromone(this.getFourmiliere(),5));
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void recolterNouriture() {
		Set<Ressource> ressources = this.get_case().getRessource();
		
		for(Ressource r : ressources) {
			
			if(this.getCharge() < this.getCharge_max()) {
			
				if(r.getQuantite() <= this.getCharge_max()-this.getCharge()) {
					//Tout prendre
					r.diminuerQuantite(this.getCharge_max()-this.getCharge());	
					this.setCharge(this.getCharge_max());
					
				} else {
					//Prendre le maximum possible
					r.diminuerQuantite(r.getQuantite());
					this.get_case().supprimerRessource(r); //Suppression de la ressource
					this.setCharge(this.getCharge()+r.getQuantite());
				}
			
			}
			
		}
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void deposerNouriture() {
		this.getFourmiliere().augmenterRessources(this.charge);
		this.charge = 0;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Ouvriere(Fourmiliere fourmiliere) {
		super(fourmiliere);
		this.etat = States.SUIVRE_PHEROMONES;
		this.charge_max = 5;
		this.chemin_retour = null;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @param _case
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Ouvriere(Fourmiliere fourmiliere, Case _case) {
		super(fourmiliere,_case);
		this.etat = States.SUIVRE_PHEROMONES;
		this.charge_max = 5;
		this.chemin_retour = null;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @param _case
	 * @param etat
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Ouvriere(Fourmiliere fourmiliere, Case _case, States etat) {
		super(fourmiliere,_case);
		this.etat = etat;
		this.charge_max = 5;
		this.chemin_retour = null;
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
				logger.info("L'ouvriere ("+this.hashCode()+") mange");
			}
			
			if(this.getAge() >= this.getEsperance_de_vie() || this.getSante() <= 0) {
				//Mort de la fourmi
				this.mourir();
				logger.info("L'ouvriere ("+this.hashCode()+") est morte");
				
			} else {
				
				switch(this.getEtat()) {
				
					case SUIVRE_PHEROMONES:
						
						if(this.get_case().getRessource().size() > 0) {
							this.setEtat(States.RECOLTER);
							logger.info("L'Ouvriere ("+this.hashCode()+") a trouvé des ressources et commence sa recolte");
						
						} else {
						
							//Recherche des phéromones
							List<Case> adjacentes = this.get_case().getCasesInRadius(1);
							List<Case> cases_with_ph = new ArrayList<Case>();
							int puissance_max = 0;
							
							for(Case c : adjacentes) {
								
								int puissance = 0;
								
								for(Pheromone ph : c.getPheromone()) {
									
									if(ph.getFourmiliere().equals(this.getFourmiliere())) {
										puissance += ph.getPuissance();
									}
									
								}
								
								if(puissance > puissance_max) {
									cases_with_ph.clear();
									puissance_max = puissance;
								}
								
								if(puissance == puissance_max) {
									cases_with_ph.add(c);
								}
								
							}
							
							//Deplacement dans une des cases adjacentes ayant le plus de phéromones
							if(cases_with_ph.size() > 0) {
								int index = new Random().nextInt(cases_with_ph.size());
								this.seDeplacer(cases_with_ph.get(index));
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
							//Se déplacer sur la prochaine case
							this.seDeplacer(this.getChemin_retour().get(0));
							this.getChemin_retour().remove(0);
							logger.info("L'Ouvriere ("+this.hashCode()+") se déplace en ("+this.get_case().getX()+","+this.get_case().getY()+")");
							//Déposer des phéromones sur la nouvelle case
							this.poserPheromones();
							logger.info("L'Ouvriere ("+this.hashCode()+") pose des phéromones");
						
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


}