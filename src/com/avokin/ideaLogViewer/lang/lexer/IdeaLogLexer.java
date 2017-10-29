package com.avokin.ideaLogViewer.lang.lexer;

import com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;

public class IdeaLogLexer extends MergingLexerAdapter {
  private static final TokenSet TOKENS_TO_MERGE = TokenSet.create(IdeaLogViewerTokenTypes.TEXT);

  public IdeaLogLexer() {
    super(new FlexAdapter(new _IdeaLogLexer(null)), TOKENS_TO_MERGE);
  }

  @Override
  public void advance() {
    super.advance();
  }
}
