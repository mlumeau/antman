/**
 * 
 */
package org.ICE.PDC.antman.controller;

import java.util.Timer;

import javax.swing.event.EventListenerList;

import org.ICE.PDC.antman.model.MapListener;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.view.MainFrame;
import org.ICE.PDC.antman.view.MainFrameListener;

public class MainCtrl implements MainFrameListener {

	private MainFrame mainFrame;
	private Monde monde;
    private Timer timer;
    
    
    
	
	public MainCtrl(Monde monde){
		this.monde = monde;
		this.mainFrame = new MainFrame();
		this.ticker
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
	}

	public void setVitesse(int vitesse) {
		// TODO Auto-generated method stub
	}

	public void jouerTour() {
		// TODO Auto-generated method stub
	}

	public void setMeteo(int meteo) {
		// TODO Auto-generated method stub
	}

	public void setAbondance(int abondance) {
		// TODO Auto-generated method stub
	}
	
}