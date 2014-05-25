package org.ICE.PDC.antman.model;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * Une ressource pouvant servir de nourriture aux fourmis
 */
public class Ressource implements Serializable {
	
	private static final long serialVersionUID = -5373067628319412719L;
	private static Logger logger = Logger.getLogger(Ressource.class);
	
	/**Quantité de la ressource*/
	private int quantite;

	/**
	 * @param quantite
	 */
	public Ressource(int quantite) {
		this.setQuantite(quantite);
		logger.debug("Ressource crée : "+this);
	}
	
	@Override
	public String toString() {
		return "(Ressource) - "+this.hashCode()+" -> {Quantité : "+this.getQuantite()+"}";
	}

	/** 
	 * @param quantite
	 */
	public void diminuerQuantite(int quantite) {
		this.setQuantite(this.getQuantite()-quantite);
	}

	/** 
	 * @param quantite
	 */
	public void augmenterQuantite(int quantite) {
		this.setQuantite(this.getQuantite()+quantite);
	}
	
	/** 
	 * @return quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/** 
	 * @param quantite quantité à définir
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}