package org.ICE.PDC.antman.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.ICE.PDC.antman.ConfigurationLoader;
import org.ICE.PDC.antman.Launcher;
import org.ICE.PDC.antman.controller.MainCtrl;
import org.ICE.PDC.antman.model.Monde;

import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.rootpane.WebFrame;

public class LaunchFrame extends WebFrame{

	private static final long serialVersionUID = 1L;

	public LaunchFrame( String mapsPath, String savePath)
	{
		
		try{
		Object[] possibilities = {"Nouvelle simulation", "Créer une nouvelle carte", "Charger une simulation"};

		String launchChoice = (String)WebOptionPane.showInputDialog( 
                null,
                "Bienvenue dans Antman Simulator 2014 ©",
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
	                    "Séléction de la carte",
	                    WebOptionPane.PLAIN_MESSAGE,
	                    null,
	                    availablesFiles.toArray(),
	                    "");

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
	                    "SÃ©lÃ©ction de la simulation",
	                    WebOptionPane.PLAIN_MESSAGE,
	                    null,
	                    availablesFiles.toArray(),
	                    "");

				String mapFilePath = savePath+"/"+saveName;
				
				Monde monde = Launcher.loadContext(mapFilePath); 
				
				//INITIALIZE MAIN FRAME
				MainFrame mf = new MainFrame();
				MainCtrl ctrl = new MainCtrl(monde);
				ctrl.setMainFrame(mf);
				mf.setVisible(true);
				mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
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
