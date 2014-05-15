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
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.ICE.PDC.antman.Launcher;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.slider.WebSlider;
import com.alee.laf.spinner.WebSpinner;

public class CreationFrame extends WebFrame {

	public CreationFrame(String maps, String save) {
		setMinimumSize(new Dimension(720, 0));
		
		final String mapsPath = maps;
		final String savePath = save;
		
		setLocationByPlatform(true);
		setSize(new Dimension(700, 400));
		
		setTitle("Création d'une carte");
		setResizable(true);
		
		final WebSpinner webSpinner = new WebSpinner();
		webSpinner.setPreferredSize(new Dimension(50, 26));
		final WebSpinner webSpinner_1 = new WebSpinner();
		webSpinner_1.setPreferredSize(new Dimension(50, 26));

		webSpinner.setModel(new SpinnerNumberModel(new Integer(15), new Integer(1), null, new Integer(1)));
		webSpinner_1.setModel(new SpinnerNumberModel(new Integer(15), new Integer(1), null, new Integer(1)));

		
		final int tailleX = (Integer) webSpinner.getValue();
		final int tailleY = (Integer) webSpinner_1.getValue();
		

		
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
		map.setLayout(new GridLayout(tailleX, tailleY, 0, 0));
		
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
			    
			    p.setSize(10,10);
			    p.addMouseListener((new MouseListener() {
					
					public void mouseClicked(MouseEvent arg0) {
						if(label.getBackground().equals(Color.BLACK))
							label.setBackground(null);
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
		
		final WebLabel webLabel_3 = new WebLabel();
		webLabel_3.setVisible(false);
		webLabel_3.setText("15");
		panel_8.add(webLabel_3);
		
		JPanel panel_9 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_9.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_6.add(panel_9);
		
		WebLabel wblblLargeur = new WebLabel();
		wblblLargeur.setText("Largeur :   ");
		panel_9.add(wblblLargeur);
		
		
		panel_9.add(webSpinner_1);
		
		final WebLabel webLabel_4 = new WebLabel();
		webLabel_4.setVisible(false);
		webLabel_4.setText("15");
		panel_9.add(webLabel_4);
		
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
				webLabel_3.setText(""+tailleX);
				int tailleY = (Integer) webSpinner_1.getValue();
				webLabel_4.setText(""+tailleY);
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
		
		final WebSlider webSlider = new WebSlider();
		webSlider.setPaintTicks(true);
		webSlider.setPaintLabels(true);
		webSlider.setMinorTickSpacing(99);
		webSlider.setMinimum(1);
		webSlider.setMajorTickSpacing(99);
		webSlider.setValue(20);
		
		
		
		final WebLabel webLabel = new WebLabel();
		webLabel.setText("20");
		panel_2.add(webLabel);
		
		webSlider.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
			webLabel.setText("" + webSlider.getValue());
				
			}
		}); 
		
		WebLabel webLabel_1 = new WebLabel();
		webLabel_1.setText("%");
		panel_2.add(webLabel_1);
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
		
		WebLabel webLabel_2 = new WebLabel();
		webLabel_2.setText("1");
		panel_3.add(webLabel_2);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		WebButton wbtnValider = new WebButton();
		wbtnValider.setPreferredSize(new Dimension(160, 30));
		wbtnValider.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				
				//Sauvegarde d'un fond de carte 
				int dimension_x = Integer.parseInt(webLabel_3.getText());
				int dimension_y = Integer.parseInt(webLabel_4.getText());
				Map<Integer[],Integer> obstacles = new HashMap<Integer[], Integer>(); 
				int cpt = 0;
				for ( Component c : map.getComponents())
				{
					int posX = cpt%dimension_x; 
					int posY = cpt/dimension_y; 
					if(((Container) c).getComponent(0).getBackground().equals(Color.BLACK))
					{
						obstacles.put(new Integer[]{posX, posY},1);
					}
					
					cpt++;
				}
				
				String s = (String)JOptionPane.showInputDialog(
	                    map,
	                    "Nom de la carte",
	                    "Customized Dialog",
	                    JOptionPane.PLAIN_MESSAGE);

				//If a string was returned, say so.
				if ((s != null) && (s.length() > 0)) {
				    
					try {
						Launcher.saveMap(mapsPath+"/"+s+".xml",dimension_x,dimension_y,obstacles);
						
						JOptionPane.showMessageDialog(map,"La carte a été enregistrée.");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  //TODO REMOVE ME (TEST LINE)
					
				}
				else
				{
					JOptionPane.showMessageDialog(map,"Donnez un nom à votre carte.", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelSouth.add(wbtnValider);
		
		wbtnValider.setAlignmentX(Component.CENTER_ALIGNMENT);
		wbtnValider.setText("Enregistrer");
		
		WebButton wbtnGnrerUneCarte = new WebButton();
		panelSouth.add(wbtnGnrerUneCarte);
		wbtnGnrerUneCarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for ( Component c : map.getComponents())
				{
					if(Math.random()*100 > Integer.parseInt(webLabel.getText()))
						((Container) c).getComponent(0).setBackground(Color.WHITE); 
					else
						((Container) c).getComponent(0).setBackground(Color.BLACK); 
				}
			}
		});
		wbtnGnrerUneCarte.setPreferredSize(new Dimension(160, 30));
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
		wbtnRinitialiser.setPreferredSize(new Dimension(160, 30));
		wbtnRinitialiser.setText("Nettoyer la carte");
		
		WebButton wbtnRetournerAuMenu = new WebButton();
		wbtnRetournerAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0ActionEvent) {
				Component p = (Component)arg0ActionEvent.getSource(); 
				while ( (p = p.getParent()) != null && !(p instanceof WebFrame) );
				
				CreationFrame cf = (CreationFrame)p; 
				cf.setDefaultCloseOperation(EXIT_ON_CLOSE);
				cf.dispose();	
				new LaunchFrame(mapsPath, savePath); 
					
			}
		});
		wbtnRetournerAuMenu.setPreferredSize(new Dimension(160, 30));
		wbtnRetournerAuMenu.setText("Retourner au menu");
		panelSouth.add(wbtnRetournerAuMenu);
	}

	private static final long serialVersionUID = 1L;
	
}
