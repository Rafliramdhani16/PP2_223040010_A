package MembershipORM.src.view.jenismember;

import MembershipORM.src.dao.JenisMemberDao;
import java.util.List;
import javax.swing.*;
import java.awt.event.*;
import MembershipORM.src.model.JenisMember;

public class JenisMemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;
    private JTable table;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton buttonSimpan;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 350, 30);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(15, 60, 350, 30);

        buttonSimpan = new JButton("Simpan");
        buttonSimpan.setBounds(15, 100, 100, 40);

        buttonUpdate = new JButton("Update");
        buttonUpdate.setBounds(125, 100, 100, 40);

        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(235, 100, 100, 40);

        table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 200);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        // Action Listeners
        buttonSimpan.addActionListener(new JenisMemberButtonSimpanActionListener(this, jenisMemberDao));

        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    JenisMember selectedMember = jenisMemberList.get(selectedRow);
                    selectedMember.setNama(textFieldNama.getText());
                    jenisMemberDao.update(selectedMember);
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris yang akan diupdate");
                }
            }
        });

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
                        JenisMember selectedMember = jenisMemberList.get(selectedRow);
                        jenisMemberDao.delete(selectedMember);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris yang akan dihapus");
                }
            }
        });

        // Mouse listener for table selection
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    JenisMember selectedMember = jenisMemberList.get(selectedRow);
                    textFieldNama.setText(selectedMember.getNama());
                }
            }
        });

        this.add(buttonSimpan);
        this.add(buttonUpdate);
        this.add(buttonDelete);
        this.add(labelInput);
        this.add(textFieldNama);
        this.add(scrollableTable);
        this.setLayout(null);
        this.setBounds(100, 100, 400, 400);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void addJenisMember(JenisMember jenisMember) {
        jenisMemberList.add(jenisMember);
        tableModel.setJenisMemberList(jenisMemberList);
    }

    private void refreshTable() {
        jenisMemberList = jenisMemberDao.findAll();
        tableModel.setJenisMemberList(jenisMemberList);
        tableModel.fireTableDataChanged();
        textFieldNama.setText("");
    }
}
