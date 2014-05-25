package org.ICE.PDC.antman.model;

import org.ICE.PDC.antman.model.events.FourmiAjouteeEvent;
import org.ICE.PDC.antman.model.events.FourmiEtatChangeEvent;
import org.ICE.PDC.antman.model.events.FourmiPositionChangeeEvent;
import org.ICE.PDC.antman.model.events.FourmiSupprimeeEvent;
import org.ICE.PDC.antman.model.events.FourmiliereAjouteeEvent;
import org.ICE.PDC.antman.model.events.FourmiliereRessourcesChangeesEvent;
import org.ICE.PDC.antman.model.events.FourmiliereSupprimeeEvent;
import org.ICE.PDC.antman.model.events.PheromoneAjouteeEvent;
import org.ICE.PDC.antman.model.events.PheromonePuissanceChangeeEvent;
import org.ICE.PDC.antman.model.events.PheromoneSupprimeeEvent;
import org.ICE.PDC.antman.model.events.RessourceAjouteeEvent;
import org.ICE.PDC.antman.model.events.RessourceQuantiteChangeeEvent;
import org.ICE.PDC.antman.model.events.RessourceSupprimeeEvent;
import org.ICE.PDC.antman.model.events.TourJoueEvent;

/** 
 * Cette interface permet de créer la lien entre le modèle et la vue (qui l'implémentera)
 */
public interface MapListener {
	
	/**
	 * @param _e
	 */
	public void tourJoue(TourJoueEvent _e);
	
	/**
	 * @param _e
	 */
	public void fourmiPositionChangee(FourmiPositionChangeeEvent _e);

	/**
	 * @param _e
	 */
	public void fourmiEtatChange(FourmiEtatChangeEvent e);

	/**
	 * @param _e
	 */
	public void fourmiAjoutee(FourmiAjouteeEvent _e);

	/**
	 * @param _e
	 */
	public void fourmiSupprimee(FourmiSupprimeeEvent _e);

	/**
	 * @param _e
	 */
	public void ressourceAjoutee(RessourceAjouteeEvent _e);

	/**
	 * @param _e
	 */
	public void ressourceQuantiteChangee(RessourceQuantiteChangeeEvent _e);

	/**
	 * @param _e
	 */
	public void ressourceSupprimee(RessourceSupprimeeEvent _e);

	/**
	 * @param _e
	 */
	public void pheromoneAjoutee(PheromoneAjouteeEvent _e);

	/**
	 * @param _e
	 */
	public void pheromoneSupprimee(PheromoneSupprimeeEvent _e);

	/**
	 * @param _e
	 */
	public void pheromonePuissanceChangee(PheromonePuissanceChangeeEvent _e);

	/**
	 * @param _e
	 */
	public void fourmiliereAjoutee(FourmiliereAjouteeEvent _e);

	/**
	 * @param _e
	 */
	public void fourmiliereSupprimee(FourmiliereSupprimeeEvent _e);

	/**
	 * @param _e
	 */
	public void fourmiliereRessourcesChangees(FourmiliereRessourcesChangeesEvent _e);
	
}