package com.avokin.ideaLogViewer.lang.psi;

import com.avokin.ideaLogViewer.lang.IdeaLogElementVisitor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes.LOG_LEVELS;

public class IdeaLogRecord extends ASTWrapperPsiElement {
    public IdeaLogRecord(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    public IElementType getLogLevelToken() {
        ASTNode childNode = getNode().getFirstChildNode();
        while (childNode != null && !LOG_LEVELS.contains(childNode.getElementType())) {
            childNode = childNode.getTreeNext();
        }
        return childNode != null ? childNode.getElementType() : null;
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof IdeaLogElementVisitor) {
            ((IdeaLogElementVisitor)visitor).visitIdeaLogRecord(this);
        } else {
            super.accept(visitor);
        }
    }
}
