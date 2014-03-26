/**
 * 
 */
package org.ICE.PDC.antman.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import org.ICE.PDC.antman.model.MapListener;
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

import com.alee.extended.panel.WebAccordion;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.slider.WebSlider;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */

public class MainFrame extends JFrame implements MapListener {
	
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private MainFrameListener mainFrameListener;

	
	public MainFrame() {
		setSize(new Dimension(800, 700));
		setLocation(100, 100);
		
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel viewPanel = new JPanel();
		getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewPanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		viewPanel.add(splitPane, BorderLayout.CENTER);
		
		JPanel mainPanel = new JPanel();
		splitPane.setLeftComponent(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel controlPanel = new JPanel();
		controlPanel.setOpaque(false);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		controlPanel.setLayout(new MigLayout("", "[][][][119.00][-52.00][][][][][]", "[][]"));
		
		JLabel speedLabel = new JLabel("Vitesse mode auto");
		controlPanel.add(speedLabel, "cell 3 0");
		
		JLabel meteoLabel = new JLabel("Météo");
		controlPanel.add(meteoLabel, "cell 6 0");
		
		JLabel abondanceLabel = new JLabel("Abondance");
		controlPanel.add(abondanceLabel, "cell 9 0");
		
		WebButton wbtnJouerTour = new WebButton();
		wbtnJouerTour.setText("Jouer tour");
		controlPanel.add(wbtnJouerTour, "cell 1 0 1 2,grow");
		
		WebToggleButton wbtglbtnModeAutomatique = new WebToggleButton();
		wbtglbtnModeAutomatique.setText("Mode automatique");
		controlPanel.add(wbtglbtnModeAutomatique, "cell 0 0 1 2,growy");
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 30));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setOpaque(true);
		controlPanel.add(separator, "cell 4 0 1 2");
		
		WebSlider vitesseWebSlider = new WebSlider();
		vitesseWebSlider.setSnapToTicks(true);
		vitesseWebSlider.setMajorTickSpacing (10);
		vitesseWebSlider.setMinimum(10);
		vitesseWebSlider.setMaximum(30);
		java.util.Hashtable<Integer,JLabel> vitesselabelTable = new java.util.Hashtable<Integer,JLabel>();  
	    vitesselabelTable.put(new Integer(10), new JLabel("Lent"));  
	    vitesselabelTable.put(new Integer(20), new JLabel("Moyen"));  
	    vitesselabelTable.put(new Integer(30), new JLabel("Rapide"));  
	    vitesseWebSlider.setLabelTable(vitesselabelTable);
		vitesseWebSlider.setPaintTicks (true);  
		vitesseWebSlider.setPaintLabels (true);   
		controlPanel.add(vitesseWebSlider, "cell 2 1 2 1,grow");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setPreferredSize(new Dimension(0, 30));
		controlPanel.add(separator_1, "cell 7 0 1 2");
		
		WebSlider meteoWebSlider = new WebSlider();
		meteoWebSlider.setSnapToTicks(true);
		meteoWebSlider.setMajorTickSpacing (10);
		meteoWebSlider.setMinimum(10);
		meteoWebSlider.setMaximum(30);
		java.util.Hashtable<Integer,JLabel> meteolabelTable = new java.util.Hashtable<Integer,JLabel>();  
		meteolabelTable.put(new Integer(10), new JLabel("Mauvais"));  
		meteolabelTable.put(new Integer(20), new JLabel("Moyen"));  
		meteolabelTable.put(new Integer(30), new JLabel("Bon"));  
		meteoWebSlider.setLabelTable(meteolabelTable);
		meteoWebSlider.setPaintTicks (true);  
		meteoWebSlider.setPaintLabels (true);   
		meteoWebSlider.setSnapToTicks(true);
		controlPanel.add(meteoWebSlider, "cell 5 1 2 1,grow");
		
		WebSlider abondanceWebSlider = new WebSlider();
		abondanceWebSlider.setSnapToTicks(true);
		abondanceWebSlider.setMajorTickSpacing (10);
		abondanceWebSlider.setMinimum(10);
		abondanceWebSlider.setMaximum(30);
		java.util.Hashtable<Integer,JLabel> abondancelabelTable = new java.util.Hashtable<Integer,JLabel>();  
		abondancelabelTable.put(new Integer(10), new JLabel("Basse"));  
		abondancelabelTable.put(new Integer(20), new JLabel("Moyenne"));  
		abondancelabelTable.put(new Integer(30), new JLabel("Haute"));  
		abondanceWebSlider.setLabelTable(abondancelabelTable);
		abondanceWebSlider.setPaintTicks (true);  
		abondanceWebSlider.setPaintLabels (true);   
		abondanceWebSlider.setSnapToTicks(true);
		controlPanel.add(abondanceWebSlider, "cell 8 1 2 1,grow");
		
		JPanel mapPanel = new JPanel();
		
		WebScrollPane mapScrollPane = new WebScrollPane(mapPanel);
		mainPanel.add(mapScrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(10);
		
		WebScrollPane webScrollPane = new WebScrollPane(textArea);
		splitPane.setRightComponent(webScrollPane);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		WebAccordion webAccordion = new WebAccordion();
		webAccordion.setPreferredSize(new Dimension(150, 6));
		panel.add(webAccordion);
		
		WebMenuBar webMenuBar = new WebMenuBar();
		setJMenuBar(webMenuBar);
		
		WebMenu wbmnFichier = new WebMenu();
		wbmnFichier.setText("Fichier");
		webMenuBar.add(wbmnFichier);
		
		WebMenu wbmnSimulation = new WebMenu();
		wbmnSimulation.setText("Simulation");
		webMenuBar.add(wbmnSimulation);
		
		WebMenuItem wbmntmHelp = new WebMenuItem();
		wbmntmHelp.setText("Help");
		webMenuBar.add(wbmntmHelp);
		
		
		
		/*-----------------*/
		/*ACTION LISTENERS */
		/*-----------------*/
		
		wbtnJouerTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrameListener.jouerTour();
			}
		});
		
		
	}
	
	
	
	/*-----------------*/
	/* GETTERS/SETTERS */
	/*-----------------*/
 
	/** 
	 * @return mainFrameListener
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public MainFrameListener getMainFrameListener() {
		// begin-user-code
		return mainFrameListener;
		// end-user-code
	}

	/** 
	 * @param mainFrameListener mainFrameListener � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setMainFrameListener(MainFrameListener mainFrameListener) {
		// begin-user-code
		this.mainFrameListener = mainFrameListener;
		// end-user-code
	}
	
	
	
	/*-----------------*/
	/* EVENT LISTENERS */
	/*-----------------*/
	

	/** 
	 * (non-Javadoc)
	 * @see MapListener#fourmiPositionChangee(FourmiPositionChangeeEvent _e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void fourmiPositionChangee(FourmiPositionChangeeEvent _e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#fourmiEtatChange(FourmiEtatChangeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void fourmiEtatChange(FourmiEtatChangeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#fourmiAjoutee(FourmiAjouteeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void fourmiAjoutee(FourmiAjouteeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#fourmiSupprimee(FourmiSupprimeeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void fourmiSupprimee(FourmiSupprimeeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#ressourceAjoutee(RessourceAjouteeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void ressourceAjoutee(RessourceAjouteeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#ressourceQuantiteChangee(RessourceQuantiteChangeeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void ressourceQuantiteChangee(RessourceQuantiteChangeeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#ressourceSupprimee(RessourceSupprimeeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void ressourceSupprimee(RessourceSupprimeeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#PheromoneAjoutee(PheromoneAjouteeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void PheromoneAjoutee(PheromoneAjouteeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#PheromoneSupprimee(PheromoneSupprimeeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void PheromoneSupprimee(PheromoneSupprimeeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#PheromonePuissanceChangee(PheromonePuissanceChangeeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void PheromonePuissanceChangee(PheromonePuissanceChangeeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#FourmiliereAjoutee(FourmiliereAjouteeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void FourmiliereAjoutee(FourmiliereAjouteeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#FourmiliereSupprimee(FourmiliereSupprimeeEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void FourmiliereSupprimee(FourmiliereSupprimeeEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}

	/** 
	 * (non-Javadoc)
	 * @see MapListener#FourmiliereRessourcesChangees(FourmiliereRessourcesChangeesEvent e)
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void FourmiliereRessourcesChangees(
			FourmiliereRessourcesChangeesEvent e) {
		// begin-user-code
		// TODO Module de remplacement de m�thode auto-g�n�r�

		// end-user-code
	}
}