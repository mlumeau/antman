package org.ICE.PDC.antman;

import static org.junit.Assert.*;
import org.ICE.PDC.antman.model.Eclaireuse;
import org.ICE.PDC.antman.model.Fourmi;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.junit.Before;
import org.junit.Test;

public class FourmiTest {

	private Fourmiliere f;
	private Monde map; 
	
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
		int ressources = 1;
		f = new Fourmiliere(map,map.getCaseAt(5,5),fecondite,taille_max,ressources);
	}
	@Test
	public void deplacementAleatoireTest() throws Exception
	{
		Fourmi fourmi = new Eclaireuse(f,map.getCaseAt(5, 5) );  
		fourmi.seDeplacerAlea(); 
		assertTrue(fourmi.get_case().getX() == 4 || fourmi.get_case().getX() == 5 || fourmi.get_case().getX() == 6); 
		assertTrue(fourmi.get_case().getY() == 4 || fourmi.get_case().getY() == 5 || fourmi.get_case().getY() == 6); 
		
		
	}
}
