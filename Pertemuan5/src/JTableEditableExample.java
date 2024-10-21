import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableEditableExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable Editable Example");

        // Nama kolom untuk JTable
        String[] columnNames = { "ID", "Name", "Age" };

        // Data untuk JTable
        Object[][] data = {
                {1, "Rafli", 25},
                {2, "Bhadrika", 30},
                {3, "Lisvindanu", 99999},
        };

        // Membuat DefaultTableModel dengan data dan nama kolom
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hanya kolom 'Name' yang dapat diedit
                return column == 1;
            }
        };

        // Membuat JTable dengan model yang sudah disesuaikan
        JTable table = new JTable(model);

        // JScrollPane memudahkan scroll tabel jika diperlukan
        JScrollPane scrollPane = new JScrollPane(table);

        // Menambahkan JScrollPane yang berisi JTable ke frame
        frame.add(scrollPane);

        // Mengatur ukuran frame dan operasi penutupan
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}