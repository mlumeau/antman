package org.ICE.PDC.antman;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.ICE.PDC.antman.model.Eclaireuse;
import org.ICE.PDC.antman.model.Fourmi;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.junit.Before;
import org.junit.Test;

public class FourmiliereTest {

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
		int ressources = 2;
		f = new Fourmiliere(map,map.getCaseAt(5,5),fecondite,taille_max,ressources);
	}
	
	@Test
	public void ajoutFourmiTest() throws Exception
	{
		Fourmi fourmi = new Eclaireuse(f,map.getCaseAt(5, 5) );
		f.ajouterFourmi(fourmi);  
		assertEquals("Taille de la fourmilière après ajout d'une nouvelle fourmi incorrecte", f.getFourmi().size(), 1); 
		f.ajouterFourmi(fourmi);
		assertEquals("Contrainte d'unicité des fourmis dans la fourmilière non respectée", f.getFourmi().size(), 1); 	
	}
	
	@Test
	public void supprimerFourmiTest() throws Exception
	{
		Fourmi fourmi = new Eclaireuse(f,map.getCaseAt(5, 5) );
		f.ajouterFourmi(fourmi);  
		
		f.supprimerFourmi(fourmi);
		assertEquals("Suppression de la fourmi ne marche pas", f.getFourmi().size(), 0); 	
	}
	
	
	
	
}
