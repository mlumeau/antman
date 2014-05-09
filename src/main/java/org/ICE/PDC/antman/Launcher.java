package org.ICE.PDC.antman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.ICE.PDC.antman.controller.MainCtrl;
import org.ICE.PDC.antman.model.Eclaireuse;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Ouvriere;
import org.ICE.PDC.antman.model.Reine;
import org.ICE.PDC.antman.view.MainFrame;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
		
		   int meteo = 50;
		   int abondance = 100;
		  
		   saveMap("map.xml"); //Sauvegarde d'un fond de carte par défaut //TODO REMOVE ME
		  
		   logger.info("Initialisation du monde ...");
		   Monde monde = loadMap("map.xml");
	     
		   monde.setMeteo(meteo);
		   monde.setAbondance(abondance);

	       logger.info("Initialisation des fourmilieres ...");
	       //TODO <BOUCLE> POUR CHAQUE FOURMILIERE DEFINIE
		       int fecondite = 5;
		       int taille_max = 45;
		       int ressources = 100;
		       int x = 5;
		       int y = 5;
		       int nbOuvrieres = 3;
		       int nbEclaireuses = 3;
		       //int ratioEclaireuses = 10; //TODO USE OR NOT ???
		       
		       Fourmiliere f1 = new Fourmiliere(monde,monde.getCaseAt(x,y),fecondite,taille_max,ressources);
		       
		       logger.info("Initialisation des fourmis ...");
		       //Ajout de la reine
		       new Reine(f1);
		       //Ajout des fourmis
		       for(int i = 0;i<nbOuvrieres;i++) {
		    	  new Ouvriere(f1);
		       }
		       for(int i = 0;i<nbEclaireuses;i++) {
		    	  new Eclaireuse(f1);
		       }
	       //</BOUCLE>
	       
	       logger.info("Paramétrage terminé !");
	       
	       //TRY CONTEXT LOADER
	       saveContext("monde.antman", monde);
	       
	       monde = loadContext("monde.antman");
	       
	       MainCtrl ctrl = new MainCtrl(monde);
	       
	       ctrl.setMainFrame(mf);
	       
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
      	
      	mf.setVisible(true);
      	
      	mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static final Monde loadMap(String filePath) throws Exception {
		  //Lecture du fichier de fond de carte
		  /*
		   * <world>
		   * 	<size_x>10</size_x>
		   * 	<size_y>15</size_y>
		   * 	<obstacles>
		   * 		<obstacle>
		   * 			<pos_x></pos_x>
		   * 			<pos_y></pos_y>
		   * 			<level></level>
		   * 		</obstacle>
		   * 	</obstacles>
		   * (...)
		   * </world>
		   */
		  File xmlFile = new File(filePath); 
		  Document document = (Document) new SAXBuilder().build(xmlFile);
		  Element rootNode = document.getRootElement();
		
	       //Définition des paramètres de départ
		   int dimension_x = Integer.valueOf(rootNode.getChildText("size_x"));
		   int dimension_y = Integer.valueOf(rootNode.getChildText("size_y"));

		   Monde monde = new Monde(dimension_x, dimension_y,0,0);
		   
	       logger.info("Initialisation des obstacles ...");
	       for(Element obstacle : rootNode.getChild("obstacles").getChildren("obstacle")) {
	    	   int x = Integer.valueOf(obstacle.getChildText("pos_x"));
				int y = Integer.valueOf(obstacle.getChildText("pos_y"));
				int level = Integer.valueOf(obstacle.getChildText("level"));
				monde.getCaseAt(x,y).setNiveau_obstacle(level);
	       }
		   
	       return monde;
	}
	
	
	
	public static void saveMap(String filePath) throws IOException {
		  //Définition du fond de carte	
		  int dimension_x = 20;
		  int dimension_y = 20;
		
		  Map<Integer[],Integer> obstacles = new HashMap<Integer[], Integer>();
		
		  obstacles.put(new Integer[]{3,5},1);
		  obstacles.put(new Integer[]{2,5},1);
		  obstacles.put(new Integer[]{3,4},1);
			
		  //Enregistrement du fond de carte sous forme de fichier XML
		  /*
		   * <world>
		   * 	<size_x>10</size_x>
		   * 	<size_y>15</size_y>
		   * 	<obstacles>
		   * 		<obstacle>
		   * 			<pos_x></pos_x>
		   * 			<pos_y></pos_y>
		   * 		<level></level>
		   * 		</obstacle>
		   * 	</obstacles>
		   * (...)
		   * </world>
		   */
		  
		  Element map = new Element("map");
		  Document doc = new Document(map);

		  map.addContent(new Element("size_x").setText(Integer.toString(dimension_x)));
		  map.addContent(new Element("size_y").setText(Integer.toString(dimension_y)));
		  Element obstaclesXML = new Element("obstacles");
		  map.addContent(obstaclesXML);
		  
		  for(Integer[] key : obstacles.keySet()) {
			  Element obstacle = new Element("obstacle");
			  obstacle.addContent(new Element("pos_x").setText(Integer.toString(key[0])));
			  obstacle.addContent(new Element("pos_y").setText(Integer.toString(key[1])));
			  obstacle.addContent(new Element("level").setText(Integer.toString(obstacles.get(key))));
			  obstaclesXML.addContent(obstacle);
		  }
		  
		  XMLOutputter xmlOutput = new XMLOutputter();
		  xmlOutput.setFormat(Format.getPrettyFormat());
		  xmlOutput.output(doc, new FileWriter(filePath));
	}
	
	
	public static void saveContext(String filePath,Monde monde) throws IOException {
		FileOutputStream fout = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(monde);
		oos.close();
	}
	
	public static Monde loadContext(String filePath) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(filePath);
		ObjectInputStream ois = new ObjectInputStream(fin);
		Monde monde = (Monde) ois.readObject();
		ois.close();
		return monde;
	}

}
