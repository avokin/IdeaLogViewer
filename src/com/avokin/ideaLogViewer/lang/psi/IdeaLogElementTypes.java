package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.psi.tree.IFileElementType;

public interface IdeaLogElementTypes {
    IFileElementType IDEA_LOG_FILE = new IFileElementType("IdeaLogFile", IdeaLogLanguage.INSTANCE);
}
