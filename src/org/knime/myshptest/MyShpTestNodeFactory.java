package org.knime.myshptest;

import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;
import org.knime.core.node.port.PortType;
import org.knime.core.node.port.database.DatabaseConnectionPortObject;

/**
 * <code>NodeFactory</code> for the "MyShpTest" Node.
 * This is test for shpobject. TG
 *
 * @author TG
 */


public class MyShpTestNodeFactory 
        extends NodeFactory<MyShpTestNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MyShpTestNodeModel createNodeModel() {
       // return new DBReaderNodeFactory();
    	return new MyShpTestNodeModel(new PortType[]{DatabaseConnectionPortObject.TYPE_OPTIONAL,BufferedDataTable.TYPE},
    			new PortType[]{BufferedDataTable.TYPE,DatabaseConnectionPortObject.TYPE_OPTIONAL}); 
    //	return new MyShpTestNodeModel(new PortType[]{DatabaseConnectionPortObject.TYPE_OPTIONAL},
     //           new PortType[]{BufferedDataTable.TYPE});
    	
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
        return 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<MyShpTestNodeModel> createNodeView(final int viewIndex,
            final MyShpTestNodeModel nodeModel) {
        return new MyShpTestNodeView(nodeModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasDialog() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeDialogPane createNodeDialogPane() {
        return new MyShpTestNodeDialog();
    }

}

