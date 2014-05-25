package org.ICE.PDC.antman.controller;

import org.ICE.PDC.antman.ConfigurationLoader;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.view.MainFrame;
import org.ICE.PDC.antman.view.MainFrameListener;

/**
 * Contrôleur pour la fenêtre principale de la simulation
 */
public class MainCtrl implements MainFrameListener {

	/**Fenêtre principale - Vue*/
	private MainFrame mainFrame;
	/**Monde - Modèle*/
	private Monde monde;
	/**Vitesse determinant la durée d'un tour*/
    private int vitesse = 0;
    /**Determine si le facteur météo a été modifié ou non*/
    private boolean meteoSet = false;
    /**Determine si le facteur abondance a été modifié ou non*/
    private boolean abondanceSet = false;
    /**Météo courante*/
    private int meteo = 0;
    /**Abondance courante*/
    private int abondance = 0;
    /**Temps d'éxecution du tour au niveau du modèle*/
	private long executionTime = 0;
	/**Determine si un tour est déjà en cours ou non*/
	private boolean isPlaying = false;
	/**Thread utilisée pour jouer un tour*/
	private Thread thread;
    
	public MainCtrl(Monde monde){
		this.monde = monde;
	}
	
	/** 
	 * @return mainFrame
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	/** 
	 * @param mainFrame mainFrame à définir
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.mainFrame.setMainFrameListener(this);
		this.monde.addListener(this.mainFrame);
	}

	/**
	 * Permet de déterminer la vitesse de jeu
	 * @param v : Si la vitesse est paramétrée à 0 le jeu passe en mode manuel
	 */
	public void setVitesse(final int v) {
		
		this.vitesse = v;

		if ((this.thread == null || !this.thread.isAlive()) && this.vitesse > 0) {
			
			this.thread = new Thread(new Runnable() {
				
				public void run() {
					
					try {
						while(vitesse > 0) {
							//On attend au moins X secondes entre chaque tour
							if(((4-vitesse)*1000)/ConfigurationLoader.VITESSE_MULTIPLICATOR-executionTime > 0 ) {
								Thread.sleep(((4-vitesse)*1000)/ConfigurationLoader.VITESSE_MULTIPLICATOR-executionTime);
							}

							jouerTour();
						}
						
					} catch (Exception e) {
						Thread.currentThread().interrupt();
					}
					
				}
				
			});
			
			this.thread.start();
		}
		
	}

	/**
	 * Joue un tour si un tour n'est pas déjà en cours d'éxecution
	 */
	public void jouerTour() {
		
		//Si un tour n'a pas déjà été lancé alors on lance un nouveau tour
		if(!this.isPlaying) {
			this.performJouerTour();
		}
		
	}
	
	/**
	 * Joue effectivement un tour et calcule son temps d'éxecution au niveau du modèle
	 */
	public void performJouerTour() {

			this.isPlaying = true;
			
			long startTime = System.currentTimeMillis();

			if(this.abondanceSet) {
				this.monde.setAbondance(this.abondance);
				this.abondanceSet = false;
			}
			
			if(this.meteoSet) {
				this.monde.setMeteo(this.meteo);
				this.meteoSet = false;
			}

			this.monde.jouerTour();
			
			this.executionTime  = System.currentTimeMillis()-startTime;
			
			this.isPlaying = false;
	}

	/**
	 * @param meteo
	 */
	public void setMeteo(int meteo) {
		this.meteoSet = true;
		this.meteo = meteo;
	}

	/**
	 * @param abondance
	 */
	public void setAbondance(int abondance) {
		this.abondanceSet = true;
		this.abondance = abondance;
	}
	
}