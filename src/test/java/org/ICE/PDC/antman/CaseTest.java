package org.ICE.PDC.antman;

import static org.junit.Assert.*;

import java.util.List;

import org.ICE.PDC.antman.model.Case;
import org.ICE.PDC.antman.model.Monde;
import org.junit.Before;
import org.junit.Test;


public class CaseTest {
	
	private Case c; 
	
	@Before
    public void setUp() throws Exception {
		int dimension_x = 10;
		int dimension_y = 10;
		int meteo = 50;
		int abondance = 2;
		Monde map = new Monde(dimension_x, dimension_y, meteo, abondance);
		
		c = map.getCaseAt(5, 5);
		
    }
	
	@Test
	public void mapConnueTest()
	{
		assertNotNull("Map inconnue", c.getMonde()); 
	}
	
	@Test
	public void niveauObstacleTest()
	{
		c.setNiveau_obstacle(2); 
		assertEquals("Niveau d'obstacle incorrect",2, c.getNiveau_obstacle()); 
	}
	
	@Test
	public void pathTest()
	{
		Case cTest = new Case(c.getMonde(),5,9); 
		List<Case> res = c.getPathTo(cTest);
		
		assertEquals("Path de taille incorrecte", 4,res.size());
		assertEquals(6, res.get(0).getY()); 
		assertEquals(7, res.get(1).getY()); 
		assertEquals(8, res.get(2).getY()); 
		assertEquals(9, res.get(3).getY()); 
	}
	
	@Test 
	public void caseInRadiusTest()
	{
		List<Case> res = c.getCasesInRadius(1); 
		assertEquals("Radius de taille incorrecte", 8, res.size()); 
		
		Case cTest = new Case(c.getMonde(),9,9); 
		res = cTest.getCasesInRadius(1); 
		assertEquals("Radius de taille incorrecte en bord de map", 3, res.size()); 
	}
	
}
