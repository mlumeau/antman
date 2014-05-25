package org.ICE.PDC.antman.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.ICE.PDC.antman.model.Fourmi;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Pheromone;
import org.ICE.PDC.antman.model.Ressource;
import org.ICE.PDC.antman.model.events.FourmiAjouteeEvent;
import org.ICE.PDC.antman.model.events.FourmiSupprimeeEvent;
import org.ICE.PDC.antman.model.events.FourmiliereAjouteeEvent;
import org.ICE.PDC.antman.model.events.FourmiliereSupprimeeEvent;
import org.ICE.PDC.antman.model.events.MapEvent;
import org.ICE.PDC.antman.model.events.PheromoneAjouteeEvent;
import org.ICE.PDC.antman.model.events.PheromoneSupprimeeEvent;
import org.ICE.PDC.antman.model.events.RessourceAjouteeEvent;
import org.ICE.PDC.antman.model.events.RessourceSupprimeeEvent;

/**
 * Permet de récupérer les statistiques d'une partie à partir d'un objet monde
 */
public class StatsCalculator {

	private ArrayList<Set<Fourmiliere>> fourmilieresAjoutees = new ArrayList<Set<Fourmiliere>>();
	private ArrayList<Set<Fourmiliere>> fourmilieresSupprimees = new ArrayList<Set<Fourmiliere>>();
	private ArrayList<Set<Ressource>> ressourcesAjoutees =  new ArrayList<Set<Ressource>>();	
	private ArrayList<Set<Ressource>> ressourcesSupprimees =  new ArrayList<Set<Ressource>>();
	private ArrayList<Set<Pheromone>> pheromonesAjoutees =  new ArrayList<Set<Pheromone>>();
	private ArrayList<Set<Pheromone>> pheromonesSupprimees =  new ArrayList<Set<Pheromone>>();
	private ArrayList<Set<Fourmi>> fourmisAjoutees =  new ArrayList<Set<Fourmi>>();
	private ArrayList<Set<Fourmi>> fourmisSupprimees =  new ArrayList<Set<Fourmi>>();
	private HashMap<Fourmiliere,ArrayList<Integer>> populationTicks = new HashMap<Fourmiliere, ArrayList<Integer>>();
	private HashMap<Fourmiliere,Integer> population = new HashMap<Fourmiliere, Integer>();
	
	/**
	 * @param monde
	 */
	public StatsCalculator(Monde monde) {

		populationTicks = new HashMap<Fourmiliere, ArrayList<Integer>>();
		population = new HashMap<Fourmiliere, Integer>();	
		for(int i = 0; i <= monde.getTour() ; i++) {
			fourmilieresAjoutees.add(new HashSet<Fourmiliere>());
			fourmilieresSupprimees.add(new HashSet<Fourmiliere>());
			ressourcesAjoutees.add(new HashSet<Ressource>());
			ressourcesSupprimees.add(new HashSet<Ressource>());
			pheromonesAjoutees.add(new HashSet<Pheromone>());
			pheromonesSupprimees.add(new HashSet<Pheromone>());
			fourmisAjoutees.add(new HashSet<Fourmi>());
			fourmisSupprimees.add(new HashSet<Fourmi>());
		}
		for(int i = 0; i <= monde.getTour() ; i++) {

			for(MapEvent e : monde.getEvents().get(i)) {		
				
				if(e instanceof FourmiliereAjouteeEvent) {
					this.fourmilieresAjoutees.get(i).add(((FourmiliereAjouteeEvent)e).getFourmiliere());
				}
				else if (e instanceof FourmiliereSupprimeeEvent) {
					this.fourmilieresSupprimees.get(i).add(((FourmiliereSupprimeeEvent)e).getFourmiliere());
				}				
				else if (e instanceof RessourceAjouteeEvent) {
					this.ressourcesAjoutees.get(i).add(((RessourceAjouteeEvent)e).getRessource());
				}				
				else if (e instanceof RessourceSupprimeeEvent) {
					this.ressourcesSupprimees.get(i).add(((RessourceSupprimeeEvent)e).getRessource());
				}				
				else if (e instanceof PheromoneAjouteeEvent) {
					this.pheromonesAjoutees.get(i).add(((PheromoneAjouteeEvent)e).getPheromone());
				}				
				else if (e instanceof PheromoneSupprimeeEvent) {
					this.pheromonesSupprimees.get(i).add(((PheromoneSupprimeeEvent)e).getPheromone());
				}				
				else if (e instanceof FourmiAjouteeEvent) {
					this.fourmisAjoutees.get(i).add(((FourmiAjouteeEvent)e).getFourmi());
					if(population.get(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere()) == null){
						populationTicks.put(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere(),new ArrayList<Integer>());
						for(int j = 0; j <= monde.getTour() ; j++) {
							populationTicks.get(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere()).add(0);
						}
						populationTicks.get(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere()).add(i,1); 	
						population.put(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere(),1);
					}else{
						populationTicks.get(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere()).add(i,population.get(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere())+1); 	
						population.put(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere(),population.get(((FourmiAjouteeEvent)e).getFourmi().getFourmiliere())+1);
					}
				}				
				else if (e instanceof FourmiSupprimeeEvent) {
					this.fourmisSupprimees.get(i).add(((FourmiSupprimeeEvent)e).getFourmi());
					if(population.get(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere()) == null){
						populationTicks.put(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere(),new ArrayList<Integer>());
						for(int j = 0; j <= monde.getTour() ; j++) {
							populationTicks.get(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere()).add(0);
						}
						populationTicks.get(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere()).add(i,1); 
						population.put(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere(),0);
					}else{
						populationTicks.get(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere()).add(i,population.get(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere())-1); 	
						population.put(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere(),population.get(((FourmiSupprimeeEvent)e).getFourmi().getFourmiliere())-1);
					}
				}
			}
		}
	}
	
	/**
	 * @param tour
	 * @return la population d'une fourmiliere lors d'un tour donné
	 */
	public Integer getPopulationFourmiliereAt(int tour,Fourmiliere fourmiliere) {
		
		Integer population = this.populationTicks.get(fourmiliere).get(tour);
		return population;		
	}	
	
	/**
	 * @param tour
	 * @return la liste des fourmilières ajoutées lors d'un tour donné
	 */
	public Set<Fourmiliere> getFourmilieresAjouteesAt(int tour) {
		
		Set<Fourmiliere> ajoutees = this.fourmilieresAjoutees.get(tour);
		
		if (ajoutees == null) {
			return new HashSet<Fourmiliere>();
		} else {
			return ajoutees;
		}		
	}
	
	/**
	 * @param tour
	 * @return la liste des fourmilières supprimées lors d'un tour donné
	 */
	public Set<Fourmiliere> getFourmilieresSupprimeesAt(int tour) {
		
		Set<Fourmiliere> supprimees = this.fourmilieresSupprimees.get(tour);
		
		if (supprimees == null) {
			return new HashSet<Fourmiliere>();
		} else {
			return supprimees;
		}		
	}	
	
	/**
	 * @param tour
	 * @return  la liste des ressources ajoutées lors d'un tour donné
	 */
	public Set<Ressource> getRessourcesAjouteesAt(int tour) {
		
		Set<Ressource> ajoutees = this.ressourcesAjoutees.get(tour);
		
		if (ajoutees == null) {
			return new HashSet<Ressource>();
		} else {
			return ajoutees;
		}
		
	}
	
	/**
	 * @param tour
	 * @return la liste des ressources supprimées lors d'un tour donné
	 */
	public Set<Ressource> getRessourcesSupprimeesAt(int tour) {
		
		Set<Ressource> supprimees = this.ressourcesSupprimees.get(tour);
		
		if (supprimees == null) {
			return new HashSet<Ressource>();
		} else {
			return supprimees;
		}
		
	}	
	

	/**
	 * @param tour
	 * @return la liste des ressources ajoutées lors d'un tour donné
	 */
	public Set<Pheromone> getPheromonesAjouteesAt(int tour) {
		
		Set<Pheromone> ajoutees = this.pheromonesAjoutees.get(tour);
		
		if (ajoutees == null) {
			return new HashSet<Pheromone>();
		} else {
			return ajoutees;
		}
		
	}
	
	/**
	 * @param tour
	 * @return la liste des ressources supprimées lors d'un tour donné
	 */
	public Set<Pheromone> getPheromonesSupprimeesAt(int tour) {
		
		Set<Pheromone> supprimees = this.pheromonesSupprimees.get(tour);
		
		if (supprimees == null) {
			return new HashSet<Pheromone>();
		} else {
			return supprimees;
		}
		
	}
	
	/**
	 * @param tour
	 * @return la liste des ressources ajoutées lors d'un tour donné
	 */
	public Set<Fourmi> getFourmisAjouteesAt(int tour) {
		
		Set<Fourmi> ajoutees = this.fourmisAjoutees.get(tour);
		
		if (ajoutees == null) {
			return new HashSet<Fourmi>();
		} else {
			return ajoutees;
		}
		
	}
	
	/**
	 * @param tour
	 * @return la liste des ressources supprimées lors d'un tour donné
	 */
	public Set<Fourmi> getFourmisSupprimeesAt(int tour) {
		
		Set<Fourmi> supprimees = this.fourmisSupprimees.get(tour);
		
		if (supprimees == null) {
			return new HashSet<Fourmi>();
		} else {
			return supprimees;
		}
		
	}

	/**
	 * @return
	 */
	public HashMap<Fourmiliere, ArrayList<Integer>> getPopulationTicks() {
		return populationTicks;
	}

	/**
	 * @param populationTicks
	 */
	public void setPopulationTicks(
			HashMap<Fourmiliere, ArrayList<Integer>> populationTicks) {
		this.populationTicks = populationTicks;
	}

}
