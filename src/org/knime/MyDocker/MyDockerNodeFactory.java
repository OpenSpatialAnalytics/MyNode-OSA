package org.knime.MyDocker;

import org.knime.core.node.NodeDialogPane;
import org.knime.core.node.NodeFactory;
import org.knime.core.node.NodeView;

/**
 * <code>NodeFactory</code> for the "MyDocker" Node.
 * This is a test for Docker integration
 *
 * @author TG
 */
public class MyDockerNodeFactory 
        extends NodeFactory<MyDockerNodeModel> {

    /**
     * {@inheritDoc}
     */
    @Override
    public MyDockerNodeModel createNodeModel() {
        return new MyDockerNodeModel();
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
    public NodeView<MyDockerNodeModel> createNodeView(final int viewIndex,
            final MyDockerNodeModel nodeModel) {
        return new MyDockerNodeView(nodeModel);
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
        return new MyDockerNodeDialog();
    }

}

