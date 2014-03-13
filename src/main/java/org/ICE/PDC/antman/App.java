package org.ICE.PDC.antman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.ICE.PDC.antman.model.Case;
import org.ICE.PDC.antman.model.Eclaireuse;
import org.ICE.PDC.antman.model.Fourmi;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Ouvriere;
import org.ICE.PDC.antman.model.Pheromone;
import org.ICE.PDC.antman.model.Reine;
import org.ICE.PDC.antman.model.Ressource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

/**
 * Point d'entrée principal du programme : Lance la simulation
 */
public class App 
{
	
	private static Logger logger = Logger.getLogger(App.class);
	private static boolean auto = false;
	private static int i = 0;
	
    public static void main( String[] args )
    {
    	
       try {
    	   
    	   JTextArea textArea = new JTextArea (10, 80);
    	   textArea.setEditable (false);
    	   textArea.getCaret().setSelectionVisible(true);
    	   textArea.getCaret().setVisible(true);
  
    	   TextAreaAppender apender = new TextAreaAppender ();
    	   apender.setLayout(new SimpleLayout());
    	   TextAreaAppender.setTextArea(textArea);
    	   
    	   Logger logRoot = Logger.getRootLogger();
    	   logRoot.addAppender(apender);
    	   logRoot.setLevel(Level.INFO);
    	   
	       logger.info("Paramétrage de la simmulation ...");
	       
	       //Définition des paramètres globaux
	       int dimension_x = 20;
	       int dimension_y = 20;
	       int meteo = 50;
	       int abondance = 100;
	       
	       logger.info("Initialisation du monde ...");
	       final Monde monde = new Monde(dimension_x, dimension_y, meteo, abondance);
	       
	       logger.info("Initialisation des obstacles ...");
	       monde.getCaseAt(3,5).setNiveau_obstacle(1);
	       monde.getCaseAt(2,5).setNiveau_obstacle(1);
	       monde.getCaseAt(3,4).setNiveau_obstacle(1);
	       
	       logger.info("Initialisation des fourmilieres ...");
	       int fecondite = 5;
	       int taille_max = 45;
	       int ressources = 100;
	       Fourmiliere f1 = new Fourmiliere(monde,monde.getCaseAt(5,5),fecondite,taille_max,ressources);
	       
	       logger.info("Initialisation des fourmis ...");
	       new Reine(f1);
	       new Eclaireuse(f1);
	       //new Ouvriere(f1);
	       //new Eclaireuse(f1,monde.getCaseAt(7,7));
	       
	       logger.info("Paramétrage terminé !");
	       
	       logger.info("Début de la simmulation ...");

    	   JFrame mainFrame = new JFrame("Demonstration");
    	   mainFrame.setSize(700,700);
    	   mainFrame.setLocation(350,50);
	       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       mainFrame.setVisible(true);
	       
	       final JPanel controls = new JPanel(new FlowLayout());
	       final JButton jouerTour = new JButton("Jouer Tour");
	       final JButton jouerAuto = new JButton("Mode Automatique");
	       
	       controls.add(jouerTour);
	       controls.add(jouerAuto);

	       final JPanel panel = new JPanel();
	       panel.setLayout(new BoxLayout(panel,  BoxLayout.Y_AXIS));
	    	
	       mainFrame.add(controls,BorderLayout.NORTH);
		   mainFrame.add(panel,BorderLayout.CENTER);
		   mainFrame.add (
		         new JScrollPane (
		             textArea, 
		             JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		             JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),
		          BorderLayout.SOUTH);
			
		  
		   
		   jouerTour.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
				     if(monde.getTotalFourmis() > 0) {
				    	 jouerTour.setEnabled(false);
				    	 jouerAuto.setEnabled(false);
				    	 logger.info("Tour n°"+(++App.i)+" - Total fourmilieres : "+monde.getFourmilieres().size()+" - Total fourmis : "+monde.getTotalFourmis());
					     monde.jouerTour();
					     getFrameFor(panel,monde); 
					    
					     //Fin du tour
					     if(monde.getTotalFourmis() > 0) {
						     jouerTour.setEnabled(true);
					    	 jouerAuto.setEnabled(true);
					     }
					
				     } 
				     
				   

				}
				
		       });
		       
		    jouerAuto.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						if(App.auto) {
							jouerAuto.setText("Mode Automatique");
							jouerTour.setEnabled(false);
							App.auto = false;
							
						} else {
							jouerAuto.setText("Mode Manuel");
							jouerTour.setEnabled(false);
							App.auto = true;
						}

					}
					
			 });
		   
		    getFrameFor(panel,monde);
		    
			 while(monde.getTotalFourmis() > 0) {
			    
				 if(App.auto) {
					//Thread.sleep(1000);
				    logger.info("Tour n°"+(++App.i)+" - Total fourmilieres : "+monde.getFourmilieres().size()+" - Total fourmis : "+monde.getTotalFourmis());
				    monde.jouerTour();
				    getFrameFor(panel,monde); 
				    
				     //Fin du Tour 
				     if(!App.auto) {
				    	 jouerTour.setEnabled(true);
				     }
				    
				 }
				 
			}
			    
		   if(monde.getTotalFourmis() == 0) {
			    logger.info("Fin de la simulation ! "+App.i+" tours joués.");
			    jouerTour.setEnabled(false);
			    jouerAuto.setEnabled(false);
		   }   
		       
		       
       } catch (Exception e) {
    	   e.printStackTrace();
    	   logger.error(e.getMessage());
       }
       
    }
    
    public static void  getFrameFor(JPanel panel,Monde monde)  {

      try {
    	  
	   int nbcases = monde.get_cases().size();
	   int nbcases_sqrt = (int)Math.sqrt(nbcases);
	    
    	panel.removeAll(); 
    	
    	JPanel infos = new JPanel();

    	
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
    	
    	String infosString = "TOUR n° "+App.i+
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
		
		
		panel.add(map);
		panel.add(infos);
		
		panel.repaint();
		panel.revalidate();
		
      } catch (Exception e) {
    	  e.printStackTrace();
      }
		
    }
    
}
