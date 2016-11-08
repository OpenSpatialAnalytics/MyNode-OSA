package org.knime.myshptest;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;

/**
 * <code>NodeDialog</code> for the "MyShpTest" Node.
 * This is test for shpobject. TG
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author TG
 */
public class MyShpTestNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring MyShpTest node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    protected MyShpTestNodeDialog() {
        super();
        
        addDialogComponent(new DialogComponentNumber(
                new SettingsModelIntegerBounded(
                    MyShpTestNodeModel.CFGKEY_COUNT,
                    MyShpTestNodeModel.DEFAULT_COUNT,
                    Integer.MIN_VALUE, Integer.MAX_VALUE),
                    "Counter:", /*step*/ 1, /*componentwidth*/ 5));
                    
    }
}

