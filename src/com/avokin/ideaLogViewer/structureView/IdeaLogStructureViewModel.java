package com.avokin.ideaLogViewer.structureView;

import com.avokin.ideaLogViewer.lang.psi.IdeaLogRecord;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.structureView.TextEditorBasedStructureViewModel;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class IdeaLogStructureViewModel extends TextEditorBasedStructureViewModel implements StructureViewModel.ElementInfoProvider {
    private PsiFile myIdeaLogFile;

    IdeaLogStructureViewModel(Editor editor, PsiFile file) {
        super(editor, file);
        myIdeaLogFile = file;
    }

    @Override
    public boolean isAlwaysShowsPlus(StructureViewTreeElement structureViewTreeElement) {
        return false;
    }

    @Override
    public boolean isAlwaysLeaf(StructureViewTreeElement structureViewTreeElement) {
        return false;
    }

    @NotNull
    @Override
    public StructureViewTreeElement getRoot() {
        return new StructureViewTreeElement() {
            @Override
            public Object getValue() {
                return myIdeaLogFile;
            }

            @NotNull
            @Override
            public ItemPresentation getPresentation() {
                return new ItemPresentation() {
                    @NotNull
                    @Override
                    public String getPresentableText() {
                        return myIdeaLogFile.getName();
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
                };
            }

            @NotNull
            @Override
            public TreeElement[] getChildren() {
                List<TreeElement> result = new ArrayList<>();
                for (PsiElement logRecord: myIdeaLogFile.getFirstChild().getChildren()) {
                    if (logRecord instanceof IdeaLogRecord) {
                        result.add(new IdeaLogStructureViewElement((IdeaLogRecord) logRecord));
                    }
                }
                return result.toArray(new TreeElement[result.size()]);
            }

            @Override
            public void navigate(boolean b) {
            }

            @Override
            public boolean canNavigate() {
                return false;
            }

            @Override
            public boolean canNavigateToSource() {
                return false;
            }
        };
    }
}
