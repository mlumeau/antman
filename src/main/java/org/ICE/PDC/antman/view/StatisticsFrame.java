/**
 * 
 */
package org.ICE.PDC.antman.view;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebFrame;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class StatisticsFrame extends WebFrame {

	private static final long serialVersionUID = 1L;
	private Monde monde;
	
	
	public StatisticsFrame(Monde m) {
		setTitle("Résultats");
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
    				
    				StatisticsFrame sf = (StatisticsFrame) p; 
    				sf.setDefaultCloseOperation(EXIT_ON_CLOSE);
    				sf.dispose();	
    				new LaunchFrame(); 
	        	}
	        });
	        this.addWindowListener(new WindowAdapter()
	        {
	            @Override
	            public void windowClosing(WindowEvent e){
    				setDefaultCloseOperation(EXIT_ON_CLOSE);
    				dispose();	
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
	    chart.setAntiAlias(true);
	    return new ChartPanel(chart);
    }
 
    private XYDataset createDataset() {
        // creates an XY dataset...
        // returns the dataset
    	XYSeriesCollection dataset = new XYSeriesCollection();
    	
    	StatsCalculator sc = new StatsCalculator(monde);
    	
    	int i=0;
    	Object[] fourms =  sc.getPopulationTicks().keySet().toArray();
    	
    	while (i<5 && i<fourms.length){
    		ArrayList<Integer> pop = sc.getPopulationTicks().get((Fourmiliere)fourms[i]);
    	    XYSeries serie = new XYSeries("Fourmiliere "+ i);
    	    
    		for(int j=0 ; j<=monde.getTour();j++){
    			serie.add(j,pop.get(j),true);
    		}
    		dataset.addSeries(serie);
    		i++;
    	}
    	
    	return dataset;
    }
}