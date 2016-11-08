package org.knime.MyWFS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.geotools.data.DataStoreFinder;
import org.geotools.data.wfs.WFSDataStore;
import org.geotools.data.wfs.WFSDataStoreFactory;
import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentButton;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.DialogComponentStringSelection;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.mynode.MyFirstNodeModel;

/**
 * <code>NodeDialog</code> for the "MyWFS" Node.
 * This is a test node for Web Feature Service.TG
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author Gen Tian
 */
public class MyWFSNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring MyWFS node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    protected MyWFSNodeDialog() {
        super();
        SettingsModelString wfs_url=
          		 new SettingsModelString(MyWFSNodeModel.CFGKEY_WFS_URL,
          				 "http://127.0.0.1:8080/geoserver/wfs");
        addDialogComponent(new DialogComponentString(
        		    wfs_url,
        		    "Enter WFS URL:"));
        DialogComponentButton mButton=new DialogComponentButton("Connect");
        addDialogComponent(mButton);
        SettingsModelString selStr = 
        		new SettingsModelString(MyWFSNodeModel.CFGKEY_STRSEL, "");
        DialogComponentStringSelection dlgcombox = new DialogComponentStringSelection(
		        selStr,       //select the first one
		        "Your choice:","");
        addDialogComponent(dlgcombox);	
        Collection<String> strc = new ArrayList<String>();
//        strc.add("tiangen");
//        strc.add("ddd");
//        dlgcombox.replaceListItems(strc, null);
        
//        addDialogComponent(new DialogComponentStringSelection(
//        		        selStr,       //select the first one
//        		        "Your choice:",""));
        
        mButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "TGtesettee");
				// TODO Auto-generated method stub
				 String getCapabilities = null;
			        if (wfs_url.getStringValue().equals("")) {
			             getCapabilities = "http://127.0.0.1:8080/geoserver/wfs?REQUEST=GetCapabilities&version=1.0.0";	
					}
			        else {
			        	 getCapabilities = wfs_url.getStringValue() + "?REQUEST=GetCapabilities&version=1.0.0";
					}
					// String getCapabilities = "http://127.0.0.1:8080/geoserver/wfs?REQUEST=GetCapabilities";
					  Map connectionParameters = new HashMap();
					  connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", getCapabilities);
					  WFSDataStoreFactory  dsf = new WFSDataStoreFactory();
					  String strtmp = "";
					  try {
						  MyWFSNodeModel.dataStore = dsf.createDataStore(connectionParameters);
						 // MyWFSNodeModel.dataStore = DataStoreFinder.getDataStore( connectionParameters );
						  MyWFSNodeModel.m_blconnect=true;
					      String typeNames[] = MyWFSNodeModel.dataStore.getTypeNames();
					      for (int i = 0; i < typeNames.length; i++) {
					    	  strc.add(typeNames[i]);
								 System.out.println(typeNames[i]);
					    	 //  strtmp = strtmp + "\n" + typeNames[i];
								}
					   //   dlgcombox.replaceListItems(strc, null);
					      System.out.println("heheheheeh:"+ selStr.getStringValue());
					      dlgcombox.replaceListItems(strc, selStr.getStringValue());
					      //JOptionPane.showMessageDialog(null, strtmp);
					      
					  }
					  catch (IOException ex) {
						  MyWFSNodeModel.m_blconnect = false;
					      ex.printStackTrace();
					  }
					  
			}
		} );
      
        
        
                    
    }
}

