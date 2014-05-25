package org.ICE.PDC.antman.view;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import org.ICE.PDC.antman.ConfigurationLoader;
import org.ICE.PDC.antman.Launcher;
import org.ICE.PDC.antman.controller.MainCtrl;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.rootpane.WebFrame;

/**
 * Menus présents au lancement de l'application
 */
public class LaunchFrame extends WebFrame{

	private static final long serialVersionUID = 1L;

	/**
	 * @param mapsPath - Chemin du dossier de sauvegarde des fonds de carte
	 * @param savePath - Chemin du dossier de sauvegarde des parties
	 */
	public LaunchFrame( String mapsPath, String savePath)
	{
		
		try{
		Object[] possibilities = {"Nouvelle simulation", "Créer une nouvelle carte", "Charger une simulation"};

		String launchChoice = (String)WebOptionPane.showInputDialog( 
                null,
                "Bienvenue dans Antman Simulator 2014",
                "Antman Simulator",
                WebOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                "");

		if(launchChoice == "Nouvelle simulation") {
			
			//SELECTION D'UN FOND DE CARTE
			List<String> availablesFiles = new ArrayList<String>();
			
			for (final File fileEntry : new File(mapsPath).listFiles()) {
			     if (fileEntry.getName().endsWith(".xml")) {
			    	 availablesFiles.add(fileEntry.getName());
			     }
		    }
			
			if(availablesFiles.size() > 0) {
			
				String mapName = (String)WebOptionPane.showInputDialog( 
	                    null,
	                    "Choisissez une carte à charger:\n",
	                    "Sélection de la carte",
	                    WebOptionPane.PLAIN_MESSAGE,
	                    null,
	                    availablesFiles.toArray(),
	                    "");

				if(mapName!=null) {
					String mapFilePath = mapsPath+"/"+mapName;
					
					//CHARGEMENT DU FOND DE CARTE SELECTIONNE
					Monde monde;
					monde = Launcher.loadMap(mapFilePath);
					
					//INITIALIZE FRAMES
					ConfigFrame cf = new ConfigFrame();
					cf.setMonde(monde);
					cf.setVisible(true);
					cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} else {
					System.exit(0);
				}
				
			} else {
				//Affichage d'un message d'erreur
				WebOptionPane.showMessageDialog(null,
				    "Aucune carte trouvée. Vous devez d'abord créer une carte avant de créer une simulation.",
				    "Antman simulator",
				    WebOptionPane.ERROR_MESSAGE);
			}
			
		} else if (launchChoice == "Créer une nouvelle carte") {
			
			CreationFrame cf = new CreationFrame(mapsPath, savePath);
			cf.setVisible(true);
			cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		} else if (launchChoice == "Charger une simulation") {
			
			//SELECTION D'UN FOND DE CARTE
			List<String> availablesFiles = new ArrayList<String>();
			
			for (final File fileEntry : new File(savePath).listFiles()) {
			     if (fileEntry.getName().endsWith(".antman")) {
			    	 availablesFiles.add(fileEntry.getName());
			     }
		    }
			
			if(availablesFiles.size() > 0) {
				
				String saveName = (String)WebOptionPane.showInputDialog( 
	                    null,
	                    "Choisissez une simulation à charger:\n",
	                    "Sélection de la simulation",
	                    WebOptionPane.PLAIN_MESSAGE,
	                    null,
	                    availablesFiles.toArray(),
	                    "");

				if(saveName!=null) {
				
				String mapFilePath = savePath+"/"+saveName;
				
				Monde monde = Launcher.loadContext(mapFilePath); 
				
				//INITIALIZE MAIN FRAME
				HashMap<Fourmiliere, Color> colors = new HashMap<Fourmiliere, Color>();
				Random rand = new Random();
				
				for(Fourmiliere f : monde.getFourmilieres()){
					colors.put(f, new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
				}
				
				MainCtrl ctrl = new MainCtrl(monde);
				MainFrame mf = new MainFrame(ctrl,colors);
				ctrl.setMainFrame(mf);
				mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mf.setVisible(true);
				mf.initMonde(monde);
				
				} else {
					System.exit(0);
				}
				
			} else {
				//Affichage d'un message d'erreur
				WebOptionPane.showMessageDialog(null,
					"Aucune simulation trouvée. Vous devez d'abord créer une simulation.",
				    "Antman simulator",
				    WebOptionPane.ERROR_MESSAGE);
				
			}
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	public LaunchFrame() {
		this(ConfigurationLoader.MAPS_PATH,ConfigurationLoader.SAVE_PATH);
	}
	
}
