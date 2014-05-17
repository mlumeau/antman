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
	private Monde map; 
	
	@Before
    public void setUp() throws Exception {
		
		/*
		 * Map pour la version 3 du pathFinding (A*)
		 * F : Emplacement de la fourmi
		 * R : Emplacement des ressources
		 * X : Emplacement des obstacles
		 *
		 *     |---|---|---|---|---|
		 *     |F/R|   |   |   | R |
		 *     |---|---|---|---|---|
		 *     | X | X | X |   |   |
		 *     |---|---|---|---|---|
		 *     | X | R | X |   |   |
		 *     |---|---|---|---|---|
		 *     |   |   | X |   |   |
		 *     |---|---|---|---|---|
		 *     |   |   |   |   |   |
		 *     |---|---|---|---|---|
		 *     
		 */

		int dimension_x = 5;
		int dimension_y = 5;

		map = new Monde(dimension_x, dimension_y, 0, 0);
		
		c = map.getCaseAt(0,0);

		map.getCaseAt(0, 1).setNiveau_obstacle(1);
		map.getCaseAt(1, 1).setNiveau_obstacle(1);
		map.getCaseAt(2, 1).setNiveau_obstacle(1);
		map.getCaseAt(0, 2).setNiveau_obstacle(1);
		map.getCaseAt(2, 2).setNiveau_obstacle(1);
		map.getCaseAt(2, 3).setNiveau_obstacle(1);
		
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
	public void pathTest() throws Exception
	{
		Case cTest = new Case(c.getMonde(),0,0); 
		List<Case> res = c.getPathTo(cTest);
		
		assertEquals("Path de taille incorrecte", 0,res.size());
		
		cTest = new Case(c.getMonde(),1,2); 
		res = c.getPathTo(cTest);
		
		assertEquals("Path de taille incorrecte", 8,res.size());

		assertTrue("Path incorrect",res.contains(map.getCaseAt(1, 0)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(2, 0)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(3, 1)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(3, 2)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(3, 3)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(2, 4)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(1, 3)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(1, 2)));
		
		cTest = new Case(c.getMonde(),4,0); 
		res = c.getPathTo(cTest);
		
		assertEquals("Path de taille incorrecte", 4,res.size());
		assertTrue("Path incorrect",res.contains(map.getCaseAt(1, 0)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(2, 0)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(3, 0)));
		assertTrue("Path incorrect",res.contains(map.getCaseAt(4, 0)));

	}
	
	
	@Test 
	public void caseInRadiusTest() throws Exception
	{
		List<Case> res = c.getCasesInRadius(1); 
		assertEquals("Radius de taille incorrecte", 1, res.size()); 
		assertTrue("Radius incorrect",res.contains(map.getCaseAt(1, 0)));
		
		res = c.getCasesInRadius(2); 
		assertEquals("Radius de taille incorrecte", 3, res.size()); 
		assertTrue("Radius incorrect",res.contains(map.getCaseAt(1, 0)));
		assertTrue("Radius incorrect",res.contains(map.getCaseAt(2, 0)));
		assertTrue("Radius incorrect",res.contains(map.getCaseAt(1, 2)));
		
	}
	
}
