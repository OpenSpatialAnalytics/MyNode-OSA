package org.knime.mynode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.activity.InvalidActivityException;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.io.FileExistsException;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.container.ColumnRearranger;
import org.knime.core.data.container.ContainerTable;
import org.knime.core.data.container.DataContainer;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettings;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelDouble;
import org.knime.core.node.defaultnodesettings.SettingsModelFilterString;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.node.v210.OutPortDocument.OutPort;


/**
 * This is the model implementation of MyFirst.
 * This node is made by TG
 *
 * @author KNIME
 */
public class MyFirstNodeModel extends NodeModel {
    
    // the logger instance
    private static final NodeLogger logger = NodeLogger
            .getLogger(MyFirstNodeModel.class);
        
    /** the settings key which is used to retrieve and 
        store the settings (from the dialog or from a settings file)    
       (package visibility to be usable from the dialog). */
    /** TG 0624, the setting key is very important to the relations between SettingModel variables to configure object*/
	static final String CFGKEY_COUNT = "Count";

    /** initial default count value. */
    static final int DEFAULT_COUNT = 100;
    
    //TG 0624,add the configure key, the following strings are used by the dialog and 
    //model, as key to the store settings in the setting object.
    static final String CFGKEY_STR = "STR";
    static final String CFGKEY_INT = "INT";
    static final String CFGKEY_DBL = "DBL";
    static final String CFGKEY_BOOL = "BOOL";
    static final String CFGKEY_STRSEL = "STRSEL";
    static final String CFGKEY_COLSEL = "COLSEL";
    //TG 0630 S
    static final String CFGKEY_FILEC = "FILEC";
    //TG 0630 E
    //TG 0701 S
    static final String CFGKEY_COLFILTER = "COLFILTER";
    //TG 0701 E
    //TG 0705 S
    static final String CFG_TG_SETTINGSNAME = "TG_SETTINGS";
    static final String CFG_TG_FILENAME = "tg";
    static final String CFG_TG_INT = "TG_INT";
    private int tgint = 10;
    private BufferedDataTable m_out;
    //TG 0705 E
    // example value: the models count variable filled from the dialog 
    // and used in the models execution method. The default components of the
    // dialog work with "SettingsModels".
     private final SettingsModelIntegerBounded m_count =
        new SettingsModelIntegerBounded(MyFirstNodeModel.CFGKEY_COUNT,
                    MyFirstNodeModel.DEFAULT_COUNT,
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
     
     /*TG 0624, these are the members storing user setting.
      * use the same settings model (but a new instance) as in
      * the node dialog for that value
      */
     private final SettingsModelString m_str=
    		 new SettingsModelString(CFGKEY_STR,null);
   //  private final SettingsModelIntegerBounded m_intBounded =
   // 		 new SettingsModelIntegerBounded(CFGKEY_INT, 4, 1, 17);
    //TG 0624 another method using static function
     private final SettingsModelIntegerBounded m_intBounded = 
    		 createRangeSettingsModel(); 
     //TG 0624 another method using static function
     private final SettingsModelDouble m_dbl =
    		 new SettingsModelDouble(CFGKEY_DBL, .4);
     private final SettingsModelBoolean m_bool =
    		 new SettingsModelBoolean(CFGKEY_BOOL, true);
     private final SettingsModelString m_selStr =
    		 new SettingsModelString(CFGKEY_STRSEL, "");
     private final SettingsModelString m_colSel =
    		 new SettingsModelString(CFGKEY_COLSEL, "none");
     //TG 0624 use static create-methods to instantiate SettingsModels
     static SettingsModelIntegerBounded createRangeSettingsModel(){
    	 return  new SettingsModelIntegerBounded(CFGKEY_INT, 4, 1, 17);
     }
     
     //TG 0630 S
     private final SettingsModelString m_fileC =
    		 new SettingsModelString(CFGKEY_FILEC, null);
     //TG 0630 E
     //TG 0701 S
     private final SettingsModelFilterString m_colFilter =
    		 new SettingsModelFilterString(CFGKEY_COLFILTER);
     //TG 0701 E
     /////////////////////////////////////////////////////////
     //TG 0704 S
     private final SettingsModelString m_selection = 
    		 createSettingsModelSelection();
     private final SettingsModelInteger m_value =
    		 createSettingsModelValue();
     private final SettingsModelBoolean m_enable =
    		 createSettingsModelEnabled();	 
     ////use static create-methods to instantiate SettingsModels
     static SettingsModelString createSettingsModelSelection(){
    	 return new SettingsModelString("SELECTION", "");   //can be defined as a  static final String
     }
     static SettingsModelInteger createSettingsModelValue(){
    	 return new SettingsModelInteger("VALUE", 0);
     }
     static SettingsModelBoolean createSettingsModelEnabled(){
    	 return new SettingsModelBoolean("ENABLE", false);
     }
     //TG 0704 E
     /* TG 0624 add this to make sure the usage of MyFirstNodeModel.CFGKEY_COUNT and CFGKEY_COUNT
     private final SettingsModelIntegerBounded m_count =
    	        new SettingsModelIntegerBounded(MyFirstNodeModel.CFGKEY_COUNT,
    	                    DEFAULT_COUNT,
    	                    Integer.MIN_VALUE, Integer.MAX_VALUE);*/
    

    /**
     * Constructor for the node model.
     */
    protected MyFirstNodeModel() {
    
        // TODO one incoming port and one outgoing port is assumed
       super(1, 1);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec) throws Exception {

        // TODO do something here
        logger.info("Node Model Stub... this is not yet implemented !");

        
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
        for (int i = 0; i < m_count.getIntValue(); i++) {
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
            exec.setProgress(i / (double)m_count.getIntValue(), 
                "Adding row " + i);
        }
        // once we are done, we close the container and return its table
        container.close();
        BufferedDataTable out = container.getTable();
        m_out = out;//TG 0705 T
       
        return new BufferedDataTable[]{out};
		// TG 0705 S : notifyViews
		//This method can be called from the derived model in order to inform the views
        //about changes of the settings or during execution, if you want the views to 
        //show the progress, and if they can display models half way through the execution
        //In the view NodeView#updateModel(Object) is called and needs to be overridden.
        //Usually, the NodeView displays the data when the NodeModel 
        //has finished execution. There is also a possibility to watch how 
        //the model is generated to update the view during execution of the NodeModel.
        //1. NodeModel#notifyViews(Object)
        //2. NodeView#setShowNODATALabel(false)
        //Per default the NODATA label is set to true, which avoids that any methods
        //are forwarded to the inner component until the NodeModel is executed. 
        //It also comes along with a "no data available" label which is displayed 
        //when the node is reset while the view is opened. However, if the process 
        //of the model building should be visualized, the NODATA label has to 
        //be set to false
        //3. NodeView#updateModel(Object)
        //This method gets the intermediate model passed, which the NodeModel
        //passes to the notifyViews method, i.e. these methods have to correspond. 
        //The updateModel method is assumed to know how to 
        //visualize the intermediate view model
        //This method can be overridden by views that want to receive events from 
        //their assigned models via the NodeModel.notifyViews(Object) method.
        //Can be used to iteratively update the view during execute.
        //setWarningMessage
        //The user can be informed about anything strange happened during the execution
        //of the node with this method. This will not cancel execution 
        //but set a warning icon with the provided message as the tooltip. 
		// TG -705 E
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
        // TODO Code executed on reset.
        // Models build during execute are cleared here.
        // Also data handled in load/saveInternals will be erased here.
    	/* TG 0705 
    	 * Note: there is a strong connection between the load-/saveInternals 
    	 * and the reset method. Everything which is necessary to restore the view and 
    	 * therefore has to be saved and loaded, has to be deleted in the reset method,
    	 *  since the view displays the result of the execute method 
    	 *  and the reset method should set the node to its initial state, 
    	 *  i.e. as it was before execution
    	 *  All internally stored data structures should be released.
    	 *  User settings should not be deleted/reset though.
    	 *  All temporarily created data structures necessary for the execution 
    	 *  should be blown away at the end of the execution method
    	 */
    	 //m_internalDataStructure = null;
    	//JOptionPane.showMessageDialog(null, "reset");
    	
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
       //JOptionPane.showMessageDialog(null, "TG:configure");//TG 0704 T
       // System.out.println("configure runned!");    //TG 0704 T
		/* TG 0705 T
		 * if (m_includedColumns.getIncludeList() == null ||
		 * m_includedColumns.getIncludeList().size() <= 0) { setWarningMessage(
		 * "No columns to transfrom selected. Will have no effect!"); }
		 *///setWarningMessage("No columns to transfrom selected. Will have no effect!");
    	
        return new DataTableSpec[]{null};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {

        // TODO save user settings to the config object.
        // tg 0624 this means set workflow model data to the config object
    	//m_count.setIntValue(258);//tg 0624
        m_count.saveSettingsTo(settings);
        m_bool.saveSettingsTo(settings);
        m_dbl.saveSettingsTo(settings);
        m_intBounded.saveSettingsTo(settings);
        m_selStr.saveSettingsTo(settings);
        m_str.saveSettingsTo(settings);
      //  m_colSel.saveSettingsTo(settings);
       //TG 0701 S
        m_fileC.saveSettingsTo(settings);
        m_colFilter.saveSettingsTo(settings);
       //TG 0701 E
       //TG 0704 S
        m_selection.saveSettingsTo(settings);
     
        m_value.saveSettingsTo(settings);
        //TG 0704 E
        //TG 0704 S
       //  test the ChangeListener, to maintain the consistently status of the enable property
        /*
        m_enable.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				 m_value.setEnabled(m_enable.getBooleanValue());
			}
		});
        m_enable.setBooleanValue(true);  //this will call the stateChanged function
        */
        m_enable.saveSettingsTo(settings);                                 // and the setEnabled function will be called
        
        //TG 0704 E
        //TG 0705 S
        //If DefaultComponents with their SettingsModels are used, 
        //the method would look as above, otherwise is as following
        //settings.addInt(CFG_MEAN_THRESHOLD, m_percentage);
        //settings.addBoolean(CFG_REPLACE, m_replace);
        //TG 0705 E
    }
    //TG 0624,get the value with key and default value and directly set the 
    //value in the GUI component 
    //m_percentage.setValue(settings.getInt(ExampleNodeModel.CFG_PERCENTAGE,100));
    //default component uses a swing component (like a JTextField or JSpinner) as input field,
    //and allows for adding a label to that field, to add an explanatory text for the user.
    //Besides that each dialog component needs a SettingsModel. 
    //This settings model holds the actual value of the component and does the storing, loading and validation. 
    //When you instantiate the component you must provide a settings model ,just like,SettingsModelIntegerBounded
    //- when you want to load the entered value in your NodeModel, 
    //you use an instance of that same SettingsModel. To identify the value handled by the settings model, 
    //you must provide a unique configName (i.e. a string ID).This is mechanism,and it is very important.
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
            throws InvalidSettingsException {
            
        // TODO load (valid) settings from the config object.
        // It can be safely assumed that the settings are valided by the 
        // method below.
    	//tg 0624,this means save the workflow model data from the config object 
        m_count.loadSettingsFrom(settings);
        m_bool.loadSettingsFrom(settings);
        m_dbl.loadSettingsFrom(settings);
        m_intBounded.loadSettingsFrom(settings);
        m_selStr.loadSettingsFrom(settings);
        m_str.loadSettingsFrom(settings);
        
       // m_colSel.loadSettingsFrom(settings);
        //TG 0701 S
        m_fileC.loadSettingsFrom(settings);
        m_colFilter.loadSettingsFrom(settings);
        //TG 0701 E
        //TG 0704 S
        m_selection.loadSettingsFrom(settings);
        m_value.loadSettingsFrom(settings);
        m_enable.loadSettingsFrom(settings);
        //TG 0704 E
        //TG 0705 S
        //If DefaultComponents with their SettingsModels are used, 
        //the method would look as above. otherwise, like following
          //m_percentage = settings.getInt(CFG_PERCENTAGE);
          //m_replace = settings.getBoolean(CFG_REPLACE);
        //TG 0705 E
        

    }
    //TG 0624 
    //values for user settings from the dialog's components are being copied into the NodeSetting Object
    //setting.addInt(ExampleNodeModel.CFG_PERCENTAGE,(Integer)m_percentage.getValue());
    //seeting.AddBoolean(ExampleNodeModel.CFG_REPLACE,m_replaceBox.isSelected());

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

        m_count.validateSettings(settings);
        m_bool.validateSettings(settings);
        m_dbl.validateSettings(settings);
        m_intBounded.validateSettings(settings);
        m_selStr.validateSettings(settings);
        m_str.validateSettings(settings);
     //   m_colSel.validateSettings(settings);
        //TG 0701
        m_fileC.validateSettings(settings);
        m_colFilter.validateSettings(settings);
        //TG 0701
        //TG 0704
        m_selection.validateSettings(settings);
        m_value.validateSettings(settings);
        m_enable.validateSettings(settings);
        //TG 0704
        /* TG 0624
         * If there are dependencies to check between two settings values, 
         * the validateSettings method becomes a bit more complicated because 
         * if a settings model validates one value but it does not store that value, 
         * thus you have no access to that first value you are supposed to 
         * validate against a second.
         *  If you need to check some values against each other (like, to ensure 
         *  that the new minimum is smaller than the specified maximum, for example),
         *   you need to create new temporary settings models, load the new values in,
         *    read and verify them, and release the settings models at the end.
         *     Here is a code example
         * 
         */
        /*
         // create new (temp) settings models
         // (if m_min and m_max are fields of the NodeModel)
          SettingsModelInteger min =
               m_min.createCloneWithValidatedValue(MIN, settings);
           SettingsModelInteger max =
               m_max.createCloneWithValidatedValue(MAX, settings);
 
          if (min.getIntValue() >= max.getIntValue()) {
                 throw new InvalidSettingsException("The specified minimum "
               + "must be smaller than the maximum value.");
           }
         */
        //TG 0705 S
        //// simply checks if the keys are available
        //settings.getInt(CFG_PERCENTAGE);
        //settings.getBoolean(CFG_REPLACE);
        //Consider that a node was already released and 
        //in the next release a new field is added to the dialog and
        //therefore also to the settings. Then every existing workflow will fail 
        //while loading, since the new field cannot be found in the settings. 
        //To avoid this you could also in the model provide some default values
        //if the key cannot be found. See below:
        //settings.getBoolean(CFG_NEW_FIELD, true);
        //Of course, in the loadValidatedSettings method the same default value has to be used.
        //If DefaultComponents with their SettingsModels are used, the method would look as above.
       
        //TG 0705 E

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
        //TG 0704 T
    	//1.Node Settings, in case the internal view model is not very large
    	/*
    	 File f = new File(internDir,CFG_FILENAME)
    	 //load the settings from the file
    	 NodeSettingsRO settings = NodeSettings.loadFromXML(
    	            new FileInputStream(f));
    	 //retrieve the stored values
    	 int yourint= settings.getInt(CFG_INT);
    	 */
    	//2.DataContainer: in case the internal model is stored in a DataTable
    	/*
    	  File f = new File(internDir,CFG_FILENAME)
    	  // load the table 
          yourTable = DataContainer.readFromZip(f);
    	 */
    	//3.Own Format:
    	/*Since only a directory is passed to the methods, theoretically any format 
    	 * is possible to store the internal model. However, the two above mentioned 
    	 * approaches are recommended. Defining you own format for the persistence of
    	 *  the internal model should only be done, if the internal model doesn't fit 
    	 *  neither into the NodeSettings nor into the DataTable or if an own format 
    	 *  increases the performance dramatically.
    	 */
    	//TG 0704 E
    	//TG 0705 S
		System.out.println("TG Test: loadInternals:internaDir is:\n" + internDir);
		/*
		//First method: need to judge the CFG_TG_FILENAME is exist or not
		File f = new File(internDir,CFG_TG_FILENAME + ".xml");
		// load the settings from the file
  		NodeSettingsRO settings = NodeSettings.loadFromXML(new FileInputStream(f));
		// retrieve the stored values
		try {
			f = new File(internDir, CFG_TG_FILENAME);
			tgint = settings.getInt(CFG_TG_INT);
			System.out.println("The loaded TG data:tgint is :" + tgint);
		} catch (InvalidSettingsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//    int yourint= settings.getInt(CFG_TG_INT);
	   */
		//Second method: DataContainer: in case the internal model is stored in a DataTable
      	  File f = new File(internDir,CFG_TG_FILENAME + ".zip");
    	  // load the table 
      	  ContainerTable m_outable = DataContainer.readFromZip(f);
         //TG ???
      	 //the following code cause the type mismatch, how to convert??
         // m_out = DataContainer.readFromZip(f);
      	//The Third Method: Own Format, Defining you own format 
      	
    	//TG 0705 E
    	
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
    	
    	//TG 0705 S
    	
    	System.out.println("TG Test: saveInternals:internaDir is:\n" + internDir);
    	/*
    	//First method: create a settings object with a config name 
    	NodeSettings settings = new NodeSettings(CFG_TG_SETTINGSNAME);
    	//store your values under a certain key
    	tgint = 20; //here is a test, should do the model logic
    	settings.addInt(CFG_TG_INT, tgint);
    	//create a file in the given directory
    	File f = new File(internDir,CFG_TG_FILENAME + ".xml");
    	//and save it
    	settings.saveToXML(new FileOutputStream(f));
    	*/
    	///Second method:DataContainer for DataTable
    	File f = new File(internDir,CFG_TG_FILENAME + ".zip");//zip file
    	DataContainer.writeToZip(m_out, f, exec); 	
    	//The Third Method: Own Format, Defining you own format 
        
    	//TG 0705 E
      
    }

}


