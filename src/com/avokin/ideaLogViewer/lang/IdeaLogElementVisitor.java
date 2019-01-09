package com.avokin.ideaLogViewer.lang;

import com.avokin.ideaLogViewer.lang.psi.IdeaLogRecord;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogStackTraceElement;
import com.intellij.psi.PsiElementVisitor;

public class IdeaLogElementVisitor extends PsiElementVisitor {
  public void visitIdeaLogRecord(IdeaLogRecord record) {
    visitElement(record);
  }

  public void visitStackTraceElement(IdeaLogStackTraceElement ideaLogStackTraceElement) {
    visitElement(ideaLogStackTraceElement);
  }
}
