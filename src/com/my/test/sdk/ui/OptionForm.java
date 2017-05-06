package com.my.test.sdk.ui;

import com.intellij.openapi.project.Project;
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
    private JComboBox comboBoxLimitTargetSdkVersion;
    private JTextField textReplaceMainActivityIntentFilter;
    private JComboBox comboBoxLaunchMode;
    private JTextArea mainActivityAddIntentFilter;
    private JRadioButton radioButtonTrue;
    private JRadioButton radioButtonFalse;
    private JButton btnMkdirAssets;
    private JButton btnMkdirRes;

    public static OptionForm show(Project project) {
        OptionForm optionForm = new OptionForm(project);
        optionForm.setSize(600, 400);
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
        btnMkdirAssets.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsHelper.mkDir(project, "assets");
            }
        });
        btnMkdirRes.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OptionsHelper.mkDir(project, "res");
            }
        });
        RadioListener listener = new RadioListener();
        radioButtonTrue.setActionCommand("true");
        radioButtonFalse.setActionCommand("false");
        radioButtonTrue.addActionListener(listener);
        radioButtonFalse.addActionListener(listener);

        // 填充组建值
        Options options = OptionsHelper.load(project);
        radioButtonFalse.setSelected(true);
        if (options != null) {
            textApplicationName.setText(options.getApplicationName());
            comboBoxLimitTargetSdkVersion.setSelectedItem(options.getLimitTargetSdkVersion());
            textReplaceMainActivityIntentFilter.setText(options.getReplaceMainActivityIntentFilter());
            mainActivityAddIntentFilter.setText(options.getMainActivityAddIntentFilter());
            comboBoxLaunchMode.setSelectedItem(options.getLaunchMode());
            String lm = options.getReplaceScreenOrientation();
            if (lm != null && lm.equals("true")) {
                radioButtonTrue.setSelected(true);
            }
        }
    }

    private void onOk() {
        dispose();

        String applicationName = textApplicationName.getText();
        String limitTargetSdkVersion = (String) comboBoxLimitTargetSdkVersion.getSelectedItem();
        String replaceMainActivityIntentFilter = textReplaceMainActivityIntentFilter.getText();
        String mainActivityAddIntentFilterStr = mainActivityAddIntentFilter.getText();
        String launchMode = (String) comboBoxLaunchMode.getSelectedItem();

        Options options = new Options();
        options.setApplicationName(applicationName);
        options.setLimitTargetSdkVersion(limitTargetSdkVersion);
        options.setReplaceMainActivityIntentFilter(replaceMainActivityIntentFilter);
        options.setMainActivityAddIntentFilter(mainActivityAddIntentFilterStr);
        options.setLaunchMode(launchMode);
        options.setReplaceScreenOrientation(replaceScreenOrientation);

        OptionsHelper.save(mProject, options);
    }

    private String replaceScreenOrientation = "false";

    private class RadioListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            replaceScreenOrientation = e.getActionCommand();
        }
    }
}
