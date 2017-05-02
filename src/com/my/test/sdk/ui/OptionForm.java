package com.my.test.sdk.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;

/**
 * Created by Administrator on 2017/5/2.
 */
public class OptionForm extends JFrame {

    private JPanel mPanel;
    private JButton mBtnCancel;
    private JButton mBtnOk;
    private JTextField textApplicationName;
    private JTextField textLimitTargetSdkVersion;
    private JTextField textReplaceScreenOrientation;
    private JComboBox comboBoxLaunchMode;
    private JTextArea mainActivityAddIntentFilter;
    private JList list1;
    private JRadioButton radioButtonTrue;
    private JRadioButton radioButtonFalse;

    public static OptionForm show(Project project) {
        OptionForm optionForm = new OptionForm(project);
        optionForm.setSize(450, 450);
        optionForm.setLocationRelativeTo(null);
        optionForm.setAlwaysOnTop(true);
        optionForm.setVisible(true);
        return optionForm;
    }

    OptionForm(Project project) {
        setContentPane(mPanel);
    }
}
