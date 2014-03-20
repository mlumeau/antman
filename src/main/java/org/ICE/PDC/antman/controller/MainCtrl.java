/**
 * 
 */
package org.ICE.PDC.antman.controller;

import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.view.MainFrame;
import org.ICE.PDC.antman.view.MainFrameListener;

public class MainCtrl implements MainFrameListener {

	private MainFrame mainFrame;
	private Monde monde;
    
    
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
		
	}

	public void jouerTour() {
		System.out.println("...TODO...");
	}

	public void setMeteo(int meteo) {
		
	}

	public void setAbondance(int abondance) {
		
	}
	
}