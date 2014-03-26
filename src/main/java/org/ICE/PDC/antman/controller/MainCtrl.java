package org.ICE.PDC.antman.controller;

import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.view.MainFrame;
import org.ICE.PDC.antman.view.MainFrameListener;

public class MainCtrl implements MainFrameListener {

	private MainFrame mainFrame;
	private Monde monde;
    private long wait = 0;
    private boolean meteoSet = false;
    private boolean abondanceSet = false;
    private int meteo = 0;
    private int abondance = 0;
	private long executionTime = 0;
    
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
	}

	public void setVitesse(int vitesse) {
		if(vitesse > 0) {
			this.wait = (1/vitesse)*3000;
		} else {
			this.wait = 0;
		}
	}

	public void jouerTour() {
		
		try {
			
			if(this.wait-this.executionTime > 0) {
				Thread.sleep(this.wait-this.executionTime);
			}
			
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
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
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