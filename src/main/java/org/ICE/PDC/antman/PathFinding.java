package org.ICE.PDC.antman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ICE.PDC.antman.model.Case;
import org.ICE.PDC.antman.model.Monde;

public class PathFinding {
	
	class noeud{
	    float cout_g, cout_h, cout_f;
	    Case parent;   
	};
	private Monde monde;
	private Case arrivee;
	private Case depart;
	private HashMap< Case, noeud> liste_fermee;
	private HashMap< Case, noeud> liste_ouverte;
	
	
	public PathFinding(Case depart, Case arrivee){
		this.depart = depart;
		this.monde = depart.getMonde();
		this.arrivee = arrivee;
		liste_ouverte = new HashMap<Case, PathFinding.noeud>();
		liste_fermee = new HashMap<Case, PathFinding.noeud>();
	}
	
	private float distance(int x1, int y1, int x2, int y2){
	    return (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	private void ajouter_cases_adjacentes(Case n) throws Exception{
	    noeud tmp = new noeud();
	    /* on met tous les noeud adjacents dans la liste ouverte (+vérif) */
	    for (int i=n.getX()-1; i<=n.getX()+1; i++){
	        if ((i<0) || (i>=n.getMonde().getDimensionX()))  /* en dehors de l'image, on oublie */
	            continue;
	        for (int j=n.getY()-1; j<=n.getY()+1; j++){
	        	tmp = new noeud();
	            if ((j<0) || (j>=n.getMonde().getDimensionY()))   /* en dehors, on oublie */
	                continue;
	            if ((i==n.getX()) && (j==n.getY()))  /* case actuelle n, on oublie */
	                continue;
	 
	            if ( n.getMonde().getCaseAt(i,j).getNiveau_obstacle() > 0)
	                /* obstace, terrain non franchissable, on oublie */
	                continue;
	 
	            Case it = n.getMonde().getCaseAt(i,j);
	            if (!deja_present_dans_liste(it, liste_fermee)){
	                /* le noeud n'est pas déjà présent dans la liste fermée */
	 
	                /* calcul du cout G du noeud en cours d'étude : cout du parent + distance jusqu'au parent */
	            	if(liste_fermee.get(n) != null){
	            		tmp.cout_g = liste_fermee.get(n).cout_g + distance(i,j,n.getX(),n.getY());  
	            	}
	            	else{
	            		tmp.cout_g = distance(i,j,n.getX(),n.getY());
	            	}
	                /* calcul du cout H du noeud à la destination */
	                tmp.cout_h = distance(i,j,arrivee.getX(),arrivee.getY());
	                tmp.cout_f = tmp.cout_g + tmp.cout_h;
	                tmp.parent = n;
	 
	                if (deja_present_dans_liste(it, liste_ouverte)){
	                    /* le noeud est déjà présent dans la liste ouverte, il faut comparer les couts */
	                    if (tmp.cout_f < liste_ouverte.get(it).cout_f){
	                        /* si le nouveau chemin est meilleur, on met à jour */
	                        liste_ouverte.put(it,tmp);
	                    }	 
	                    /* le noeud courant a un moins bon chemin, on ne change rien */	 
	                }else{
	                    /* le noeud n'est pas présent dans la liste ouverte, on l'y ajoute */
	                    liste_ouverte.put(n.getMonde().getCaseAt(i,j), tmp);
	                }
	            }
	        }
	    }
	}

	private boolean deja_present_dans_liste(Case it,
			HashMap<Case, noeud> liste) {
		if(liste.get(it) != null) return true;
		return false;
	}
	
	
	private Case meilleur_noeud(HashMap<Case, noeud> l){
	    float m_coutf = 0;
	    Case m_noeud = null;
	    Iterator<?> it = l.entrySet().iterator();
	    while (it.hasNext()) {
	        @SuppressWarnings("rawtypes")
			Map.Entry pairs = (Map.Entry)it.next();
	        if(m_noeud == null){
	        	m_coutf = ((noeud)pairs.getValue()).cout_f;
	        	m_noeud = ((Case)pairs.getKey());
	        }
	        if (((noeud)pairs.getValue()).cout_f< m_coutf){
	        	m_coutf = ((noeud)pairs.getValue()).cout_f;
	        	m_noeud = ((Case)pairs.getKey());
	        }
	    }
	    return m_noeud;
	}
	
	private void ajouter_liste_fermee(Case p){
		if(liste_ouverte.get(p) != null){
		    noeud n = liste_ouverte.get(p);
		    liste_fermee.put(p,n);
		    liste_ouverte.remove(p);		       
		}
	    return;
	}
	
	private List<Case> retrouver_chemin() throws Exception{
		List<Case> path = new ArrayList<Case>();
		
		/*Si la fourmi est déja sur la fourmiliere le chemin de retour est vide*/
		if(depart.equals(arrivee)) {
			return path;
		}
		
	    /* l'arrivée est le dernier élément de la liste fermée */
	    noeud tmp = liste_fermee.get(monde.getCaseAt(arrivee.getX(),arrivee.getY()));
	    path.add(monde.getCaseAt(arrivee.getX(),arrivee.getY()));
	    Case prec = tmp.parent;	
	    path.add(prec);
	    while (prec != depart){
	        tmp = liste_fermee.get(tmp.parent);
	        prec = tmp.parent;
		    path.add(prec);
	    }
	    path.remove(prec);
	    List<Case> result = new ArrayList<Case>();
		for(int i=path.size()-1; i>=0; i--)
		    result.add(path.get(i));
		return result;
	}
	
	public List<Case> findPath() throws Exception{
	   Case courant;
	  
	    /* déroulement de l'algo A* */
	 
	    /* initialisation du noeud courant */
	    courant = depart;
	    /* ajout de courant dans la liste ouverte */
	    liste_ouverte.put(courant,new noeud());
	    ajouter_liste_fermee(courant);
	    ajouter_cases_adjacentes(courant);	 
	    /* tant que la destination n'a pas été atteinte et qu'il reste des noeuds à explorer dans la liste ouverte */
	    while(!((courant.getX() == arrivee.getX()) && (courant.getY() == arrivee.getY())) && (!liste_ouverte.isEmpty())){	 
	        /* on cherche le meilleur noeud de la liste ouverte, on sait qu'elle n'est pas vide donc il existe */
	        courant = meilleur_noeud(liste_ouverte);	 
	        /* on le passe dans la liste fermee, il ne peut pas déjà y être */
	        ajouter_liste_fermee(courant);	 
	        /* on recommence la recherche des noeuds adjacents */
	        ajouter_cases_adjacentes(courant);
	    }	 
	    /* si la destination est atteinte, on remonte le chemin */
	    if ((courant.getX() == arrivee.getX()) && (courant.getY() == arrivee.getY())){
	      return retrouver_chemin();
	    }else{
	      return new ArrayList<Case>();
	    }
	}
}
