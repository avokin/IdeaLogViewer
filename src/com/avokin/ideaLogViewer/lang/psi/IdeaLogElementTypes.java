package com.avokin.ideaLogViewer.lang.psi;

import com.avokin.ideaLogViewer.lang.IdeaLogLanguage;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;

public interface IdeaLogElementTypes {
    IFileElementType IDEA_LOG_FILE = new IFileElementType("IdeaLogFile", IdeaLogLanguage.INSTANCE);

    IElementType LOG_RECORD = new IElementType("LOG_RECORD", IdeaLogLanguage.INSTANCE);
    IElementType IDE_STARTED_RECORD = new IElementType("IDE_STARTED_RECORD", IdeaLogLanguage.INSTANCE);
}
