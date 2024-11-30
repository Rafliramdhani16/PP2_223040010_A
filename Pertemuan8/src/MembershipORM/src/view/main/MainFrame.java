package MembershipORM.src.view.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import MembershipORM.src.view.member.MemberFrame;
import MembershipORM.src.view.jenismember.JenisMemberFrame;
import MembershipORM.src.dao.JenisMemberDao;
import MembershipORM.src.dao.MemberDao;

public class MainFrame extends JFrame {
    private JButton buttonJenisMember;
    private JButton buttonMember;
    private JenisMemberFrame jenisMemberFrame;
    private MemberFrame memberFrame;
    private JenisMemberDao jenisMemberDao;
    private MemberDao memberDao;

    public MainFrame(JenisMemberDao jenisMemberDao, MemberDao memberDao) {
        this.jenisMemberDao = jenisMemberDao;
        this.memberDao = memberDao;

        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        this.buttonJenisMember = new JButton("Jenis Member");
        this.buttonJenisMember.setBounds(50, 50, 150, 30);

        this.buttonMember = new JButton("Member");
        this.buttonMember.setBounds(50, 100, 150, 30);

        this.add(buttonJenisMember);
        this.add(buttonMember);

        this.buttonJenisMember.addActionListener(new MainButtonActionListener(this));
        this.buttonMember.addActionListener(new MainButtonActionListener(this));
    }

    public JButton getButtonJenisMember() {
        return buttonJenisMember;
    }

    public JButton getButtonMember() {
        return buttonMember;
    }

    public void showJenisMemberFrame() {
        if (jenisMemberFrame == null) {
            jenisMemberFrame = new JenisMemberFrame(jenisMemberDao);
        }
        jenisMemberFrame.setVisible(true);
    }

    public void showMemberFrame() {
        if (memberFrame == null) {
            memberFrame = new MemberFrame(memberDao, jenisMemberDao);
        }
        memberFrame.populateComboJenis();
        memberFrame.setVisible(true);
    }
}