package com.avokin.ideaLogViewer.structureView;

import com.avokin.ideaLogViewer.IdeaLogItemPresentation;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogLaunch;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogLoadedPluginsRecord;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class IdeaLogStructureViewElement implements StructureViewTreeElement {
    private final PsiElement myElement;
    private final String myPresentableText;

    IdeaLogStructureViewElement(@NotNull PsiElement ideaLogRecord, @NotNull String text) {
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
        if (myElement instanceof IdeaLogLaunch) {
            List<TreeElement> result = new ArrayList<>();
            for (PsiElement element : myElement.getChildren()) {
                if (element instanceof IdeaLogLoadedPluginsRecord) {
                    String presentableText = "Loaded bundled plugins";
                    result.add(new IdeaLogStructureViewElement(element, presentableText));
                }
            }
            return result.toArray(TreeElement.EMPTY_ARRAY);
        }
        return TreeElement.EMPTY_ARRAY;
    }

    @Override
    public void navigate(boolean requestFocus) {
        new OpenFileDescriptor(myElement.getProject(), myElement.getContainingFile().getVirtualFile(),
                myElement.getTextOffset()).navigate(requestFocus);
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
