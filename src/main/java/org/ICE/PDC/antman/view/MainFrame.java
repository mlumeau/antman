/**
 * 
 */
package org.ICE.PDC.antman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;

import net.miginfocom.swing.MigLayout;

import org.ICE.PDC.antman.Launcher;
import org.ICE.PDC.antman.TextAreaAppender;
import org.ICE.PDC.antman.model.Case;
import org.ICE.PDC.antman.model.Eclaireuse;
import org.ICE.PDC.antman.model.Fourmi;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.MapListener;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Ouvriere;
import org.ICE.PDC.antman.model.Pheromone;
import org.ICE.PDC.antman.model.Reine;
import org.ICE.PDC.antman.model.Ressource;
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
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

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
	private JPanel mapPanel;
	private WebButton wbtnJouerTour;
	private WebToggleButton wbtglbtnModeAutomatique;
	private static Logger logger = Logger.getLogger(Launcher.class);
	private WebSlider vitesseWebSlider;
	private WebSlider meteoWebSlider;
	private WebSlider abondanceWebSlider;
	
	public MainFrame() {
		setSize(new Dimension(800, 700));
		setLocation(100, 100);
		
		JTextArea logTextArea = new JTextArea();
		logTextArea.setRows(10);
		logTextArea.setEditable (false);
		logTextArea.getCaret().setSelectionVisible(true);
		logTextArea.getCaret().setVisible(true);

  	   TextAreaAppender appender = new TextAreaAppender ();
  	   appender.setLayout(new SimpleLayout());
  	   TextAreaAppender.setTextArea(logTextArea);
  	   
  	   Logger logRoot = Logger.getRootLogger();
  	   logRoot.addAppender(appender);
  	   logRoot.setLevel(Level.INFO);
		
		
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
		
		wbtnJouerTour = new WebButton();
		wbtnJouerTour.setText("Jouer tour");
		controlPanel.add(wbtnJouerTour, "cell 1 0 1 2,grow");
		
		wbtglbtnModeAutomatique = new WebToggleButton();
		wbtglbtnModeAutomatique.setText("Mode automatique");
		controlPanel.add(wbtglbtnModeAutomatique, "cell 0 0 1 2,growy");
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 30));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setOpaque(true);
		controlPanel.add(separator, "cell 4 0 1 2");
		
		vitesseWebSlider = new WebSlider();
		vitesseWebSlider.setSnapToTicks(true);
		vitesseWebSlider.setMajorTickSpacing (1);
		vitesseWebSlider.setMinimum(1);
		vitesseWebSlider.setMaximum(3);
		java.util.Hashtable<Integer,JLabel> vitesselabelTable = new java.util.Hashtable<Integer,JLabel>();  
	    vitesselabelTable.put(new Integer(1), new JLabel("Lent"));  
	    vitesselabelTable.put(new Integer(2), new JLabel("Moyen"));  
	    vitesselabelTable.put(new Integer(3), new JLabel("Rapide"));  
	    vitesseWebSlider.setLabelTable(vitesselabelTable);
		vitesseWebSlider.setPaintTicks (true);  
		vitesseWebSlider.setPaintLabels (true);   
		controlPanel.add(vitesseWebSlider, "cell 2 1 2 1,grow");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setPreferredSize(new Dimension(0, 30));
		controlPanel.add(separator_1, "cell 7 0 1 2");
		
		meteoWebSlider = new WebSlider();
		meteoWebSlider.setSnapToTicks(true);
		meteoWebSlider.setMajorTickSpacing (1);
		meteoWebSlider.setMinimum(1);
		meteoWebSlider.setMaximum(3);
		java.util.Hashtable<Integer,JLabel> meteolabelTable = new java.util.Hashtable<Integer,JLabel>();  
		meteolabelTable.put(new Integer(1), new JLabel("Mauvais"));  
		meteolabelTable.put(new Integer(2), new JLabel("Moyen"));  
		meteolabelTable.put(new Integer(3), new JLabel("Bon"));  
		meteoWebSlider.setLabelTable(meteolabelTable);
		meteoWebSlider.setPaintTicks (true);  
		meteoWebSlider.setPaintLabels (true);   
		meteoWebSlider.setSnapToTicks(true);
		controlPanel.add(meteoWebSlider, "cell 5 1 2 1,grow");
		
		abondanceWebSlider = new WebSlider();
		abondanceWebSlider.setSnapToTicks(true);
		abondanceWebSlider.setMajorTickSpacing (1);
		abondanceWebSlider.setMinimum(1);
		abondanceWebSlider.setMaximum(3);
		java.util.Hashtable<Integer,JLabel> abondancelabelTable = new java.util.Hashtable<Integer,JLabel>();  
		abondancelabelTable.put(new Integer(1), new JLabel("Basse"));  
		abondancelabelTable.put(new Integer(2), new JLabel("Moyenne"));  
		abondancelabelTable.put(new Integer(3), new JLabel("Haute"));  
		abondanceWebSlider.setLabelTable(abondancelabelTable);
		abondanceWebSlider.setPaintTicks (true);  
		abondanceWebSlider.setPaintLabels (true);   
		abondanceWebSlider.setSnapToTicks(true);
		controlPanel.add(abondanceWebSlider, "cell 8 1 2 1,grow");
		
		mapPanel = new JPanel();
		mapPanel.setLayout(new BoxLayout(mapPanel,  BoxLayout.Y_AXIS));
		
		WebScrollPane mapScrollPane = new WebScrollPane(mapPanel);
		mainPanel.add(mapScrollPane, BorderLayout.CENTER);

		
		WebScrollPane webScrollPane = new WebScrollPane(logTextArea);
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
				wbtnJouerTour.setEnabled(false);
				mainFrameListener.jouerTour();
				
			}
		});
		
		wbtglbtnModeAutomatique.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED)
		        {
		        	wbtglbtnModeAutomatique.setText("Mode manuel");
		        	int i=vitesseWebSlider.getValue();
		        	mainFrameListener.setVitesse(vitesseWebSlider.getValue());
		        }
		        else
		        {
		        	wbtglbtnModeAutomatique.setText("Mode automatique");
		        	mainFrameListener.setVitesse(0);
		        } 
		    }
		});
		
		vitesseWebSlider.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent arg0) {
				if (wbtglbtnModeAutomatique.isSelected()){
					mainFrameListener.setVitesse(vitesseWebSlider.getValue());
				}
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
	
	public void tourJoue(TourJoueEvent _e){
		
		Monde monde = _e.getMonde();
		
		try {
	    	  
			   int nbcases = monde.get_cases().size();
			   int nbcases_sqrt = (int)Math.sqrt(nbcases);
			    
		    	mapPanel.removeAll(); 
		    	
		    	JPanel infos = new JPanel();


		    	 logger.info("Tour n°"+(_e.getTour())+" - Total fourmilieres : "+monde.getFourmilieres().size()+" - Total fourmis : "+monde.getTotalFourmis());
		    	int reines = 0;
		    	int ouvrieres = 0;
		    	int eclaireuses = 0;
		    	
		    	for(Fourmiliere f : monde.getFourmilieres()) {
		    		
		    		for(Fourmi fo : f.getFourmi()) {
		    			
		    			if(fo instanceof Ouvriere)  {
		    				ouvrieres++;
		    			} else if (fo instanceof Eclaireuse) {
		    				eclaireuses++;
		    			} else if (fo instanceof Reine) {
		    				reines++;
		    			}
		    			
		    		}
		    		
		    	}
		    	
		    	String infosString = "TOUR n° "+_e.getTour()+
						 " -- Fourmilières : "+monde.getFourmilieres().size()+
						 " - Fourmis : "+monde.getTotalFourmis()+
						 "  (Reines : "+reines+
						 " / Ouvrières : "+ouvrieres+
						 " / Eclaireuses : "+eclaireuses+")";
						 
		    	infos.add(new JLabel(infosString));
		    	
		    	JPanel map = new JPanel(new GridLayout(nbcases_sqrt,nbcases_sqrt));
		    	map.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		    	
				for(int x=0; x<nbcases_sqrt; x++) {
					
					for(int y=0; y<nbcases_sqrt; y++) {
						final Case current = monde.getCaseAt(x,y);
						
						final JLabel label = new JLabel("", SwingConstants.CENTER);
					    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					    label.setOpaque(true);
					    label.setSize(10,10);
					    
						if(current.getFourmis().size() > 0) {
							label.setText("F");
						}
					    
						if(current.getFourmiliere() != null) {
							label.setBackground(Color.BLACK);
							
							label.setText(String.valueOf(current.getFourmiliere().getRessources()));
							
						} else if(current.getRessources().size() > 0) {
							label.setBackground(Color.YELLOW);
							
							int q = 0;
							
							for(Ressource r : current.getRessources()) {
								q+= r.getQuantite();
							}
							
							label.setText(String.valueOf(q));
							
						} else if (current.getPheromones().size() > 0) {
						
							int ph = 0;
							
							for(Pheromone p : current.getPheromones()) {
								
								ph+= p.getPuissance();
							}
						
							if(ph != 0) {
								
								int gb = 0;
								
								if((10*ph) < 255) {
									gb = 255 - (10*ph);
								}
								
								label.setBackground(new Color(255,gb,gb));
							}
							
						}
						
						map.add(label);
					}
					
				}
				
				
				mapPanel.add(map);
				mapPanel.add(infos);
				
				mapPanel.repaint();
				mapPanel.revalidate();
				
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
		
		wbtnJouerTour.setEnabled(true);
	}

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