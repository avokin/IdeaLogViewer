package com.avokin.ideaLogViewer.structureView;

import com.avokin.ideaLogViewer.lang.psi.IdeaLogRecord;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.NodeDescriptorProvidingKey;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IdeaLogStructureViewElement implements StructureViewTreeElement, ItemPresentation, NodeDescriptorProvidingKey {
    private IdeaLogRecord myElement;

    IdeaLogStructureViewElement(IdeaLogRecord ideaLogRecord) {
        myElement = ideaLogRecord;
    }

    @Override
    public Object getValue() {
        return myElement;
    }

    @NotNull
    @Override
    public Object getKey() {
        return getPresentableText();
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        return this;
    }

    @NotNull
    @Override
    public TreeElement[] getChildren() {
        return new TreeElement[0];
    }

    @NotNull
    @Override
    public String getPresentableText() {
        return "IDE start: " + myElement.getText();
    }

    @Nullable
    @Override
    public String getLocationString() {
        return null;
    }

    @Nullable
    @Override
    public Icon getIcon(boolean b) {
        return null;
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
