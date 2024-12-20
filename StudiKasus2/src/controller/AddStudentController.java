package controller;

import model.Student;
import model.StudentMapper;
import view.AddStudentView;
import org.apache.ibatis.session.SqlSession;

public class AddStudentController {
    private AddStudentView view;
    private StudentMapper mapper;
    private SqlSession session;

    public AddStudentController(AddStudentView view, StudentMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        initController();
    }

    private void initController() {
        view.addSimpanListener(e -> addStudent());
        view.addKembaliListener(e -> view.dispose());
    }

    private void addStudent() {
        try {
            if (!validateInput()) return;

            Student student = new Student();
            student.setNim(view.getNIM());
            student.setNama(view.getNama());
            student.setJurusan(view.getJurusan());
            student.setAngkatan(view.getAngkatan());
            student.setEmail(view.getEmail());

            mapper.insertStudent(student);
            view.showMessage("Data mahasiswa berhasil ditambahkan!");
            view.clearFields();
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    private boolean validateInput() {
        if (view.getNIM().isEmpty() || view.getNama().isEmpty() ||
                view.getJurusan().isEmpty() || view.getAngkatan().isEmpty() ||
                view.getEmail().isEmpty()) {
            view.showMessage("Semua field harus diisi!");
            return false;
        }
        return true;
    }
}