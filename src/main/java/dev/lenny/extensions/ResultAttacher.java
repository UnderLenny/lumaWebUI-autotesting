package dev.lenny.extensions;

import dev.lenny.helpers.ScreenshotUtils;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ResultAttacher implements AfterTestExecutionCallback {

    public void afterTestExecution(ExtensionContext context) throws Exception {
        Boolean isTestFailed = context.getExecutionException().isPresent();

        ScreenshotUtils.takeScreenshot();

        if (isTestFailed) {
            ScreenshotUtils.attachDomTree();
        }
    }
}
