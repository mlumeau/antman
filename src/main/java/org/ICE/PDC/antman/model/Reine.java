/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import org.ICE.PDC.antman.ConfigurationLoader;
import org.ICE.PDC.antman.model.events.FourmiEtatChangeEvent;
import org.apache.log4j.Logger;

/** 
 * Une fourmi reine :<br/>
 * -Crée des nouvelles fourmis tout les tours<br/>
 * -Peut créer sa propre fourmilière
 */
public class Reine extends Fourmi implements Serializable {
	
	private static final long serialVersionUID = -4942844918143927908L;
	private static Logger logger = Logger.getLogger(Reine.class);
	
	/**
	 * États de la fourmi<br/>
	 * INSTALEE : Crée des nouvelles fourmis tout les tours<br/>
	 * RECHERCHE_EMPLACEMENT : Recherche un emplacement pour créer une nouvelle fourmilière
	 */
	public enum States {
		INSTALEE,
		RECHERCHE_EMPLACEMENT
	}

	/**Etat courant de la fourmi*/
	private States etat;

	/**
	 * Crée de nouvelles fourmis dans la fourmilière :<br/>
	 * Le nombre de fourmis crées est à la fois influencé par la fécondité de la fourmilière et le paramètre de configuration LIMIT_FOURMIS_NUMBER<br/>
	 * Le type des fourmis crées est influencé par le paramètre tauxEclaireuses de la fourmilière<br/>
	 * De nouvelles reines ne peuvent êtres crées que si la fourmilière a atteint sa taille critique (leur chance de naissance dépend alors du paramètre de configuration CHANCES_NAISSANCE_REINES)   
	 */
	public void pondre() {
		
		int naissances = 0;
		
		if(this.getFourmiliere().getFecondite() > 0) {
			naissances = new Random().nextInt(this.getFourmiliere().getFecondite());
		}
		
		if(ConfigurationLoader.LIMIT_FOURMIS_NUMBER && this.getFourmiliere().getTotalFourmis()+naissances >= this.getFourmiliere().getTaille_max()) {
			naissances = this.getFourmiliere().getTaille_max()-this.getFourmiliere().getTotalFourmis();
		}
		
		for(int i=0; i<naissances; i++) {
			
			int rand = new Random().nextInt(100);
			
			//On crée aléatoirement une ouvrière ou une éclaireuse
			if(rand >= this.getFourmiliere().get_tauxEclaireuses()) {
				new Ouvriere(this.getFourmiliere());
				
			} else {
				new Eclaireuse(this.getFourmiliere());
			}
			
		}
		
		//Si la fourmiliere a atteint sa taille maximum, alors il est possible de créer une nouvelle reine
		if(this.getFourmiliere().getTotalFourmis() >= this.getFourmiliere().getTaille_max()
			&& (ConfigurationLoader.MAX_FOURMILIERES > this.getFourmiliere().getMonde().getFourmilieres().size())) {
		
			if(new Random().nextInt(100) < ConfigurationLoader.CHANCES_NAISSANCE_REINES) {
				new Reine(this.getFourmiliere()).setEtat(States.RECHERCHE_EMPLACEMENT);
			}
			
		}

	}

	/** 
	 * @param fourmiliere
	 */
	public Reine(Fourmiliere fourmiliere) {
		super(fourmiliere);
		this.setEtat(States.INSTALEE);
		this.setSante(ConfigurationLoader.SANTE_REINE);
		this.setSante_max(ConfigurationLoader.SANTE_MAX_REINE);
		this.setEsperance_de_vie(ConfigurationLoader.ESPERANCE_VIE_REINE);
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 */
	public Reine(Fourmiliere fourmiliere, Case _case) {
		super(fourmiliere,_case);
		this.setEtat(States.INSTALEE);
		this.setSante(ConfigurationLoader.SANTE_REINE);
		this.setSante_max(ConfigurationLoader.SANTE_MAX_REINE);
		this.setEsperance_de_vie(ConfigurationLoader.ESPERANCE_VIE_REINE);
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 * @param etat
	 */
	public Reine(Fourmiliere fourmiliere, Case _case, States etat) {
		super(fourmiliere,_case);
		setEtat( etat);
		this.setSante(ConfigurationLoader.SANTE_REINE);
		this.setSante_max(ConfigurationLoader.SANTE_MAX_REINE);
		this.setEsperance_de_vie(ConfigurationLoader.ESPERANCE_VIE_REINE);
	}

	/** 
	 * @see Fourmi#agir()
	 */
	public void agir() {
		this.setAge(this.getAge()+1);
		this.setSante(this.getSante()-1);
		
			if(this.get_case().equals(this.getFourmiliere().get_case())) {
				//Manger :)
				this.manger();
				logger.info("La Reine ("+this.hashCode()+") mange");
			}
			
			if(this.getAge() >= this.getEsperance_de_vie() || this.getSante() <= 0) {
				//Mort de la fourmi
				this.mourir();
				logger.info("La Reine ("+this.hashCode()+") est morte");
				
			} else {
				//Action
				switch(this.getEtat()) {
				
					case INSTALEE:
						this.pondre();
					break;
						
					case RECHERCHE_EMPLACEMENT:
						this.seDeplacerAlea();
						
						//L'emplacement ne doit pas déjà contenir une fourmiliere
						if(this.get_case().getFourmiliere() == null && ConfigurationLoader.MAX_FOURMILIERES > this.getFourmiliere().getMonde().getFourmilieres().size()) {
							
							if(new Random().nextInt(100) < ConfigurationLoader.CHANCES_INSTALLATION_REINES) {
								Fourmiliere f = new Fourmiliere(this.getFourmiliere().getMonde(),this.get_case(),this.getFourmiliere().getFecondite(), this.getFourmiliere().getTaille_max(),0,this.getFourmiliere().get_tauxEclaireuses());
								this.setFourmiliere(f);
								this.setEtat(States.INSTALEE);
								logger.info("La Reine ("+this.hashCode()+") a fondé une nouvelle fourmiliere");
							}
							
						}
						
					break;
						
				}
				
			}
			
	}
	
	/** 
	 * @return etat
	 */
	public States getEtat() {
		return etat;
	}

	/** 
	 * Fire l'event FourmiEtatChangeEvent
	 * @param etat etat à définir
	 */
	public void setEtat(States etat) {
		States old = this.etat;
		this.etat = etat;
		
		//Ajout de l'évennement FourmiEtatChangeEvent
		this.getFourmiliere().getMonde().fireEvent(new FourmiEtatChangeEvent(getFourmiliere().getMonde().getTour(), new Date(),this, old));
		
	}
}