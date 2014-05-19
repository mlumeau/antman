package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ICE.PDC.antman.PathFinding;
import org.ICE.PDC.antman.model.events.PheromoneAjouteeEvent;
import org.ICE.PDC.antman.model.events.PheromoneSupprimeeEvent;
import org.ICE.PDC.antman.model.events.RessourceAjouteeEvent;
import org.ICE.PDC.antman.model.events.RessourceSupprimeeEvent;
import org.apache.log4j.Logger;

/** 
 * Une case du terrain
 */
public class Case implements Serializable {
	
	private static final long serialVersionUID = 1397721472047296408L;

	private static Logger logger = Logger.getLogger(Case.class);
	
	private int niveau_obstacle;
	private int x;
	private int y;
	private Set<Ressource> ressources;
	private Set<Pheromone> pheromones;
	private Set<Fourmi> fourmis;
	private Fourmiliere fourmiliere;
	private Monde monde;
	
	/**
	 * @param monde
	 * @param x
	 * @param y
	 */
	public Case(Monde monde,int x, int y) {
		this.ressources = new HashSet<Ressource>();
		this.fourmis = new HashSet<Fourmi>();
		this.pheromones = new HashSet<Pheromone>();
		//NB : Pour les cases seulement le lien avec le Monde est fait directement dans le contructeur Monde(...)
		this.monde = monde; 
		//TODO add non existence control here?
		this.x = x;
		this.y = y;
		this.niveau_obstacle = 0;
		logger.debug("Case crée : "+this);
	}
	
	
	@Override
	public String toString() {
		
		String ressources = "[";
		
		for(Ressource r : this.getRessources()) {
			if(!ressources.isEmpty()) ressources+=", ";
			ressources+= r;
		}
		
		ressources = "["+ressources+"]";
		
		String fourmis = "";
		
		for(Fourmi f : this.getFourmis()) {
			if(!fourmis.isEmpty()) fourmis+=", ";
			fourmis+= f.hashCode();
		}
		
		fourmis = "["+fourmis+"]";
		
		String fourmiliere = "";
		
		if(this.getFourmiliere() != null) {
			fourmiliere = Integer.toString(this.getFourmiliere().hashCode());
		}
		
		return "(Case) - "+this.hashCode()+" -> {X : "+this.getX()+
				" , Y : "+this.getY()+
				" , Niveau_obstacle : "+this.getNiveau_obstacle()+
				" , Ressources : "+ressources+
				" , Fourmis : "+fourmis+"}"+
				", Fourmiliere : "+fourmiliere;
	}
	

	/** 
	 * @return niveau_obstacle
	 */
	public int getNiveau_obstacle() {
		// begin-user-code
		return niveau_obstacle;
		// end-user-code
	}

	/** 
	 * @param niveau_obstacle niveau_obstacle � d�finir
	 */
	public void setNiveau_obstacle(int niveau_obstacle) {
		this.niveau_obstacle = niveau_obstacle;
		logger.debug("Niveau d'obstacle de la case ("+x+","+y+") reglé à "+niveau_obstacle);
	}

	/** 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/** 
	 * @param x x à définir
	 */
	public void setX(int x) {
		this.x = x;
	}


	/** 
	 * @return ressources
	 */
	public Set<Ressource> getRessources() {
		return ressources;
	}

	/** 
	 * @param ressources ressources à définir
	 */
	public void setRessources(Set<Ressource> ressources) {
		this.ressources = ressources;
	}

	/** 
	 * @return pheromones
	 */
	public Set<Pheromone> getPheromones() {
		return pheromones;
	}

	/** 
	 * @param pheromone pheromones à définir
	 */
	public void setPheromones(Set<Pheromone> pheromones) {
		this.pheromones = pheromones;
	}

	/** 
	 * @return monde
	 */
	public Monde getMonde() {
		return monde;
	}

	/** 
	 * @param monde monde à définir
	 */
	public void setMonde(Monde monde) {
		// begin-user-code
		this.monde = monde;
		// end-user-code
	}


	/** 
	 * @return fourmis
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Fourmi> getFourmis() {
		return fourmis;
	}

	/** 
	 * @param fourmis fourmis à définir
	 */
	public void setFourmi(Set<Fourmi> fourmis) {
		this.fourmis = fourmis;
	}

	/** 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/** 
	 * @param y y à définir
	 */
	public void setY(int y) {
		this.y = y;
	}

	/** 
	 * @return fourmiliere
	 */
	public Fourmiliere getFourmiliere() {
		return fourmiliere;
	}

	/** 
	 * @param fourmiliere fourmiliere à définir
	 */
	public void setFourmiliere(Fourmiliere fourmiliere) {
		this.fourmiliere = fourmiliere;
		logger.debug("Case ("+this.hashCode()+") Fourmiliere = ["+fourmiliere+"]");
	}

	/** 
	 * @param f
	 */
	public void ajouterFourmi(Fourmi f) {
		this.fourmis.add(f);
		logger.debug("Case ("+this.hashCode()+") Ajout de la Fourmi : "+f+" ");
	}

	/** 
	 * @param r
	 */
	public void ajouterRessource(Ressource r) {
		this.ressources.add(r);
		
		//Ajout de l'évennement RessourceAjouteeEvent
		this.monde.fireEvent(new RessourceAjouteeEvent(this.monde.getTour(), new Date(),r));
		
		logger.debug("Case ("+this.hashCode()+") Ajout de la Ressource : "+r+" ");
	}

	/** 
	 * @param ph
	 */
	public void ajouterPheromone(Pheromone ph) {
		this.pheromones.add(ph);
		
		//Ajout de l'évennement RessourceAjouteeEvent
		this.monde.fireEvent(new PheromoneAjouteeEvent(this.monde.getTour(), new Date(),ph));

		logger.debug("Case ("+this.hashCode()+") Ajout de Pheromones : "+ph+" ");
	}

	/** 
	 * @param f
	 */
	public void supprimerFourmi(Fourmi f) {
		this.fourmis.remove(f);
		logger.debug("Case ("+this.hashCode()+") Suppression de la Fourmi : "+f+" ");
	}

	/** 
	 * @param _r
	 */
	public void supprimerRessource(Ressource r) {
		this.ressources.remove(r);
		
		//Ajout de l'évennement RessourceSupprimeeEvent
		this.monde.fireEvent(new RessourceSupprimeeEvent(this.monde.getTour(), new Date(),r));

		logger.debug("Case ("+this.hashCode()+") Suppression de la Ressource : "+r+" ");
	}

	/** 
	 * @param ph
	 */
	public void supprimerPheromone(Pheromone ph) {
		this.pheromones.remove(ph);
		
		//Ajout de l'évennement PheromoneSupprimeeEvent
		this.monde.fireEvent(new PheromoneSupprimeeEvent(this.monde.getTour(), new Date(),ph));
		
		logger.debug("Case ("+this.hashCode()+") Suppression de Pheromones : "+ph+" ");
	}

	/**
	 * Retourne toutes les cases dans un rayon donné autour de la case courante qui ne contiennent pas d'obstacle infranchissable
	 * @param radius
	 * @return Les cases sous forme de liste ordonée 
	 */
	public List<Case> getCasesInRadius(int radius) {
		
		List<Case> matched = new ArrayList<Case>();
		String log = "";
		
		for(int x=(this.getX()-radius); x<=(this.getX()+radius); x++) {
			
			for(int y=(this.getY()-radius); y<=(this.getY()+radius); y++) {
				
				if(x != this.getX() || y != this.getY()) {
					
					try {
						Case c = this.getMonde().getCaseAt(x,y);
						
						if(c.getNiveau_obstacle() < 1) {
							matched.add(c);
							log += c;
						}
						
					} catch (Exception e) {
						//Nothing to do here
					}
					
				}
			}
			
		}
		
		logger.debug("Case ("+this.hashCode()+") - Cases dans un rayon de "+radius+" : "+log);
		return matched;
	}
	
	/**
	 * Permet de trouver le chemin le plus optimisé entre la case courante et une autre case
	 * V3 : A* pathfinding
	 * @param target
	 * @return Le chemin sous la forme d'une Liste triée de cases
	 */
	public List<Case> getPathTo(Case target) {
		try {
			return new PathFinding(this, target).findPath();
		} catch (Exception e) {
			logger.error("Fail to finding Path :"+e.getCause());
		}
		return new ArrayList<Case>();
	}

}