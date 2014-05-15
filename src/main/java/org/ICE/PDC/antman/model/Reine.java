/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import org.ICE.PDC.antman.model.events.FourmiEtatChangeEvent;
import org.apache.log4j.Logger;

/** 
 * Une fourmi reine :
 * -Crée des nouvelles fourmis tout les tours
 */

public class Reine extends Fourmi implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(Reine.class);
	
	public enum States {
		INSTALEE,
		RECHERCHE_EMPLACEMENT
	}

	private States etat;

	public void pondre() {
		
		int naissances = new Random().nextInt(this.getFourmiliere().getFecondite());
		
		for(int i=0; i<naissances; i++) {
			
			int rand = new Random().nextInt(100);
			
			if(rand >= this.getFourmiliere().get_tauxEclaireuses()) {
				new Ouvriere(this.getFourmiliere());
			} else {
				new Eclaireuse(this.getFourmiliere());
			}
			
			//Si la fourmiliere a atteind sa taille maximum, alors il est possible de creer une nouvelle reine
			//(Une reine se creera en moyenne tout les 10 tours)
			if(this.getFourmiliere().getTotalFourmis() >= this.getFourmiliere().getTaille_max()) {
				
				if(rand >= 90) {
					new Reine(this.getFourmiliere()).setEtat(States.RECHERCHE_EMPLACEMENT);
				}
				
			}

		}
	}

	/** 
	 * @param fourmiliere
	 */
	public Reine(Fourmiliere fourmiliere) {
		super(fourmiliere);
		this.setEtat(States.INSTALEE);
		this.setEsperance_de_vie(99999);
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 */
	public Reine(Fourmiliere fourmiliere, Case _case) {
		super(fourmiliere,_case);
		this.setEtat(States.INSTALEE);
		this.setEsperance_de_vie(99999);
	}

	/** 
	 * @param fourmiliere
	 * @param _case
	 * @param etat
	 */
	public Reine(Fourmiliere fourmiliere, Case _case, States etat) {
		super(fourmiliere,_case);
		setEtat( etat);
		this.setEsperance_de_vie(99999);
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
						
						//L'emplacement ne doit pas déja contenir une fourmiliere
						if(this.get_case().getFourmiliere() == null) {
							
							int rand = new Random().nextInt(10);
							
							if(rand >= 9) {
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
	 * @param etat etat à définir
	 */
	public void setEtat(States etat) {
		States old = this.etat;
		this.etat = etat;
		
		//Ajout de l'évennement FourmiEtatChangeEvent
		this.getFourmiliere().getMonde().fireEvent(new FourmiEtatChangeEvent(getFourmiliere().getMonde().getTour(), new Date(),this, old));
		
	}
}