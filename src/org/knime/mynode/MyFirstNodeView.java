package org.knime.mynode;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.knime.core.node.NodeView;

/**
 * <code>NodeView</code> for the "MyFirst" Node.
 * This node is made by TG
 *
 * @author KNIME
 */
public class MyFirstNodeView extends NodeView<MyFirstNodeModel> {

    /**
     * Creates a new view.
     * 
     * @param nodeModel The model (class: {@link MyFirstNodeModel})
     */
    protected MyFirstNodeView(final MyFirstNodeModel nodeModel) {
        super(nodeModel);

        // TODO instantiate the components of the view here.
		// TG 0705 S
		// 1. NodeModel#notifyViews(Object)
		// 2. NodeView#setShowNODATALabel(false)
		// 3. NodeView#updateModel(Object)
		// setShowNODATALabel(false);
        /*
         * The NodeViewdisplays the data of the NodeModelin a specific way. 
         * This can be done either by using Java components such as 
         * javax.swing.JTable or javax.swing.JTree or by drawing the data using
         *  the java.awt.Graphics object. In the latter case a javax.swing.JPanel
         *  can be used and the paintComponent(Graphics) method must be overridden. 
         *  There are four sites in the NodeView to place your code:
         *  The constructor is called whenever the view is opened. 
         *  This is the best place to initialize the fields, data structures, 
         *  and components you need. When everything is initialized and
         *  the preferences are set, call the setComponent(Component).
         *  Only one component can be set with this method, thus, 
         *  if you have several components you have the make a composite 
         *  by your own and set this composite. It is good practice to 
         *  call this method only once and then in the constructor.
         *  Note: you can leave the view open, when resetting the node or even change 
         *  the input of the node, i.e. all operations related to the data of the model
         *   cannot be placed in the constructor.Hence, the method informs the view 
         *   about changes in the underlying NodeModel.It is possible to open several 
         *   views for a single NodeModel, though. Local functionality, such as 
         *   selection, has to be administered in the NodeView
         *   
         */
		// m_panel = new YourSpecializedPanel();
		// setComponent(m_panel);
        /*
		JPanel tgpanel = new JPanel();
		JCheckBox chinButton = new JCheckBox();
		JButton button = new JButton("tgpanelbutton");
		tgpanel.add(button);
		tgpanel.add(chinButton);
		setComponent(tgpanel);
		*/
		// TG 0705 E
        
       
          
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void modelChanged() {

        // TODO retrieve the new model from your nodemodel and 
        // update the view.
        MyFirstNodeModel nodeModel = 
            (MyFirstNodeModel)getNodeModel();
        assert nodeModel != null;
        
        // be aware of a possibly not executed nodeModel! The data you retrieve
        // from your nodemodel could be null, emtpy, or invalid in any kind.
    	//JOptionPane.showMessageDialog(null, "TG:modelChanged");//TG 0705 T
        // System.out.println("TG:modelChanged");
        /* TG 0705 T
         * This method is called, whenever the underlying NodeModel has changed. 
         * Hence, in this method the data to be visualized is retrieved from the 
         * NodeModel. Then the data is interpreted by the view, i.e. converted 
         * in a component or drawn. The NodeModel is accessible via the 
         * getNodeModel method. If you have to call this method frequently 
         * you can override this method and have your derived NodeModel 
         * as the return type of the overridden method.
         */
		// retrieve the data to visualize...
		//Object viewModel = nodeModel.getViewModel();
		// set it to spacialized panel implementation
		// to interprete the model and visualize it
		//m_panel.visualizeModel(viewModel);
        //TG 0705 T
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onClose() {
    
        // TODO things to do when closing the view
    	//JOptionPane.showMessageDialog(null, "TG:onClose");//TG 0705 T
    	/*TG 0705 T
    	 * This method is called, when the view is closed. Usually, this method 
    	 * is used to de-register from any handlers if the NodeView implements
    	 *  a listener, i.e. the HiLiteListener.
    	 */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onOpen() {

        // TODO things to do when opening the view
        //	JOptionPane.showMessageDialog(null, "TG:onOpen");//TG 0705 T
		/* TG 0705 T
		 * This method is called when the view is opened, i.e. after construction. 
		 * In most of the cases you can place all the initialization code into the constructor.
		 */
    	
    }

}

