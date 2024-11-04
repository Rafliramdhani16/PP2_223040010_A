package Membership.src.view.member;

import javax.swing.*;
import java.util.*;
import Membership.src.model.*;
import Membership.src.dao.MemberDao;
import Membership.src.dao.JenisMemberDao;
import Membership.src.view.member.MemberTableModel;
import Membership.src.view.member.MemberButtonSimpanActionListener;

public class MemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private List<Member> memberList;
    private JTextField textFieldNama;
    private MemberTableModel tableModel;
    private JComboBox<String> comboJenis;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;
    private JButton buttonSimpan;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private Member selectedMember;
    private JTable table;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Member Management");
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;
        this.memberList = this.memberDao.findAll();
        this.jenisMemberList = this.jenisMemberDao.findAll();

        initializeComponents();
        initializeButtons();
        setupLayout();
        setupTable();
        setupListeners();
    }

    private void initializeComponents() {
        // Labels
        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 10);

        JLabel labelJenis = new JLabel("Jenis Member:");
        labelJenis.setBounds(15, 100, 350, 10);

        // Text Field
        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        // Combo Box
        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 120, 150, 30);

        this.add(labelInput);
        this.add(textFieldNama);
        this.add(labelJenis);
        this.add(comboJenis);
    }

    private void initializeButtons() {
        // Save Button
        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 160, 100, 40);

        // Update Button
        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(125, 160, 100, 40);

        // Delete Button
        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(235, 160, 100, 40);

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
        scrollableTable.setBounds(15, 220, 350, 200);
        this.add(scrollableTable);

        tableModel = new MemberTableModel(memberList);
        table.setModel(tableModel);
    }

    private void setupListeners() {
        // Save Button Listener
        MemberButtonSimpanActionListener saveActionListener = new MemberButtonSimpanActionListener(this, memberDao);
        buttonSimpan.addActionListener(saveActionListener);

        // Update Button Listener
        buttonUpdate.addActionListener(e -> handleUpdate());

        // Delete Button Listener
        buttonDelete.addActionListener(e -> handleDelete());

        // Table Selection Listener
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    selectedMember = tableModel.getMemberAt(selectedRow);
                    textFieldNama.setText(selectedMember.getNama());
                    comboJenis.setSelectedItem(selectedMember.getJenisMember().getNama());
                }
            }
        });
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
    }

    private void handleUpdate() {
        if (selectedMember == null) {
            showAlert("Please select a member to update");
            return;
        }

        String nama = getNama();
        if (nama.isEmpty()) {
            showAlert("Name cannot be empty");
            return;
        }

        selectedMember.setNama(nama);
        selectedMember.setJenisMember(getJenisMember());
        memberDao.update(selectedMember);
        refreshTable();
        clearForm();
    }

    private void handleDelete() {
        if (selectedMember == null) {
            showAlert("Please select a member to delete");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this member?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            memberDao.delete(selectedMember);
            refreshTable();
            clearForm();
        }
    }

    public void refreshTable() {
        memberList = memberDao.findAll();
        tableModel.setData(memberList);
        tableModel.fireTableDataChanged();
    }

    private void clearForm() {
        textFieldNama.setText("");
        selectedMember = null;
        table.clearSelection();
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public JenisMember getJenisMember() {
        return jenisMemberList.get(comboJenis.getSelectedIndex());
    }

    public void addMember(Member member) {
        tableModel.add(member);
        textFieldNama.setText("");
    }

    public void showAlert(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}