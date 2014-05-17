package org.ICE.PDC.antman;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathFactory;

public class ConfigurationLoader {
	
	public static String MAPS_PATH = "maps";
	public static String SAVE_PATH = "save";
	
	public static int PHEROMONES_ECLAIREUSES = 10;
	public static int PHEROMONES_OUVRIERES = 5;
	
	public static int ESPERANCE_VIE_OUVRIERE = 20;
	public static int ESPERANCE_VIE_ECLAIREUSE = 20;
	public static int ESPERANCE_VIE_REINE = 9999;
	
	public static int SANTE_OUVRIERE = 20;
	public static int SANTE_ECLAIREUSE = 20;
	public static int SANTE_REINE = 20;
	
	public static int SANTE_MAX_OUVRIERE = 20;
	public static int SANTE_MAX_ECLAIREUSE = 20;
	public static int SANTE_MAX_REINE = 20;
	
	public static int CHANCES_NAISSANCE_REINES = 10;
	public static int CHANCES_INSTALLATION_REINES = 10;
	
	public static int CHARGE_MAX_OUVRIERE = 20;
	
	public static int METEO_MULTIPLICATOR = 1;
	public static int VITESSE_MULTIPLICATOR = 1;
	public static int ABONDANCE_MULTIPLICATOR = 1;
	
	public static void load(boolean createMissingDirs) throws JDOMException, IOException {
		
		String configPath = ConfigurationLoader.class.getResource("config.xml").getPath();
		configPath=configPath.replaceAll("%20", " ");
		File xmlFile = new File(configPath); 
		
		Document document = (Document) new SAXBuilder().build(xmlFile);
		Element rootNode = document.getRootElement();
		
		//FICHIERS DE CONFIGURATION
		String mapsPath = rootNode.getChildText("mapsPath");
		String savePath = rootNode.getChildText("savePath");
		
		if(mapsPath != null && !mapsPath.equals("${antman.maps}")) {
			ConfigurationLoader.MAPS_PATH = mapsPath;
		}
		
		if(savePath != null && !savePath.equals("${antman.save}")) {
			ConfigurationLoader.SAVE_PATH = savePath;
		}
		
		//PARAMETRES GLOBAUX
		String meteo_multiplicator = rootNode.getChildText("meteo_multiplicator");
		
		if(meteo_multiplicator != null) {
			ConfigurationLoader.METEO_MULTIPLICATOR = Integer.valueOf(meteo_multiplicator);
		}
		
		String vitesse_multiplicator = rootNode.getChildText("vitesse_multiplicator");
		
		if(vitesse_multiplicator != null) {
			ConfigurationLoader.VITESSE_MULTIPLICATOR = Integer.valueOf(vitesse_multiplicator);
		}
		
		String abondance_multiplicator = rootNode.getChildText("abondance_multiplicator");
		
		if(abondance_multiplicator != null) {
			ConfigurationLoader.ABONDANCE_MULTIPLICATOR = Integer.valueOf(abondance_multiplicator);
		}
		
		//OUVRIERE
		
		Element ph_ouvriere = XPathFactory.instance()
				.compile("/antman/fourmis/ouvriere/pheromones",Filters.element())
				.evaluateFirst(document);
		
		if(ph_ouvriere != null) {
			ConfigurationLoader.PHEROMONES_OUVRIERES = Integer.valueOf(ph_ouvriere.getText());
		}

		Element esp_ouvriere = XPathFactory.instance()
				.compile("/antman/fourmis/ouvriere/esperance_vie",Filters.element())
				.evaluateFirst(document);
		
		if(esp_ouvriere != null) {
			ConfigurationLoader.ESPERANCE_VIE_OUVRIERE = Integer.valueOf(esp_ouvriere.getText());
		}
		
		Element sante_ouvriere = XPathFactory.instance()
				.compile("/antman/fourmis/ouvriere/sante",Filters.element())
				.evaluateFirst(document);
		
		if(sante_ouvriere != null) {
			ConfigurationLoader.SANTE_OUVRIERE = Integer.valueOf(sante_ouvriere.getText());
		}
		
		Element sante_max_ouvriere = XPathFactory.instance()
				.compile("/antman/fourmis/ouvriere/sante_max",Filters.element())
				.evaluateFirst(document);
		
		if(sante_max_ouvriere != null) {
			ConfigurationLoader.SANTE_MAX_OUVRIERE = Integer.valueOf(sante_max_ouvriere.getText());
		}
		
		Element charge_max_ouvriere = XPathFactory.instance()
				.compile("/antman/fourmis/reine/charge_max",Filters.element())
				.evaluateFirst(document);
		
		if(charge_max_ouvriere != null) {
			ConfigurationLoader.CHARGE_MAX_OUVRIERE = Integer.valueOf(charge_max_ouvriere.getText());
		}
		
		//ECLAIREUSE
		
		Element ph_eclaireuse = XPathFactory.instance()
				.compile("/antman/fourmis/eclaireuse/pheromones",Filters.element())
				.evaluateFirst(document);

		if(ph_eclaireuse != null) {
			ConfigurationLoader.PHEROMONES_ECLAIREUSES = Integer.valueOf(ph_eclaireuse.getText());
		}

		Element esp_eclaireuse = XPathFactory.instance()
				.compile("/antman/fourmis/eclaireuse/esperance_vie",Filters.element())
				.evaluateFirst(document);
		
		if(esp_eclaireuse != null) {
			ConfigurationLoader.ESPERANCE_VIE_ECLAIREUSE = Integer.valueOf(esp_eclaireuse.getText());
		}
		
		Element sante_eclaireuse = XPathFactory.instance()
				.compile("/antman/fourmis/eclaireuse/sante",Filters.element())
				.evaluateFirst(document);
		
		if(sante_eclaireuse != null) {
			ConfigurationLoader.SANTE_ECLAIREUSE = Integer.valueOf(sante_eclaireuse.getText());
		}
		
		Element sante_max_eclaireuse = XPathFactory.instance()
				.compile("/antman/fourmis/eclaireuse/sante_max",Filters.element())
				.evaluateFirst(document);
		
		if(sante_max_eclaireuse != null) {
			ConfigurationLoader.SANTE_MAX_ECLAIREUSE = Integer.valueOf(sante_max_eclaireuse.getText());
		}
		
		//REINE
		
		Element esp_reine = XPathFactory.instance()
				.compile("/antman/fourmis/reine/esperance_vie",Filters.element())
				.evaluateFirst(document);
		
		if(esp_reine != null) {
			ConfigurationLoader.ESPERANCE_VIE_REINE = Integer.valueOf(esp_reine.getText());
		}
		
		Element sante_reine = XPathFactory.instance()
				.compile("/antman/fourmis/reine/sante",Filters.element())
				.evaluateFirst(document);
		
		if(sante_reine != null) {
			ConfigurationLoader.SANTE_REINE = Integer.valueOf(sante_reine.getText());
		}
		
		Element sante_max_reine = XPathFactory.instance()
				.compile("/antman/fourmis/reine/sante_max",Filters.element())
				.evaluateFirst(document);
		
		if(sante_max_reine != null) {
			ConfigurationLoader.SANTE_MAX_REINE = Integer.valueOf(sante_max_reine.getText());
		}
		
		Element chances_naissance_reine = XPathFactory.instance()
				.compile("/antman/fourmis/reine/chances_naissance",Filters.element())
				.evaluateFirst(document);
		
		if(chances_naissance_reine != null) {
			ConfigurationLoader.CHANCES_NAISSANCE_REINES = Integer.valueOf(chances_naissance_reine.getText());
		}
		
		Element chances_installation_reine = XPathFactory.instance()
				.compile("/antman/fourmis/reine/chances_installation",Filters.element())
				.evaluateFirst(document);
		
		if(chances_installation_reine != null) {
			ConfigurationLoader.CHANCES_INSTALLATION_REINES = Integer.valueOf(chances_installation_reine.getText());
		}
		
		if(createMissingDirs) {
			
			//On crée les répertoire si ceux-ci n'éxistent pas déja
			File md = new File(ConfigurationLoader.MAPS_PATH);
			if (!md.exists() || !md.getCanonicalFile().isDirectory()) {
				md.mkdirs();
			}
			
			File sd = new File(ConfigurationLoader.SAVE_PATH);
			if (!sd.exists() || !sd.getCanonicalFile().isDirectory()) {
				sd.mkdirs();
			}
			
		}
		
	}
	
	
}
