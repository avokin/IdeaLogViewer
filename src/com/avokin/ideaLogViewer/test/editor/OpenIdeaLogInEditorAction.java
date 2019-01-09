package com.avokin.ideaLogViewer.test.editor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.testFramework.VfsTestUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class OpenIdeaLogInEditorAction extends AnAction implements DumbAware {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        final File logFile = new File(PathManager.getLogPath(), "idea.log");
        if (!logFile.exists()) {
            return;
        }

        VirtualFile logVirtualFile = VfsTestUtil.findFileByCaseSensitivePath(logFile.getAbsolutePath());
        if (!logVirtualFile.exists()) {
            return;
        }

        FileEditorManager.getInstance(project).openEditor(new OpenFileDescriptor(project, logVirtualFile), true);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        Presentation presentation = e.getPresentation();
        presentation.setVisible(project != null);
        presentation.setEnabled(project != null);
    }
}
