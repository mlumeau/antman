package org.ICE.PDC.antman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebFrame;

import java.awt.ComponentOrientation;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JTextField;

import com.alee.laf.spinner.WebSpinner;

import javax.swing.SpinnerNumberModel;

import com.alee.laf.text.WebFormattedTextField;
import com.alee.laf.slider.WebSlider;

public class CreationFrame extends WebFrame {

	public CreationFrame() {
		setLocationByPlatform(true);
		setSize(new Dimension(699, 400));
		
		setTitle("Création d'une carte");
		setResizable(true);
		
		final WebSpinner webSpinner = new WebSpinner();
		webSpinner.setPreferredSize(new Dimension(50, 26));
		final WebSpinner webSpinner_1 = new WebSpinner();
		webSpinner_1.setPreferredSize(new Dimension(50, 26));

		webSpinner.setModel(new SpinnerNumberModel(new Integer(10), new Integer(0), null, new Integer(1)));
		webSpinner_1.setModel(new SpinnerNumberModel(new Integer(10), new Integer(0), null, new Integer(1)));

		
		int tailleX = (Integer) webSpinner.getValue();
		int tailleY = (Integer) webSpinner_1.getValue();
		

		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerSize(0);
		splitPane.setResizeWeight(0.8);
		splitPane.setEnabled(false);
		splitPane.setPreferredSize(new Dimension(350,350));
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setPreferredSize(new Dimension(500, 10));
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panelLeft.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel mapPanel = new JPanel();
		panel.add(mapPanel, BorderLayout.CENTER);
		mapPanel.setBorder(new TitledBorder(null, "Prévisualisation de la carte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
		
		final JPanel map = new JPanel();
		
		map.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		
		
		
		mapPanel.add(map);
		map.setLayout(new GridLayout(10, 10, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		mapPanel.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
    	
		for(int x=0; x<tailleX; x++) {
			
			for(int y=0; y<tailleY; y++) {
					
				JPanel p = new JPanel(); 
				p.setLayout(new BorderLayout());
				final JLabel label = new JLabel("", SwingConstants.CENTER);
			    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    label.setOpaque(true);
			    label.setSize(10,10);
			    label.setBackground(Color.WHITE);
			    p.setSize(10,10);
			    p.addMouseListener((new MouseListener() {
					
					public void mouseClicked(MouseEvent arg0) {
						if(label.getBackground().equals(Color.BLACK))
							label.setBackground(Color.WHITE);
						else
							label.setBackground(Color.BLACK);
						
					}

					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				}));
				p.add(label); 
				map.add(p);
			}
			
		}
		
		JPanel panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panelRight.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Dimension de la carte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_8.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_8);
		
		JLabel lblLongueur = new JLabel("Longueur :");
		panel_8.add(lblLongueur);
		
		panel_8.add(webSpinner);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_9.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_9);
		
		WebLabel wblblLargeur = new WebLabel();
		wblblLargeur.setText("Largeur :   ");
		panel_9.add(wblblLargeur);
		
		
		panel_9.add(webSpinner_1);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		FlowLayout flowLayout_4 = (FlowLayout) panel_5.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		
		WebButton wbtnNouvelleCarteVide = new WebButton();
		wbtnNouvelleCarteVide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				map.setVisible(false);
				map.removeAll();

				int tailleX = (Integer) webSpinner.getValue();
				int tailleY = (Integer) webSpinner_1.getValue();
				map.setLayout(new GridLayout(tailleY, tailleX, 0, 0));
				
				for(int x=0; x<tailleX; x++) {
					
					for(int y=0; y<tailleY; y++) {
							
						JPanel p = new JPanel(); 
						p.setLayout(new BorderLayout());
						final JLabel label = new JLabel("", SwingConstants.CENTER);
					    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					    label.setOpaque(true);
					    label.setSize(10,10);
					    label.setBackground(Color.WHITE);
					    p.setSize(10,10);
					    p.addMouseListener((new MouseListener() {
							
							public void mouseClicked(MouseEvent arg0) {
								if(label.getBackground().equals(Color.BLACK))
									label.setBackground(Color.WHITE);
								else
									label.setBackground(Color.BLACK);
								
							}

							public void mouseEntered(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mouseExited(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mousePressed(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}

							public void mouseReleased(MouseEvent arg0) {
								// TODO Auto-generated method stub
								
							}
						}));
						p.add(label); 
						map.add(p);
					}
					
				}
				
				
				map.revalidate();
				map.setVisible(true);

			}
		});
		panel_5.add(wbtnNouvelleCarteVide);
		wbtnNouvelleCarteVide.setPreferredSize(new Dimension(150, 30));
		wbtnNouvelleCarteVide.setText("Appliquer les dimensions");
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelRight.add(panel_2);
		
		WebLabel wblblFacteursDobstacle = new WebLabel();
		wblblFacteursDobstacle.setText("Facteurs d'obstacle : ");
		panel_2.add(wblblFacteursDobstacle);
		
		WebSlider webSlider = new WebSlider();
		webSlider.setPaintTicks(true);
		webSlider.setPaintLabels(true);
		webSlider.setMinorTickSpacing(99);
		webSlider.setMinimum(1);
		webSlider.setMajorTickSpacing(99);
		panel_2.add(webSlider);
		
		
		DecimalFormatSymbols symbol = new DecimalFormatSymbols(Locale.FRANCE);
		symbol.setDecimalSeparator('.');
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelRight.add(panel_3);
		
		WebLabel wblblNiveauDobstacles = new WebLabel();
		wblblNiveauDobstacles.setText("Niveau d'obstacles : ");
		panel_3.add(wblblNiveauDobstacles);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		WebButton wbtnValider = new WebButton();
		panelSouth.add(wbtnValider);
		wbtnValider.setPreferredWidth(200);
		wbtnValider.setPreferredHeight(30);
		
		wbtnValider.setAlignmentX(Component.CENTER_ALIGNMENT);
		wbtnValider.setText("Valider");
		
		WebButton wbtnGnrerUneCarte = new WebButton();
		panelSouth.add(wbtnGnrerUneCarte);
		wbtnGnrerUneCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for ( Component c : map.getComponents())
				{
					if(Math.random() > (Double)0.2)
						((Container) c).getComponent(0).setBackground(Color.WHITE); 
					else
						((Container) c).getComponent(0).setBackground(Color.BLACK); 
				}
			}
		});
		wbtnGnrerUneCarte.setPreferredSize(new Dimension(200, 30));
		wbtnGnrerUneCarte.setText("Générer une carte aléatoire");
		
		WebButton wbtnRinitialiser = new WebButton();
		panelSouth.add(wbtnRinitialiser);
		wbtnRinitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for ( Component c : map.getComponents())
				{
					((Container) c).getComponent(0).setBackground(Color.WHITE); 
				}
				
			}
		});
		wbtnRinitialiser.setPreferredSize(new Dimension(200, 30));
		wbtnRinitialiser.setText("Réinitialiser");
	}

	private static final long serialVersionUID = 1L;
	
}
