package org.ICE.PDC.antman;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.ICE.PDC.antman.controller.MainCtrl;
import org.ICE.PDC.antman.model.Eclaireuse;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Reine;
import org.ICE.PDC.antman.view.MainFrame;
import org.apache.log4j.Logger;

import com.alee.laf.WebLookAndFeel;

public class Launcher {

	private static Logger logger = Logger.getLogger(Launcher.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebLookAndFeel.install();
		
      	MainFrame mf = new MainFrame();
		
		logger.info("Paramétrage de la simmulation ...");
		try { 
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
	       Fourmiliere f1;
		   f1 = new Fourmiliere(monde,monde.getCaseAt(5,5),fecondite,taille_max,ressources);
	
	       
	       logger.info("Initialisation des fourmis ...");
	       new Reine(f1);
	       new Eclaireuse(f1);
	       //new Ouvriere(f1);
	       //new Eclaireuse(f1,monde.getCaseAt(7,7));
	       
	       logger.info("Paramétrage terminé !");
	       
	       MainCtrl ctrl = new MainCtrl(monde);
	       
	       ctrl.setMainFrame(mf);
	       
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
      	
      	mf.setVisible(true);
      	
      	mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
