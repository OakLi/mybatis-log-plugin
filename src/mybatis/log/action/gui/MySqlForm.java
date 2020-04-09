package mybatis.log.action.gui;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.sql.SqlFileType;
import com.intellij.sql.psi.SqlLanguage;
import com.intellij.ui.EditorTextField;

import javax.swing.*;
import java.awt.*;

/**
 * @author bruce ge
 */
public class MySqlForm {
    private JPanel thePanel;
    private EditorTextField myEditorTextField;
    private JPanel toolbarPanel;
    private PsiFile myPsiFile;
    private Project myProject;
    private String theSqlText;
    private Document myDocument;

    public MySqlForm(Project project, String text) {
        myProject = project;
        theSqlText = text;
        this.$$$setupUI$$$();
    }

    public String getTheSqlText() {
        return theSqlText;
    }

    public JPanel getThePanel() {
        return thePanel;
    }

    private void createUIComponents() {
        PsiFile fileFromText = PsiFileFactory.getInstance(myProject).createFileFromText("mybatis.sql", SqlLanguage.INSTANCE, theSqlText);
        myPsiFile = fileFromText;
        Document document = PsiDocumentManager.getInstance(myProject).getDocument(fileFromText);
        myDocument = document;
        myEditorTextField = new EditorTextField(document, myProject, SqlFileType.INSTANCE) {
            @Override
            protected EditorEx createEditor() {
                EditorEx editor = super.createEditor();
                editor.setVerticalScrollbarVisible(true);
                return editor;
            }
        };
        myEditorTextField.setOneLineMode(false);
        myEditorTextField.setAutoscrolls(true);

        toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new BorderLayout());
        DefaultActionGroup actionResultGroup = new DefaultActionGroup("MybatisCodeHelper.MybatisRunner", true);
        actionResultGroup.add(new ExecuteAction(this));
        ActionToolbar actionToolBar = ActionManager.getInstance().createActionToolbar("MybatisRunnerActions", actionResultGroup, true);

        actionToolBar.setLayoutPolicy(ActionToolbar.AUTO_LAYOUT_POLICY);
        JComponent actionToolBarComponent = actionToolBar.getComponent();
        actionToolBarComponent.setBorder(null);
        actionToolBarComponent.setOpaque(false);
        toolbarPanel.add(actionToolBarComponent, BorderLayout.CENTER);
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        thePanel = new JPanel();
        thePanel.setLayout(new BorderLayout(0, 0));
        thePanel.add(myEditorTextField, BorderLayout.CENTER);
        thePanel.add(toolbarPanel, BorderLayout.SOUTH);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return thePanel;
    }
}
