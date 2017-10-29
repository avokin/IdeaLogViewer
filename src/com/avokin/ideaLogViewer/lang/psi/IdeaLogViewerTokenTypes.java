package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.psi.tree.IElementType;

public interface IdeaLogViewerTokenTypes {
  IElementType LOG_LEVEL_DEBUG = new IdeaLogLevelElementType("DEBUG");
  IElementType LOG_LEVEL_INFO = new IdeaLogLevelElementType("INFO");
  IElementType LOG_LEVEL_WARN = new IdeaLogLevelElementType("WARN");
  IElementType LOG_LEVEL_ERROR = new IdeaLogLevelElementType("ERROR");

  IElementType TIME_STAMP = new IdeaLogLevelElementType("TIME_STAMP");
  IElementType TEXT = new IdeaLogLevelElementType("TEXT");
  IElementType MESSAGE = new IdeaLogLevelElementType("MESSAGE");
  IElementType CLASS_NAME = new IdeaLogLevelElementType("CLASS_NAME");
}
