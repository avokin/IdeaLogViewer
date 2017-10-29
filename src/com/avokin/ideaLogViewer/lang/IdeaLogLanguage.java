package com.avokin.ideaLogViewer.lang;

import com.intellij.lang.Language;

public class IdeaLogLanguage extends Language {
  public static final IdeaLogLanguage INSTANCE = new IdeaLogLanguage();

  protected IdeaLogLanguage() {
    super("IDEA log");
  }
}
