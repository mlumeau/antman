package org.ICE.PDC.antman;

import static org.junit.Assert.assertEquals;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Ouvriere;
import org.ICE.PDC.antman.model.Ressource;
import org.junit.Before;
import org.junit.Test;

public class OuvriereTest {

	private Fourmiliere f;
	private Monde map; 
	private Ouvriere ouv; 
	
	@Before
	public void setUp() throws Exception
	{
		int dimension_x = 10;
		int dimension_y = 10;
		int meteo = 50;
		int abondance = 2;
		   
		map = new Monde(dimension_x, dimension_y, meteo, abondance);
		int fecondite = 10;
		int taille_max = 10;
		int ressources = 2;
		f = new Fourmiliere(map,map.getCaseAt(5,5),fecondite,taille_max,ressources);
		
		ouv = new Ouvriere(f,map.getCaseAt(3, 5) );  
		
	}
	
	@Test public void recolterNourritureTest() throws Exception
	{
		map.getCaseAt(3, 5).ajouterRessource(new Ressource(1)); 
		ouv.recolterNouriture(); 
		assertEquals("Quantité de ressource après la récolte incorrect", 1, ouv.getCharge()); 
	}
	
	

}
