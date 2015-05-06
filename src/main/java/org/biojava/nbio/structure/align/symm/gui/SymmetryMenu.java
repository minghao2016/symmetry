package org.biojava.nbio.structure.align.symm.gui;

import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.biojava.nbio.structure.align.gui.MenuCreator;
import org.biojava.nbio.structure.align.gui.MyAlignmentLoadListener;
import org.biojava.nbio.structure.align.gui.MyDistMaxListener;
import org.biojava.nbio.structure.align.model.AFPChain;

/**
 *  Create a menu for the Symmetry analysis GUI. Adapted from MenuCreator in biojava.
 *	
 *	@author lafita
 *
 */
public class SymmetryMenu extends MenuCreator {
	
	//Menu Options for the Symmetry Display
	public static final String SEQUENCE_PANEL = "Sequence Panel";
	public static final String SUBUNIT_DISPLAY = "Subunit Superimposition";
	public static final String MULTIPLE_STRUCT = "Multiple Structure Alignment";
	public static final String SYMMETRY = "New Symmetry Analysis";
	
	/** 
	 *  Provide a JMenuBar that can be added to a JFrame
	 */
	public static JMenuBar initMenu(JFrame frame, SymmetryJmol parent, AFPChain afpChain){

		JMenuBar menu = new JMenuBar();

		//FILE tab
		JMenu file= new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		file.getAccessibleContext().setAccessibleDescription("File Menu");

		if ( parent != null){
			JMenuItem loadF = getLoadMenuItem();
			loadF.addActionListener(new MyAlignmentLoadListener(parent));
			file.add(loadF);
		}

		JMenuItem saveF = getSaveAlignmentMenuItem(afpChain);
		file.add(saveF);

		JMenuItem openPDB = getShowPDBMenuItem();
		file.add(openPDB);

		JMenuItem openI = getOpenPDBMenuItem();

		file.add(openI);

		if ( parent != null){
			JMenuItem exportI =  getExportPDBMenuItem(parent);

			file.add(exportI);
		}

		JMenuItem openDBI = getDBResultMenuItem();
		file.add(openDBI);
		file.addSeparator();

		if ( parent != null){
			JMenuItem print = getPrintMenuItem();
			print.addActionListener(parent.getJmolPanel());

			file.add(print);
		}
		file.addSeparator();

		JMenuItem closeI = getCloseMenuItem(frame);

		file.add(closeI);

		JMenuItem exitI = getExitMenuItem();
		file.add(exitI);
		menu.add(file);

		//ALIGNMENT tab
		JMenu align = new JMenu("View");
		align.setMnemonic(KeyEvent.VK_V);

		if ( parent != null){
			JMenuItem aligpI = MenuCreator.getIcon(parent,SEQUENCE_PANEL);
			aligpI.setMnemonic(KeyEvent.VK_L);
			aligpI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, keyMask));
			align.add(aligpI);
		}

		if ( afpChain != null){
			JMenuItem distMax = new  JMenuItem("Show Distance Matrices");
			distMax.setMnemonic(KeyEvent.VK_D);
			distMax.addActionListener(new MyDistMaxListener(afpChain));
			align.add(distMax);
		}
		
		JMenuItem pairI = getPairwiseAlignmentMenuItem();
		align.add(pairI);
		menu.add(align);
		
		//SYMMETRY tab
		JMenu sym = new JMenu("Symmetry");
		sym.setMnemonic(KeyEvent.VK_S);
		
		JMenuItem subunits = new JMenuItem(SUBUNIT_DISPLAY);
		subunits.addActionListener(parent);
		subunits.setMnemonic(KeyEvent.VK_D);
		
		JMenuItem mulStAln = new JMenuItem(MULTIPLE_STRUCT);
		mulStAln.addActionListener(parent);
		mulStAln.setMnemonic(KeyEvent.VK_T);
		
		JMenuItem newSym = getNewSymmetryMenuItem();
		newSym.addActionListener(parent);
		
		sym.add(subunits);
		sym.add(mulStAln);
		sym.add(newSym);
		menu.add(sym);

		//HELP tab
		JMenu about = new JMenu("Help");
		about.setMnemonic(KeyEvent.VK_H);

		JMenuItem helpM = getHelpMenuItem();
		about.add(helpM);

		JMenuItem aboutM = getAboutMenuItem();
		about.add(aboutM);

		menu.add(Box.createGlue());
		menu.add(about);

		return menu;

	}
	
	/** 
	 *  Provide a display for a symmetry analysis
	 */
	protected static void showSymmDialog(){
		SymmetryGui gui =  SymmetryGui.getInstance();
		gui.setVisible(true);
	}
	
	private static JMenuItem getNewSymmetryMenuItem() {
		ImageIcon alignIcon = createImageIcon("/icons/window_new.png");

		JMenuItem symI;
		if ( alignIcon == null)
			symI = new JMenuItem(SYMMETRY);
		else 
			symI = new JMenuItem(SYMMETRY, alignIcon);
		symI.setMnemonic(KeyEvent.VK_Y);
		symI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, keyMask));
		return symI;
	}
}