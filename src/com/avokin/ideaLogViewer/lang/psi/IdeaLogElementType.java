package com.avokin.ideaLogViewer.lang.psi;

import com.avokin.ideaLogViewer.lang.IdeaLogLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

class IdeaLogElementType extends IElementType {
  IdeaLogElementType(@NotNull String debugName) {
    super(debugName, IdeaLogLanguage.INSTANCE);
  }
}
