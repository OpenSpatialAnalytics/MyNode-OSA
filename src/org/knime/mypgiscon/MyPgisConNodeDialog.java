package org.knime.mypgiscon;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentNumber;
import org.knime.core.node.defaultnodesettings.SettingsModelIntegerBounded;

/**
 * <code>NodeDialog</code> for the "MyPgisCon" Node.
 * This mypgiscon will connect postgres database. TG.
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author TG
 */
public class MyPgisConNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring MyPgisCon node dialog.
     * This is just a suggestion to demonstrate possible default dialog
     * components.
     */
    protected MyPgisConNodeDialog() {
        super();
        
        addDialogComponent(new DialogComponentNumber(
                new SettingsModelIntegerBounded(
                    MyPgisConNodeModel.CFGKEY_COUNT,
                    MyPgisConNodeModel.DEFAULT_COUNT,
                    Integer.MIN_VALUE, Integer.MAX_VALUE),
                    "Counter:", /*step*/ 1, /*componentwidth*/ 5));
                    
    }
}

