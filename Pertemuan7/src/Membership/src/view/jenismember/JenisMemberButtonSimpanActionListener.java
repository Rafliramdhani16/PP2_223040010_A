package Membership.src.view.jenismember;

import java.awt.event.*;
import java.util.UUID;
import Membership.src.model.JenisMember;
import Membership.src.dao.JenisMemberDao;

public class JenisMemberButtonSimpanActionListener implements ActionListener {
    private JenisMemberFrame jeniMemberFrame;
    private JenisMemberDao jeniMemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jeniMemberFrame, JenisMemberDao jeniMemberDao) {
        this.jeniMemberFrame = jeniMemberFrame;
        this.jeniMemberDao = jeniMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jeniMemberFrame.getNama();
        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);

        this.jeniMemberFrame.addJenisMember(jenisMember);
        this.jeniMemberDao.insert(jenisMember);
    }
}
