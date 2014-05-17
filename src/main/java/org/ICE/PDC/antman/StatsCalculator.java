package org.ICE.PDC.antman;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ICE.PDC.antman.model.Case;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Ressource;
import org.ICE.PDC.antman.model.events.FourmiliereAjouteeEvent;
import org.ICE.PDC.antman.model.events.FourmiliereSupprimeeEvent;
import org.ICE.PDC.antman.model.events.MapEvent;
import org.ICE.PDC.antman.model.events.TourJoueEvent;

/**
 * Permet de récupérer les statistiques d'une partie à partir d'un objet monde
 *
 */
public class StatsCalculator {

	private List<Monde> mondes;
	private Map<Integer,Set<Fourmiliere>> fourmilieresAjoutees = new HashMap<Integer, Set<Fourmiliere>>();
	private Map<Integer,Set<Fourmiliere>> fourmilieresSupprimees= new HashMap<Integer, Set<Fourmiliere>>();
	
	/**
	 * @param monde
	 */
	public StatsCalculator(Monde monde) {

		for(int i = 0; i <= monde.getTour() ; i++) {

			for(MapEvent e : monde.getEvents().get(i)) {

				fourmilieresAjoutees.put(i,new HashSet<Fourmiliere>());
				fourmilieresSupprimees.put(i,new HashSet<Fourmiliere>());
				
				if(e instanceof TourJoueEvent) {
					Monde m = ((TourJoueEvent) e).getMonde() ;
					this.mondes.add(m);
				}
				else if(e instanceof FourmiliereAjouteeEvent) {
					this.fourmilieresAjoutees.get(i).add(((FourmiliereAjouteeEvent)e).getFourmiliere());
				}
				else if (e instanceof FourmiliereSupprimeeEvent) {
					this.fourmilieresSupprimees.get(i).add(((FourmiliereSupprimeeEvent)e).getFourmiliere());
				}

			}

		}
	}
	
	/**
	 * Retourne la liste des fourmilieres à un tour donné
	 * @param tour
	 */
	public Set<Fourmiliere> getFourmilieresAt(int tour) {
		return this.mondes.get(tour).getFourmilieres();
	}
	
	/**
	 * Retourne la quantitité de ressources à un tour donné
	 * @param tour
	 */
	public int getRessourcesAt(int tour) {
		
		int ressources = 0;
		
		for(Case c : this.mondes.get(tour).get_cases()) {
			
			for(Ressource r : c.getRessources()) {
				ressources += r.getQuantite();
			}
			
		}
		
		return ressources;
	
	}
	
	/**
	 * Retourne la meteo pour un tour donné
	 * @param tour
	 */
	public int getMeteoAt(int tour) {
		return this.mondes.get(tour).getMeteo();
	}
	
	/**
	 * Retourne l'abondance pour un tour donné
	 * @param tour
	 */
	public int getAbondanceAt(int tour) {
		return this.mondes.get(tour).getAbondance();
	}
	
	/**
	 * Retourne la liste des fourmilieres ajoutées lors d'un tour donné
	 * @param tour
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
	 * Retourne la liste des fourmilieres supprimées lors d'un tour donné
	 * @param tour
	 */
	public Set<Fourmiliere> getFourmilieresSupprimeesAt(int tour) {
		
		Set<Fourmiliere> supprimees = this.fourmilieresSupprimees.get(tour);
		
		if (supprimees == null) {
			return new HashSet<Fourmiliere>();
		} else {
			return supprimees;
		}
		
	}	

}
