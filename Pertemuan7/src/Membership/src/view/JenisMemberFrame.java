package Membership.src.view;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import Membership.src.model.JenisMember;
import Membership.src.dao.JenisMemberDao;

public class JenisMemberFrame extends JFrame {
    private List<JenisMember> jenisMemberList;
    private JTextField textFieldNama;
    private JenisMemberTableModel tableModel;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberFrame(JenisMemberDao jenisMemberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.jenisMemberList = jenisMemberDao.findAll();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel labelInput = new JLabel("Nama:");
        labelInput.setBounds(15, 40, 50, 10);
        this.add(labelInput);

        textFieldNama = new JTextField();
        textFieldNama.setBounds(65, 35, 230, 30);
        this.add(textFieldNama);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 100, 280, 40);
        this.add(button);

        JTable table = new JTable();
        JScrollPane scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 150, 350, 280);
        this.add(scrollableTable);

        tableModel = new JenisMemberTableModel(jenisMemberList);
        table.setModel(tableModel);

        JenisMemberButtonSimpanActionListener actionListener = new JenisMemberButtonSimpanActionListener(this, jenisMemberDao);
        button.addActionListener(actionListener);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public String getNama() {
        return textFieldNama.getText();
    }

    public void addJenisMember(JenisMember jenisMember) {
        tableModel.add(jenisMember);
        textFieldNama.setText("");
    }
}