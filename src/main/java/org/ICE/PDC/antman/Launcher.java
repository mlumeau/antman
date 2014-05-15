package org.ICE.PDC.antman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.ICE.PDC.antman.controller.MainCtrl;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.view.ConfigFrame;
import org.ICE.PDC.antman.view.CreationFrame;
import org.ICE.PDC.antman.view.LaunchFrame;
import org.ICE.PDC.antman.view.MainFrame;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.optionpane.WebOptionPane;

public class Launcher {

	private static Logger logger = Logger.getLogger(Launcher.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicConfigurator.configure();
		WebLookAndFeel.install();

        WebLookAndFeel.setDecorateFrames ( true );
        WebLookAndFeel.setDecorateDialogs ( true );

		try { 
			
			//CHARGEMENT DES PARAMETRES DE CONFIGURATION
			String configPath = Launcher.class.getResource("config.xml").getPath();
			configPath=configPath.replaceAll("%20", " ");
			File xmlFile = new File(configPath); 
			
			Document document = (Document) new SAXBuilder().build(xmlFile);
			Element rootNode = document.getRootElement();
			String mapsPath = rootNode.getChildText("mapsPath");
			String savePath = rootNode.getChildText("savePath");
			
			if(mapsPath.equals("${antman.maps}")) {
				mapsPath = "maps";
			}
			
			if(savePath.equals("${antman.save}")) {
				savePath = "save";
			}
			
			//On crée les répertoire si ceux-ci n'éxistent pas déja
			File md = new File(mapsPath);
			if (!md.exists() || !md.getCanonicalFile().isDirectory()) {
				md.mkdirs();
			}
			
			File sd = new File(savePath);
			if (!sd.exists() || !sd.getCanonicalFile().isDirectory()) {
				sd.mkdirs();
			}
			
			//Sauvegarde d'un fond de carte (TEST)
			int dimension_x = 20; //TODO REMOVE ME (TEST LINE)
			int dimension_y = 20; //TODO REMOVE ME (TEST LINE)
			Map<Integer[],Integer> obstacles = new HashMap<Integer[], Integer>(); //TODO REMOVE ME (TEST LINE)
			obstacles.put(new Integer[]{3,5},1); //TODO REMOVE ME (TEST LINE)
			obstacles.put(new Integer[]{2,5},1); //TODO REMOVE ME (TEST LINE)
			obstacles.put(new Integer[]{3,4},1); //TODO REMOVE ME (TEST LINE)
			saveMap(mapsPath+"/map.xml",dimension_x,dimension_y,obstacles);  //TODO REMOVE ME (TEST LINE)

			LaunchFrame lf = new LaunchFrame(mapsPath, savePath); 
			
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}

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



	public static void saveMap(String filePath, int dimension_x, int dimension_y, Map<Integer[], Integer> obstacles) throws IOException {
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
