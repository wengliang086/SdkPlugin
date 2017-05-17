package com.my.test.sdk.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.my.test.sdk.model.Options;
import com.my.test.sdk.util.OptionsHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private JComboBox comboBoxLaunchMode;
    private JTextArea mainActivityAddIntentFilter;
    private JRadioButton radioButtonTrue;
    private JRadioButton radioButtonFalse;
    private JButton btnMkdirAssets;
    private JButton btnMkdirRes;
    private JTable tableCustomMap;
    private JTable tableIntentFilter;
    private JButton addIntentFilterButton;
    private JButton removeIntentFilterButton;
    private JButton addCustomMapButton;
    private JButton removeCustomMapButton;

    public static OptionForm show(Project project) {
        OptionForm optionForm = new OptionForm(project);
        optionForm.setSize(620, 500);
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
        addIntentFilterButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tableIntentFilter.getModel();
                model.addRow(new Object[]{"action", "android:name", ""});
            }
        });
        removeIntentFilterButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tableIntentFilter.getModel();
                if (model.getRowCount() > 0) {
                    if (tableIntentFilter.getSelectedRow() == -1) {
                        model.removeRow(model.getRowCount() - 1);
                    } else {
                        model.removeRow(tableIntentFilter.getSelectedRow());
                    }
                }
            }
        });
        addCustomMapButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tableCustomMap.getModel();
                model.addRow(new String[]{"", ""});
            }
        });
        removeCustomMapButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tableCustomMap.getModel();
                if (model.getRowCount() > 0) {
                    if (tableCustomMap.getSelectedRow() == -1) {
                        model.removeRow(model.getRowCount() - 1);
                    } else {
                        model.removeRow(tableCustomMap.getSelectedRow());
                    }
                }
            }
        });
        /**
         * MainActivityIntentFilter 控件处理
         */
        DefaultTableModel model = (DefaultTableModel) tableIntentFilter.getModel();
        tableIntentFilter.setRowHeight(tableIntentFilter.getRowHeight() + 6);
        model.setColumnIdentifiers(new String[]{"TagName", "AttributeName", "value"});
        TableColumnModel mmm = tableIntentFilter.getColumnModel();
        TableColumn tc0 = mmm.getColumn(0);
        tc0.setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                ComboBox cb0 = new ComboBox(new String[]{"action", "category"});
                cb0.setSelectedItem(value);
                return cb0;
            }
        });
        tc0.setCellEditor(new DefaultCellEditor(new ComboBox(new String[]{"action", "category"})));
        TableColumn tc1 = mmm.getColumn(1);
        tc1.setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                ComboBox cb1 = new ComboBox(new String[]{"android:name"});
                cb1.setSelectedItem(value);
                return cb1;
            }
        });
        tc1.setCellEditor(new DefaultCellEditor(new ComboBox(new String[]{"android:name"})));
        /**
         * 自定义替换 控件处理
         */
        DefaultTableModel tableModel = (DefaultTableModel) tableCustomMap.getModel();
        tableModel.setColumnIdentifiers(new String[]{"要替换的值", "替换后的值"});

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
            comboBoxLimitTargetSdkVersion.setSelectedItem(options.getLimitTargetSdkVersion() > 0 ? options.getLimitTargetSdkVersion() : "不限制");
            mainActivityAddIntentFilter.setText(options.getMainActivityAddIntentFilter());
            comboBoxLaunchMode.setSelectedItem(options.getLaunchMode());
            radioButtonTrue.setSelected(options.isReplaceScreenOrientation());
            List<String[]> filters = options.getReplaceMainActivityIntentFilter();
            if (filters != null) {
                for (String[] array : filters) {
                    model.addRow(array);
//                    model.addRow(new Object[]{"category", "android:name", "aa"});
//                    model.addRow(new Object[]{"action", "android:name", "bb"});
                }
            }
            Map<String, String> map = options.getCustomReplaceMap();
            if (map != null) {
                for (Map.Entry<String, String> e : map.entrySet()) {
                    tableModel.addRow(new String[]{e.getKey(), e.getValue()});
                }
            }
        }
    }

    private void onOk() {
        dispose();

        String applicationName = textApplicationName.getText();
        int limitTargetSdkVersion = 0;
        try {
            limitTargetSdkVersion = (int) comboBoxLimitTargetSdkVersion.getSelectedItem();
        } catch (Exception e) {
        }
        String mainActivityAddIntentFilterStr = mainActivityAddIntentFilter.getText();
        String launchMode = (String) comboBoxLaunchMode.getSelectedItem();

        Options options = new Options();
        options.setApplicationName(applicationName);
        options.setLimitTargetSdkVersion(limitTargetSdkVersion);
        options.setMainActivityAddIntentFilter(mainActivityAddIntentFilterStr);
        options.setLaunchMode(launchMode);
        options.setReplaceScreenOrientation(replaceScreenOrientation);

        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < tableIntentFilter.getRowCount(); i++) {
            String[] ss = new String[tableIntentFilter.getColumnCount()];
            for (int j = 0; j < tableIntentFilter.getColumnCount(); j++) {
                ss[j] = tableIntentFilter.getValueAt(i, j).toString();
            }
            list.add(ss);
        }
        options.setReplaceMainActivityIntentFilter(list);

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < tableCustomMap.getRowCount(); i++) {
            String key = tableCustomMap.getValueAt(i, 0).toString();
            String value = tableCustomMap.getValueAt(i, 1).toString();
            map.put(key, value);
        }
        options.setCustomReplaceMap(map);

        OptionsHelper.save(mProject, options);
    }

    private boolean replaceScreenOrientation = false;

    private class RadioListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            replaceScreenOrientation = e.getActionCommand().equals("true");
        }
    }

}
