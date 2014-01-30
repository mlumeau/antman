/**
 * 
 */
package org.ICE.PDC.antman.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Case {
	
	private static Logger logger = Logger.getLogger(Case.class);
	
	public Case(Monde monde,int x, int y) {
		this.ressource = new HashSet<Ressource>();
		this.fourmi = new HashSet<Fourmi>();
		this.pheromone = new HashSet<Pheromone>();
		//NB : Pour les cases seulement le lien avec le Monde est fait directement dans le contructeur Monde(...)
		this.map = monde; 
		//TODO add non existence control here?
		this.x = x;
		this.y = y;
		this.niveau_obstacle = 0;
		logger.debug("Case crée : "+this);
	}
	
	@Override
	public String toString() {
		
		String ressources = "[";
		
		for(Ressource r : this.getRessource()) {
			ressources+= r;
		}
		
		ressources += "]";
		
		String fourmis = "[";
		
		for(Fourmi f : this.getFourmi()) {
			ressources+= f;
		}
		
		fourmis += "]";
		
		return "(Case) - "+this.hashCode()+" -> {X : "+this.getX()+
				" , Y : "+this.getY()+
				" , Niveau_obstacle : "+this.getNiveau_obstacle()+
				" , Ressources : "+ressources+
				" , Fourmis : "+fourmis+"}";
	}
	
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int niveau_obstacle;

	/** 
	 * @return niveau_obstacle
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getNiveau_obstacle() {
		// begin-user-code
		return niveau_obstacle;
		// end-user-code
	}

	/** 
	 * @param niveau_obstacle niveau_obstacle � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setNiveau_obstacle(int niveau_obstacle) {
		this.niveau_obstacle = niveau_obstacle;
		logger.debug("Niveau d'obstacle de la case ("+x+","+y+") reglé à "+niveau_obstacle);
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int x;

	/** 
	 * @return x
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getX() {
		// begin-user-code
		return x;
		// end-user-code
	}

	/** 
	 * @param x x � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setX(int x) {
		// begin-user-code
		this.x = x;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Ressource> ressource;

	/** 
	 * @return ressource
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Ressource> getRessource() {
		// begin-user-code
		return ressource;
		// end-user-code
	}

	/** 
	 * @param ressource ressource � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setRessource(Set<Ressource> ressource) {
		// begin-user-code
		this.ressource = ressource;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Pheromone> pheromone;

	/** 
	 * @return pheromone
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Pheromone> getPheromone() {
		// begin-user-code
		return pheromone;
		// end-user-code
	}

	/** 
	 * @param pheromone pheromone � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setPheromone(Set<Pheromone> pheromone) {
		// begin-user-code
		this.pheromone = pheromone;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Monde map;

	/** 
	 * @return map
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Monde getMap() {
		// begin-user-code
		return map;
		// end-user-code
	}

	/** 
	 * @param map map � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setMap(Monde map) {
		// begin-user-code
		this.map = map;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Set<Fourmi> fourmi;

	/** 
	 * @return fourmi
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Set<Fourmi> getFourmi() {
		// begin-user-code
		return fourmi;
		// end-user-code
	}

	/** 
	 * @param fourmi fourmi � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setFourmi(Set<Fourmi> fourmi) {
		// begin-user-code
		this.fourmi = fourmi;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private int y;

	/** 
	 * @return y
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public int getY() {
		// begin-user-code
		return y;
		// end-user-code
	}

	/** 
	 * @param y y � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setY(int y) {
		// begin-user-code
		this.y = y;
		// end-user-code
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Fourmiliere fourmiliere;

	/** 
	 * @return fourmiliere
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Fourmiliere getFourmiliere() {
		// begin-user-code
		return fourmiliere;
		// end-user-code
	}

	/** 
	 * @param fourmiliere fourmiliere � d�finir
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void setFourmiliere(Fourmiliere fourmiliere) {
		this.fourmiliere = fourmiliere;
		logger.debug("Case ("+this.hashCode()+") Fourmiliere = ["+fourmiliere+"]");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param f
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void ajouterFourmi(Fourmi f) {
		this.fourmi.add(f);
		logger.debug("Case ("+this.hashCode()+") Ajout de la Fourmi : "+f+" ");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param r
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void ajouterRessource(Ressource r) {
		this.ressource.add(r);
		logger.debug("Case ("+this.hashCode()+") Ajout de la Ressource : "+r+" ");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param ph
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void ajouterPheromone(Pheromone ph) {
		this.pheromone.add(ph);
		logger.debug("Case ("+this.hashCode()+") Ajout de Pheromones : "+ph+" ");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param f
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void supprimerFourmi(Fourmi f) {
		this.fourmi.remove(f);
		logger.debug("Case ("+this.hashCode()+") Suppression de la Fourmi : "+f+" ");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param _r
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void supprimerRessource(Ressource r) {
		this.ressource.remove(r);
		logger.debug("Case ("+this.hashCode()+") Suppression de la Ressource : "+r+" ");
	}

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @param ph
	 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public void supprimerPheromone(Pheromone ph) {
		this.pheromone.remove(ph);
		logger.debug("Case ("+this.hashCode()+") Suppression de Pheromones : "+ph+" ");
	}

	public List<Case> getCasesInRadius(int radius) {
		
		List<Case> matched = new ArrayList<Case>();
		String log = "";
		
		for(int x = (this.getX()-radius); x<=(this.getX()+radius); x++) {
			
			for(int y = (this.getY()-radius); y<=(this.getY()+radius); y++) {
				
				if(x != this.getX() && y != this.getY()) {
					
					try {
						Case c = this.getMap().getCaseAt(x,y);
						matched.add(c);
						log += c;
						
					} catch (Exception e) {
						//Nothing to do here
					}
					
				}
			}
			
		}
		
		logger.debug("Case ("+this.hashCode()+") - Cases dans un rayon de "+radius+" : "+log);
		return matched;
	}

	public List<Case> getPathTo(Case target) {
		List<Case> path = new ArrayList<Case>();
		
		//V1 : Pas de gestion des obstacles
		int x = this.getX();
		int y = this.getY();
		
		while(target.getX() != x && target.getY() != y) {
			
			if(target.getX() > x) {
				x++;
			} else if (target.getX() < x) {
				x--;
			}
			
			if(target.getY() > y) {
				y++;
			} else if (target.getY() < y) {
				y--;
			}
			
			try {
				path.add(this.getMap().getCaseAt(x, y));
			} catch (Exception e) {
				//Unexpected Error
				e.printStackTrace();
			}
			
		}
		
		return path;
	}

}