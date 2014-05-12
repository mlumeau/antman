package org.ICE.PDC.antman.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.slider.WebSlider;

import javax.swing.JSplitPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.GridLayout;
import com.alee.laf.scroll.WebScrollPane;

public class ConfigFrame extends JFrame {
	private WebSlider vitesseWebSlider;
	private WebSlider meteoWebSlider;
	private WebSlider abondanceWebSlider;

	public ConfigFrame() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		java.util.Hashtable<Integer,JLabel> vitesselabelTable = new java.util.Hashtable<Integer,JLabel>();  
	    vitesselabelTable.put(new Integer(1), new JLabel("Lent"));  
	    vitesselabelTable.put(new Integer(2), new JLabel("Moyen"));  
	    vitesselabelTable.put(new Integer(3), new JLabel("Rapide"));  
		java.util.Hashtable<Integer,JLabel> meteolabelTable = new java.util.Hashtable<Integer,JLabel>();  
		meteolabelTable.put(new Integer(1), new JLabel("Mauvais"));  
		meteolabelTable.put(new Integer(2), new JLabel("Moyen"));  
		meteolabelTable.put(new Integer(3), new JLabel("Bon"));  
		java.util.Hashtable<Integer,JLabel> abondancelabelTable = new java.util.Hashtable<Integer,JLabel>();  
		abondancelabelTable.put(new Integer(1), new JLabel("Basse"));  
		abondancelabelTable.put(new Integer(2), new JLabel("Moyenne"));  
		abondancelabelTable.put(new Integer(3), new JLabel("Haute"));  

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		WebPanel controlPanel = new WebPanel();
		controlPanel.setMinimumHeight(200);
		controlPanel.setMinimumWidth(100);
		WebPanel mapPanel = new WebPanel();
		
		JSplitPane mainSplitPane = new JSplitPane();
		mainSplitPane.setResizeWeight(1.0);
		splitPane.setLeftComponent(mainSplitPane);
		
		WebPanel fourmilieresPanel = new WebPanel();
		splitPane.setRightComponent(fourmilieresPanel);
		fourmilieresPanel.setLayout(new BorderLayout(0, 0));
		
		WebPanel fourmList = new WebPanel();
		fourmilieresPanel.add(fourmList, BorderLayout.CENTER);
		
		WebPanel fourmControls = new WebPanel();
		fourmilieresPanel.add(fourmControls, BorderLayout.SOUTH);

		mainSplitPane.setLeftComponent(mapPanel);
		mapPanel.setLayout(new BorderLayout(0, 0));
		
		WebScrollPane webScrollPane = new WebScrollPane((Component) null);
		mapPanel.add(webScrollPane, BorderLayout.CENTER);
		
		WebPanel webPanel = new WebPanel();
		mapPanel.add(webPanel, BorderLayout.NORTH);
		webPanel.setLayout(new BoxLayout(webPanel, BoxLayout.X_AXIS));
		
		JLabel lblMapPreview = new JLabel("Map preview");
		webPanel.add(lblMapPreview);
		lblMapPreview.setPreferredSize(new Dimension(65, 30));
		lblMapPreview.setAlignmentX(0.5f);
		lblMapPreview.setHorizontalAlignment(SwingConstants.CENTER);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		webPanel.add(horizontalStrut);
		
		JLabel lblTaille = new JLabel("");
		webPanel.add(lblTaille);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		webPanel.add(horizontalStrut_1);
		
		JLabel lblObstacles = new JLabel("");
		webPanel.add(lblObstacles);
		mainSplitPane.setRightComponent(controlPanel);
		controlPanel.setLayout(new MigLayout("", "[200px]", "[19.00px][][44px][][][44px][][][44px]"));
		
		JLabel lblMapControls = new JLabel("Map controls:");
		lblMapControls.setVerticalAlignment(SwingConstants.TOP);
		lblMapControls.setHorizontalAlignment(SwingConstants.CENTER);
		lblMapControls.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlPanel.add(lblMapControls, "cell 0 0,grow");
		
		JLabel speedLabel = new JLabel("Vitesse mode auto");
		speedLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		controlPanel.add(speedLabel, "cell 0 1,grow");
		
		vitesseWebSlider = new WebSlider();
		vitesseWebSlider.setPreferredWidth(200);
		vitesseWebSlider.setSnapToTicks(true);
		vitesseWebSlider.setMajorTickSpacing (1);
		vitesseWebSlider.setMinimum(1);
		vitesseWebSlider.setMaximum(3);
		vitesseWebSlider.setLabelTable(vitesselabelTable);
		vitesseWebSlider.setPaintTicks (true);  
		vitesseWebSlider.setPaintLabels (true);   
		controlPanel.add(vitesseWebSlider, "cell 0 2,grow");
		
		meteoWebSlider = new WebSlider();
		meteoWebSlider.setSnapToTicks(true);
		meteoWebSlider.setMajorTickSpacing (1);
		meteoWebSlider.setMinimum(1);
		meteoWebSlider.setMaximum(3);
		meteoWebSlider.setLabelTable(meteolabelTable);
		
		JSeparator separator_1 = new JSeparator();
		controlPanel.add(separator_1, "cell 0 3,growx");
		
		JLabel meteoLabel = new JLabel("Météo");
		meteoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		controlPanel.add(meteoLabel, "cell 0 4,grow");
		meteoWebSlider.setPaintTicks (true);  
		meteoWebSlider.setPaintLabels (true);   
		meteoWebSlider.setSnapToTicks(true);
		controlPanel.add(meteoWebSlider, "cell 0 5,grow");
		
		abondanceWebSlider = new WebSlider();
		abondanceWebSlider.setPreferredSize(new Dimension(100, 22));
		abondanceWebSlider.setSnapToTicks(true);
		abondanceWebSlider.setMajorTickSpacing (1);
		abondanceWebSlider.setMinimum(1);
		abondanceWebSlider.setMaximum(3);
		abondanceWebSlider.setLabelTable(abondancelabelTable);
		
		JSeparator separator = new JSeparator();
		controlPanel.add(separator, "cell 0 6,growx");
		
		JLabel abondanceLabel = new JLabel("Abondance");
		abondanceLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		controlPanel.add(abondanceLabel, "cell 0 7,grow");
		abondanceWebSlider.setPaintTicks (true);  
		abondanceWebSlider.setPaintLabels (true);   
		abondanceWebSlider.setSnapToTicks(true);
		controlPanel.add(abondanceWebSlider, "cell 0 8,grow");
	}

}
