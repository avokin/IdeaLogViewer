package com.avokin.ideaLogViewer;

import com.intellij.navigation.ItemPresentation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IdeaLogItemPresentation implements ItemPresentation {
  private final String myText;
  private final Icon myIcon;

  public IdeaLogItemPresentation(@NotNull String text, @Nullable Icon icon) {
    myText = text;
    myIcon = icon;
  }

  @Nullable
  @Override
  public String getPresentableText() {
    return myText;
  }

  @Nullable
  @Override
  public String getLocationString() {
    return null;
  }

  @Nullable
  @Override
  public Icon getIcon(boolean unused) {
    return myIcon;
  }
}
