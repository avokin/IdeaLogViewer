package com.avokin.ideaLogViewer.lang.psi;

import com.intellij.icons.AllIcons;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IdeaLogLoadedPluginsRecord extends IdeaLogRecord {
  public IdeaLogLoadedPluginsRecord(@NotNull ASTNode node) {
    super(node);
  }

  @Nullable
  @Override
  public Icon getIcon(int flags) {
    return AllIcons.Nodes.Plugin;
  }
}
