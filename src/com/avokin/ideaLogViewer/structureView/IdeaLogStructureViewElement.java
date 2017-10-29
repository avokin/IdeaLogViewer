package com.avokin.ideaLogViewer.structureView;

import com.avokin.ideaLogViewer.IdeaLogItemPresentation;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogRecord;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import org.jetbrains.annotations.NotNull;

public class IdeaLogStructureViewElement implements StructureViewTreeElement {
    private IdeaLogRecord myElement;
    private String myPresentableText;

    IdeaLogStructureViewElement(@NotNull IdeaLogRecord ideaLogRecord, @NotNull String text) {
        myElement = ideaLogRecord;
        myPresentableText = text;
    }

    @Override
    public Object getValue() {
        return myElement;
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        return new IdeaLogItemPresentation(myPresentableText, myElement.getIcon(0));
    }

    @NotNull
    @Override
    public TreeElement[] getChildren() {
        return TreeElement.EMPTY_ARRAY;
    }

    @Override
    public void navigate(boolean requestFocus) {
        new OpenFileDescriptor(myElement.getProject(), myElement.getContainingFile().getVirtualFile(), myElement.getTextOffset()).navigate(requestFocus);
    }

    @Override
    public boolean canNavigate() {
        return true;
    }

    @Override
    public boolean canNavigateToSource() {
        return true;
    }
}
