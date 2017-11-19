package com.avokin.ideaLogViewer;

import org.jetbrains.annotations.NotNull;

public class IdeaLogStringUtil {
    public static String shorten(@NotNull String s, int maxLenght) {
        if (s.length() < maxLenght) {
            return s;
        }
        return s.substring(0, maxLenght);
    }
}
