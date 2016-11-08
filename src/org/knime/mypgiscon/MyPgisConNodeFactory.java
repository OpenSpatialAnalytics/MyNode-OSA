package org.knime.mypgiscon;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "MyPgisCon" Node.
 * This mypgiscon will connect postgres database. TG.
 *
 * @author TG
 */
public class MyPgisConNodeFactory 
        extends NodeFactory<MyPgisConNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MyPgisConNodeModel createNodeModel() {
        return new MyPgisConNodeModel();
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
    public NodeView<MyPgisConNodeModel> createNodeView(final int viewIndex,
            final MyPgisConNodeModel nodeModel) {
        return new MyPgisConNodeView(nodeModel);
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
        return new MyPgisConNodeDialog();
    }

}

