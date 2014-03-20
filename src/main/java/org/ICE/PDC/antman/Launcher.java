package org.ICE.PDC.antman;

import javax.swing.UIManager;

import org.ICE.PDC.antman.controller.MainCtrl;
import org.ICE.PDC.antman.view.MainFrame;

import com.alee.laf.WebLookAndFeel;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WebLookAndFeel.install();
      	MainFrame mf = new MainFrame();
      	
      	mf.setVisible(true);

	}

}
