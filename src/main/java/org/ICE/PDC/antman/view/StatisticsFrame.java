/**
 * 
 */
package org.ICE.PDC.antman.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.ICE.PDC.antman.StatsCalculator;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebFrame;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class StatisticsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Monde monde;
	
	
	public StatisticsFrame(Monde m) {
		monde=m;
		
		setSize(new Dimension(800, 600));
		setLocationByPlatform(true);
		
		 JPanel chartPanel = createChartPanel();
	        getContentPane().add(chartPanel, BorderLayout.CENTER);
	        
	        JPanel panel = new JPanel();
	        getContentPane().add(panel, BorderLayout.SOUTH);
	        
	        WebButton wbtnFermer = new WebButton();
	        wbtnFermer.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
    				Component p = (Component)arg0.getSource(); 
    				while ( (p = p.getParent()) != null && !(p instanceof WebFrame) );
    				
    				CreationFrame cf = (CreationFrame)p; 
    				cf.setDefaultCloseOperation(EXIT_ON_CLOSE);
    				cf.dispose();	
    				new LaunchFrame(); 
	        	}
	        });
	        wbtnFermer.setText("Fermer");
	        panel.add(wbtnFermer);
	}
	
	private JPanel createChartPanel() {
        // creates a line chart object
        // returns the chart panel
		String chartTitle = "RÉSULTATS DE LA SIMULATION";
	    String xAxisLabel = "TOUR";
	    String yAxisLabel = "POPULATION DES FOURMILIÈRES";
	 
	    XYDataset dataset = createDataset();
	 
	    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, true,true, false);
	 
	    return new ChartPanel(chart);
    }
 
    private XYDataset createDataset() {
        // creates an XY dataset...
        // returns the dataset
    	XYSeriesCollection dataset = new XYSeriesCollection();
    	
    	StatsCalculator sc = new StatsCalculator(monde);
    	
    	int i=0;
    	
    	for (Fourmiliere f : monde.getFourmilieres()){
    		ArrayList<Integer> pop = sc.getPopulationTicks().get(f);
    	    XYSeries serie = new XYSeries("Fourmiliere "+ i);
    	    
    		for(int j=0 ; j<pop.size();j++){
    			serie.add(j,pop.get(j));
    		}
    		
    		i++;
    	}
    	
    	return dataset;
    }
}