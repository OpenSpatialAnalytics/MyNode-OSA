package org.knime.MyDocker;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.mynode.MyFirstNodeModel;

/**
 * <code>NodeDialog</code> for the "MyDocker" Node.
 * This is a test for Docker integration
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author TG
 */
public class MyDockerNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring MyDocker node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    protected MyDockerNodeDialog() {
        super();
        
        addDialogComponent(new DialogComponentString(
        		new SettingsModelString(
        			MyDockerNodeModel.CFGKEY_DOCKER_CMD,
        			"docker run docker/whalesay cowsay KnimeDockerTGtest"),
        		    "Command Line:"));
                    
    }
}

