package com.avokin.ideaLogViewer;

import com.avokin.ideaLogViewer.lang.IdeaLogElementVisitor;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogStackTraceElement;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes;
import com.intellij.execution.filters.ExceptionFilter;
import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.impl.EditorHyperlinkSupport;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiUtilCore;
import org.jetbrains.annotations.NotNull;

public class IdeaLogAnnotatorVisitor extends IdeaLogElementVisitor {
  @NotNull
  private final ExceptionFilter myFilter;

  @NotNull
  private final AnnotationHolder myHolder;

  public IdeaLogAnnotatorVisitor(@NotNull Project project, @NotNull AnnotationHolder holder) {
    myFilter = new ExceptionFilter(GlobalSearchScope.projectScope(project));
    myHolder = holder;
  }

  @Override
  public void visitStackTraceElement(IdeaLogStackTraceElement ideaLogStackTraceElement) {
    super.visitStackTraceElement(ideaLogStackTraceElement);
  }

  @Override
  public void visitElement(PsiElement element) {
    if (PsiUtilCore.getElementType(element) != IdeaLogViewerTokenTypes.CODE_REFERENCE) {
      return;
    }
    Filter.Result result = myFilter.applyFilter(element.getText(), element.getTextLength());
    if (result == null) {
      return;
    }

    VirtualFile virtualFile = myHolder.getCurrentAnnotationSession().getFile().getVirtualFile();
    Project project = element.getProject();
    FileEditor[] editors = FileEditorManager.getInstance(project).getEditors(virtualFile);
    if (editors.length != 1) {
      return;
    }
    if (!(editors[0] instanceof TextEditor)) {
      return;
    }
    Editor editor = ((TextEditor) editors[0]).getEditor();
    EditorHyperlinkSupport hyperlinkSupport = new EditorHyperlinkSupport(editor, project);

    for (Filter.ResultItem item : result.getResultItems()) {
      TextAttributes textAttributes = item.getHighlightAttributes();
      HyperlinkInfo hyperlinkInfo = item.getHyperlinkInfo();
      int annotationStartOffset = element.getNode().getStartOffset() + item.getHighlightStartOffset();
      int annotationEndOffset = element.getNode().getStartOffset() + item.getHighlightEndOffset();
      if (hyperlinkInfo != null) {
        ApplicationManager.getApplication().invokeLater(() -> hyperlinkSupport
          .createHyperlink(annotationStartOffset, annotationEndOffset, textAttributes, hyperlinkInfo));
      }
    }

    super.visitElement(element);
  }
}
