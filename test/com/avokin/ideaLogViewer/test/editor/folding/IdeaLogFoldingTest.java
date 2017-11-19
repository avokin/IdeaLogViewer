package com.avokin.ideaLogViewer.test.editor.folding;

import com.avokin.ideaLogViewer.test.IdeaLogViewerTestUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

public class IdeaLogFoldingTest extends LightCodeInsightFixtureTestCase {
    private static final String DATA_PATH = IdeaLogViewerTestUtil.getTestsRoot() + "editor/folding/testData/";

    public void testInfos() {
        doTest();
    }

    private void doTest() {
        myFixture.testFolding(DATA_PATH  + getTestName(true) + ".log");
    }
}
