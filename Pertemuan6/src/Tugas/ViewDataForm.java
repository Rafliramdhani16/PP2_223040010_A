package Tugas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class ViewDataForm extends JFrame {
    private JTable tabelData;
    private DefaultTableModel modelTabel;

    public ViewDataForm() {
        setTitle("Data Mahasiswa");
        setSize(1000, 600);
        setLocationRelativeTo(null);

        // Inisialisasi tabel dan model tabel
        String[] kolom = {
                "Nama", "NIM", "Tanggal Lahir", "Jenis Kelamin",
                "Status", "Pendidikan", "Alamat", "Telepon",
                "Email", "Jurusan", "Fakultas"
        };
        modelTabel = new DefaultTableModel(kolom, 0);
        tabelData = new JTable(modelTabel);

        // Penyesuaian tampilan tabel
        tabelData.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelData.setRowHeight(25);
        JTableHeader header = tabelData.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 13));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.WHITE);

        // Set lebar kolom
        int[] lebarKolom = {150, 100, 100, 100, 100, 100, 200, 100, 150, 100, 100};
        for (int i = 0; i < kolom.length; i++) {
            tabelData.getColumnModel().getColumn(i).setPreferredWidth(lebarKolom[i]);
        }

        // Menambahkan tabel ke scroll pane
        JScrollPane scrollPane = new JScrollPane(tabelData);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        // Tombol Refresh dan Kembali
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton tombolRefresh = new JButton("Refresh Data");
        JButton tombolKembali = new JButton("Kembali");

        styleButton(tombolRefresh, new Color(46, 125, 50));
        styleButton(tombolKembali, new Color(211, 47, 47));

        // Menambahkan fungsi tombol
        tombolRefresh.addActionListener(e -> loadData());
        tombolKembali.addActionListener(e -> dispose());

        panelTombol.add(tombolRefresh);
        panelTombol.add(tombolKembali);

        // Mengatur tata letak
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(panelTombol, BorderLayout.SOUTH);

        // Memuat data awal
        loadData();
    }

    private void loadData() {
        modelTabel.setRowCount(0); // Hapus data lama

        for (DataMahasiswa mahasiswa : FormInput.getDataMahasiswa()) {
            Object[] barisData = {
                    mahasiswa.getNama(),
                    mahasiswa.getNim(),
                    mahasiswa.getTanggalLahir(),
                    mahasiswa.getJenisKelamin(),
                    mahasiswa.getStatus(),
                    mahasiswa.getPendidikan(),
                    mahasiswa.getAlamat(),
                    mahasiswa.getTelepon(),
                    mahasiswa.getEmail(),
                    mahasiswa.getJurusan(),
                    mahasiswa.getFakultas()
            };
            modelTabel.addRow(barisData);
        }
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(100, 30));
    }
}
