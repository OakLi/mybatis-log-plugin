package bruce.plugin.sqlxplugin;

import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.datatransfer.StringSelection;

/**
 * pacakgeName bruce.plugin.sqlxplugin
 *
 * @author bruce ge
 */
public class MyTextAreaDialog extends DialogWrapper {
    private JTextArea structTextArea;
    private JPanel thePanel;
    @Nullable
    private Project myProject;
    private IntellijTableInfo myIntellijTableInfo;

    protected MyTextAreaDialog(@Nullable Project project,IntellijTableInfo intellijTableInfo) {
        super(project, true);
        myProject = project;
        myIntellijTableInfo = intellijTableInfo;
        setTitle("Type struct text");

        StringBuilder builder = new StringBuilder();
        String tableNameFormat = MyDbUtils.underScoreToCamel(intellijTableInfo.getTableName());
        builder.append("type " + tableNameFormat + " struct {\n");
        for (IntellijColumnInfo columnInfo : intellijTableInfo.getColumnInfos()) {
            String name = columnInfo.getName();
            String type = MyDbUtils.convertType(columnInfo.getDataType());
            builder.append(MyDbUtils.underScoreToCamel(name) + " " + type + " `db:\"" + name + "\"`\n");
        }
        builder.append("}");
        structTextArea.setText(builder.toString());
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return thePanel;
    }


    @Override
    protected void doOKAction() {
        CopyPasteManager.getInstance().setContents(new StringSelection(structTextArea.getText()));
        JBPopupFactory.getInstance().createMessage("copy struct text success").showCenteredInCurrentWindow(myProject);
        super.doOKAction();
    }
}
