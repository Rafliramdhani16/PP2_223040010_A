package MembershipORM.src.view.member;

import MembershipORM.src.dao.JenisMemberDao;
import MembershipORM.src.dao.MemberDao;
import java.awt.Label;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import MembershipORM.src.model.JenisMember;
import MembershipORM.src.model.Member;

public class MemberFrame extends JFrame {
    private JTextField textFieldNama;
    private JComboBox<String> comboJenis;
    private JButton button;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JTable table;
    private MemberTableModel tableModel;
    private List<JenisMember> jenisMemberList;
    private MemberDao memberDao;
    private JenisMemberDao jenisMemberDao;

    public MemberFrame(MemberDao memberDao, JenisMemberDao jenisMemberDao) {
        this.memberDao = memberDao;
        this.jenisMemberDao = jenisMemberDao;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.jenisMemberList = this.jenisMemberDao.findAll();
        this.tableModel = new MemberTableModel(this.memberDao.findAll());

        Label labelNama = new Label("Nama:");
        labelNama.setBounds(15, 10, 100, 30);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 50, 300, 30);

        Label labelJenis = new Label("Jenis Member:");
        labelJenis.setBounds(15, 90, 150, 30);

        comboJenis = new JComboBox<>();
        comboJenis.setBounds(15, 130, 300, 30);

        button = new JButton("Simpan");
        button.setBounds(15, 180, 100, 30);

        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(125, 180, 100, 30);

        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(235, 180, 100, 30);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(15, 250, 350, 200);

        // Action listener untuk tombol Simpan
        MemberButtonSimpanActionListener actionListener =
                new MemberButtonSimpanActionListener(this, memberDao);
        button.addActionListener(actionListener);

        // Action listener untuk tombol Update
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Member selectedMember = tableModel.getMemberAt(selectedRow);
                    selectedMember.setNama(textFieldNama.getText());
                    selectedMember.setJenisMember(getJenisMember());
                    selectedMember.setJenisMemberId(getJenisMember().getId());
                    memberDao.update(selectedMember);
                    refreshTable();
                } else {
                    showAlert("Pilih baris yang akan diupdate");
                }
            }
        });

        // Action listener untuk tombol Delete
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int option = JOptionPane.showConfirmDialog(null,
                            "Anda yakin ingin menghapus data ini?",
                            "Konfirmasi Hapus",
                            JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        Member selectedMember = tableModel.getMemberAt(selectedRow);
                        memberDao.delete(selectedMember);
                        refreshTable();
                    }
                } else {
                    showAlert("Pilih baris yang akan dihapus");
                }
            }
        });

        // Mouse listener untuk tabel
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    Member selectedMember = tableModel.getMemberAt(selectedRow);
                    textFieldNama.setText(selectedMember.getNama());
                    comboJenis.setSelectedItem(selectedMember.getJenisMember().getNama());
                }
            }
        });

        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelJenis);
        this.add(comboJenis);
        this.add(button);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(scrollPane);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public void populateComboJenis() {
        this.jenisMemberList = this.jenisMemberDao.findAll();
        comboJenis.removeAllItems();
        for (JenisMember jenisMember : this.jenisMemberList) {
            comboJenis.addItem(jenisMember.getNama());
        }
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

    private void refreshTable() {
        tableModel = new MemberTableModel(memberDao.findAll());
        table.setModel(tableModel);
        textFieldNama.setText("");
    }
}