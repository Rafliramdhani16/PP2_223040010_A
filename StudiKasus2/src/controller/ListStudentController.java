package controller;

import model.StudentMapper;
import view.ListStudentView;
import org.apache.ibatis.session.SqlSession;
import javax.swing.JOptionPane;
public class ListStudentController {
    private ListStudentView view;
    private StudentMapper mapper;
    private SqlSession session;

    public ListStudentController(ListStudentView view, StudentMapper mapper, SqlSession session) {
        this.view = view;
        this.mapper = mapper;
        this.session = session;

        initController();
    }

    private void initController() {
        view.addHapusListener(e -> deleteStudent());
        view.addRefreshListener(e -> refreshTable());
        view.addKembaliListener(e -> view.dispose());

        refreshTable();
    }

    private void deleteStudent() {
        try {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                view.showMessage("Pilih data yang akan dihapus!");
                return;
            }

            String nim = view.getTable().getValueAt(selectedRow, 0).toString();
            int confirm = view.showConfirmDialog("Apakah Anda yakin ingin menghapus data ini?");

            if (confirm == JOptionPane.YES_OPTION) {
                mapper.deleteStudent(nim);
                view.showMessage("Data mahasiswa berhasil dihapus!");
                refreshTable();
            }
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    private void refreshTable() {
        try {
            view.refreshTable(mapper.getAllStudents());
        } catch (Exception e) {
            view.showMessage("Error mengambil data: " + e.getMessage());
        }
    }
}