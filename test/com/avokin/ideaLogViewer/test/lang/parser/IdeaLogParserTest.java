package com.avokin.ideaLogViewer.test.lang.parser;

import com.avokin.ideaLogViewer.lang.psi.IdeaLogFileType;
import com.avokin.ideaLogViewer.test.IdeaLogViewerTestUtil;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.DebugUtil;
import com.intellij.testFramework.LightPlatformCodeInsightTestCase;
import com.intellij.util.LocalTimeCounter;
import org.jetbrains.annotations.NonNls;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;

public class IdeaLogParserTest extends LightPlatformCodeInsightTestCase {
    @NonNls
    private static final String DATA_PATH = IdeaLogViewerTestUtil.getTestsRoot() + "lang/parser/testData/";

    public void testIdeStart() throws IOException {
        doTest();
    }

    private void doTest() throws IOException {
        String log = loadFile(new File(DATA_PATH + getTestName(false) + ".log"));

        final PsiFile psiFile = PsiFileFactory.getInstance(getProject())
                .createFileFromText("test." + IdeaLogFileType.INSTANCE.getDefaultExtension(),
                        IdeaLogFileType.INSTANCE, log, LocalTimeCounter.currentTime(),
                        true);

        Assert.assertEquals(log, psiFile.getText());
        String actual = DebugUtil.psiToString(psiFile, false, false);

        String expected = loadFile(new File(DATA_PATH + getTestName(false) + ".txt"));
        Assert.assertEquals(expected, actual);
    }

    private static String loadFile(File testFile) throws IOException {
        return FileUtil.loadFile(testFile, CharsetToolkit.UTF8_CHARSET);
    }
}
