/**
 * 
 */
package org.ICE.PDC.antman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.RepaintManager;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

import org.ICE.PDC.antman.Launcher;
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
import org.apache.log4j.Logger;

import com.alee.extended.layout.TableLayout;
import com.alee.extended.panel.WebCollapsiblePane;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.menu.WebMenu;
import com.alee.laf.menu.WebMenuBar;
import com.alee.laf.menu.WebMenuItem;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.slider.WebSlider;
import java.awt.Component;
import javax.swing.Box;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */

public class MainFrame extends WebFrame implements MapListener {
	
	
	private static final long serialVersionUID = 1L;
	
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
	private JPanel mondeInfoPanel;
	private JPanel infoPanel;
	private int indexFourmiliere=0;
	
	private HashMap<Fourmiliere,WebCollapsiblePane> fourmilierePanes;
	private HashMap<Fourmiliere,Color> colors;

	private JTextArea logTextArea;

	private JPanel map;
	
	public MainFrame(HashMap<Fourmiliere, Color> c) {
		this();
		colors=c;
	}
	
	public MainFrame() {
		setTitle("Simulation");
		colors=new HashMap<Fourmiliere, Color>();
		fourmilierePanes=new HashMap<Fourmiliere, WebCollapsiblePane>();
		
		setSize(new Dimension(1024, 800));
		setLocationByPlatform(true);
	
		/*
	  	   TextAreaAppender appender = new TextAreaAppender ();
	  	   appender.setLayout(new SimpleLayout());
  	   
	  	   Logger logRoot = Logger.getRootLogger();
	  	   logRoot.addAppender(appender);
	  	   logRoot.setLevel(Level.INFO);
		*/
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		java.util.Hashtable<Integer,JLabel> vitesselabelTable = new java.util.Hashtable<Integer,JLabel>();  
	    vitesselabelTable.put(new Integer(1), new JLabel("Lent"));  
	    vitesselabelTable.put(new Integer(2), new JLabel("Moyen"));  
	    vitesselabelTable.put(new Integer(3), new JLabel("Rapide"));  
		java.util.Hashtable<Integer,JLabel> meteolabelTable = new java.util.Hashtable<Integer,JLabel>();  
		meteolabelTable.put(new Integer(1), new JLabel("Mauvais"));  
		meteolabelTable.put(new Integer(100), new JLabel("Bon"));  
		java.util.Hashtable<Integer,JLabel> abondancelabelTable = new java.util.Hashtable<Integer,JLabel>();  
		abondancelabelTable.put(new Integer(1), new JLabel("Basse"));  
		abondancelabelTable.put(new Integer(100), new JLabel("Haute"));  
		
		this.logTextArea = new JTextArea();
		logTextArea.setRows(10);
		logTextArea.setEditable(false);
		logTextArea.getCaret().setSelectionVisible(false);
		logTextArea.getCaret().setVisible(false);
		/*TextAreaAppender.setTextArea(logTextArea);*/
		
		JPanel viewPanel = new JPanel();
		viewPanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(1.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		viewPanel.add(splitPane, BorderLayout.CENTER);
		
		JPanel mainPanel = new JPanel();
		splitPane.setLeftComponent(mainPanel);
		mainPanel.setLayout(new BorderLayout(0, 0));

    	map = new JPanel();
    	map.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
    	
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
		wbtglbtnModeAutomatique.setIcon(null);
		wbtglbtnModeAutomatique.setText("Mode automatique");
		controlPanel.add(wbtglbtnModeAutomatique, "cell 0 0 1 2,growy");
		
		JSeparator separator = new JSeparator();
		separator.setPreferredSize(new Dimension(0, 30));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setOpaque(true);
		controlPanel.add(separator, "cell 4 0 1 2");
		
		vitesseWebSlider = new WebSlider();
		vitesseWebSlider.setMinorTickSpacing(1);
		vitesseWebSlider.setMaximum(3);
		vitesseWebSlider.setPaintTicks(true);
		vitesseWebSlider.setSnapToTicks(true);
		vitesseWebSlider.setMajorTickSpacing (1);
		vitesseWebSlider.setMinimum(1);
		vitesseWebSlider.setLabelTable(vitesselabelTable);
		vitesseWebSlider.setPaintLabels (true);   
		controlPanel.add(vitesseWebSlider, "cell 2 1 2 1,grow");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setPreferredSize(new Dimension(0, 30));
		controlPanel.add(separator_1, "cell 7 0 1 2");
		
		meteoWebSlider = new WebSlider();
		meteoWebSlider.setMinorTickSpacing(99);
		meteoWebSlider.setMajorTickSpacing (99);
		meteoWebSlider.setMinimum(1);
		meteoWebSlider.setPaintTicks (true);  
		meteoWebSlider.setPaintLabels (true);   
		meteoWebSlider.setLabelTable(meteolabelTable);
		controlPanel.add(meteoWebSlider, "cell 5 1 2 1,grow");
		
		abondanceWebSlider = new WebSlider();
		abondanceWebSlider.setMinorTickSpacing(99);
		abondanceWebSlider.setMajorTickSpacing (99);
		abondanceWebSlider.setMinimum(1);
		abondanceWebSlider.setPaintTicks (true);  
		abondanceWebSlider.setPaintLabels (true);   
		abondanceWebSlider.setLabelTable(abondancelabelTable);
		
		controlPanel.add(abondanceWebSlider, "cell 8 1 2 1,grow");	
		
		mapPanel=new JPanel(new BorderLayout());
		
		mapPanel.add(map,BorderLayout.CENTER);
		
		WebScrollPane mapScrollPane = new WebScrollPane(mapPanel);
		mainPanel.add(mapScrollPane, BorderLayout.CENTER);
		
		mondeInfoPanel = new JPanel();
		mainPanel.add(mondeInfoPanel, BorderLayout.NORTH);
		mondeInfoPanel.setPreferredSize(new Dimension(10, 30));
		mondeInfoPanel.setSize(new Dimension(0, 30));
		mondeInfoPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel mondeInfoLabel = new JLabel("");
		mondeInfoPanel.add(mondeInfoLabel);
		
		
		WebScrollPane webScrollPane = new WebScrollPane(logTextArea);
		splitPane.setRightComponent(webScrollPane);
		
		infoPanel = new JPanel();
		
		JSplitPane mainSplitPane = new JSplitPane();
		mainSplitPane.setPreferredSize(new Dimension(150, 25));
		getContentPane().add(mainSplitPane, BorderLayout.CENTER);
		
		WebScrollPane infoWebScrollPane = new WebScrollPane(infoPanel);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		Component glue = Box.createGlue();
		infoPanel.add(glue);
		infoWebScrollPane.setMinimumSize(new Dimension(150, 150));
		
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

		mainSplitPane.setLeftComponent(infoWebScrollPane);
		mainSplitPane.setRightComponent(viewPanel);
		
		
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
		
		abondanceWebSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mainFrameListener.setAbondance(abondanceWebSlider.getValue());
			}
		});
		
		meteoWebSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				mainFrameListener.setMeteo(meteoWebSlider.getValue());
			}
		});
		
	}
	
	public void initMonde(Monde m){
		for(Fourmiliere f : m.getFourmilieres()){
			
			FourmiliereInfoPanel fpc = new FourmiliereInfoPanel();
			
			WebCollapsiblePane fp = new WebCollapsiblePane ("Fourmilière "+indexFourmiliere, fpc );
			fp.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
	        fp.setExpanded ( true );
	        fp.getTitleComponent().setForeground(colors.get(f));

			
			int nbf=0;
			int nbo=0;
			int nbe=0;
			
			for(Fourmi fo : f.getFourmi()) {
				if(fo instanceof Ouvriere)  {
					nbo++;
				} else if (fo instanceof Eclaireuse) {
					nbe++;
				}
				nbf++;
			}
			
			fpc.getTotalValue().setText(Integer.toString(nbf));
			fpc.getOuvValue().setText(Integer.toString(nbo));
    		fpc.getEclValue().setText(Integer.toString(nbe));
    		fpc.getResValue().setText(Integer.toString(f.getRessources()));
	        
	        fourmilierePanes.put(f,fp);
	        infoPanel.add(fp,infoPanel.getComponentCount()-1);
	        
	        indexFourmiliere++;
		}
		
		map.setLayout(new GridLayout(m.getDimensionX(),m.getDimensionY(),2,2));
	}
	
	
	/*-----------------*/
	/* GETTERS/SETTERS */
	/*-----------------*/
 
	public WebToggleButton getWbtglbtnModeAutomatique() {
		return wbtglbtnModeAutomatique;
	}

	public WebSlider getVitesseWebSlider() {
		return vitesseWebSlider;
	}

	public WebSlider getMeteoWebSlider() {
		return meteoWebSlider;
	}

	public WebSlider getAbondanceWebSlider() {
		return abondanceWebSlider;
	}


	public MainFrameListener getMainFrameListener() {
		return mainFrameListener;
	}

	public void setMainFrameListener(MainFrameListener mainFrameListener) {
		this.mainFrameListener = mainFrameListener;
	}
	
	/*-----------------*/
	/* EVENT LISTENERS */
	/*-----------------*/
	
	public void tourJoue(final TourJoueEvent _e){
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				Monde monde = _e.getMonde();
				
				try {
						map.removeAll();
					
				    	 logger.info("Tour n°"+(_e.getTour())+" - Total fourmilieres : "+monde.getFourmilieres().size()+" - Total fourmis : "+monde.getTotalFourmis());
				    	int reines = 0;
				    	int ouvrieres = 0;
				    	int eclaireuses = 0;
				    	
				    	for(Fourmiliere f : monde.getFourmilieres()) {
				    		
							FourmiliereInfoPanel fpc =  (FourmiliereInfoPanel) fourmilierePanes.get(f).getContent();
							
				    		int nbf=0;
				    		int nbo=0;
				    		int nbe=0;
				    		
				    		for(Fourmi fo : f.getFourmi()) {
				    			if(fo instanceof Ouvriere)  {
				    				ouvrieres++;
				    				nbo++;
				    			} else if (fo instanceof Eclaireuse) {
				    				eclaireuses++;
				    				nbe++;
				    			} else if (fo instanceof Reine) {
				    				reines++;
				    			}
				    			nbf++;
				    		}
				    	
				    		fpc.getTotalValue().setText(Integer.toString(nbf));
				    		fpc.getOuvValue().setText(Integer.toString(nbo));
				    		fpc.getEclValue().setText(Integer.toString(nbe));
				    		fpc.getResValue().setText(Integer.toString(f.getRessources()));
				    		
				    	}
				    	
				    	String infosString = "TOUR n° "+_e.getTour()+
								 " -- Fourmilières : "+monde.getFourmilieres().size()+
								 " - Fourmis : "+monde.getTotalFourmis()+
								 "  (Reines : "+reines+
								 " / Ouvrières : "+ouvrieres+
								 " / Eclaireuses : "+eclaireuses+")";
								 
				    	((JLabel) mondeInfoPanel.getComponent(0)).setText(infosString);
				    	
						for(int x=0; x<monde.getDimensionX(); x++) {
							
							for(int y=0; y<monde.getDimensionY(); y++) {
								final Case current = monde.getCaseAt(x,y);
								
								final JLabel label = new JLabel("", SwingConstants.CENTER);
							    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							    label.setOpaque(true);
							    label.setIgnoreRepaint(true);
								map.add(label);
								
								
								if(current.getFourmiliere() != null) {
									
									label.setBackground(colors.get(current.getFourmiliere()));
									
									label.setText(String.valueOf(current.getFourmiliere().getRessources()));
									label.setForeground(Color.BLACK);
									
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
								if (current.getFourmiliere() == null && current.getFourmis().size() > 0) {
									label.setText("F");
						    		label.setForeground(colors.get(current.getFourmis().iterator().next().getFourmiliere()));
								}
								if(current.getRessources().size() > 0) {
									label.setBackground(Color.ORANGE);
									
									int q = 0;
									
									for(Ressource r : current.getRessources()) {
										q+= r.getQuantite();
									}
									
									label.setText(String.valueOf(q));
									
								}
							    
								if(current.getNiveau_obstacle() > 0) {
									label.setBackground(Color.BLACK);
								}

							}

						}
						
						
				      } catch (Exception e) {
				    	  e.printStackTrace();
				      }
					

					wbtnJouerTour.setEnabled(true);
					
					if(monde.getTotalFourmis()==0){
						mainFrameListener.setVitesse(0);
						wbtglbtnModeAutomatique.setSelected(false);
					}
			}
		});
		
	}

	public void addTextEvent(final String logText) {
		
		 SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	logTextArea.append(logText+"\n");
	            }
	        });
	}
		


	public void fourmiPositionChangee(FourmiPositionChangeeEvent e) {
		Fourmi f = e.getFourmi();
		Case c = e.getFourmi().get_case();
		this.addTextEvent("La fourmi "+f+" se déplace en "+c.getX()+"/"+c.getY());
	}

	public void fourmiEtatChange(FourmiEtatChangeEvent e) {
		Fourmi f = e.getFourmi();
		this.addTextEvent("La fourmi "+f+"a changé d'état"); //TODO ADD STATE
	}


	public void fourmiAjoutee(FourmiAjouteeEvent e) {
		Fourmi f = e.getFourmi();
		this.addTextEvent("Une nouvelle fourmi "+f.getClass()+" a été ajoutée à la fourmiliere "+f.getFourmiliere());
	}

	public void fourmiSupprimee(FourmiSupprimeeEvent e) {
		Fourmi f = e.getFourmi();
		this.addTextEvent("La fourmi "+f+" est morte");
	}
	
	public void fourmiliereAjoutee(FourmiliereAjouteeEvent e) {
		
		Case c = e.getFourmiliere().get_case();
		this.addTextEvent("Une nouvelle fourmiliere a été ajoutée en "+c.getX()+"/"+c.getY());
		Fourmiliere f = e.getFourmiliere();

		Random rand = new Random();
		colors.put(f, new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
		
		FourmiliereInfoPanel fpc = new FourmiliereInfoPanel();
		
		WebCollapsiblePane fp = new WebCollapsiblePane ("Fourmilière "+indexFourmiliere, fpc );
		fp.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        fp.setExpanded ( true );
        fp.getTitleComponent().setForeground(colors.get(f));

		int nbf=0;
		int nbo=0;
		int nbe=0;
		
		for(Fourmi fo : f.getFourmi()) {
			if(fo instanceof Ouvriere)  {
				nbo++;
			} else if (fo instanceof Eclaireuse) {
				nbe++;
			}
			nbf++;
		}
		
		fpc.getTotalValue().setText(Integer.toString(nbf));
		fpc.getOuvValue().setText(Integer.toString(nbo));
		fpc.getEclValue().setText(Integer.toString(nbe));
		fpc.getResValue().setText(Integer.toString(f.getRessources()));
		
        fourmilierePanes.put(f,fp);
        infoPanel.add(fp,infoPanel.getComponentCount()-1);
        
		indexFourmiliere++;
	}

	public void fourmiliereSupprimee(FourmiliereSupprimeeEvent e) {
		Fourmiliere f = e.getFourmiliere();
        infoPanel.remove(fourmilierePanes.get(f));
        colors.remove(f);
		fourmilierePanes.remove(f);

		this.addTextEvent("La fourmiliere "+e.getFourmiliere()+" a été supprimée");
	}

	public void ressourceAjoutee(RessourceAjouteeEvent e) {
		this.addTextEvent("Ajout de "+e.getRessource().getQuantite()+" ressource(s)");
	}

	public void ressourceQuantiteChangee(RessourceQuantiteChangeeEvent e) {

	}

	public void ressourceSupprimee(RessourceSupprimeeEvent e) {
		//Nothing to do here
	}

	public void pheromoneAjoutee(PheromoneAjouteeEvent e) {
		// TODO Module de remplacement de méthode auto-généré
		
	}

	public void pheromoneSupprimee(PheromoneSupprimeeEvent e) {
		// TODO Module de remplacement de méthode auto-généré
		
	}

	public void pheromonePuissanceChangee(PheromonePuissanceChangeeEvent e) {
		// TODO Module de remplacement de méthode auto-généré
		
	}

	public void fourmiliereRessourcesChangees(
			FourmiliereRessourcesChangeesEvent e) {
		// TODO Module de remplacement de méthode auto-généré
		
	}

	class FourmiliereInfoPanel extends JPanel{
		private static final long serialVersionUID = 6088887840869041715L;
		private JLabel totalLabel;
		private JLabel totalValue;
		private JLabel ouvLabel;
		private JLabel ouvValue;
		private JLabel eclLabel;
		private JLabel eclValue;
		private JLabel resLabel;
		private JLabel resValue;
		
		public JLabel getTotalValue() {
			return totalValue;
		}

		public JLabel getOuvValue() {
			return ouvValue;
		}

		public JLabel getEclValue() {
			return eclValue;
		}

		public JLabel getResValue() {
			return resValue;
		}

		public FourmiliereInfoPanel() {
			
			this.setLayout(new GridBagLayout());
			
			totalLabel = new JLabel(" Total fourmis : ");
			ouvLabel = new JLabel(" Ouvrières : ");
			eclLabel = new JLabel(" Éclaireuses : ");
			resLabel = new JLabel(" Ressources : ");
			
			totalValue = new JLabel("0");
			ouvValue = new JLabel("0");
			eclValue = new JLabel("0");
			resValue = new JLabel("0");
			
			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx=0.5;
			c.weighty=0.5;
			
			c.gridx=0;
			c.gridy=0;
			c.gridwidth=3;
			this.add(totalLabel,c);

			c.gridx=4;
			c.gridy=0;
			c.gridwidth=1;
			this.add(totalValue,c);
			
			c.gridx=0;
			c.gridy=1;
			c.gridwidth=3;
			this.add(ouvLabel,c);
			
			c.gridx=4;
			c.gridy=1;
			c.gridwidth=1;
			this.add(ouvValue,c);
			
			c.gridx=0;
			c.gridy=2;
			c.gridwidth=3;
			this.add(eclLabel,c);
			
			c.gridx=4;
			c.gridy=2;
			c.gridwidth=1;
			this.add(eclValue,c);
			
			c.gridx=0;
			c.gridy=3;
			c.gridwidth=3;
			this.add(resLabel,c);
			
			c.gridx=4;
			c.gridy=3;
			c.gridwidth=1;
			this.add(resValue,c);
		}
		
		
	}
}