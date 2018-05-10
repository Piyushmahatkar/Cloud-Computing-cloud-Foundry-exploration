/*******************************************************************************
 * Copyright (c) 2001, 2007 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/cpl-v10.html
 *
 * Contributors:
 *     Mathew A. Nelson
 *     - Initial API and implementation
 *     Flemming N. Larsen
 *     - Code cleanup
 *     - Removed check for the system property "SINGLEBUFFER", as it is not used
 *       anymore
 *     - Replaced the noDisplay with manager.setEnableGUI() and isGUIEnabled()
 *     - Replaced the -fps option with the -tps option
 *     - Added -nosound option and disables sound i the -nogui is specified
 *     - Updated to use methods from WindowUtil, FileUtil, Logger, which replaces
 *       methods that has been (re)moved from the robocode.util.Utils class
 *     - Moved the printRunningThreads() from robocode.util.Utils into this class
 *       and added javadoc for it
 *     - Added playing theme music at the startup, if music is provided
 *     - Changed to use FileUtil.getRobotsDir()
 *******************************************************************************/
package robocode;




import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import netscape.javascript.JSObject;

import appletComponentArch.DynamicTreePanel;

import robocode.battle.BattleProperties;
import robocode.dialog.WindowUtil;
import robocode.io.FileUtil;
import robocode.io.Logger;
import robocode.manager.RobocodeManager;
import robocode.render.ImageUtil;
import robocode.security.RobocodeSecurityManager;
import robocode.security.RobocodeSecurityPolicy;
import robocode.security.SecureInputStream;
import robocode.security.SecurePrintStream;
import robocode.util.LogUtil;


/**
 * Robocode - A programming game involving battling AI tanks.<br>
 * Copyright (c) 2001, 2007 Mathew A. Nelson and Robocode contributors
 *
 * @see <a target="_top" href="http://robocode.sourceforge.net">robocode.sourceforge.net</a>
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class Robocode extends JApplet{

	private RobocodeManager manager;

	/**
	 * Use the command-line to start Robocode.
	 * The command is:
	 * <pre>
	 *    java -Xmx512M -Dsun.io.useCanonCaches=false -jar libs/robocode.jar
	 * </pre>
	 *
	 * @param args an array of command-line arguments
	 */
	public static void main(String[] args) {

		Robocode robocode = new Robocode();
		robocode.initialize(new String[0]);
	}
	@Override
	public void init() {
		 setSize( 800, 600);
	     //Execute a job on the event-dispatching thread; creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {

            		
               /*	 DynamicTreePanel newContentPane = new DynamicTreePanel();
                     newContentPane.setOpaque(true); 
                     setContentPane(newContentPane);*/
         	       
                }
            });
        } catch (Exception e) { 
            System.err.println("createGUI didn't complete successfully");
        }
	}
	public Robocode() {}
	@Override
	public void start() {
		Robocode robocode = new Robocode();
    	
		URL url = getDocumentBase();
		FileUtil.setUrl(getCodeBase());
		
		JSObject window = JSObject.getWindow(this);
        String summary = "hello world";
        LogUtil.setWindow(window);
        LogUtil.log(summary);
        LogUtil.log("codebase url:: " + url.getFile());
		System.out.println(url.getFile());
		JPanel newContentPane = robocode.initialize(new String[0]); 
		setContentPane(newContentPane); 
	}
	public JPanel initialize(String args[]) {
		try {
			manager = new RobocodeManager(false, null);

			Thread.currentThread().setName("Application Thread");

			


			
	
			BattleProperties battleProperties = manager.getBattleManager().getBattleProperties();
			battleProperties.setSelectedRobots("sample.Corners,sample.Fire");
			manager.getBattleManager().startNewBattle(battleProperties, true, false);
			//manager.getBattleManager().getBattle().setDesiredTPS(tps);
			
			
			JPanel panel = manager.getWindowManager().getRobocodeFrame().getRobocodeContentPane();
			//panel.setBounds(x, y, width, height)
			panel.setBounds(100, 100, 500, 600);
			/*//setContentPane(panel);
			frame.setContentPane(panel);
			frame.setVisible(true);
			frame.setTitle("Raghav");*/
	        panel.setOpaque(true); 
			return panel;
		} catch (Throwable e) {
			Logger.log(e);
			return null;
		}
	}

	
}
