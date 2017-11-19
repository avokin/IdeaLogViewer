package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.icons.AllIcons;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IdeaLogIdeStartedRecord extends IdeaLogRecord {
    public IdeaLogIdeStartedRecord(@NotNull ASTNode node) {
        super(node);
    }
}
