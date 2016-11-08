package org.knime.MyWFS;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.lang.ObjectUtils.Null;
import org.geotools.data.DataStore;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.wfs.WFSDataStore;
import org.geotools.data.wfs.WFSDataStoreFactory;
import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;
import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.DataType;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.BooleanCell;
import org.knime.core.data.def.BooleanCell.BooleanCellFactory;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeType;

import com.vividsolutions.jts.geom.Geometry;

import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;


/**
 * This is the model implementation of MyWFS.
 * This is a test node for Web Feature Service.TG
 *
 * @author Gen Tian
 */
public class MyWFSNodeModel extends NodeModel {
    
    // the logger instance
    private static final NodeLogger logger = NodeLogger
            .getLogger(MyWFSNodeModel.class);
        
    /** the settings key which is used to retrieve and 
        store the settings (from the dialog or from a settings file)    
       (package visibility to be usable from the dialog). */
	//static final String CFGKEY_COUNT = "Count";
    static final String CFGKEY_WFS_URL = "WFSURL";
    static final String CFGKEY_STRSEL = "STRSEL";
    /** initial default count value. */
  //  static final int DEFAULT_COUNT = 100;

    // example value: the models count variable filled from the dialog 
    // and used in the models execution method. The default components of the
    // dialog work with "SettingsModels".
//    private final SettingsModelIntegerBounded m_count =
//        new SettingsModelIntegerBounded(MyWFSNodeModel.CFGKEY_COUNT,
//                    MyWFSNodeModel.DEFAULT_COUNT,
//                    Integer.MIN_VALUE, Integer.MAX_VALUE);
    private final SettingsModelString m_wfs_url=
   		 new SettingsModelString(CFGKEY_WFS_URL,"http://127.0.0.1:8080/geoserver/wfs");
    final SettingsModelString m_selStr =
   		 new SettingsModelString(CFGKEY_STRSEL, "");
    static WFSDataStore dataStore = null;
  //  static DataStore dataStore = null;
   // DataStore data = DataStoreFinder.getDataStore( connectionParameters );
   // static FeatureCollection<SimpleFeatureType, SimpleFeature> featurescollec; 
    static SimpleFeatureCollection featurescollec = null;
    static boolean m_blconnect =false;
    /**
     * Constructor for the node model.
     */
    protected MyWFSNodeModel() {
    
        // TODO one incoming port and one outgoing port is assumed
      //  super(1, 1);
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
      //  JOptionPane.showMessageDialog(null, "tgtest");
        /*
        // display a data store file chooser dialog for shapefiles
    	File file = JFileDataStoreChooser.showOpenFile("shp", null);
    	
    	if (file == null) {
			return new BufferedDataTable[]{};
		}
    	FileDataStore store = FileDataStoreFinder.getDataStore(file);
    	SimpleFeatureSource featureSource = store.getFeatureSource();
    	// Create a map content and add our shapefile to it
        MapContent map = new MapContent();
        map.setTitle("Quickstart");
        Style style = SLD.createSimpleStyle(featureSource.getSchema());
        Layer layer = new FeatureLayer(featureSource, style);
        map.addLayer(layer);
        
        //Now display the map
        JMapFrame.showMap(map);
        JOptionPane.showMessageDialog(null, "tgtest2");
        */
       // String getCapabilities = "http://localhost:8080/geoserver/wfs?REQUEST=GetCapabilities&version=1.0.0";
//        String getCapabilities = null;
//        if (m_wfs_url.getStringValue().equals("")) {
//             getCapabilities = "http://127.0.0.1:8080/geoserver/wfs?REQUEST=GetCapabilities&version=1.0.0";	
//		}
//        else {
//        	 getCapabilities = m_wfs_url.getStringValue() + "?REQUEST=GetCapabilities&version=1.0.0";
//		}
//		// String getCapabilities = "http://127.0.0.1:8080/geoserver/wfs?REQUEST=GetCapabilities";
//		  Map connectionParameters = new HashMap();
//		  connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", getCapabilities);
//		  WFSDataStoreFactory  dsf = new WFSDataStoreFactory();
		 
         DataTableSpec outputSpec = null;
		  BufferedDataContainer container = null;
		  
		  try {
		    //  WFSDataStore dataStore = dsf.createDataStore(connectionParameters);
		      
		     // SimpleFeatureSource source = dataStore.getFeatureSource("ali:Manategh_Tehran");
		     // SimpleFeatureSource source = dataStore.getFeatureSource("TgKnime:coastal");
		   // Step 3 - discouvery
				String typeNames[] = dataStore.getTypeNames();
				//String typeName = typeNames[0];
				//String typeName = "TgKnime:coastal";
				String typeName = m_selStr.getStringValue();//
				SimpleFeatureType schema = dataStore.getSchema( typeName );
				//read type and create table specification
				outputSpec = createSpec(schema)[0];
				container = exec.createDataContainer(outputSpec);
				//
				System.out.println( "Schema Attributes:"+schema.getAttributeCount() );
		      
			 SimpleFeatureSource source = dataStore.getFeatureSource(typeName);
			// Step 4 - target
			//	SimpleFeatureSource source = dataStore.getFeatureSource( typeName );
				System.out.println( "Metadata Bounds:"+ source.getBounds() );

		     // SimpleFeatureCollection fc = source.getFeatures();
			  featurescollec = source.getFeatures();
		     // SimpleFeatureIterator fIterator = fc.features();
			  SimpleFeatureIterator fIterator = featurescollec.features();
		      int index = 0;
			  int count = 0;
			  GeometryJSON geojson =new GeometryJSON();
		      while(fIterator.hasNext()){
		    //  while(fc.features().hasNext()){
		        //  SimpleFeature sf = fc.features().next();
		        SimpleFeature sf = fIterator.next();
		      	DataCell[] cells = new DataCell[outputSpec.getNumColumns()];
		      	for (Property p : sf.getProperties()) {
					int column = outputSpec.findColumnIndex(p.getName().toString());
					Object value = p.getValue();

					if (value == null) {
						cells[column] = DataType.getMissingCell();
					 } else if (value instanceof Geometry) {
					   cells[column] = new StringCell(geojson.toString((Geometry)(p.getValue())));
						//geoProperty = p;
					} else if (value instanceof Integer) {
						cells[column] = new IntCell((Integer) p.getValue());
					} else if (value instanceof Double) {
						cells[column] = new DoubleCell((Double) p.getValue());
					} else if (value instanceof Boolean) {
						cells[column] = BooleanCellFactory.create((Boolean) p.getValue());
					} else if (p.getValue().toString().isEmpty()) {
						cells[column] = DataType.getMissingCell();
					} else {
						cells[column] = new StringCell(p.getValue().toString());
					}
				}
		      	//RowKey key = new RowKey("Row " + index);
		        //DataRow row = new DefaultRow(key, cells);
	            //container.addRowToTable(row);
		    	container.addRowToTable(new DefaultRow(String.valueOf(index), cells));
		    	index++;
		    	exec.checkCanceled();
				exec.setProgress((double) count / (double) featurescollec.size());
				count++;
		      	
		        //  System.out.println(sf.getAttribute("NAME"));
		      }
		  } catch (IOException ex) {
		      ex.printStackTrace();
		  }
	 
		  
        //TG E
        
        // the data table spec of the single output table, 
        // the table will have three columns:
       /* DataColumnSpec[] allColSpecs = new DataColumnSpec[3];
        allColSpecs[0] = 
            new DataColumnSpecCreator("Column 0", StringCell.TYPE).createSpec();
        allColSpecs[1] = 
            new DataColumnSpecCreator("Column 1", DoubleCell.TYPE).createSpec();
        allColSpecs[2] = 
            new DataColumnSpecCreator("Column 2", IntCell.TYPE).createSpec();
        DataTableSpec outputSpec = new DataTableSpec(allColSpecs);
        */
      //  DataTableSpec outputSpec = createSpec();
        // the execution context will provide us with storage capacity, in this
        // case a data container to which we will add rows sequentially
        // Note, this container can also handle arbitrary big data tables, it
        // will buffer to disc if necessary.
       // BufferedDataContainer container = exec.createDataContainer(outputSpec);
        // let's add m_count rows to it
       /* for (int i = 0; i < m_count.getIntValue(); i++) {
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
        }*/
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
    	if (m_blconnect) {
    		m_blconnect = false;
		}
    	else {
			
		}
		
    	
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
         if (!m_blconnect) {
        	 throw new InvalidSettingsException("The WFS not connected! "); 
		}
        return new DataTableSpec[]{null};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {

        // TODO save user settings to the config object.
        
      //  m_count.saveSettingsTo(settings);
          m_wfs_url.saveSettingsTo(settings);
          m_selStr.saveSettingsTo(settings);
          
          
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
        
     //   m_count.loadSettingsFrom(settings);
    	m_wfs_url.loadSettingsFrom(settings);
    	m_selStr.loadSettingsFrom(settings);
    	
    	

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

     //   m_count.validateSettings(settings);
          m_wfs_url.validateSettings(settings);
          m_selStr.validateSettings(settings);
          
          
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
 /*
  * 
  */
    private static DataTableSpec[] createSpec(SimpleFeatureType type) {
		List<DataColumnSpec> columns = new ArrayList<>();

		for (AttributeType t : type.getTypes()) {
			//if (t == type.getGeometryDescriptor().getType()) {
			//	continue;
			//}
			String name = t.getName().toString();
			
			if (t.getBinding() == Integer.class) {
				columns.add(new DataColumnSpecCreator(name, IntCell.TYPE).createSpec());
			} else if (t.getBinding() == Double.class) {
				columns.add(new DataColumnSpecCreator(name, DoubleCell.TYPE).createSpec());
			} else if (t.getBinding() == Boolean.class) {
				columns.add(new DataColumnSpecCreator(name, BooleanCell.TYPE).createSpec());
			} else {
				columns.add(new DataColumnSpecCreator(name, StringCell.TYPE).createSpec());
			}
		}
		return new DataTableSpec[] { new DataTableSpec(columns.toArray(new DataColumnSpec[0])) };
	}
}

