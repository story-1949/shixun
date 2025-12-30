import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DeviceManagementGUI extends JFrame {

    // 表格模型
    private DefaultTableModel tableModel;
    private JTable deviceTable;

    // 输入框
    private JTextField idField;
    private JTextField nameField;
    private JTextField typeField;

    public DeviceManagementGUI() {
        setTitle("设备管理系统");
        setSize(700, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        // ===== 表格 =====
        String[] columns = {"设备ID", "设备名称", "设备类型"};
        tableModel = new DefaultTableModel(columns, 0);
        deviceTable = new JTable(tableModel);

        // 表格样式优化
        deviceTable.setRowHeight(26);
        deviceTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        deviceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        deviceTable.setFillsViewportHeight(true);
        deviceTable.setAutoCreateRowSorter(true);
        deviceTable.setShowGrid(false);
        deviceTable.setIntercellSpacing(new Dimension(0, 0));

        // 交替行颜色
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 250));
                }
                return c;
            }
        };
        for (int i = 0; i < deviceTable.getColumnCount(); i++) {
            deviceTable.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        // 设置列宽(可根据需要调整)
        if (deviceTable.getColumnModel().getColumnCount() >= 3) {
            deviceTable.getColumnModel().getColumn(0).setPreferredWidth(120);
            deviceTable.getColumnModel().getColumn(1).setPreferredWidth(320);
            deviceTable.getColumnModel().getColumn(2).setPreferredWidth(160);
        }

        JScrollPane scrollPane = new JScrollPane(deviceTable);

        // ===== 输入面板（使用 GridBag 布局以获得更好对齐） =====
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(10, 12, 12, 12));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField();
        nameField = new JTextField();
        typeField = new JTextField();

        Font labelFont = new Font("SansSerif", Font.BOLD, 13);
        JLabel idLabel = new JLabel("设备ID:");
        idLabel.setFont(labelFont);
        JLabel nameLabel = new JLabel("设备名称:");
        nameLabel.setFont(labelFont);
        JLabel typeLabel = new JLabel("设备类型:");
        typeLabel.setFont(labelFont);

        // 第一行
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.0;
        inputPanel.add(idLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.5;
        inputPanel.add(idField, gbc);
        gbc.gridx = 2; gbc.gridy = 0; gbc.weightx = 0.0;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 0.5;
        inputPanel.add(nameField, gbc);

        // 第二行
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.0;
        inputPanel.add(typeLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2; gbc.weightx = 1.0;
        inputPanel.add(typeField, gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        buttonPanel.setOpaque(false);
        JButton addButton = new JButton("添加设备");
        addButton.setToolTipText("添加一条新设备记录");
        addButton.setMnemonic('A');
        JButton deleteButton = new JButton("删除设备");
        deleteButton.setToolTipText("删除选中的设备（单选）");
        deleteButton.setMnemonic('D');
        addButton.setFocusable(false);
        deleteButton.setFocusable(false);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        gbc.gridx = 3; gbc.gridy = 1; gbc.gridwidth = 1; gbc.weightx = 0.0;
        inputPanel.add(buttonPanel, gbc);

        // ===== 按钮事件 =====
        addButton.addActionListener(this::addDevice);
        deleteButton.addActionListener(e -> deleteDevice());

        // ===== 主布局 =====
        setLayout(new BorderLayout(10, 10));
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void addDevice(ActionEvent e) {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String type = typeField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请填写完整信息");
            return;
        }

        tableModel.addRow(new Object[]{id, name, type});

        // 清空输入框
        idField.setText("");
        nameField.setText("");
        typeField.setText("");
    }

    private void deleteDevice() {
        int selectedRow = deviceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的设备");
            return;
        }
        tableModel.removeRow(selectedRow);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DeviceManagementGUI().setVisible(true);
        });
    }
}
