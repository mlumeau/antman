/**
 * 
 */
package org.ICE.PDC.antman.controller;

import javax.swing.event.EventListenerList;

import org.ICE.PDC.antman.model.Eclaireuse;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.MapListener;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Reine;
import org.ICE.PDC.antman.view.MainFrame;
import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class MainCtrl {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private MainFrame mainFrame;
	private Monde monde;

	private EventListenerList listeners;
	
	public MainCtrl(int dimension_x,int dimension_y,int meteo,int abondance){
		monde = new Monde(dimension_x, dimension_y, meteo, abondance);
	}
	 
	/** 
	 * @return mainFrame
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public MainFrame getMainFrame() {
		// begin-user-code
		return mainFrame;
		// end-user-code
	}

	/** 
	 * @param mainFrame mainFrame à définir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setMainFrame(MainFrame mainFrame) {
		// begin-user-code
		this.mainFrame = mainFrame;
		// end-user-code
	}
	
	/** 
	 * @param l
	 */
	public void addMapListener(MapListener l) {
		//TODO
	}

	/** 
	 * @param L
	 */
	public void removeMapListener(MapListener L) {
		// TODO
	}
	
	
}