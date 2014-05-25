package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ICE.PDC.antman.model.events.PheromoneAjouteeEvent;
import org.ICE.PDC.antman.model.events.PheromoneSupprimeeEvent;
import org.ICE.PDC.antman.model.events.RessourceAjouteeEvent;
import org.ICE.PDC.antman.model.events.RessourceSupprimeeEvent;
import org.ICE.PDC.antman.utils.PathFinding;
import org.apache.log4j.Logger;

/** 
 * Représente une case du terrain
 */
public class Case implements Serializable {
	
	private static final long serialVersionUID = 1397721472047296408L;
	private static Logger logger = Logger.getLogger(Case.class);
	
	/**Niveau d'obstacle de la case. Plus le niveau est élevé plus l'obstacle est important*/
	private int niveau_obstacle;
	/**Abscisse de la case dans le monde*/
	private int x;
	/**Ordonnée de la case dans le monde*/
	private int y;
	/**Listes des ressources présentes sur case*/
	private Set<Ressource> ressources;
	/**Liste des phéromones présentes sur case*/
	private Set<Pheromone> pheromones;
	/**Liste des fourmis présentes sur case*/
	private Set<Fourmi> fourmis;
	/**Fourmilière de la case (null si aucune fourmilière n'est présente)*/
	private Fourmiliere fourmiliere;
	/**Monde de référence de la case*/
	private Monde monde;
	
	/**
	 * @param monde - Monde de référence de la case
	 * @param x - Abscisse de la case dans le monde
	 * @param y - Ordonnée de la case dans le monde
	 */
	public Case(Monde monde,int x, int y) {
		this.ressources = new HashSet<Ressource>();
		this.fourmis = new HashSet<Fourmi>();
		this.pheromones = new HashSet<Pheromone>();
		//NB : Pour les cases seulement le lien avec le Monde est fait directement dans le constructeur Monde(...)
		this.monde = monde; 
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
		return niveau_obstacle;
	}

	/** 
	 * @param niveau_obstacle niveau_obstacle à définir
	 */
	public void setNiveau_obstacle(int niveau_obstacle) {
		this.niveau_obstacle = niveau_obstacle;
		logger.debug("Niveau d'obstacle de la case ("+x+","+y+") réglé à "+niveau_obstacle);
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
	 * @return phéromones
	 */
	public Set<Pheromone> getPheromones() {
		return pheromones;
	}

	/** 
	 * @param pheromone phéromones à définir
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
		this.monde = monde;
	}

	/** 
	 * @return fourmis
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
	 * @return fourmilière
	 */
	public Fourmiliere getFourmiliere() {
		return fourmiliere;
	}

	/** 
	 * @param fourmiliere fourmilière à définir
	 */
	public void setFourmiliere(Fourmiliere fourmiliere) {
		this.fourmiliere = fourmiliere;
		logger.debug("Case ("+this.hashCode()+") Fourmiliere = ["+fourmiliere+"]");
	}

	/** 
	 * Ajoute une fourmi à la case
	 * @param f
	 */
	public void ajouterFourmi(Fourmi f) {
		this.fourmis.add(f);
		logger.debug("Case ("+this.hashCode()+") Ajout de la Fourmi : "+f+" ");
	}
	
	/** 
	 * Supprime une fourmi de la case
	 * @param f
	 */
	public void supprimerFourmi(Fourmi f) {
		this.fourmis.remove(f);
		logger.debug("Case ("+this.hashCode()+") Suppression de la Fourmi : "+f+" ");
	}

	/** 
	 * Ajoute une ressource à la case - Fire l'event RessourceAjouteeEvent
	 * @param r
	 */
	public void ajouterRessource(Ressource r) {
		this.ressources.add(r);
		//Ajout de l'évennement RessourceAjouteeEvent
		this.monde.fireEvent(new RessourceAjouteeEvent(this.monde.getTour(), new Date(),r));
		logger.debug("Case ("+this.hashCode()+") Ajout de la Ressource : "+r+" ");
	}
	
	/** 
	 * Supprime une ressource de la case - Fire l'event RessourceSupprimeeEvent
	 * @param _r
	 */
	public void supprimerRessource(Ressource r) {
		this.ressources.remove(r);
		//Ajout de l'évennement RessourceSupprimeeEvent
		this.monde.fireEvent(new RessourceSupprimeeEvent(this.monde.getTour(), new Date(),r));
		logger.debug("Case ("+this.hashCode()+") Suppression de la Ressource : "+r+" ");
	}

	/** 
	 * Ajoute une phéromone à la case - Fire l'event PheromoneAjouteeEvent
	 * @param ph
	 */
	public void ajouterPheromone(Pheromone ph) {
		this.pheromones.add(ph);
		//Ajout de l'évennement RessourceAjouteeEvent
		this.monde.fireEvent(new PheromoneAjouteeEvent(this.monde.getTour(), new Date(),ph));
		logger.debug("Case ("+this.hashCode()+") Ajout de Phéromones : "+ph+" ");
	}

	/** 
	 * Supprime une phéromone de la case - Fire l'event PheromoneSupprimeeEvent
	 * @param ph
	 */
	public void supprimerPheromone(Pheromone ph) {
		this.pheromones.remove(ph);
		//Ajout de l'évennement PheromoneSupprimeeEvent
		this.monde.fireEvent(new PheromoneSupprimeeEvent(this.monde.getTour(), new Date(),ph));
		logger.debug("Case ("+this.hashCode()+") Suppression de Phéromones : "+ph+" ");
	}

	/**
	 * Retourne toutes les cases dans un rayon donné autour de la case courante qui ne contiennent pas d'obstacle infranchissable
	 * @param radius
	 * @return Les cases sous forme de liste ordonnée 
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
	 * Permet de trouver le chemin le plus optimisé entre la case courante et une autre case<br/>
	 * V3 : Utilisation de l'algorithme A* pour le PathFinding
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