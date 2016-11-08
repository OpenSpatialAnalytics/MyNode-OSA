package org.knime.MyDocker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;


/**
 * This is the model implementation of MyDocker.
 * This is a test for Docker integration
 *
 * @author TG
 */
public class MyDockerNodeModel extends NodeModel {
    
    // the logger instance
    private static final NodeLogger logger = NodeLogger
            .getLogger(MyDockerNodeModel.class);
        
    /** the settings key which is used to retrieve and 
        store the settings (from the dialog or from a settings file)    
       (package visibility to be usable from the dialog). */
    static final String CFGKEY_DOCKER_CMD = "DockerCMD";
    /** initial default count value. */
    static final int DEFAULT_COUNT = 100;
    
    // example value: the models count variable filled from the dialog 
    // and used in the models execution method. The default components of the
    // dialog work with "SettingsModels".
    
    private final SettingsModelString m_strDockerCmd=
   		 new SettingsModelString(CFGKEY_DOCKER_CMD,"docker run docker/whalesay cowsay KnimeDockerTGtest");
    /**
     * Constructor for the node model.
     */
    protected MyDockerNodeModel() {
    
        // TODO one incoming port and one outgoing port is assumed
       // super(1, 1);
        super(0, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec) throws Exception {

        // TODO do something here
        logger.info("Node Model Stub... this is not yet implemented !");
        //TG S
        //Process process = Runtime.getRuntime().exec("C:/apps/PostgreSQL/9.5/bin/pgAdmin3.exe");
        //  Process process = Runtime.getRuntime().exec("C:/apps/QGIS Essen/bin/nircmd.exe" + " exec hide C:/apps/QGISES~1/bin/qgis.bat");
       //	Process process = Runtime.getRuntime().exec("C:/apps/QGIS Essen/bin/nircmd.exe" + " exec hide C:/apps/QGISES~1/bin/qgis.bat",
    //			new String[] {"D:/GIS DataBase/kangarooIsland"});
    //	Process process = Runtime.getRuntime().exec("D:/GIS DataBase/kangarooIsland.qgs");
    //	process.waitFor();
    	//WFSDataStore db;
    	
    	Process p = null;
    	try {
    		//Test for QGIS
    		//p = Runtime.getRuntime().exec("C:/apps/QGIS Essen/bin/nircmd.exe" + " exec hide C:/apps/QGISES~1/bin/qgis.bat");
    		//
    		//p = Runtime.getRuntime().exec("ping 192.168.99.100");		
    	//	p = Runtime.getRuntime().exec("docker-machine env --shell cmd default");
    	//	p = Runtime.getRuntime().exec("docker version"); //someting seem not correct TG till now in command line
    		//org.knime.r.RSnippetNodeFactory
    	//	org.knime.ext.r.RCorePlugin.getRExecutable()
    		
    		p = Runtime.getRuntime().exec(m_strDockerCmd.getStringValue()); 
    		
    	//	p = Runtime.getRuntime().exec("docker run docker/whalesay cowsay KnimeDockerTGtest"); //something seem not correct TG till now in command line
    		
    		
    		
    		//p = Runtime.getRuntime().exec("docker run gentian/docker-whale"); //someting seem not correct TG till now in command line
    		
    		//String[] commonds = {"cmd","/c","start"};  //ok
    		
//    		String[] commonds = {"cmd","/c","start "
//    		        + "set DOCKER_TLS_VERIFY=1 &&"
//      				+ "set DOCKER_HOST=tcp://192.168.99.100:2376 && "
//    				+ "set DOCKER_CERT_PATH=C:/users/thinkpad/.docker/machine/machines/default && "
//    				+ "set DOCKER_MACHINE_NAME=default && "
//    				+ "docker version"};  
    		
    	    
    	//	p = Runtime.getRuntime().exec(commonds);
    		
//    		String commonds ="cmd exe /c"+" set DOCKER_TLS_VERIFY=1 && "
//    		        + "set DOCKER_HOST=tcp://192.168.99.100:2376 && "
//    		        + "set DOCKER_CERT_PATH=C:/users/thinkpad/.docker/machine/machines/default && "
//    		        + "set DOCKER_MACHINE_NAME=default && "
//    		        + "docker version";
    				
   	//	p = Runtime.getRuntime().exec(commonds);
    //		p = Runtime.getRuntime().exec("sh -c"+" set DOCKER_TLS_VERIFY=1");
    		
    		
    		
    		/*
    		p = Runtime.getRuntime().exec("set DOCKER_TLS_VERIFY=1 &&"
    				+ "set DOCKER_HOST=tcp://192.168.99.100:2376 &&"
    				+ "set DOCKER_CERT_PATH=C:/users/thinkpad/.docker/machine/machines/default &&"
    				+ "set DOCKER_MACHINE_NAME=default &&"
    				+ "docker version");
    	    */
    				
    				
    		InputStream is = p.getInputStream();	
    		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    		String line;
    		String strMsg="";
    		while((line = reader.readLine())!= null){
    		   System.out.println(line);
    		   strMsg = strMsg + line +"\n" ;
    		 }
    		JOptionPane.showMessageDialog(null, strMsg);
    		//p = Runtime.getRuntime().exec("notepad.exe");
    		p.waitFor();
    		is.close();
    		reader.close();
    		p.destroy();
    		System.out.println("Waiting over");
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	
        //TG E
        
        // the data table spec of the single output table, 
        // the table will have three columns:
        DataColumnSpec[] allColSpecs = new DataColumnSpec[3];
        allColSpecs[0] = 
            new DataColumnSpecCreator("Column 0", StringCell.TYPE).createSpec();
        allColSpecs[1] = 
            new DataColumnSpecCreator("Column 1", DoubleCell.TYPE).createSpec();
        allColSpecs[2] = 
            new DataColumnSpecCreator("Column 2", IntCell.TYPE).createSpec();
        DataTableSpec outputSpec = new DataTableSpec(allColSpecs);
        // the execution context will provide us with storage capacity, in this
        // case a data container to which we will add rows sequentially
        // Note, this container can also handle arbitrary big data tables, it
        // will buffer to disc if necessary.
        BufferedDataContainer container = exec.createDataContainer(outputSpec);
        // let's add m_count rows to it
        for (int i = 0; i < 20; i++) {
            RowKey key = new RowKey("Row " + i);
            // the cells of the current row, the types of the cells must match
            // the column spec (see above)
            DataCell[] cells = new DataCell[3];
            cells[0] = new StringCell("String_" + i); 
            cells[1] = new DoubleCell(0.5 * i); 
            cells[2] = new IntCell(i);
            DataRow row = new DefaultRow(key, cells);
            container.addRowToTable(row);
            
            // check if the execution monitor was canceled
            exec.checkCanceled();
            exec.setProgress(i / (double)20, 
                "Adding row " + i);
        }
        // once we are done, we close the container and return its table
        container.close();
        BufferedDataTable out = container.getTable();
        return new BufferedDataTable[]{out};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
        // TODO Code executed on reset.
        // Models build during execute are cleared here.
        // Also data handled in load/saveInternals will be erased here.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DataTableSpec[] configure(final DataTableSpec[] inSpecs)
            throws InvalidSettingsException {
        
        // TODO: check if user settings are available, fit to the incoming
        // table structure, and the incoming types are feasible for the node
        // to execute. If the node can execute in its current state return
        // the spec of its output data table(s) (if you can, otherwise an array
        // with null elements), or throw an exception with a useful user message

        return new DataTableSpec[]{null};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {

        // TODO save user settings to the config object.
        
        m_strDockerCmd.saveSettingsTo(settings);
        

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
            
        // TODO load (valid) settings from the config object.
        // It can be safely assumed that the settings are valided by the 
        // method below.
        
      
        m_strDockerCmd.loadSettingsFrom(settings);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
            throws InvalidSettingsException {
            
        // TODO check if the settings could be applied to our model
        // e.g. if the count is in a certain range (which is ensured by the
        // SettingsModel).
        // Do not actually set any values of any member variables.

      
        m_strDockerCmd.validateSettings(settings);
        

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
        
        // TODO load internal data. 
        // Everything handed to output ports is loaded automatically (data
        // returned by the execute method, models loaded in loadModelContent,
        // and user settings set through loadSettingsFrom - is all taken care 
        // of). Load here only the other internals that need to be restored
        // (e.g. data used by the views).

    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
            final ExecutionMonitor exec) throws IOException,
            CanceledExecutionException {
       
        // TODO save internal models. 
        // Everything written to output ports is saved automatically (data
        // returned by the execute method, models saved in the saveModelContent,
        // and user settings saved through saveSettingsTo - is all taken care 
        // of). Save here only the other internals that need to be preserved
        // (e.g. data used by the views).

    }

}

