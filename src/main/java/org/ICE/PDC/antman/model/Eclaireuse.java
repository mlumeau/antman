/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.util.List;

import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Eclaireuse extends Fourmi {
	
	private static Logger logger = Logger.getLogger(Eclaireuse.class);
	
	private List<Case> chemin_retour;
	
	public List<Case> getChemin_retour() {
		return this.chemin_retour;
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
		CHERCHER_RESSOURCES,
		/** 
		 * <!-- begin-UML-doc -->
		 * <!-- end-UML-doc -->
		 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
		 */
		RETOUR
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
		this.get_case().ajouterPheromone(new Pheromone(this.getFourmiliere(),10));
	}


	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Eclaireuse(Fourmiliere fourmiliere) {
		super(fourmiliere);
		this.etat = States.CHERCHER_RESSOURCES;
		this.chemin_retour = null;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param fourmiliere
	 * @param _case
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Eclaireuse(Fourmiliere fourmiliere, Case _case) {
		super(fourmiliere,_case);
		this.etat = States.CHERCHER_RESSOURCES;
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
	public Eclaireuse(Fourmiliere fourmiliere, Case _case, States etat) {
		super(fourmiliere,_case);
		this.etat = etat;
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
							if(this.get_case().getRessource().size() > 0) {
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
							//Se déplacer sur la prochaine case
							this.seDeplacer(this.getChemin_retour().get(0));
							this.getChemin_retour().remove(0);
							logger.info("L'éclaireuse ("+this.hashCode()+") se déplace en ("+this.get_case().getX()+","+this.get_case().getY()+")");
							//Déposer des phéromones sur la nouvelle case
							this.poserPheromones();
							logger.info("L'éclaireuse ("+this.hashCode()+") pose des phéromones");
						
						} else {
							//Retourner dans l'état recherche
							this.setEtat(States.CHERCHER_RESSOURCES);
							logger.info("L'éclaireuse ("+this.hashCode()+") part de la fourmiliere à la recherche de ressources");
						}
						
					break;
				
				}
				
			}
		
	}
}