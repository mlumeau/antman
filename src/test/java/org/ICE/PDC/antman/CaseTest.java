package org.ICE.PDC.antman;

import static org.junit.Assert.*;

import java.util.List;

import org.ICE.PDC.antman.model.Case;
import org.ICE.PDC.antman.model.Monde;
import org.junit.Before;
import org.junit.Test;

/* Tests de la classe Case
 * 
 * Pour les tests sur le path 2 cas sont testés
 * 	- une ligne droite entre 2 cases
 *  - un déplacement en diagonale
 * 
 * Pour le test du découvrement des cases autour d'une case : 
 * radius de 1 et 2
 * 	- case en bord de map (nb de case)
 *  - case en milieu de map (nb de case)


*/
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

		assertEquals(5, res.get(0).getX()); 
		assertEquals(5, res.get(1).getX()); 
		assertEquals(5, res.get(2).getX()); 
		assertEquals(5, res.get(3).getX()); 
		
		assertEquals(6, res.get(0).getY()); 
		assertEquals(7, res.get(1).getY()); 
		assertEquals(8, res.get(2).getY()); 
		assertEquals(9, res.get(3).getY()); 
		
		res = c.getPathTo(new Case(c.getMonde(),6,6));		
		assertEquals("Path de taille incorrecte", 1,res.size());

	}
	
	@Test 
	public void caseInRadiusTest()
	{
		List<Case> res = c.getCasesInRadius(1); 
		assertEquals("Radius de taille incorrecte", 8, res.size()); 
		res = c.getCasesInRadius(2); 
		assertEquals("Radius de taille incorrecte", 24, res.size()); 
		
		Case cTest = new Case(c.getMonde(),9,9); 
		res = cTest.getCasesInRadius(1); 
		assertEquals("Radius de taille incorrecte en bord de map", 3, res.size()); 
		
		res = cTest.getCasesInRadius(2); 
		assertEquals("Radius de taille incorrecte en bord de map", 8, res.size()); 
	}
	
}
