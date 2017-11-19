package com.avokin.ideaLogViewer.lang.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import static com.avokin.ideaLogViewer.lang.psi.IdeaLogElementTypes.*;
import static com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes.*;

public class IdeaLogParser implements PsiParser {
    private static final String IDE_STARTED_MESSAGE = "------------------------------------------------------ IDE STARTED ------------------------------------------------------ ";
    private static final String LOADED_PLUGINS_PREFIX = "Loaded bundled plugins:";

    @NotNull
    @Override
    public ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        PsiBuilder.Marker markerWrapper = builder.mark();
        PsiBuilder.Marker marker = builder.mark();
        PsiBuilder.Marker currentLaunch = builder.mark();
        while (builder.getTokenType() != null) {
            if (builder.getTokenType() == TIME_STAMP) {
                if (parseLogRecord(builder, true)) {
                    if (builder.getCurrentOffset() == 0) {
                        currentLaunch.drop();
                    } else {
                        currentLaunch.done(LAUNCH);
                    }
                    currentLaunch = builder.mark();
                    parseLogRecord(builder, false);
                }
            } else {
                builder.advanceLexer();
            }
        }
        currentLaunch.done(LAUNCH);
        marker.done(root);
        markerWrapper.done(root);
        return builder.getTreeBuilt();
    }

    private static boolean parseLogRecord(@NotNull PsiBuilder builder, boolean exitIfNewLaunch) {
        PsiBuilder.Marker marker = builder.mark();

        IElementType recordElementType = LOG_RECORD;
        PsiBuilder.Marker logRecordMarker = builder.mark();
        builder.advanceLexer();
        while (builder.getTokenType() != null && builder.getTokenType() != TIME_STAMP) {
            String tokenText = builder.getTokenText();
            if (builder.getTokenType() == MESSAGE && tokenText != null) {
                if (IDE_STARTED_MESSAGE.equals(tokenText)) {
                    if (exitIfNewLaunch) {
                        marker.rollbackTo();
                        return true;
                    }
                    recordElementType = IDE_STARTED_RECORD;
                }
                if (tokenText.startsWith(LOADED_PLUGINS_PREFIX)) {
                    recordElementType = LOADED_PLUGINS_RECORD;
                }
            }
            if (builder.getTokenType() == CODE_REFERENCE) {
                PsiBuilder.Marker codeReferenceMarker = builder.mark();
                builder.advanceLexer();
                codeReferenceMarker.done(CODE_REFERENCE_ELEMENT);
            } else {
                builder.advanceLexer();
            }
        }
        logRecordMarker.done(recordElementType);
        marker.drop();
        return false;
    }
}
