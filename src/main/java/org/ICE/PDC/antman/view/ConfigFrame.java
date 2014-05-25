package org.ICE.PDC.antman.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.miginfocom.swing.MigLayout;

import org.ICE.PDC.antman.ConfigurationLoader;
import org.ICE.PDC.antman.controller.MainCtrl;
import org.ICE.PDC.antman.model.Case;
import org.ICE.PDC.antman.model.Fourmiliere;
import org.ICE.PDC.antman.model.Monde;
import org.ICE.PDC.antman.model.Reine;

import com.alee.extended.colorchooser.WebColorChooserField;
import com.alee.laf.button.WebButton;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.slider.WebSlider;
import com.alee.laf.table.WebTable;

/**
 * Fenêtre de configuration d'une simulation
 */
public class ConfigFrame extends WebFrame {
	private static final long serialVersionUID = -6522556577077046520L;
	private WebSlider vitesseWebSlider;
	private WebSlider meteoWebSlider;
	private WebSlider abondanceWebSlider;
	
	private Monde m;
	private WebScrollPane mapWebScrollPane;
	private FourmiliereTableModel frmModel;
	private WebTable table;
	private WebButton wbtnSupprimer;
	private JLabel lblTaille;
	private HashMap<Fourmiliere, Color> colors;

	public ConfigFrame() {
		setTitle("Configuration de la simulation");
		setMinimumSize(new Dimension(500, 400));
		setSize(new Dimension(850, 630));
		setLocationByPlatform(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		java.util.Hashtable<Integer,JLabel> vitesselabelTable = new java.util.Hashtable<Integer,JLabel>();  
	    vitesselabelTable.put(new Integer(1), new JLabel("Lent"));  
	    vitesselabelTable.put(new Integer(2), new JLabel("Moyen"));  
	    vitesselabelTable.put(new Integer(3), new JLabel("Rapide"));  
		java.util.Hashtable<Integer,JLabel> meteolabelTable = new java.util.Hashtable<Integer,JLabel>();  
		meteolabelTable.put(new Integer(1), new JLabel("Mauvais"));  
		meteolabelTable.put(new Integer(100), new JLabel("Bon"));  
		java.util.Hashtable<Integer,JLabel> abondancelabelTable = new java.util.Hashtable<Integer,JLabel>();  
		abondancelabelTable.put(new Integer(1), new JLabel("Basse"));  
		abondancelabelTable.put(new Integer(100), new JLabel("Haute"));  

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.8);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		WebPanel controlPanel = new WebPanel();
		controlPanel.setSize(new Dimension(200, 0));
		controlPanel.setPreferredWidth(200);
		controlPanel.setMinimumHeight(200);
		controlPanel.setMinimumWidth(100);
		WebPanel mapPanel = new WebPanel();
		
		JSplitPane mainSplitPane = new JSplitPane();
		mainSplitPane.setResizeWeight(1.0);
		mainSplitPane.setDividerSize(0);
		mainSplitPane.setEnabled(false);
		splitPane.setLeftComponent(mainSplitPane);
		
		WebPanel fourmilieresPanel = new WebPanel();
		fourmilieresPanel.setMinimumSize(new Dimension(0, 200));
		splitPane.setRightComponent(fourmilieresPanel);
		fourmilieresPanel.setLayout(new BorderLayout(0, 0));
		
		WebPanel fourmList = new WebPanel();
		fourmilieresPanel.add(fourmList, BorderLayout.CENTER);
		
		WebPanel fourmControls = new WebPanel();
		fourmilieresPanel.add(fourmControls, BorderLayout.SOUTH);
		fourmControls.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		
		mainSplitPane.setLeftComponent(mapPanel);
		mapPanel.setLayout(new BorderLayout(0, 0));
		
		mapWebScrollPane = new WebScrollPane((Component) null);
		mapPanel.add(mapWebScrollPane, BorderLayout.CENTER);
		
		WebPanel webPanel = new WebPanel();
		webPanel.setMinimumHeight(20);
		mapPanel.add(webPanel, BorderLayout.NORTH);
		webPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMapPreview = new JLabel("  Prévisualisation de la carte");
		webPanel.add(lblMapPreview, BorderLayout.WEST);
		lblMapPreview.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblMapPreview.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblTaille = new JLabel("");
		webPanel.add(lblTaille, BorderLayout.EAST);
		mainSplitPane.setRightComponent(controlPanel);
		controlPanel.setLayout(new MigLayout("", "[200px]", "[19.00px][][][][44px][][][][44px][][][][44px]"));
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		controlPanel.add(verticalStrut_1, "cell 0 1");
		
		Component verticalStrut = Box.createVerticalStrut(20);
		controlPanel.add(verticalStrut, "cell 0 2");
		
		JLabel speedLabel = new JLabel("Vitesse mode auto");
		speedLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		controlPanel.add(speedLabel, "cell 0 3,grow");
		
		vitesseWebSlider = new WebSlider();
		vitesseWebSlider.setValue(2);
		vitesseWebSlider.setPreferredWidth(200);
		vitesseWebSlider.setSnapToTicks(true);
		vitesseWebSlider.setMajorTickSpacing (1);
		vitesseWebSlider.setMinimum(1);
		vitesseWebSlider.setMaximum(3);
		vitesseWebSlider.setLabelTable(vitesselabelTable);
		vitesseWebSlider.setPaintTicks (true);  
		vitesseWebSlider.setPaintLabels (true);   
		controlPanel.add(vitesseWebSlider, "cell 0 4,grow");
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		controlPanel.add(verticalStrut_2, "cell 0 5");
		
		JSeparator separator_1 = new JSeparator();
		controlPanel.add(separator_1, "cell 0 6,growx");
		
		meteoWebSlider = new WebSlider();
		meteoWebSlider.setMinorTickSpacing(99);
		meteoWebSlider.setMajorTickSpacing (99);
		meteoWebSlider.setMinimum(1);
		meteoWebSlider.setPaintTicks (true);  
		meteoWebSlider.setPaintLabels (true);   
		meteoWebSlider.setLabelTable(meteolabelTable);
		controlPanel.add(meteoWebSlider, "cell 5 1 2 1,grow");
		
		abondanceWebSlider = new WebSlider();
		abondanceWebSlider.setMinorTickSpacing(99);
		abondanceWebSlider.setMajorTickSpacing (99);
		abondanceWebSlider.setMinimum(1);
		abondanceWebSlider.setPaintTicks (true);  
		abondanceWebSlider.setPaintLabels (true);   
		abondanceWebSlider.setLabelTable(abondancelabelTable);
		controlPanel.add(abondanceWebSlider, "cell 8 1 2 1,grow");
		
		JLabel meteoLabel = new JLabel("Météo");
		meteoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		controlPanel.add(meteoLabel, "cell 0 7,grow");
		controlPanel.add(meteoWebSlider, "cell 0 8,grow");
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		controlPanel.add(verticalStrut_3, "cell 0 9");
		
		JSeparator separator = new JSeparator();
		controlPanel.add(separator, "cell 0 10,growx");
		
		JLabel abondanceLabel = new JLabel("Abondance");
		abondanceLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		controlPanel.add(abondanceLabel, "cell 0 11,grow");
		controlPanel.add(abondanceWebSlider, "cell 0 12,grow");
		
		 // Table
		frmModel = new FourmiliereTableModel();
        table = new WebTable ( frmModel );
        WebScrollPane tableScrollPane = new WebScrollPane ( table );

        table.setRowHeight(25);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			public void valueChanged(ListSelectionEvent arg0) {
				ListSelectionModel lsm = (ListSelectionModel)arg0.getSource();
				
				if(lsm.isSelectionEmpty())
					wbtnSupprimer.setEnabled(false);
				else
					wbtnSupprimer.setEnabled(true);
			}
        });
        
        // Custom column
        TableColumn column = table.getColumnModel ().getColumn ( 8 );
        
        TableCellRenderer renderer = new TableCellRenderer () {
			
			public Component getTableCellRendererComponent(JTable table, final Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		        // Color chooser field
		        WebColorChooserField simpleColorChooser = new WebColorChooserField ();
		        simpleColorChooser.setPipetteEnabled ( false );
		        simpleColorChooser.setColumns ( 11 );
		        simpleColorChooser.setColor((Color)value);
		        
		        if (isSelected)
		        {
		        	simpleColorChooser.setBackground(table.getSelectionBackground());
		        	simpleColorChooser.setForeground(table.getSelectionForeground());
		        }
		        else
		        {
		        	simpleColorChooser.setBackground(table.getBackground());
		        	simpleColorChooser.setForeground(table.getForeground());
		        }
		        simpleColorChooser.setEditable(false);
		        
		        return simpleColorChooser;
			}
            
		};
        column.setCellRenderer ( renderer );
        
        TableCellEditor editor = new ColorTableCellEditor(new WebColorChooserField());
        column.setCellEditor(editor);

        // Better column sizes
        initColumnSizes ( table );
        
        fourmList.add(tableScrollPane, BorderLayout.CENTER);
        
        WebPanel webPanelFourmHeader = new WebPanel();
        fourmList.add(webPanelFourmHeader, BorderLayout.NORTH);
        webPanelFourmHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        
        JLabel lblFourm = new JLabel("Fourmilières au départ");
        webPanelFourmHeader.add(lblFourm);
        
        wbtnSupprimer = new WebButton();
		wbtnSupprimer.setIcon(new ImageIcon(ConfigFrame.class.getResource("/com/alee/laf/tree/icons/collapse.png")));
		wbtnSupprimer.setEnabled(false);
		wbtnSupprimer.setText("Supprimer");
		fourmControls.add(wbtnSupprimer);
		wbtnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmModel.removeRow(table.getSelectedRow());
			}
		});
		
		WebButton addFourmWebButton = new WebButton();
		addFourmWebButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmModel.addRow();
			}
		});
		addFourmWebButton.setIcon(new ImageIcon(ConfigFrame.class.getResource("/com/alee/laf/tree/icons/expand.png")));
		addFourmWebButton.setText("Ajouter une fourmilière");
		fourmControls.add(addFourmWebButton);

		WebButton wbtnLancerLaSimulation = new WebButton();
		wbtnLancerLaSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Creation des fourmis de départ & enregistrement des couleurs
				colors = new HashMap<Fourmiliere, Color>();
				
				for(Object[] frm : frmModel.data){
					try {
						Fourmiliere f;
						
						f = new Fourmiliere(m, m.getCaseAt((Integer)frm[2], (Integer)frm[1]), (Integer)frm[4], (Integer)frm[3], (Integer)frm[7],(Integer)frm[6]);
						
						Reine r= new Reine(f);
						
						for(int i=0; i<(Integer)frm[5];i++){
							r.pondre();
						}
						
						colors.put(f, (Color)frm[8]);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				//Création & Affichage de la fenêtre principale
				MainCtrl ctrl = new MainCtrl(m);
				MainFrame mf = new MainFrame(ctrl,colors);
				ctrl.setMainFrame(mf);
				mf.getAbondanceWebSlider().setValue(abondanceWebSlider.getValue());
				mf.getMeteoWebSlider().setValue(meteoWebSlider.getValue());
				mf.getVitesseWebSlider().setValue(vitesseWebSlider.getValue());
				mf.getMainFrameListener().setAbondance(abondanceWebSlider.getValue());
				mf.getMainFrameListener().setMeteo(meteoWebSlider.getValue());
				mf.setVisible(true);
				mf.setDefaultCloseOperation(EXIT_ON_CLOSE);
				mf.initMonde(m);
				//Fermeture de la fenêtre de configuration
				dispose();
				
			}
		});
		wbtnLancerLaSimulation.setIcon(new ImageIcon(ConfigFrame.class.getResource("/com/alee/laf/splitpane/icons/right.png")));
		wbtnLancerLaSimulation.setText("Lancer la simulation");
		fourmControls.add(wbtnLancerLaSimulation);

	}
	
	/**
	 * Dessine graphiquement un objet monde
	 * @param monde
	 * @param model
	 * @throws Exception
	 */
	public void paintMap(Monde monde, FourmiliereTableModel model) throws Exception{
		JPanel map = new JPanel(new GridLayout(monde.getDimensionX(),monde.getDimensionY(),2,2));
    	map.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
    	
    	lblTaille.setText("Dimensions: "+monde.getDimensionX()+ "x"+monde.getDimensionY());
    	
		for(int x=0; x<monde.getDimensionX(); x++) {
			
			for(int y=0; y<monde.getDimensionY(); y++) {
				final Case current = monde.getCaseAt(x,y);
				
				final JLabel label = new JLabel("", SwingConstants.CENTER);
			    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			    label.setOpaque(true);
			    
				if(current.getNiveau_obstacle() > 0) {
					label.setBackground(Color.BLACK);
				}
				
				map.add(label);
			}
			
		}

		for(int row = 0; row<model.getRowCount();row++){
			((JLabel) map.getComponent((Integer) model.getValueAt(row, 2)*monde.getDimensionY() + (Integer) model.getValueAt(row, 1)) ).setBackground((Color) model.getValueAt(row, 8));
			((JLabel) map.getComponent((Integer) model.getValueAt(row, 2)*monde.getDimensionY() + (Integer) model.getValueAt(row, 1)) ).setText(model.getValueAt(row, 7).toString());
		}
		
		mapWebScrollPane.setViewportView(map);
	}
	
	public Monde getMonde() {
		return m;
	}

	/**
	 * Défini le monde à utiliser et crée des fourmilières par défaut
	 * @param m
	 */
	public void setMonde(Monde m) {
		this.m = m;
		
		List<Case> cases_libres = new ArrayList<Case>();
		
		for(Case c : this.m.get_cases()) {
			if(c.getNiveau_obstacle() == 0) {
				cases_libres.add(c);
			}
		}
		
		if(cases_libres.size() > 0 && ConfigurationLoader.MAX_FOURMILIERES >=1) {
			Case c = cases_libres.get(new Random().nextInt(cases_libres.size()));
			cases_libres.remove(c);
			frmModel.data.add(new Object[] {1, c.getY(), c.getX(), 45, 5, 6, 20, 100, Color.green});
		}
		
		if(cases_libres.size() > 0 && ConfigurationLoader.MAX_FOURMILIERES >=2) {
			Case c = cases_libres.get(new Random().nextInt(cases_libres.size()));
			cases_libres.remove(c);
			frmModel.data.add(new Object[] {0, c.getY(), c.getX(), 45, 5, 6, 20, 100, Color.blue});
		}
		
        try {
			paintMap(m,frmModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param table
	 */
	private void initColumnSizes ( JTable table )
    {
		FourmiliereTableModel model = ( FourmiliereTableModel ) table.getModel ();
        TableColumn column;
        Component comp;
        int headerWidth;
        int cellWidth;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer = table.getTableHeader ().getDefaultRenderer ();

        for ( int i = 0; i < model.getColumnCount (); i++ )
        {
            column = table.getColumnModel ().getColumn ( i );

            comp = headerRenderer.getTableCellRendererComponent ( null, column.getHeaderValue (), false, false, 0, 0 );
            headerWidth = comp.getPreferredSize ().width;

            comp = table.getDefaultRenderer ( model.getColumnClass ( i ) ).getTableCellRendererComponent ( table, longValues[ i ], false, false, 0, i );
            cellWidth = comp.getPreferredSize ().width;

            column.setPreferredWidth ( Math.max ( headerWidth, cellWidth ) );
        }
    }
	
	/**
	 * Classe correspondant au tableau de fourmilières
	 */
	class FourmiliereTableModel extends AbstractTableModel
    {
		private static final long serialVersionUID = -236812648575778836L;
		private String[] columnNames = { "#", "PosX", "PosY", "Taille crit.", "Fécondité/tour", "Nb fourmis", "% éclaireuses", "Ressources départ","Couleur" };
        private ArrayList<Object[]> data = new ArrayList<Object[]>();

        public final Object[] longValues = {0, 5, 5, 45, 5, 6, 20, 100, Color.blue};
        
        public FourmiliereTableModel(){
        }

        public int getColumnCount ()
        {
            return columnNames.length;
        }

        public int getRowCount ()
        {
            return data.size();
        }

        @Override
        public String getColumnName ( int col )
        {
            return columnNames[ col ];
        }

        public Object getValueAt ( int row, int col )
        {
            return data.get(row)[ col ];
        }

        @Override
        public Class<? extends Object> getColumnClass ( int c )
        {
            return longValues[ c ].getClass ();
        }

        @Override
        public boolean isCellEditable ( int row, int col )
        {
            return col >= 1;
        }

        @Override
        public void setValueAt ( Object value, int row, int col )
        {
        	//On vérifie de ne pas écraser une autre fourmiliere ou de ne pas tomber sur un obstacle
        	boolean valid = true;
        	
        	if(col == 1 || col == 2) {
	        	
	        	int x = 0;
	        	int y = 0;
	        	
	        	//Y modifié
	        	if(col == 1) {
	        		x = (Integer)data.get(row)[ 2 ];
	        		y = (Integer)value;
	        	}
	        	
	        	//X modifié
	        	if(col == 2) {
	        		x = (Integer)value;
	        		y = (Integer)data.get(row)[ 1 ];
	        	}
	        	
	        	//Vérification fourmiliere
				for(Object[] d : data) {
					if((Integer)d[2] == x && (Integer)d[1] == y) {
						valid = false;
					}
				}
				
				if(!valid) {
					//Affichage d'un message d'erreur
					WebOptionPane.showMessageDialog(null,
						"Impossible de déplacer la fourmilière ici.\nUne autre fourmiliere existe déjà à cet emplacement",
					    "Antman simulator",
					    WebOptionPane.ERROR_MESSAGE);
					
				} else {
				
					//Vérification obstacle
					try {
						int obstacle = m.getCaseAt(x, y).getNiveau_obstacle();
						
						if(obstacle > 0) {
							//Affichage d'un message d'erreur
							WebOptionPane.showMessageDialog(null,
								"Impossible de déplacer la fourmilière ici.\nUn obstacle occupe cet emplacement",
							    "Antman simulator",
							    WebOptionPane.ERROR_MESSAGE);
							valid = false;
						}
						
					} catch (Exception e) {
						
						//Affichage d'un message d'erreur
						WebOptionPane.showMessageDialog(null,
							"Impossible de déplacer la fourmilière ici.\nCase inexistante",
						    "Antman simulator",
						    WebOptionPane.ERROR_MESSAGE);
						valid = false;
						
					}
				
				}
	        	
        	}
        	
        	if(valid) {
	        	data.get(row)[ col ] = value;
	            fireTableCellUpdated ( row, col );
	            try {
					paintMap(m, frmModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	
        }
        
        public void addRow(){
        	
        	if(ConfigurationLoader.MAX_FOURMILIERES > data.size()) {
        	
	        	List<Case> cases_libres = new ArrayList<Case>();
	    		
	    		for(Case c : m.get_cases()) {
	    			if(c.getNiveau_obstacle() == 0) {
	    				boolean libre = true;
	    				
	    				for(Object[] d : data) {
	    					if((Integer)d[2] == c.getX() && (Integer)d[1] == c.getY()) {
	    						libre = false;
	    					}
	    				}
	    				
	    				if(libre)  {
	    					cases_libres.add(c);
	    				}
	    			}
	    		}
	    		
	    		if(cases_libres.size() > 0) {
	    			Random rand=new Random();
		    		Case c = cases_libres.get(rand.nextInt(cases_libres.size()));
		        	data.add(new Object[]{data.size(), c.getY(), c.getX(), 45, 5, 6, 50, 100, new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat())});
		            fireTableDataChanged();
		
		            try {
						paintMap(m, frmModel);
					} catch (Exception e) {
						e.printStackTrace();
					}
	    		} else {
	    			//Affichage d'un message d'erreur
					WebOptionPane.showMessageDialog(null,
						"Impossible d'ajouter une nouvelle fourmilière.\nTout les emplacements sont occupés",
					    "Antman simulator",
					    WebOptionPane.ERROR_MESSAGE);
	    		}
        	} else {
        		//Affichage d'un message d'erreur
				WebOptionPane.showMessageDialog(null,
					"Impossible d'ajouter une nouvelle fourmilière.\nNombre maximum de fourmilières atteint (Modifiez le fichier config.xml pour autoriser plus de fourmilières)",
				    "Antman simulator",
				    WebOptionPane.ERROR_MESSAGE);
        	}
    		
        }
        
        public void removeRow(int n){
        	
        	data.remove(n);
        	
        	for(int i=0;i<data.size();i++){
        		data.get(i)[0] = i;
        	}
        	
            fireTableDataChanged();

            try {
				paintMap(m, frmModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            if(data.size()>n)
            	table.setSelectedRow(n);
        }
    }
	
	class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor{
		private static final long serialVersionUID = 536203261345474595L;
		WebColorChooserField simpleColorChooser;
		
		public ColorTableCellEditor(WebColorChooserField scc){
			simpleColorChooser = scc;
		}
  
		public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
	        simpleColorChooser.setPipetteEnabled ( false );
	        simpleColorChooser.setColumns ( 11 );
	        simpleColorChooser.setColor((Color)arg1);
	        
	        simpleColorChooser.setEditable(false);
	        

	        return simpleColorChooser;
		}

		public Object getCellEditorValue() {
			return simpleColorChooser.getColor();
		}

		
	}

}
