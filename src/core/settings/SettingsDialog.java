package core.settings;

import base.BaseDialog;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.PlatformIcons;
import global.utils.i18n.Localizer;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * Created by CeH9 on 22.06.2016.
 */

public class SettingsDialog extends BaseDialog implements SettingsView {

    private SettingsPresenter presenter;

    public SettingsDialog(Project project) {
        super(project);
        presenter = new SettingsPresenterImpl(this);
    }


    @Override
    public void preShow() {
        presenter.onPreShow();
    }

    @Override
    public void buildView() {
        panel.setLayout(new MigLayout());

        ToolbarDecorator tbDecorator = ToolbarDecorator
                .createDecorator(createTree())
                .setAddAction(anActionButton -> System.out.println("AddAction"))
                .setRemoveAction(anActionButton -> System.out.println("RemoveAction"))
                .setEditAction(anActionButton -> System.out.println("EditAction"))
                .addExtraAction(new AnActionButton("Export", PlatformIcons.EXPORT_ICON) {
                    @Override
                    public void actionPerformed(AnActionEvent e) {
                        System.out.println("Export");
                    }
                });

        panel.add(tbDecorator.createPanel(), new CC().wrap().spanX().growX().pushX());
    }

    private Tree createTree() {
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("The Java Series");
        createNodes(top);
        Tree tree = new Tree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        return tree;
    }

    private void createNodes(DefaultMutableTreeNode top) {
        top.add(new DefaultMutableTreeNode("First"));
        top.add(new DefaultMutableTreeNode("Second"));
        top.add(new DefaultMutableTreeNode("Third"));
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        Action cancelAction = getCancelAction();
        cancelAction.putValue(Action.NAME, Localizer.get("action.Done"));
        return new Action[]{cancelAction};
    }
}
