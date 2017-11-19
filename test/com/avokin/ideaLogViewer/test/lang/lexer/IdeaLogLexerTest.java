package com.avokin.ideaLogViewer.test.lang.lexer;

import com.avokin.ideaLogViewer.lang.lexer.IdeaLogLexer;
import com.avokin.ideaLogViewer.test.IdeaLogViewerTestUtil;
import com.intellij.lexer.Lexer;
import com.intellij.testFramework.LexerTestCase;
import org.jetbrains.annotations.NotNull;

public class IdeaLogLexerTest extends LexerTestCase {
  public void testGeneral() {
    doFileTest();
  }

  @Override
  protected Lexer createLexer() {
    return new IdeaLogLexer();
  }

  @Override
  protected String getDirPath() {
    return "lang/lexer/testData/";
  }

  @NotNull
  protected String getPathToTestDataFile(String extension) {
    return IdeaLogViewerTestUtil.getTestsRoot() + getDirPath() + getTestName(true) + extension;
  }

  private void doFileTest() {
    doFileTest("log");
  }
}
