package org.ICE.PDC.antman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.view.LaunchFrame;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.alee.laf.WebLookAndFeel;

/**
 * Programme principal de l'application Antman
 */
public class Launcher {

	private static Logger logger = Logger.getLogger(Launcher.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Correction d'un bug de org.jdom2. see :  https://coderwall.com/p/kqsrrw
		System.setProperty("javax.xml.parsers.SAXParserFactory", "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
		
		//Configuration de log4j	
		PropertyConfigurator.configure(new File("src/ressources/log4j.properties").getAbsolutePath());
		
		WebLookAndFeel.install();

        WebLookAndFeel.setDecorateFrames ( true );
        WebLookAndFeel.setDecorateDialogs ( true );

		try { 
			
			//CHARGEMENT DES PARAMETRES DE CONFIGURATION
			ConfigurationLoader.load(true);
			new LaunchFrame(ConfigurationLoader.MAPS_PATH,ConfigurationLoader.SAVE_PATH); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Permet de charger un fichier de fond de carte
	 * @param filePath
	 * @return la carte chargée
	 * @throws Exception
	 */
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


    /**
     * Permet de sauvegarder un fichier de fond de carte
     * @param filePath
     * @param dimension_x
     * @param dimension_y
     * @param obstacles
     * @throws IOException
     */
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


	/**
	 * Permet de sauvegarder une partie en cours
	 * @param filePath
	 * @param monde
	 * @throws IOException
	 */
	public static void saveContext(String filePath,Monde monde) throws IOException {
		FileOutputStream fout = new FileOutputStream(filePath);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(monde);
		oos.close();
	}

	/**
	 * Permet de charger un fichier de sauvegarde
	 * @param filePath
	 * @return le monde contenu dans le fichier choisi
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Monde loadContext(String filePath) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(filePath);
		ObjectInputStream ois = new ObjectInputStream(fin);
		Monde monde = (Monde) ois.readObject();
		ois.close();
		return monde;
	}
	

}
