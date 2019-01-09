package com.avokin.ideaLogViewer;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class IdeaLogAnnotator implements Annotator {
  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    new IdeaLogAnnotatorVisitor(element.getProject(), holder).visitElement(element);
  }
}
