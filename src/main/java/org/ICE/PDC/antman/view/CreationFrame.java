/**
 * 
 */
package org.ICE.PDC.antman.view;

import javax.swing.JFrame;
import com.alee.laf.menu.WebMenuBar;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import com.alee.laf.scroll.WebScrollPane;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import com.alee.laf.label.WebLabel;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.DropMode;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import com.alee.laf.button.WebButton;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author S219
 * @generated "UML vers Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class CreationFrame extends JFrame {
	public CreationFrame() {
		setSize(new Dimension(700, 300));
		setResizable(false);
		setPreferredSize(new Dimension(700, 500));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setPreferredSize(new Dimension(0, 0));
		splitPane.setMinimumSize(new Dimension(0, 0));
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setPreferredSize(new Dimension(400, 400));
		splitPane.setLeftComponent(panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panelLeft.add(panel);
		
		JPanel map = new JPanel();
		panel.add(map);
		map.setBorder(new TitledBorder(null, "Prévisualisation de la carte", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JTextArea textArea = new JTextArea();
		map.add(textArea);
		textArea.setRows(10);
		textArea.setColumns(20);
		
		JPanel panelRight = new JPanel();
		splitPane.setRightComponent(panelRight);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panelRight.add(panel_1);
		FlowLayout fl_panel_1 = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel_1.setLayout(fl_panel_1);
		
		WebLabel wblblDimensionsDeLa = new WebLabel();
		wblblDimensionsDeLa.setText("Dimensions de la carte : ");
		panel_1.add(wblblDimensionsDeLa);
		
		WebLabel wblblX = new WebLabel();
		wblblX.setText("x");
		panel_1.add(wblblX);
		
		WebLabel webLabel = new WebLabel();
		webLabel.setText("*");
		panel_1.add(webLabel);
		
		WebLabel wblblY = new WebLabel();
		wblblY.setText("y");
		panel_1.add(wblblY);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelRight.add(panel_2);
		
		WebLabel wblblFacteursDobstacle = new WebLabel();
		wblblFacteursDobstacle.setText("Facteurs d'obstacle : ");
		panel_2.add(wblblFacteursDobstacle);
		
		WebLabel wblblN = new WebLabel();
		wblblN.setText("n");
		panel_2.add(wblblN);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelRight.add(panel_3);
		
		WebLabel wblblNiveauDobstacles = new WebLabel();
		wblblNiveauDobstacles.setText("Niveau d'obstacles : ");
		panel_3.add(wblblNiveauDobstacles);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panelRight.add(panel_4);
		
		WebButton wbtnGnrerUneCarte = new WebButton();
		wbtnGnrerUneCarte.setPreferredSize(new Dimension(200, 30));
		wbtnGnrerUneCarte.setText("Générer une carte aléatoire");
		panel_4.add(wbtnGnrerUneCarte);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_5.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panelRight.add(panel_5);
		
		WebButton wbtnNouvelleCarteVide = new WebButton();
		wbtnNouvelleCarteVide.setPreferredSize(new Dimension(200, 30));
		wbtnNouvelleCarteVide.setText("Nouvelle carte vide");
		panel_5.add(wbtnNouvelleCarteVide);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_6.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panelRight.add(panel_6);
		
		WebButton wbtnRinitialiser = new WebButton();
		wbtnRinitialiser.setPreferredSize(new Dimension(200, 30));
		wbtnRinitialiser.setText("Réinitialiser");
		panel_6.add(wbtnRinitialiser);
	}

	private static final long serialVersionUID = 1L;
	
}