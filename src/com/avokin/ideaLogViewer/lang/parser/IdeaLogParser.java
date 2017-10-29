package com.avokin.ideaLogViewer.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.avokin.ideaLogViewer.lang.psi.IdeaLogElementTypes.IDE_STARTED_RECORD;
import static com.avokin.ideaLogViewer.lang.psi.IdeaLogElementTypes.LOG_RECORD;
import static com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes.MESSAGE;
import static com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes.TIME_STAMP;

public class IdeaLogParser implements PsiParser {
    private static final String IDE_STARTED_MESSAGE = "------------------------------------------------------ IDE STARTED ------------------------------------------------------";

    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        PsiBuilder.Marker markerWrapper = builder.mark();
        PsiBuilder.Marker marker = builder.mark();
        while (builder.getTokenType() != null) {
            if (builder.getTokenType() == TIME_STAMP) {
                parseLogRecord(builder);
            } else {
                builder.advanceLexer();
            }

        }
        marker.done(root);
        markerWrapper.done(root);
        return builder.getTreeBuilt();
    }

    private void parseLogRecord(@NotNull PsiBuilder builder) {
        boolean isIdeStartedRecord = false;
        PsiBuilder.Marker logRecordMarker = builder.mark();
        builder.advanceLexer();
        while (builder.getTokenType() != null && builder.getTokenType() != TIME_STAMP) {
            if (builder.getTokenType() == MESSAGE && IDE_STARTED_MESSAGE.equals(builder.getTokenText())) {
                isIdeStartedRecord = true;
            }
            builder.advanceLexer();
        }
        logRecordMarker.done(isIdeStartedRecord ? IDE_STARTED_RECORD : LOG_RECORD);
    }
}
