package com.avokin.ideaLogViewer.lang.psi;

import com.avokin.ideaLogViewer.lang.IdeaLogLanguage;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.fileTypes.ex.FileTypeIdentifiableByVirtualFile;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IdeaLogFileType extends LanguageFileType implements FileTypeIdentifiableByVirtualFile {
    public static final IdeaLogFileType INSTANCE = new IdeaLogFileType();

    private IdeaLogFileType() {
        super(IdeaLogLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "IDEA log";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "IDEA log";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "---";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return AllIcons.FileTypes.Text;
    }

    @Override
    public boolean isMyFileType(@NotNull VirtualFile virtualFile) {
        return virtualFile.getName().matches("idea\\.log[\\d]*");
    }
}
