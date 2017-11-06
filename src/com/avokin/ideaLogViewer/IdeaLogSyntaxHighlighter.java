package com.avokin.ideaLogViewer;

import com.avokin.ideaLogViewer.lang.lexer.IdeaLogLexer;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
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
        ATTRIBUTES.put(LOG_LEVEL_INFO, ConsoleViewContentType.SYSTEM_OUTPUT_KEY);
        ATTRIBUTES.put(LOG_LEVEL_WARN, ConsoleViewContentType.LOG_WARNING_OUTPUT_KEY);
        ATTRIBUTES.put(LOG_LEVEL_ERROR, ConsoleViewContentType.LOG_ERROR_OUTPUT_KEY);
        ATTRIBUTES.put(TIME_STAMP, DefaultLanguageHighlighterColors.NUMBER);
        ATTRIBUTES.put(TEXT, ConsoleViewContentType.NORMAL_OUTPUT_KEY);
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
