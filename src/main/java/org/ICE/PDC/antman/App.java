package org.ICE.PDC.antman;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.ICE.PDC.antman.model.Case;
import org.ICE.PDC.antman.model.Eclaireuse;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Reine;
import org.apache.log4j.Logger;

/**
 * Point d'entrée principal du programme : Lance la simulation
 */
public class App 
{
	
	private static Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	
       try {
	       logger.info("Paramétrage de la simmulation ...");
	       
	       //Définition des paramètres globaux
	       int dimension_x = 10;
	       int dimension_y = 10;
	       int meteo = 50;
	       int abondance = 2;
	       
	       logger.info("Initialisation du monde ...");
	       Monde monde = new Monde(dimension_x, dimension_y, meteo, abondance);
	       
	       logger.info("Initialisation des obstacles ...");
	       monde.getCaseAt(3,5).setNiveau_obstacle(1);
	       monde.getCaseAt(2,5).setNiveau_obstacle(1);
	       monde.getCaseAt(3,4).setNiveau_obstacle(1);
	       
	       logger.info("Initialisation des fourmilieres ...");
	       int fecondite = 10;
	       int taille_max = 10;
	       int ressources = 1;
	       Fourmiliere f1 = new Fourmiliere(monde,monde.getCaseAt(5,5),fecondite,taille_max,ressources);
	       
	       logger.info("Initialisation des fourmis ...");
	       new Reine(f1);
	       new Eclaireuse(f1);
	       //new Ouvriere(f1);
	       //new Eclaireuse(f1,monde.getCaseAt(7,7));
	       
	       logger.info("Paramétrage terminé !");
	       
	       logger.info("Début de la simmulation ...");
	       int i = 0;
	       
    	   JFrame mainFrame = new JFrame("Demonstration");
    	   mainFrame.setSize(500,500);
    	   mainFrame.setLocation(500,200);
	       mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       mainFrame.setVisible(true);
	       
		       while(monde.getTotalFourmis() > 0) {
		    	   new Scanner(System.in).nextLine();
		    	   logger.info("Tour n°"+(++i)+" - Total fourmilieres : "+monde.getFourmiliere().size()+" - Total fourmis : "+monde.getTotalFourmis());
		    	   monde.jouerTour();
		    	   mainFrame.setContentPane(getFrameFor(monde));
		    	   mainFrame.revalidate();
		    	   mainFrame.repaint();
		    	   
		       }
	       
	       logger.info("Fin de la simmulation ! "+i+" tours joués.");
       
       } catch (Exception e) {
    	   e.printStackTrace();
    	   logger.error(e.getMessage());
       }
       
    }
    
    public static Container  getFrameFor(Monde monde) throws Exception {
    	
    	int nbcases = monde.get_case().size();
    	int nbcases_sqrt = (int)Math.sqrt(nbcases);
    	
    	GridLayout layout = new GridLayout(nbcases_sqrt,nbcases_sqrt);
    	
		JPanel panel = new JPanel(layout);
		panel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		
		for(int x=0; x<nbcases_sqrt; x++) {
			
			for(int y=0; y<nbcases_sqrt; y++) {
				Case current = monde.getCaseAt(x,y);
				
				final JLabel label = new JLabel("", SwingConstants.CENTER);
			    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    label.setOpaque(true);
			    
				if(current.getFourmiliere() != null) {
					label.setBackground(Color.BLACK);
				} else if(current.getRessource().size() > 0) {
					label.setBackground(Color.YELLOW);
				}
			    
				if(current.getFourmi().size() > 0) {
					label.setText("F");
				}
				
			    panel.add(label);
			}
			
		}
		
    	return panel;
    }
    
}
