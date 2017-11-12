package com.avokin.ideaLogViewer.lang.lexer;


import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import java.util.Stack;

import com.intellij.psi.TokenType;

import static com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes.*;

/**
 * The IdeaLogViewer lexer
 *
 * @author Andrey Vokin
 */
%%
%debug
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

EOL              = "\n"
WHITE_SPACE      = ([ \f\n\r\t\u000b​\u00a0\u1680​\u180e\u2000-\u200a​\u2028\u2029​​\u202f\u205f​\u3000])+
TIME_STAMP       = (\d{4}-\d{1,2}-\d{1,2}[ ]\d{1,2}:\d{1,2}:\d{1,2},\d{3})
UPTIME           = [ \t]*\d+
STACK_TRACE_PREFIX = [ \t]"at"[ \t]
STACK_TRACE_ELEMENT = [a-zA-Z0-9_.]+\([a-zA-Z0-9_.]+:\d+\)
STACK_TRACE_LINE = {STACK_TRACE_PREFIX}{STACK_TRACE_ELEMENT}{EOL}

%state YY_CLASS_NAME, YY_AFTER_CLASS_NAME, YY_LOG_RECORD, YY_UPTIME, YY_TEXT_UNTIL_END_OF_LINE, YY_STACK_TRACE_LINE

%%

<YYINITIAL> {
  {TIME_STAMP}                { pushStateAndBegin(YY_UPTIME);
                                return TIME_STAMP; }

  [\n]                        { return TokenType.WHITE_SPACE; }
}

<YY_UPTIME> {
  {WHITE_SPACE}               { return TokenType.WHITE_SPACE; }
  "["                         { return BRACKET_START; }
  {UPTIME}                    { return UPTIME; }
  "]"                         { pushStateAndBegin(YY_LOG_RECORD);
                                return BRACKET_END; }
}

<YY_LOG_RECORD> {
  {WHITE_SPACE}               { return TokenType.WHITE_SPACE; }

  "DEBUG"                     { return LOG_LEVEL_DEBUG; }
  "INFO"                      { return LOG_LEVEL_INFO; }
  "WARN"                      { return LOG_LEVEL_WARN; }
  "ERROR"                     { return LOG_LEVEL_ERROR; }

  " -"[ ]+                    { pushStateAndBegin(YY_CLASS_NAME);
                               return TEXT; }
}

<YY_CLASS_NAME> {
  [^- \t\n]+                  { return CLASS_NAME; }

  " - "                       { pushStateAndBegin(YY_AFTER_CLASS_NAME);
                                return TEXT; }
}

<YY_AFTER_CLASS_NAME> {
  [^\n]+                      { return MESSAGE; }

  [\n]                        { stack.clear();
                                pushStateAndBegin(YYINITIAL);
                                return TokenType.WHITE_SPACE; }
}

<YY_STACK_TRACE_LINE> {
  {STACK_TRACE_PREFIX}        { return TEXT; }
  {STACK_TRACE_ELEMENT}       { popState();
                                return CODE_REFERENCE; }
}

<YY_TEXT_UNTIL_END_OF_LINE> {
  {STACK_TRACE_LINE}          { pushStateAndBegin(YY_STACK_TRACE_LINE);
                                yypushback(yylength()); }

  [^\n]+                      { return TEXT; }


  [\n]                        { popState();
                                return TEXT; }
}


[^]                           { stack.clear();
                                pushStateAndBegin(YY_TEXT_UNTIL_END_OF_LINE);
                                yypushback(yylength());}