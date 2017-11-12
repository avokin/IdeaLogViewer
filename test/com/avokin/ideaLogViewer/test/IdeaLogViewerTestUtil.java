package com.avokin.ideaLogViewer.test;

import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.util.io.FileUtil;

public class IdeaLogViewerTestUtil {
  public static String getTestsRoot() {
    return System.getProperty("user.dir") + "/test/com/avokin/ideaLogViewer/test/";
  }
}
