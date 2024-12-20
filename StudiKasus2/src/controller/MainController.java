package controller;

import model.StudentMapper;
import view.*;
import org.apache.ibatis.session.SqlSession;

public class MainController {
    private WelcomeView welcomeView;
    private StudentMapper mapper;
    private SqlSession session;

    public MainController(WelcomeView welcomeView, StudentMapper mapper, SqlSession session) {
        this.welcomeView = welcomeView;
        this.mapper = mapper;
        this.session = session;

        initController();
    }

    private void initController() {
        welcomeView.addTambahListener(e -> showAddView());
        welcomeView.addEditListener(e -> showEditView());
        welcomeView.addLihatListener(e -> showListView());
    }

    private void showAddView() {
        AddStudentView addView = new AddStudentView();
        AddStudentController addController = new AddStudentController(addView, mapper, session);
        addView.setVisible(true);
    }

    private void showEditView() {
        EditStudentView editView = new EditStudentView();
        EditStudentController editController = new EditStudentController(editView, mapper, session);
        editView.setVisible(true);
    }

    private void showListView() {
        ListStudentView listView = new ListStudentView();
        ListStudentController listController = new ListStudentController(listView, mapper, session);
        listView.setVisible(true);
    }
}