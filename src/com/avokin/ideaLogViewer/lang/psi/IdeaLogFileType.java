package com.avokin.ideaLogViewer.lang.psi;

import com.avokin.ideaLogViewer.lang.IdeaLogLanguage;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IdeaLogFileType extends LanguageFileType {
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
        return "log";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return null;
    }
}
