package bruce.plugin.sqlxplugin;

import com.google.common.base.CaseFormat;
import com.intellij.database.psi.DbTable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * pacakgeName bruce.plugin.sqlxplugin
 *
 * @author bruce ge
 */
public class TableGeneratedStructAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {

        PsiElement psiElement = e.getData(LangDataKeys.PSI_ELEMENT);
        if (!(psiElement instanceof DbTable)) {
            return;
        }
        DbTable currentTable = (DbTable) psiElement;
        IntellijTableInfo intellijTableInfo = MyDbUtils.buildIntellijTableInfoFromDatabase(currentTable);
        MyTextAreaDialog myTextAreaDialog = new MyTextAreaDialog(e.getProject(), intellijTableInfo);

        myTextAreaDialog.showAndGet();


    }



    @Override
    public void update(@NotNull AnActionEvent e) {
        PsiElement psiElement = e.getData(LangDataKeys.PSI_ELEMENT);
        if (psiElement instanceof DbTable) {
            e.getPresentation().setVisible(true);
            return;
        } else {
            e.getPresentation().setVisible(false);
            return;
        }
    }
}
