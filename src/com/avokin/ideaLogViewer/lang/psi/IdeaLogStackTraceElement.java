package com.avokin.ideaLogViewer.lang.psi;

import com.avokin.ideaLogViewer.codeInsight.IdeaLogStackTraceReference;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IdeaLogStackTraceElement extends ASTWrapperPsiElement {
    public IdeaLogStackTraceElement(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        String className = getClassName();
        if (className == null) {
            return null;
        }
        return new IdeaLogStackTraceReference(this);
    }

    @Nullable
    public String getClassName() {
        String text = getText();
        int end = text.indexOf("(");
        if (end < 0) {
            return null;
        }
        String classAndMethod = text.substring(0, end);
        int lastPoint = classAndMethod.lastIndexOf('.');
        if (lastPoint < 0) {
            return null;
        }
        return classAndMethod.substring(0, lastPoint);
    }

    public int getLineNumber() {
        String text = getText();
        int colonIndex = text.lastIndexOf(':');
        if (colonIndex < 0) {
            return -1;
        }
        int parenthesisIndex = text.lastIndexOf(')');
        if (parenthesisIndex <= colonIndex + 1) {
            return -1;
        }

        return StringUtil.parseInt(text.substring(colonIndex + 1, parenthesisIndex), -1);
    }
}
