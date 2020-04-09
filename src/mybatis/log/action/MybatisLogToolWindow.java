package mybatis.log.action;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import mybatis.log.Icons;
import mybatis.log.action.gui.ListForm;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author bruce ge
 */
public class MybatisLogToolWindow implements ToolWindowFactory, DumbAware {
    private JPanel myToolWindowContent;

    private ToolWindow myToolWindow;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        myToolWindowContent = new ListForm(project).getThePanel();
        myToolWindow = toolWindow;
        ContentFactory instance = ContentFactory.SERVICE.getInstance();
        Content content = instance.createContent(myToolWindowContent, "", false);
        content.setIcon(Icons.MyBatisIcon);
        toolWindow.getContentManager().addContent(content);
    }

    public void init(ToolWindow window) {
        window.setIcon(Icons.MyBatisIcon);
    }



    public boolean shouldBeAvailable(@NotNull Project project) {
        return true;
    }

    public boolean isDoNotActivateOnStart() {
        return false;
    }
}
