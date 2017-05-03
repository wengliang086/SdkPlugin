package com.my.test.sdk.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.my.test.sdk.model.Options;
import com.my.test.sdk.util.OptionsHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Administrator on 2017/5/2.
 */
public class OptionForm extends JFrame {

    private Project mProject;

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
        optionForm.setSize(600, 450);
        optionForm.setLocationRelativeTo(null);
        optionForm.setAlwaysOnTop(true);
        optionForm.setVisible(true);
        return optionForm;
    }

    OptionForm(Project project) {
        mProject = project;
        setContentPane(mPanel);
        setTitle("SDK 打包配置");

        mBtnOk.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOk();
            }
        });
        mBtnCancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // 填充组建值
        Options options = OptionsHelper.load(project);
        if (options != null) {
            textApplicationName.setText(options.getApplicationName());
            textLimitTargetSdkVersion.setText(options.getLimitTargetSdkVersion());
            textReplaceScreenOrientation.setText(options.getReplaceScreenOrientation());
            comboBoxLaunchMode.setToolTipText(options.getLaunchMode());
        }
    }

    private void onOk() {
        dispose();

        String applicationName = textApplicationName.getText();
        String limitTargetSdkVersion = textLimitTargetSdkVersion.getText();
        String replaceScreenOrientation = textReplaceScreenOrientation.getText();
        String launchMode = comboBoxLaunchMode.getToolTipText();
//        String mainActivityAddIntentFilter = mainActivityAddIntentFilter.ge

        Options options = new Options();
        options.setApplicationName(applicationName);
        options.setLimitTargetSdkVersion(limitTargetSdkVersion);
        options.setReplaceScreenOrientation(replaceScreenOrientation);
        options.setLaunchMode(launchMode);

        OptionsHelper.save(mProject, options);
    }
}
