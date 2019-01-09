package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

// ToDo: remove
public class IdeaLogStackTraceElement extends ASTWrapperPsiElement {
    public IdeaLogStackTraceElement(@NotNull ASTNode node) {
        super(node);
    }
}
