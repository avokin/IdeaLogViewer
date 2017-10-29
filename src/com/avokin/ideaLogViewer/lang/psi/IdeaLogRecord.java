package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class IdeaLogRecord extends ASTWrapperPsiElement {
    public IdeaLogRecord(@NotNull ASTNode node) {
        super(node);
    }
}
