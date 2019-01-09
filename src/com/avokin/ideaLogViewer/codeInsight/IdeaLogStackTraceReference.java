package com.avokin.ideaLogViewer.codeInsight;

import com.avokin.ideaLogViewer.lang.psi.IdeaLogStackTraceElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.LineTokenizer;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IdeaLogStackTraceReference implements PsiReference {
  private final IdeaLogStackTraceElement myElement;

  public IdeaLogStackTraceReference(IdeaLogStackTraceElement element) {
    myElement = element;
  }

  @NotNull
  @Override
  public PsiElement getElement() {
    return myElement;
  }

  @NotNull
  @Override
  public TextRange getRangeInElement() {
    int start = myElement.getText().indexOf('(');
    int end = myElement.getText().indexOf(')');
    if (start > 0 && end > 0 && start + 1 < end) {
      return new TextRange(start + 1, end);
    }
    return new TextRange(0, myElement.getTextLength());
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    String className = myElement.getClassName();
    if (className == null) {
      return null;
    }
    JavaPsiFacade psiFacade = JavaPsiFacade.getInstance(myElement.getProject());
    PsiClass[] resultClasses = psiFacade.findClasses(className, GlobalSearchScope.projectScope(myElement.getProject()));
    if (resultClasses.length == 0) {
      resultClasses = psiFacade.findClasses(className, GlobalSearchScope.allScope(myElement.getProject()));
    }
    PsiClass psiClass = resultClasses.length != 0 ? resultClasses[0] : null;
    if (psiClass == null) {
      return null;
    }
    int line = myElement.getLineNumber();
    if (line >= 0) {
      LineTokenizer lineTokenizer = new LineTokenizer(psiClass.getContainingFile().getText());
      for (int i = 0; i < line - 1; i++) {
        lineTokenizer.advance();
      }

      PsiElement element = psiClass.getContainingFile().findElementAt(lineTokenizer.getOffset());
      if (element instanceof PsiWhiteSpace && element.getNextSibling() != null) {
        return element.getNextSibling();
      }
      return element;
    }

    return psiClass;
  }

  @NotNull
  @Override
  public String getCanonicalText() {
    return myElement.getText();
  }

  @Override
  public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
    return null;
  }

  @Override
  public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
    return null;
  }

  @Override
  public boolean isReferenceTo(@NotNull PsiElement element) {
    return resolve() == element;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    return ArrayUtil.EMPTY_OBJECT_ARRAY;
  }

  @Override
  public boolean isSoft() {
    return false;
  }
}
