package com.avokin.ideaLogViewer.log.viewer.lang.lexer;


import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.intellij.psi.TokenType;

import com.avokin.ideaLogViewer.log.viewer.lang.psi.IdeaLogViewerTokenTypes;
import static com.avokin.ideaLogViewer.log.viewer.lang.psi.IdeaLogViewerTokenTypes.*;

/**
 * The IdeaLogViewer lexer
 *
 * @author Andrey Vokin
 */
%%

%unicode

%public
%class _IdeaLogLexer
%implements FlexLexer
%type IElementType

%function advance

%{
  private final Stack<Integer> stack = new Stack<Integer>();

  /**
   * Push the actual state on top of the stack
   */
  private void pushState() {
    stack.push(yystate());
  }

  /**
   * Push the actual state on top of the stack
   * and change into another state
   *
   * @param state The new state
   */
  private void pushStateAndBegin(int state) {
    stack.push(yystate());
    yybegin(state);
  }

  /**
   * Pop the last state from the stack and change to it.
   * If the stack is empty, go to YYINITIAL
   */
  private void popState() {
    if (!stack.empty()) {
      yybegin(stack.pop());
    } else {
      pushStateAndBegin(YYINITIAL);
    }
  }
%}


WHITE_SPACE     = ([ \f\n\r\t\u000b​\u00a0\u1680​\u180e\u2000-\u200a​\u2028\u2029​​\u202f\u205f​\u3000])+
TIME_STAMP      = [0-9\-:, ]+ \[[ \t]*\d+\]

%state YY_CLASS_NAME, YY_AFTER_CLASS_NAME

%%

<YYINITIAL> {
  "DEBUG"                     { return LOG_LEVEL_DEBUG; }
  "INFO"                      { return LOG_LEVEL_INFO; }
  "WARN"                      { return LOG_LEVEL_WARN; }
  "ERROR"                     { return LOG_LEVEL_ERROR; }

  " - "                       { pushStateAndBegin(YY_CLASS_NAME);
                                return TEXT; }

  {TIME_STAMP}                { return TIME_STAMP; }
}

<YY_CLASS_NAME> {
  [^- \t\n]+                  { return CLASS_NAME; }

  " - "                       { pushStateAndBegin(YY_AFTER_CLASS_NAME);
                                return TEXT; }
}

<YY_AFTER_CLASS_NAME> {
  [^\n]+                      { return MESSAGE; }
}


[\n]                          { stack.clear();
                                pushStateAndBegin(YYINITIAL);
                                return TokenType.WHITE_SPACE; }

{WHITE_SPACE}                 { return TokenType.WHITE_SPACE; }

[^]                           { stack.clear();
                                pushStateAndBegin(YYINITIAL);
                                return TEXT; }