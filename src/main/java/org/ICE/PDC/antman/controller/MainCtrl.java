package org.ICE.PDC.antman.controller;

import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.view.MainFrame;
import org.ICE.PDC.antman.view.MainFrameListener;

public class MainCtrl implements MainFrameListener {

	private MainFrame mainFrame;
	private Monde monde;
    private int vitesse = 0;
    private boolean meteoSet = false;
    private boolean abondanceSet = false;
    private int meteo = 0;
    private int abondance = 0;
	private long executionTime = 0;
	private boolean isPlaying = false;
	private final Thread thread;
    
	public MainCtrl(Monde monde){
		this.monde = monde;
		
		this.thread = new Thread(new Runnable() {
			
			public void run() {
				
				while(true) {
					
					try {

						if(vitesse != 0) {
						
							//On attend au moins X secondes entre chaque tour
							if(((4-vitesse)*500)-executionTime > 0 ) {
								Thread.sleep(((4-vitesse)*500)-executionTime);
							}
						
							jouerTour();
						
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
		});
		
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
		this.monde.setListeners(this.mainFrame);
	}

	public void setVitesse(final int v) {
		
		this.vitesse = v;

		if (!this.thread.isAlive()) {
			this.thread.start();
		}
		
	}

	public void jouerTour() {
		
		//Si un tour n'a pas déja été lancé alors on lance un nouveau tour
		if(!this.isPlaying) {
			this.performJouerTour();
		}
		
	}
	
	
	public void performJouerTour() {

			this.isPlaying = true;
			
			long startTime = System.currentTimeMillis();
			
			this.monde.jouerTour();
			
			if(this.abondanceSet) {
				this.monde.setAbondance(this.abondance);
				this.abondanceSet = false;
			}
			
			if(this.meteoSet) {
				this.monde.setMeteo(this.meteo);
				this.meteoSet = false;
			}
			
			this.executionTime  = System.currentTimeMillis()-startTime;
			
			this.isPlaying = false;
	}

	public void setMeteo(int meteo) {
		this.meteoSet = true;
		this.meteo = meteo;
	}

	public void setAbondance(int abondance) {
		this.abondanceSet = true;
		this.abondance = abondance;
	}
	
}