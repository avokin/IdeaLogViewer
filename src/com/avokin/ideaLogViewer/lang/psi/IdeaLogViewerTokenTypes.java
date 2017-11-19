package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

public interface IdeaLogViewerTokenTypes {
  IElementType LOG_LEVEL_DEBUG = new IdeaLogElementType("DEBUG");
  IElementType LOG_LEVEL_INFO = new IdeaLogElementType("INFO");
  IElementType LOG_LEVEL_WARN = new IdeaLogElementType("WARN");
  IElementType LOG_LEVEL_ERROR = new IdeaLogElementType("ERROR");

  IElementType TIME_STAMP = new IdeaLogElementType("TIME_STAMP");
  IElementType TEXT = new IdeaLogElementType("TEXT");
  IElementType MESSAGE = new IdeaLogElementType("MESSAGE");
  IElementType CLASS_NAME = new IdeaLogElementType("CLASS_NAME");

  IElementType BRACKET_START = new IdeaLogElementType("BRACKET_START");
  IElementType BRACKET_END = new IdeaLogElementType("BRACKET_END");
  IElementType UPTIME = new IdeaLogElementType("UPTIME");

  IElementType CODE_REFERENCE = new IdeaLogElementType("CODE_REFERENCE");

  TokenSet LOG_LEVELS = TokenSet.create(LOG_LEVEL_DEBUG, LOG_LEVEL_INFO, LOG_LEVEL_WARN, LOG_LEVEL_ERROR);
}
