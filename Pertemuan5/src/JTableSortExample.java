import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableSortExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTable Sorting Example");

        // Nama kolom untuk JTable
        String[] columnNames = { "ID", "Name", "Age" };

        // Data untuk tabel
        Object[][] data = {
                {1, "Rafli", 25},
                {2, "Bhadrika", 30},
                {3, "Lisvindanu", 99999},
        };

        // Membuat model tabel dengan data dan nama kolom
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Mengaktifkan pengurutan otomatis pada tabel
        table.setAutoCreateRowSorter(true);

        // Menambahkan JTable ke JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        // Mengatur ukuran, operasi penutupan, dan visibilitas frame
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}