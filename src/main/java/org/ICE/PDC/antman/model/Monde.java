/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.List;

import javax.swing.event.EventListenerList;

import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Monde {
	
	private static Logger logger = Logger.getLogger(Monde.class);
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Case> obstacle;

	/** 
	 * @return obstacle
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Case> getObstacle() {
		// begin-user-code
		return obstacle;
		// end-user-code
	}

	/** 
	 * @param obstacle obstacle � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setObstacle(Set<Case> obstacle) {
		// begin-user-code
		this.obstacle = obstacle;
		// end-user-code
	}

	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Fourmiliere> fourmiliere;

	/** 
	 * @return fourmiliere
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Fourmiliere> getFourmiliere() {
		// begin-user-code
		return fourmiliere;
		// end-user-code
	}

	/** 
	 * @param fourmiliere fourmiliere � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setFourmiliere(Set<Fourmiliere> fourmiliere) {
		// begin-user-code
		this.fourmiliere = fourmiliere;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Pheromone> pheromone;

	/** 
	 * @return ph�romone
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Pheromone> getPheromone() {
		// begin-user-code
		return pheromone;
		// end-user-code
	}

	/** 
	 * @param ph�romone ph�romone � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPheromone(Set<Pheromone> pheromone) {
		// begin-user-code
		this.pheromone = pheromone;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int meteo;

	/** 
	 * @return meteo
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getMeteo() {
		// begin-user-code
		return meteo;
		// end-user-code
	}

	/** 
	 * @param meteo meteo � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setMeteo(int meteo) {
		// begin-user-code
		this.meteo = meteo;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int abondance;

	/** 
	 * @return abondance
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getAbondance() {
		// begin-user-code
		return abondance;
		// end-user-code
	}

	/** 
	 * @param abondance abondance � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setAbondance(int abondance) {
		// begin-user-code
		this.abondance = abondance;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<Case> _case;

	/** 
	 * @return _case
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public List<Case> get_case() {
		// begin-user-code
		return _case;
		// end-user-code
	}

	/** 
	 * @param _case _case � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void set_case(List<Case> _case) {
		// begin-user-code
		this._case = _case;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private EventListenerList listeners;

	/** 
	 * @return listeners
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public EventListenerList getListeners() {
		// begin-user-code
		return listeners;
		// end-user-code
	}

	/** 
	 * @param listeners listeners � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setListeners(EventListenerList listeners) {
		// begin-user-code
		this.listeners = listeners;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void creerRessources() {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param f
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void ajouterFourmiliere(Fourmiliere f) {
		this.fourmiliere.add(f);
		logger.debug("Ajout de la Fourmiliere : "+f+" ");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @return
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getTotalFourmis() {
		int total = 0;
		
			for(Fourmiliere f : this.getFourmiliere()) {
				total+=f.getTotalFourmis();
			}

		return total;
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void jouerTour() {
		
		logger.info("Génération des Ressources ...");
		
		int foodBucket = this.abondance;
		
		while(foodBucket > 0) {
			Case where = this.get_case().get(new Random().nextInt(this._case.size()));
			int quantite = new Random().nextInt(foodBucket+1);
			where.ajouterRessource(new Ressource(quantite));
			foodBucket = foodBucket - quantite;
		}
		
		logger.info("Evaporation des phéromones ...");
		//TODO
		logger.info("Action des fourmis ...");
		for(Fourmiliere fl : this.getFourmiliere()) {
			
			/*
			 * On copie la liste de fourmi pour pouvoir supprimer certaines de celles-ci durant la boucle for 
			 * en évitant le problème des modifications concurentes 
			 */
			Set<Fourmi> fourmis = new HashSet<Fourmi>(fl.getFourmi());
			
			for(Fourmi f : fourmis) {
				f.agir();
			}
			
		}
		
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param f
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void supprimerFourmiliere(Fourmiliere f) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param l
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void addMapListener(MapListener l) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param L
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void removeMapListener(MapListener L) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param longueur
	 * @param largeur
	 * @param meteo
	 * @param abondance
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Monde(int dimension_x, int dimension_y, int meteo, int abondance) {
		
		this.fourmiliere = new HashSet<Fourmiliere>();
		
		logger.info("Génération des cases ...");
		this._case = new ArrayList<Case>();
		
		for(int x=0; x<dimension_x; x++) {
			
			for(int y=0; y<dimension_y; y++) {
				this._case.add(new Case(this,x,y));
				logger.debug("Ajout de la case "+x+", "+y+" au Monde");
			}
		}
		
		this.meteo = meteo;
		logger.info("Meteo initiale reglée à "+meteo);
		
		this.abondance = abondance;
		logger.info("Abondance initiale reglée à "+abondance);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param x
	 * @param y
	 * @throws Exception 
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Case getCaseAt(int x, int y) throws Exception {
		
		for(Case c : this.get_case()) {
			if(c.getX() == x && c.getY() == y) {
				return c;
			}
		}
		
		throw new Exception("La case de coordonnées ("+x+","+y+") n'éxiste pas");
	}
}