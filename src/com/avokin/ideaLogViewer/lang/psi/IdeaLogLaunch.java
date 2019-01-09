package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.icons.AllIcons;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IdeaLogLaunch extends ASTWrapperPsiElement {
    public IdeaLogLaunch(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return AllIcons.RunConfigurations.TestState.Run;
    }
}
