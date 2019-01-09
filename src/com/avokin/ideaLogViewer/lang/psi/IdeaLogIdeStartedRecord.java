package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public class IdeaLogIdeStartedRecord extends IdeaLogRecord {
    public IdeaLogIdeStartedRecord(@NotNull ASTNode node) {
        super(node);
    }
}
