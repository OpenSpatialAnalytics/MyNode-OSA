package org.knime.MyWFS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.geotools.data.FeatureSource;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapPane;
import org.geotools.swing.MapLayerTable;
import org.geotools.swing.JMapFrame.Tool;
import org.knime.core.node.NodeView;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Envelope;

import net.miginfocom.swing.MigLayout;

import org.geotools.filter.expression.PropertyAccessors;
import org.geotools.filter.expression.ThisPropertyAccessorFactory;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.swing.action.NoToolAction;
import org.geotools.swing.action.ZoomInAction;
import org.geotools.swt.SwtMapPane;
/**
 * <code>NodeView</code> for the "MyWFS" Node.
 * This is a test node for Web Feature Service.TG
 *
 * @author Gen Tian
 */
public class MyWFSNodeView extends NodeView<MyWFSNodeModel> {

	/**
     * Creates a new view.
     * 
     * @param nodeModel The model (class: {@link MyWFSNodeModel})
     */
    protected MyWFSNodeView(final MyWFSNodeModel nodeModel) {
        super(nodeModel);

        // TODO instantiate the components of the view here.
       
        // sets the view content in the node view
      
        try {
//        	File dir = new File("/temp");
//        	if (!dir.exists())
//        		dir.mkdir();
        	
//    		File file = new File("temp.shp");
//    		FeatureCollection<SimpleFeatureType, SimpleFeature> features = nodeModel.features;
//    		//File file = new File(fname);
//			WriteShapefile writer = new WriteShapefile(file);
//			writer.writeFeatures(features);
//			
//			Map<String, URL> map1 = new HashMap<String, URL>();      
//			map1.put("url", file.toURI().toURL());
		
//			FileDataStore store = FileDataStoreFinder.getDataStore(file);
		
	        @SuppressWarnings("rawtypes")
			//FeatureSource featureSource = nodeModel.dataStore.getFeatureSource();
	        //String typeName = "TgKnime:coastal";
	        String typeName = nodeModel.m_selStr.getStringValue();
	        SimpleFeatureSource featureSource = nodeModel.dataStore.getFeatureSource(typeName);
	        
	        MapContent map = new MapContent();
	      //  map.setTitle("KnimeMapView");
	      //  Style style = StyleLab.createStyle2(featureSource);
	        Style style = SLD.createSimpleStyle(featureSource.getSchema());
	       // Layer layer = new FeatureLayer(featureSource, style);
	        Layer layer = new FeatureLayer(featureSource, style);
	        
	        map.addLayer(layer);
	        doShowMapView(map);
	        /*
	        JMapFrame jf = new JMapFrame(map);
	        jf.setTitle("Map View");
	        jf.setVisible(true);
	        jf.setAlwaysOnTop(true);
	        jf.setSize(640,480);	
	        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	        jf.setLocation(dim.width/2-jf.getSize().width/2, dim.height/2-jf.getSize().height/2);
	        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        */
//	        JMapFrame.showMap(map);//tg
	    	//file.delete();
	        //map.dispose();
	        	        
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void modelChanged() {

        // TODO retrieve the new model from your nodemodel and 
        // update the view.
        MyWFSNodeModel nodeModel = 
            (MyWFSNodeModel)getNodeModel();
        assert nodeModel != null;
        
        // be aware of a possibly not executed nodeModel! The data you retrieve
        // from your nodemodel could be null, emtpy, or invalid in any kind.
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onClose() {
    
        // TODO things to do when closing the view
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onOpen() {

        // TODO things to do when opening the view
    }
// JMapFrame 
    /**
     * Creates a new map frame with a toolbar, map pane and status
     * bar; sets the supplied {@code MapContent}; and displays the frame.
     * If {@linkplain MapContent#getTitle()} returns a non-empty string,
     * this is used as the frame's title.
     * <p>
     * This method can be called safely from any thread.
     *
     * @param content the map content
     */
//
    private void doShowMapView(MapContent content) {
    	//tg
    	
    	 
    	JMapPane mapPane = new JMapPane(content);
    	 //mapPane.setMapContent(content);
    	 mapPane.setBackground(Color.WHITE);
         mapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      // give keyboard focus to the map pane
        /* addWindowFocusListener(new WindowAdapter() {
             @Override
             public void windowGainedFocus(WindowEvent e) {
                 mapPane.requestFocusInWindow();
             }
         });
         */
        //
        /* mapPane.addFocusListener(new FocusAdapter() {

             @Override
             public void focusGained(FocusEvent e) {
                 mapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
             }

             @Override
             public void focusLost(FocusEvent e) {
                 mapPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
             }
         });
         
         mapPane.addMouseListener(new MouseAdapter() {

             @Override
             public void mousePressed(MouseEvent e) {
                 mapPane.requestFocusInWindow();
             }
         });
         */
    	//MapLayerTable mapLayerTable;
        //JToolBar toolBar;
    	//
         StringBuilder sb = new StringBuilder();
         sb.append("[grow]"); // map pane and optionally layer table fill space
         //
         Set<Tool> toolSet;
        // toolSet = EnumSet.noneOf(Tool.class);
         toolSet = EnumSet.allOf(Tool.class);
         if (!toolSet.isEmpty()) {
             sb.append("[]"); // fixed size
         }
         //
        // JPanel panel =new JPanel();
        JPanel panel = new JPanel(new MigLayout(
                "wrap 1, insets 0", // layout constrains: 1 component per row, no insets

                "[grow]", // column constraints: col grows when frame is resized

                sb.toString() ));
      //  panel.add(mapPane, "grow");
       // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      //  mapPane.setSize(dim);
        
       // panel.setSize(800,600);
        //
        JToolBar toolBar;
        toolBar = new JToolBar();
        toolBar.setOrientation(JToolBar.HORIZONTAL);
        toolBar.setFloatable(false);
        
       
        
        JButton btn;
        ButtonGroup cursorToolGrp = new ButtonGroup();
        //btn = new JButton(new ZoomInAction(mapPane));
        btn = new JButton("test");
       // btn.setName("ToolbarZoomInButton");
        toolBar.add(btn);
        cursorToolGrp.add(btn);
        panel.add(toolBar, "grow");
        //
        panel.add(mapPane, "grow");
      //  panel.add(mapPane);
        setComponent(panel);
      //  mapPane.repaint(0, 0, 800,600);
     //   mapPane.repaint();
      //  panel.repaint();
       // panel.repaint(0, 0, 800,600);
      //  setComponent(mapPane);
    	
       // final JMapFrame frame = new JMapFrame(content);
    	  
//        frame.enableStatusBar(true);
//        frame.enableToolBar(true);
//        frame.initComponents();
//        frame.setSize(800, 600);
//        frame.setVisible(true);
    } 
    
}

