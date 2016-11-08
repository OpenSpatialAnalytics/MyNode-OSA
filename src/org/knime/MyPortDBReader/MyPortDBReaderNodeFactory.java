package org.knime.MyPortDBReader;

import org.knime.MyDBReader.MyDBReaderNodeModel;
import org.knime.base.node.io.database.util.DBReaderDialogPane;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;
import org.knime.core.node.port.PortType;
import org.knime.core.node.port.database.DatabaseConnectionPortObject;
import org.knime.mynode.myport.MyDatabaseConnPortObject;

/**
 * <code>NodeFactory</code> for the "MyPortDBReader" Node.
 * This is a testing my Database Port. TG
 *
 * @author TG
 */
public class MyPortDBReaderNodeFactory 
        extends NodeFactory<MyPortDBReaderNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MyPortDBReaderNodeModel createNodeModel() {
       // return new MyPortDBReaderNodeModel();
        return new MyPortDBReaderNodeModel(new PortType[]{MyDatabaseConnPortObject.TYPE_OPTIONAL},
    			new PortType[]{BufferedDataTable.TYPE});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNrNodeViews() {
       // return 1;
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NodeView<MyPortDBReaderNodeModel> createNodeView(final int viewIndex,
            final MyPortDBReaderNodeModel nodeModel) {
        //return new MyPortDBReaderNodeView(nodeModel);
        return null;
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
       // return new MyPortDBReaderNodeDialog();
       // return new DBReaderDialogPane(true, true) {
         return new MyPortDBReaderNodeDialog(true, true) {  //here must be this way,because the new My databaseconportobjectspec used in this class
             /** {@inheritDoc} */
             @Override
             protected boolean runWithoutConfigure() {
                 return true;
             }
         };
    }

}

