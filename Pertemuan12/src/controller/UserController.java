package controller;

import model.User;
import model.UserMapper;
import view.UserView;
import view.UserPdf;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;
    private SqlSession session;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;
        this.session = session;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameInput();
            String email = view.getEmailInput();

            if (!name.isEmpty() && !email.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);

                mapper.insertUser(user);
                session.commit();
                JOptionPane.showMessageDialog(view, "User added successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Please fill in all fields.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAllUsers();
            String[] userArray = users.stream()
                    .map(u -> u.getName() + " (" + u.getEmail() + ")")
                    .toArray(String[]::new);

            view.setUserList(userArray);
        }
    }

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAllUsers();
            pdf.exportPdf(users);
        }
    }
}