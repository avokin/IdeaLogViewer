package com.avokin.ideaLogViewer.lang.parser;

import com.avokin.ideaLogViewer.lang.lexer.IdeaLogLexer;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogElementTypes;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogFile;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class IdeaLogParserDefinition implements ParserDefinition {
  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return new IdeaLogLexer();
  }

  public PsiParser createParser(Project project) {
    return new IdeaLogParser();
  }

  @Override
  public IFileElementType getFileNodeType() {
    return IdeaLogElementTypes.IDEA_LOG_FILE;
  }

  @NotNull
  @Override
  public TokenSet getCommentTokens() {
    return TokenSet.EMPTY;
  }

  @NotNull
  @Override
  public TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @NotNull
  @Override
  public PsiElement createElement(ASTNode node) {
    return new ASTWrapperPsiElement(node);
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new IdeaLogFile(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }
}
