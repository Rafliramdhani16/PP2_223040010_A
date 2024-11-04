package Membership.src.view.main;

import java.awt.FlowLayout;
import javax.swing.*;

import Membership.src.view.member.MemberFrame;
import Membership.src.view.jenismember.JenisMemberFrame;
import Membership.src.dao.*;

public class MainFrame extends JFrame {
    private JenisMemberFrame jenisMemberFrame;
    private MemberFrame memberFrame;
    private JButton buttonJenisMember;
    private JButton buttonMember;
    private JenisMemberDao jenisMemberDao;
    private MemberDao memberDao;

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setTitle("Membership Management System");
        this.setLocationRelativeTo(null); // Center the window

        initializeDao();
        initializeComponents();
        layoutComponents();
    }

    private void initializeDao() {
        this.jenisMemberDao = new JenisMemberDao();
        this.memberDao = new MemberDao();
    }

    private void initializeComponents() {
        MainButtonActionListener actionListener = new MainButtonActionListener(this);

        this.buttonJenisMember = new JButton("Jenis Member");
        this.buttonMember = new JButton("Member");

        this.buttonJenisMember.addActionListener(actionListener);
        this.buttonMember.addActionListener(actionListener);
    }

    private void layoutComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        mainPanel.add(buttonJenisMember);
        mainPanel.add(buttonMember);

        this.add(mainPanel);
    }

    // Tambahkan getter untuk button
    public JButton getButtonJenisMember() {
        return buttonJenisMember;
    }

    public JButton getButtonMember() {
        return buttonMember;
    }

    public void showJenisMemberFrame() {
        if (jenisMemberFrame == null || !jenisMemberFrame.isDisplayable()) {
            jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        }
        jenisMemberFrame.setVisible(true);
        jenisMemberFrame.refreshTable();
    }

    public void showMemberFrame() {
        if (memberFrame == null || !memberFrame.isDisplayable()) {
            memberFrame = new MemberFrame(memberDao, jenisMemberDao);
        }
        memberFrame.populateComboJenis();
        memberFrame.refreshTable();
        memberFrame.setVisible(true);
    }  

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            MainFrame f = new MainFrame();
            f.setVisible(true);
        });
    }
}