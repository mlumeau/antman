package org.ICE.PDC.antman.model;

import org.apache.log4j.Logger;

public class Ressource {
	
	private static Logger logger = Logger.getLogger(Ressource.class);
	
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
		return "(Ressource) - "+this.hashCode()+" -> {Quantite : "+this.getQuantite()+"}";
	}

	/** 
	 * @param quantite"
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
	 * @param quantite quantite à définir
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}