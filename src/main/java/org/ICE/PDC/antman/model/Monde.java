package org.ICE.PDC.antman.model;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.List;

import org.ICE.PDC.antman.model.events.MapEvent;
import org.apache.log4j.Logger;

/** 
 * Le monde contient tout les autres élements de la simmulation
 */
public class Monde {
	
	private static Logger logger = Logger.getLogger(Monde.class);
	private MapListener listener;
	private List<Case> _cases;
	private Set<Case> obstacles;
	private Set<Fourmiliere> fourmilieres;
	private Set<Pheromone> pheromones;
	private int meteo;
	private int abondance;
	private Map<Integer,List<MapEvent>> events;
	private int tour;
	
	/** 
	 * @param longueur
	 * @param largeur
	 * @param meteo
	 * @param abondance
	 */
	public Monde(int dimension_x, int dimension_y, int meteo, int abondance) {
		
		this.fourmilieres = new HashSet<Fourmiliere>();
		
		logger.info("Génération des cases ...");
		this._cases = new ArrayList<Case>();
		
		for(int x=0; x<dimension_x; x++) {
			
			for(int y=0; y<dimension_y; y++) {
				this._cases.add(new Case(this,x,y));
				logger.debug("Ajout de la case "+x+", "+y+" au Monde");
			}
		}
		
		this.meteo = meteo;
		logger.info("Meteo initiale reglée à "+meteo);
		
		this.abondance = abondance;
		logger.info("Abondance initiale reglée à "+abondance);
		
		this.tour = 0;
	}
	
	

	public void creerRessources() {

		Case where = this.get_cases().get(new Random().nextInt(this._cases.size()));
		where.ajouterRessource(new Ressource(this.abondance/(new Random().nextInt(5)+1)));
		
		/*int foodBucket = this.abondance/(new Random().nextInt(10)+1);
		
			while(foodBucket > 0) {
				Case where = this.get_cases().get(new Random().nextInt(this._cases.size()));
				int quantite = new Random().nextInt(foodBucket)+1;
				//int quantite = foodBucket;
				where.ajouterRessource(new Ressource(foodBucket));
				foodBucket = foodBucket - quantite;
			}
		*/
	}

	/** 
	 * @return Le nombre total de fourmis dans le monde
	 */
	public int getTotalFourmis() {
		int total = 0;
		
			for(Fourmiliere f : this.getFourmilieres()) {
				total+=f.getTotalFourmis();
			}

		return total;
	}

	/** 
	 * Fait Avancer la simulation d'un tour
	 */
	public synchronized void jouerTour() {
		
		this.tour++;
		this.events.put(tour,new ArrayList<MapEvent>());
		
		logger.info("Génération des Ressources ...");
		
		this.creerRessources();
		
		logger.info("Evaporation des phéromones ...");
		for(Case c : this.get_cases()) {
			
			Set<Pheromone> pheromones = new HashSet<Pheromone>(c.getPheromones());
			
			for(Pheromone ph : pheromones) {
				ph.diminuerPuissance(1);
				
				if(ph.getPuissance() == 0) {
					c.supprimerPheromone(ph);
				}
				
			}
			
		}
		
		logger.info("Action des fourmis ...");
		/*
		 * On copie la liste de fourmilires pour pouvoir supprimer certaines de celles-ci durant la boucle for 
		 * en évitant le problème des modifications concurentes 
		 */
		Set<Fourmiliere> fourmilieres = new HashSet<Fourmiliere>(this.getFourmilieres());
		
		for(Fourmiliere fl : fourmilieres) {
			
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
	 * @param x
	 * @param y
	 * @throws Exception si la case n'existe pas dans ce terrain
	 */
	public Case getCaseAt(int x, int y) throws Exception {
		
		for(Case c : this.get_cases()) {
			if(c.getX() == x && c.getY() == y) {
				return c;
			}
		}
		
		throw new Exception("La case de coordonnées ("+x+","+y+") n'éxiste pas");
	}
	
	/** 
	 * @param f
	 */
	public void ajouterFourmiliere(Fourmiliere f) {
		this.fourmilieres.add(f);
		logger.debug("Ajout de la Fourmiliere : "+f+" ");
	}

	/** 
	 * @param f
	 */
	public void supprimerFourmiliere(Fourmiliere f) {
		this.fourmilieres.remove(f);
	}

	/** 
	 * @return obstacles
	 */
	public Set<Case> getObstacles() {
		return obstacles;
	}

	/** 
	 * @param obstacle obstacle à définir
	 */
	public void setObstacles(Set<Case> obstacles) {
		this.obstacles = obstacles;
	}

	/** 
	 * @return fourmilieres
	 */
	public Set<Fourmiliere> getFourmilieres() {
		return fourmilieres;
	}

	/** 
	 * @param fourmiliere fourmiliere à définir
	 */
	public void setFourmilieres(Set<Fourmiliere> fourmilieres) {
		this.fourmilieres = fourmilieres;
	}

	/** 
	 * @return pheromones
	 */
	public Set<Pheromone> getPheromones() {
		return pheromones;
	}

	/** 
	 * @param pheromones pheromones à définir
	 */
	public void setPheromones(Set<Pheromone> pheromones) {
		this.pheromones = pheromones;
	}

	/** 
	 * @return meteo
	 */
	public int getMeteo() {
		return meteo;
	}

	/** 
	 * @param meteo meteo à définir
	 */
	public void setMeteo(int meteo) {
		this.meteo = meteo;
	}

	/** 
	 * @return abondance
	 */
	public int getAbondance() {
		return abondance;
	}

	/** 
	 * @param abondance abondance à définir
	 */
	public void setAbondance(int abondance) {
		this.abondance = abondance;
	}

	/** 
	 * @return _case
	 */
	public List<Case> get_cases() {
		return _cases;
	}

	/** 
	 * @param _case _case à définir
	 */
	public void set_cases(List<Case> _case) {
		this._cases = _case;
	}

	/** 
	 * @return listeners
	 */
	public MapListener getListeners() {
		return listener;
	}

	/** 
	 * @param listeners listeners à définir
	 */
	public void setListeners(MapListener listener) {
		this.listener = listener;
	}
	
	/** 
	 * @return tour
	 */
	public int getTour() {
		return tour;
	}	

	public Map<Integer, List<MapEvent>> getEvents() {
		return events;
	}

	public void setEvents(Map<Integer, List<MapEvent>> events) {
		this.events = events;
	}
}