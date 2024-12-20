package controller;

import model.Student;
import model.StudentMapper;
import view.EditStudentView;
import org.apache.ibatis.session.SqlSession;
import javax.swing.*;
import java.util.List;

public class EditStudentController {
    private EditStudentView view;
    private StudentMapper mapper;
    private SqlSession session;
    private Student selectedStudent;

    public EditStudentController(EditStudentView view, StudentMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;
        this.selectedStudent = null;

        initController();
    }

    private void initController() {
        view.addTableSelectionListener(e -> handleTableSelection());
        view.addCariListener(e -> findStudent());
        view.addUpdateListener(e -> updateStudent());
        view.addKembaliListener(e -> view.dispose());

        refreshTable();
    }

    private void handleTableSelection() {
        try {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow != -1) {
                // Konversi index dari view ke model karena menggunakan sorter
                int modelRow = view.getTable().convertRowIndexToModel(selectedRow);

                // Simpan data sebelum diubah
                selectedStudent = new Student();
                selectedStudent.setNim(view.getTable().getModel().getValueAt(modelRow, 0).toString());
                selectedStudent.setNama(view.getTable().getModel().getValueAt(modelRow, 1).toString());
                selectedStudent.setJurusan(view.getTable().getModel().getValueAt(modelRow, 2).toString());
                selectedStudent.setAngkatan(view.getTable().getModel().getValueAt(modelRow, 3).toString());
                selectedStudent.setEmail(view.getTable().getModel().getValueAt(modelRow, 4).toString());

                // Set data ke form
                view.setNIM(selectedStudent.getNim());
                view.setNama(selectedStudent.getNama());
                view.setJurusan(selectedStudent.getJurusan());
                view.setAngkatan(selectedStudent.getAngkatan());
                view.setEmail(selectedStudent.getEmail());
            }
        } catch (Exception e) {
            view.showMessage("Error saat memilih data: " + e.getMessage());
        }
    }

    private void findStudent() {
        String nimCari = view.getNIMCari();
        if (nimCari.isEmpty()) {
            refreshTable();
            return;
        }
        try {
            refreshTable();
        } catch (Exception e) {
            view.showMessage("Error saat mencari data: " + e.getMessage());
        }
    }

    private void updateStudent() {
        try {
            if (!validateInput()) return;
            if (selectedStudent == null) {
                view.showMessage("Pilih data yang akan diupdate!");
                return;
            }

            int confirm = showUpdateConfirmation(
                    selectedStudent,
                    createUpdatedStudent()
            );

            if (confirm == JOptionPane.YES_OPTION) {
                Student updatedStudent = createUpdatedStudent();
                mapper.updateStudent(updatedStudent);

                showSuccessUpdate(selectedStudent, updatedStudent);

                view.clearFields();
                refreshTable();
                selectedStudent = null;
            }
        } catch (Exception e) {
            view.showMessage("Error saat update data: " + e.getMessage());
        }
    }

    private Student createUpdatedStudent() {
        Student student = new Student();
        student.setNim(view.getNIM());
        student.setNama(view.getNama());
        student.setJurusan(view.getJurusan());
        student.setAngkatan(view.getAngkatan());
        student.setEmail(view.getEmail());
        return student;
    }

    private void refreshTable() {
        try {
            List<Student> students = mapper.getAllStudents();
            view.refreshTable(students);
        } catch (Exception e) {
            view.showMessage("Error mengambil data: " + e.getMessage());
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

    private int showUpdateConfirmation(Student oldData, Student newData) {
        String message = String.format(
                "<html><body>" +
                        "<h2>Konfirmasi Perubahan Data</h2>" +
                        "<table style='border-collapse: collapse; width: 100%%;'>" +
                        "<tr style='background-color: #f0f0f0;'><th style='padding: 8px; text-align: left;'>Field</th><th style='padding: 8px; text-align: left;'>Data Lama</th><th style='padding: 8px; text-align: left;'>Data Baru</th></tr>" +
                        "<tr><td style='padding: 8px;'>NIM</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "<tr style='background-color: #f0f0f0;'><td style='padding: 8px;'>Nama</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "<tr><td style='padding: 8px;'>Jurusan</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "<tr style='background-color: #f0f0f0;'><td style='padding: 8px;'>Angkatan</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "<tr><td style='padding: 8px;'>Email</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "</table><br>" +
                        "Apakah Anda yakin ingin menyimpan perubahan ini?" +
                        "</body></html>",
                oldData.getNim(), newData.getNim(),
                oldData.getNama(), newData.getNama(),
                oldData.getJurusan(), newData.getJurusan(),
                oldData.getAngkatan(), newData.getAngkatan(),
                oldData.getEmail(), newData.getEmail()
        );

        return JOptionPane.showConfirmDialog(
                view,
                message,
                "Konfirmasi Update",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
    }

    private void showSuccessUpdate(Student oldData, Student newData) {
        String message = String.format(
                "<html><body>" +
                        "<h2>Update Data Berhasil</h2>" +
                        "<p>Data berhasil diperbarui dengan detail perubahan sebagai berikut:</p>" +
                        "<table style='border-collapse: collapse; width: 100%%;'>" +
                        "<tr style='background-color: #e8f5e9;'><th style='padding: 8px; text-align: left;'>Field</th><th style='padding: 8px; text-align: left;'>Data Lama</th><th style='padding: 8px; text-align: left;'>Data Baru</th></tr>" +
                        "<tr><td style='padding: 8px;'>NIM</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "<tr style='background-color: #f5f5f5;'><td style='padding: 8px;'>Nama</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "<tr><td style='padding: 8px;'>Jurusan</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "<tr style='background-color: #f5f5f5;'><td style='padding: 8px;'>Angkatan</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "<tr><td style='padding: 8px;'>Email</td><td style='padding: 8px;'>%s</td><td style='padding: 8px;'>%s</td></tr>" +
                        "</table>" +
                        "</body></html>",
                oldData.getNim(), newData.getNim(),
                oldData.getNama(), newData.getNama(),
                oldData.getJurusan(), newData.getJurusan(),
                oldData.getAngkatan(), newData.getAngkatan(),
                oldData.getEmail(), newData.getEmail()
        );

        JOptionPane.showMessageDialog(
                view,
                message,
                "Update Berhasil",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}