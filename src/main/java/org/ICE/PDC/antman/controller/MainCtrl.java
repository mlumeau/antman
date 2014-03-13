/**
 * 
 */
package org.ICE.PDC.antman.controller;

import java.util.Timer;

import javax.swing.event.EventListenerList;

import org.ICE.PDC.antman.model.MapListener;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.view.MainFrame;

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

    private Timer timer;
	
	public MainCtrl(Monde monde){
		this.monde = monde;
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
	
}