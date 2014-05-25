package org.ICE.PDC.antman.view;

import java.util.EventListener;

/**
 * Permet au contrôleur (implémentant l'interface) d'interpréter les évènements de la vue
 */
public interface MainFrameListener extends EventListener{

	public void setVitesse(int vitesse);
	
	public void jouerTour();
	
	public void setMeteo(int meteo);
	
	public void setAbondance(int abondance);
	
}
