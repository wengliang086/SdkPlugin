package com.my.test.sdk;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.my.test.sdk.ui.OptionForm;
import com.my.test.sdk.ui.TestDialog;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        OptionForm.show(project);
    }
}
