package org.knime.mynode;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.knime.core.data.DoubleValue;
import org.knime.core.data.IntValue;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentButton;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnFilter;
import org.knime.core.node.defaultnodesettings.DialogComponentColumnNameSelection;
import org.knime.core.node.defaultnodesettings.DialogComponentFileChooser;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentNumberEdit;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

/**
 * <code>NodeDialog</code> for the "MyFirst" Node.
 * This node is made by TG
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author KNIME
 */
public class MyFirstNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring MyFirst node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    @SuppressWarnings("unchecked")   //add GT 0624
	protected MyFirstNodeDialog() {
        super();
        addDialogComponent(new DialogComponentNumber(
                new SettingsModelIntegerBounded(
                    MyFirstNodeModel.CFGKEY_COUNT,
                    MyFirstNodeModel.DEFAULT_COUNT,
                    Integer.MIN_VALUE, Integer.MAX_VALUE),
                    "Counter:", /*step*/ 1, /*componentwidth*/ 5));
        
        // JOptionPane.showMessageDialog(null, "tgceshi");
        createNewTab("TG New Tab");
        createNewGroup("Group 1:");
        addDialogComponent(new DialogComponentString(
        		new SettingsModelString(
        			MyFirstNodeModel.CFGKEY_STR,
        			null),
        		    "Enter a string value:"));
        /*
        addDialogComponent(new DialogComponentNumber(
        		new SettingsModelInteger(
        		     MyFirstNodeModel.CFGKEY_INT,
        		     3),
        		     "Enter an integer (1-17):",1));
        */
        /*
          addDialogComponent(new DialogComponentNumber(
        		new SettingsModelIntegerBounded(
        		CFGKEY_INT, 4, 1, 17),
        		     "Enter an integer (1-17):",1));
         */
        //TG 0624 S another method using static function
        addDialogComponent(new DialogComponentNumber(
        		MyFirstNodeModel.createRangeSettingsModel(),
        		    "Enter an integer (1-17):",1));
        //TG0624 E
        addDialogComponent(new DialogComponentNumber(
        		new SettingsModelDouble(
        			MyFirstNodeModel.CFGKEY_DBL,
        			3.0),
        		    "Enter a floating point number:",0.1));
        //closes the new prev group, opens a new one>
        createNewGroup("Group 2:");
        addDialogComponent(new DialogComponentBoolean(
        		new SettingsModelBoolean(
        			MyFirstNodeModel.CFGKEY_BOOL,
        			false),
        		    "Checkit or leave it:"));
        addDialogComponent(new DialogComponentStringSelection(
        		new SettingsModelString(
        				MyFirstNodeModel.CFGKEY_STRSEL,
        				//null),   //no selection
        		        ""),       //select the first one
        		        "Your choice:","First","Second","Third"));
        closeCurrentGroup();
        //TG 0630 S
        createNewGroup("Group 3:");
        addDialogComponent(new DialogComponentFileChooser(
        		new SettingsModelString(
        				MyFirstNodeModel.CFGKEY_FILEC,
        				null),"test",".txt|"));
        //TG 0630 E
       /* 
        addDialogComponent(new DialogComponentColumnNameSelection(
        		new SettingsModelString(
        				MyFirstNodeModel.CFGKEY_COLSEL,
        				""),
        		        "Select a colum:",0,true,IntValue.class,DoubleValue.class));
        */
       /*  //this made setting model load procedure error,maybe the function parameter is wrong
        addDialogComponent(new DialogComponentColumnNameSelection(
        		new SettingsModelString(
        				MyFirstNodeModel.CFGKEY_COLSEL,
        				"none"), "select a column:", -1, IntValue.class));
        */
        /**
         * added by GT 0623
         */
          // 1.Renaming the default tab
        //  setDefaultTabTitle("GTest option");
          //2.Creating new tabs
        //  createNewTab("GT new ");
          // 3.if the createNewTab() was called, the components will be placed into the new tab
        
          
         // addDialogComponent(new DialogComponentButton("TGTest"));
          //4.Placement of dialog components
         //If set to true the next components will be added next to each other. 
         //If set to false the next component is place below the previous one.
         /*
          setHorizontalPlacement(true);
          createNewTab("GT new2 ");
          addDialogComponent(new DialogComponentButton("TG2Test"));
          //5.panel created manually
          JPanel panel = new JPanel();
          JButton button = new JButton("tgpanelbutton");
          panel.add(button);
          addTab("tgPaneltest", panel);
         */
          /** 6.
           * In a NodeDialogPane, you can create JPanels, add Swing components to them, layout those components, 
           * and add these composite panels as tabs to the dialog pane. Each dialog pane must implement load 
           * and save methods to transfer settings values from and into the components.
           * In the constructor you can create all the Swing components that you need for your user settings. 
           * Place them in a JPanel, use a LayoutManager to arrange them nicely, and add any ChangeListeners, etc.
           *  After one panel is complete, add it to the dialog pane by calling addTab(String title, Component panel).
           *   Note that the title must be unique and it can be used as ID to retrieve this panel with getTab().
           */
         /**
          * added by GT 0623
          */
          //TG 0701
          
          createNewTab("TG New 2");
          createNewGroup("TG:PDialogComponentColumnFilter:");
          addDialogComponent(new DialogComponentColumnFilter(
        		  new SettingsModelFilterString(MyFirstNodeModel.CFGKEY_COLFILTER)
        		  , 0));
          /////////////////////////////////////////
          //TG 0701
          //TG 0704
          SettingsModelString selection = MyFirstNodeModel.createSettingsModelSelection();
          SettingsModelBoolean enabled = MyFirstNodeModel.createSettingsModelEnabled();
          SettingsModelInteger parameter =MyFirstNodeModel.createSettingsModelValue();
          
          createNewTab("TG ModelListener");
          createNewGroup("Your choice");
          addDialogComponent(new DialogComponentStringSelection(
        		  selection, "Select one:","First","Second","Third"));
          createNewGroup("Parameter");
          setHorizontalPlacement(true); //next to 
        //  setHorizontalPlacement(false); //below the previous one
          addDialogComponent(new DialogComponentBoolean(enabled, "edit manually"));
          addDialogComponent(new DialogComponentNumberEdit(parameter,"",15));
          
       //   enabled.setBooleanValue(true);
          parameter.setEnabled(enabled.getBooleanValue());
          //Adding listeners to the SettingsModel
          enabled.addChangeListener(new ChangeListener() {
          	  public void stateChanged(final ChangeEvent e){
          		  //if enabled is true, the parameter field should be enabled
          		  parameter.setEnabled(enabled.getBooleanValue());
          	  }
            });
          selection.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int param = selection.getStringValue().hashCode();
				parameter.setIntValue(param);
				
				// TODO Auto-generated method stub
				
			}
		});
        
          //TG 0704        
    }
}

