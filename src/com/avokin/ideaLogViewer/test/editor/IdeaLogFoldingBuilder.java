package com.avokin.ideaLogViewer.test.editor;

import com.avokin.ideaLogViewer.lang.psi.IdeaLogLaunch;
import com.avokin.ideaLogViewer.lang.psi.IdeaLogRecord;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.CustomFoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes.LOG_LEVEL_DEBUG;
import static com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes.LOG_LEVEL_INFO;

public class IdeaLogFoldingBuilder extends CustomFoldingBuilder implements DumbAware {
    @Override
    protected void buildLanguageFoldRegions(@NotNull List<FoldingDescriptor> list, @NotNull PsiElement psiElement, @NotNull Document document, boolean b) {
        psiElement = psiElement.getFirstChild();
        for (PsiElement launchCandidate : psiElement.getChildren()) {
            if (!(launchCandidate instanceof IdeaLogLaunch)) {
                continue;
            }

            int currentInfosOffsetStart = -1;
            int lastChildEndOffset = -1;
            for (PsiElement child : launchCandidate.getChildren()) {
                if (child instanceof IdeaLogRecord) {
                    IElementType logLevelToken = ((IdeaLogRecord) child).getLogLevelToken();
                    if (logLevelToken == LOG_LEVEL_DEBUG || logLevelToken == LOG_LEVEL_INFO) {
                        if (currentInfosOffsetStart < 0) {
                            currentInfosOffsetStart = child.getTextOffset();
                        }

                        lastChildEndOffset = child.getTextOffset() + child.getTextLength();
                        continue;
                    }
                }
                if (currentInfosOffsetStart >= 0) {
                    TextRange textRange = new TextRange(currentInfosOffsetStart, child.getTextOffset() - 1);
                    list.add(new FoldingDescriptor(launchCandidate.getNode(), textRange));
                    lastChildEndOffset = -1;
                    currentInfosOffsetStart = -1;
                }
            }
            if (currentInfosOffsetStart > 0 && lastChildEndOffset > 0) {
                TextRange textRange = new TextRange(currentInfosOffsetStart, lastChildEndOffset);
                list.add(new FoldingDescriptor(launchCandidate.getNode(), textRange));
            }
        }
    }

    @Override
    protected String getLanguagePlaceholderText(@NotNull ASTNode astNode, @NotNull TextRange textRange) {
        return "INFO...";
    }

    @Override
    protected boolean isRegionCollapsedByDefault(@NotNull ASTNode astNode) {
        return true;
    }
}
