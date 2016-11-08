package org.knime.mypgiscon;

import java.io.File;
import java.io.IOException;

import org.knime.core.data.BooleanValue;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.DoubleValue;
import org.knime.core.data.IntValue;
import org.knime.core.data.LongValue;
import org.knime.core.data.RowKey;
import org.knime.core.data.blob.BinaryObjectDataCell;
import org.knime.core.data.blob.BinaryObjectDataValue;
import org.knime.core.data.date.DateAndTimeCell;
import org.knime.core.data.date.DateAndTimeValue;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.LongCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.postgres.jdbc.ConnUtil;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import java.sql.* ;//TG 0712 S

/**
 * This is the model implementation of MyPgisCon.
 * This mypgiscon will connect postgres database. TG.
 *
 * @author TG
 */
public class MyPgisConNodeModel extends NodeModel {
	
    // the logger instance
    private static final NodeLogger logger = NodeLogger
            .getLogger(MyPgisConNodeModel.class);
        
    /** the settings key which is used to retrieve and 
        store the settings (from the dialog or from a settings file)    
       (package visibility to be usable from the dialog). */
	static final String CFGKEY_COUNT = "Count";

    /** initial default count value. */
    static final int DEFAULT_COUNT = 100;

    // example value: the models count variable filled from the dialog 
    // and used in the models execution method. The default components of the
    // dialog work with "SettingsModels".
    private final SettingsModelIntegerBounded m_count =
        new SettingsModelIntegerBounded(MyPgisConNodeModel.CFGKEY_COUNT,
                    MyPgisConNodeModel.DEFAULT_COUNT,
                    Integer.MIN_VALUE, Integer.MAX_VALUE);
    

    /**
     * Constructor for the node model.
     */
    protected MyPgisConNodeModel() {
    
        // TODO one incoming port and one outgoing port is assumed
       // super(1, 1);
        super(0, 1);  //TG 0712 T, no input, no output,test only.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final BufferedDataTable[] inData,
            final ExecutionContext exec) throws Exception {

        // TODO do something here
        logger.info("Node Model Stub... this is not yet implemented !");
        //TG 0713 S
        //Test for connect to postgres database 
        Connection conn=ConnUtil.getConn(); 
      // 
        
        String sql="select * from airports"; 
        Statement stmt=null; 
        ResultSet rs=null; 
        DataTableSpec outputSpec = null;
        try 
        { 
            stmt=conn.createStatement(); 
            rs=stmt.executeQuery(sql); 
            //TG 0713
            //DatabaseReaderConnection
            //read the postgres database meatadata
            ResultSetMetaData meta = rs.getMetaData();//
            ////
            int cols = meta.getColumnCount();
            if (cols == 0) {
            	outputSpec=new DataTableSpec("database");
            }
            for (int i = 0; i < cols; i++) {
            	int dbIdx = i + 1;
                String name = meta.getColumnLabel(dbIdx);
               // System.out.println(name); //
                int type = meta.getColumnType(dbIdx);
                DataType newType;
                switch (type) {
                // all types that can be interpreted as integer
                case Types.BIT:
                case Types.BOOLEAN:
                    newType = BooleanCell.TYPE;
                    break;
                // all types that can be interpreted as integer
                case Types.TINYINT:
                case Types.SMALLINT:
                case Types.INTEGER:
                    newType = IntCell.TYPE;
                    break;
                 // all types that can be interpreted as long
                case Types.BIGINT:
                    newType = LongCell.TYPE;
                    break;
                // all types that can be interpreted as double
                case Types.FLOAT:
                case Types.DOUBLE:
                case Types.NUMERIC:
                case Types.DECIMAL:
                case Types.REAL:
                    newType = DoubleCell.TYPE;
                    break;
                // all types that can be interpreted as data-and-time
                case Types.TIME:
                case Types.DATE:
                case Types.TIMESTAMP:
                    newType = DateAndTimeCell.TYPE;
                    break;
                // all types that can be interpreted as binary object
                case Types.BLOB:
                case Types.LONGVARBINARY:
                case Types.BINARY:
                    newType = BinaryObjectDataCell.TYPE;
                    break;
                // fallback string
                default:
                    newType = StringCell.TYPE;
            }
            //cast the database metadata to DatatableSpec
            if (outputSpec == null) {
            	outputSpec = new DataTableSpec("database",
                        new DataColumnSpecCreator(name, newType).createSpec());
            } else {
                name = DataTableSpec.getUniqueColumnName(outputSpec, name);
                outputSpec = new DataTableSpec("database", outputSpec,
                       new DataTableSpec(new DataColumnSpecCreator(
                               name, newType).createSpec()));
            }
        }
           
            ////
            //TG 0713
          
           //read data from database and cast to Knime DataTable
            while(rs.next()){ 
            	//TG 0713
                System.out.println(rs.getInt(1)); 
                
              //container.addRowToTable();
                
          
                 //TG 0713
            } 
        } 
        catch (SQLException e) 
        { 
            e.printStackTrace(); 
        } 
        BufferedDataContainer container = exec.createDataContainer(outputSpec);
        
  //      String url = "jdbc:postgresql://localhost/pgis_knime_tg";
        
      //  Properties props =new Properties
        //TG 0713 E
        
        
        // TG 0712 comment the original code
        // the data table spec of the single output table, 
        // the table will have three columns:
     /*   DataColumnSpec[] allColSpecs = new DataColumnSpec[3];
        allColSpecs[0] = 
            new DataColumnSpecCreator("Column 0", StringCell.TYPE).createSpec();
        allColSpecs[1] = 
            new DataColumnSpecCreator("Column 1", DoubleCell.TYPE).createSpec();
        allColSpecs[2] = 
            new DataColumnSpecCreator("Column 2", IntCell.TYPE).createSpec();
 //       DataTableSpec outputSpec = new DataTableSpec(allColSpecs);
        outputSpec = new DataTableSpec(allColSpecs);
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
        */
        // once we are done, we close the container and return its table
        container.close();
        BufferedDataTable out = container.getTable();
        return new BufferedDataTable[]{out};
        
        //TG 0712 comment the original code 
        
        
   //     return new BufferedDataTable[0];  //TG 0712 T no output
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
        
        m_count.saveSettingsTo(settings);

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
        
        m_count.loadSettingsFrom(settings);

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

        m_count.validateSettings(settings);

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
//TG TEST DBReaderNodeFactory

