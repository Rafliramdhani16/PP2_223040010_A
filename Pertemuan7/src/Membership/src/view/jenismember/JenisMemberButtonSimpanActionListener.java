package Membership.src.view.jenismember;

import java.awt.event.*;
import java.util.UUID;
import Membership.src.model.JenisMember;
import Membership.src.dao.JenisMemberDao;

public class JenisMemberButtonSimpanActionListener implements ActionListener {
    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getNama();
        if (nama.isEmpty()) {
            this.jenisMemberFrame.showAlert("Nama tidak boleh kosong");
            return;
        }

        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);

        this.jenisMemberDao.insert(jenisMember);
        this.jenisMemberFrame.addJenisMember(jenisMember);
        this.jenisMemberFrame.refreshTable();
    }
}