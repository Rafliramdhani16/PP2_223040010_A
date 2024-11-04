package Membership.src.view.jenismember;

import javax.swing.*;
import java.util.*;
import Membership.src.model.JenisMember;
import Membership.src.dao.JenisMemberDao;

public class JenisMemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;
    private JButton buttonSimpan;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JenisMember selectedJenisMember;
    private JTable table;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Jenis Member Management");
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();

        initializeComponents();
        initializeButtons();
        setupLayout();
        setupTable();
        setupListeners();
    }

    private void initializeComponents() {
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        this.add(labelInput);
        this.add(textFieldNama);
    }

    private void initializeButtons() {
        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 100, 100, 40);

        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(125, 100, 100, 40);

        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(235, 100, 100, 40);

        this.add(buttonSimpan);
        this.add(buttonUpdate);
        this.add(buttonDelete);
    }

    private void setupLayout() {
        this.setSize(400, 500);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }

    private void setupTable() {
        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 280);
        this.add(scrollableTable);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);
    }

    private void setupListeners() {
        buttonSimpan.addActionListener(new JenisMemberButtonSimpanActionListener(this, jenisMemberDao));
        buttonUpdate.addActionListener(e -> handleUpdate());
        buttonDelete.addActionListener(e -> handleDelete());

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedJenisMember = tableModel.getJenisMemberAt(selectedRow);
                    textFieldNama.setText(selectedJenisMember.getNama());
                }
            }
        });
    }

    private void handleUpdate() {
        if (selectedJenisMember == null) {
            showAlert("Please select a member type to update");
            return;
        }

        String nama = getNama();
        if (nama.isEmpty()) {
            showAlert("Name cannot be empty");
            return;
        }

        selectedJenisMember.setNama(nama);
        jenisMemberDao.update(selectedJenisMember);
        refreshTable();
        clearForm();
    }

    private void handleDelete() {
        if (selectedJenisMember == null) {
            showAlert("Please select a member type to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this member type?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            jenisMemberDao.delete(selectedJenisMember);
            refreshTable();
            clearForm();
        }
    }

    public void refreshTable() {
        jenisMemberList = jenisMemberDao.findAll();
        tableModel.setData(jenisMemberList);
        tableModel.fireTableDataChanged();
    }

    private void clearForm() {
        textFieldNama.setText("");
        selectedJenisMember = null;
        table.clearSelection();
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}