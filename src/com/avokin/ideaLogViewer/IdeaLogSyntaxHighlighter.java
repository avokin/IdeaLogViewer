package com.avokin.ideaLogViewer;

import com.avokin.ideaLogViewer.lang.lexer.IdeaLogLexer;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.avokin.ideaLogViewer.lang.psi.IdeaLogViewerTokenTypes.*;

public class IdeaLogSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<>();

    static {
        ATTRIBUTES.put(LOG_LEVEL_DEBUG, DefaultLanguageHighlighterColors.LINE_COMMENT);
        ATTRIBUTES.put(LOG_LEVEL_INFO, DefaultLanguageHighlighterColors.METADATA);
        ATTRIBUTES.put(LOG_LEVEL_WARN, DefaultLanguageHighlighterColors.BRACKETS);
        ATTRIBUTES.put(LOG_LEVEL_ERROR, HighlighterColors.BAD_CHARACTER);
        ATTRIBUTES.put(TIME_STAMP, DefaultLanguageHighlighterColors.NUMBER);
        ATTRIBUTES.put(TEXT, DefaultLanguageHighlighterColors.STATIC_FIELD);
        ATTRIBUTES.put(MESSAGE, DefaultLanguageHighlighterColors.STRING);
        ATTRIBUTES.put(CLASS_NAME, DefaultLanguageHighlighterColors.CLASS_NAME);
        ATTRIBUTES.put(UPTIME, DefaultLanguageHighlighterColors.NUMBER);
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new IdeaLogLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }
}
