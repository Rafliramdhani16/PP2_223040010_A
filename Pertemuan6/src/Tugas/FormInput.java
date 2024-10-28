package Tugas;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class FormInput extends JFrame {
    private static ArrayList<DataMahasiswa> daftarMahasiswa = new ArrayList<>();
    private Color warnaAksen = new Color(70, 130, 180);
    private Font fontLabel = new Font("Arial", Font.BOLD, 12);

    // Komponen Formulir
    private JTextField namaField, nimField, tanggalLahirField, teleponField, emailField;
    private JTextArea alamatArea, catatanArea;
    private JRadioButton lakiRadio, perempuanRadio;
    private JComboBox<String> statusCombo, pendidikanCombo;
    private JTextField jurusanField, fakultasField;
    private JCheckBox asramaCheck, beasiswaCheck, organisasiCheck;
    private JList<String> hobiList;
    private JSlider semesterSlider;
    private JSpinner ipkSpinner;
    private JLabel semesterLabel;

    public FormInput() {
        setTitle("Formulir Input Data Mahasiswa");
        setSize(800, 900);
        setLocationRelativeTo(null);

        // Panel Utama
        JPanel panelUtama = new JPanel();
        panelUtama.setLayout(new BoxLayout(panelUtama, BoxLayout.Y_AXIS));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Bagian Informasi Pribadi
        JPanel infoPribadi = buatBagian("Data Pribadi", fontLabel);

        // Inisialisasi komponen formulir dasar
        namaField = new JTextField(20);
        nimField = new JTextField(16);
        tanggalLahirField = new JTextField(10);
        alamatArea = new JTextArea(4, 20);
        teleponField = new JTextField(15);
        emailField = new JTextField(20);
        jurusanField = new JTextField(20);
        fakultasField = new JTextField(20);

        // Komponen baru - Catatan Tambahan
        catatanArea = new JTextArea(3, 20);
        catatanArea.setLineWrap(true);
        catatanArea.setWrapStyleWord(true);

        // Panel untuk setiap field
        JPanel namaPanel = buatPanelField("Nama Lengkap:", namaField, fontLabel);
        JPanel nimPanel = buatPanelField("NIM:", nimField, fontLabel);
        JPanel tanggalLahirPanel = buatPanelField("Tanggal Lahir:", tanggalLahirField, fontLabel);

        // Pilihan Jenis Kelamin dengan RadioButton
        JPanel jenisKelaminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel jenisKelaminLabel = new JLabel("Jenis Kelamin:");
        jenisKelaminLabel.setFont(fontLabel);
        jenisKelaminLabel.setPreferredSize(new Dimension(100, 25));
        ButtonGroup genderGroup = new ButtonGroup();
        lakiRadio = new JRadioButton("Laki-laki");
        perempuanRadio = new JRadioButton("Perempuan");
        styleRadioButton(lakiRadio, warnaAksen);
        styleRadioButton(perempuanRadio, warnaAksen);
        genderGroup.add(lakiRadio);
        genderGroup.add(perempuanRadio);
        jenisKelaminPanel.add(jenisKelaminLabel);
        jenisKelaminPanel.add(lakiRadio);
        jenisKelaminPanel.add(perempuanRadio);

        // Status Pernikahan dengan ComboBox
        String[] statusPernikahan = {"Pilih Status", "Belum Menikah", "Menikah", "Cerai"};
        statusCombo = new JComboBox<>(statusPernikahan);
        JPanel statusPanel = buatPanelField("Status:", statusCombo, fontLabel);

        // Pendidikan dengan ComboBox
        String[] pendidikan = {"Pilih Pendidikan", "D3", "S1", "S2", "S3"};
        pendidikanCombo = new JComboBox<>(pendidikan);
        JPanel pendidikanPanel = buatPanelField("Pendidikan:", pendidikanCombo, fontLabel);

        // Checkbox untuk Fasilitas
        JPanel fasilitasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fasilitasPanel.setBorder(BorderFactory.createTitledBorder("Fasilitas"));
        asramaCheck = new JCheckBox("Asrama");
        beasiswaCheck = new JCheckBox("Beasiswa");
        organisasiCheck = new JCheckBox("Organisasi");
        fasilitasPanel.add(asramaCheck);
        fasilitasPanel.add(beasiswaCheck);
        fasilitasPanel.add(organisasiCheck);

        // JList untuk Hobi
        String[] hobiItems = {"Membaca", "Menulis", "Olahraga", "Musik", "Seni", "Gaming", "Traveling"};
        hobiList = new JList<>(hobiItems);
        hobiList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane hobiScroll = new JScrollPane(hobiList);
        hobiScroll.setPreferredSize(new Dimension(200, 100));
        JPanel hobiPanel = new JPanel(new BorderLayout());
        hobiPanel.setBorder(BorderFactory.createTitledBorder("Hobi"));
        hobiPanel.add(hobiScroll);

        // JSlider untuk Semester
        semesterSlider = new JSlider(JSlider.HORIZONTAL, 1, 14, 1);
        semesterSlider.setMajorTickSpacing(1);
        semesterSlider.setPaintTicks(true);
        semesterSlider.setPaintLabels(true);
        semesterLabel = new JLabel("Semester: 1");
        semesterSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                semesterLabel.setText("Semester: " + semesterSlider.getValue());
            }
        });
        JPanel semesterPanel = new JPanel(new BorderLayout());
        semesterPanel.setBorder(BorderFactory.createTitledBorder("Semester"));
        semesterPanel.add(semesterLabel, BorderLayout.NORTH);
        semesterPanel.add(semesterSlider, BorderLayout.CENTER);

        // JSpinner untuk IPK
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0.00, 0.00, 4.00, 0.01);
        ipkSpinner = new JSpinner(spinnerModel);
        ipkSpinner.setPreferredSize(new Dimension(100, 30)); // Set width to 100
        JPanel ipkPanel = buatPanelField("IPK:", ipkSpinner, fontLabel);

        // Alamat
        JPanel alamatPanel = new JPanel(new BorderLayout(5, 5));
        JLabel alamatLabel = new JLabel("Alamat:");
        alamatLabel.setFont(fontLabel);
        alamatArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        alamatPanel.add(alamatLabel, BorderLayout.NORTH);
        alamatPanel.add(new JScrollPane(alamatArea), BorderLayout.CENTER);

        // Catatan Panel
        JPanel catatanPanel = new JPanel(new BorderLayout(5, 5));
        JLabel catatanLabel = new JLabel("Catatan Tambahan:");
        catatanLabel.setFont(fontLabel);
        catatanArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        catatanPanel.add(catatanLabel, BorderLayout.NORTH);
        catatanPanel.add(new JScrollPane(catatanArea), BorderLayout.CENTER);

        // Informasi Kontak
        JPanel kontakPanel = buatBagian("Informasi Kontak", fontLabel);
        JPanel teleponPanel = buatPanelField("No. Telepon:", teleponField, fontLabel);
        JPanel emailPanel = buatPanelField("Email:", emailField, fontLabel);

        // Informasi Akademik
        JPanel akademikPanel = buatBagian("Informasi Akademik", fontLabel);
        JPanel jurusanPanel = buatPanelField("Jurusan:", jurusanField, fontLabel);
        JPanel fakultasPanel = buatPanelField("Fakultas:", fakultasField, fontLabel);

        // Panel Tombol
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton tombolSimpan = new JButton("Simpan");
        JButton tombolReset = new JButton("Reset");
        JButton tombolBatal = new JButton("Batal");

        styleButton(tombolSimpan, new Color(46, 125, 50));
        styleButton(tombolReset, new Color(156, 39, 176));
        styleButton(tombolBatal, new Color(211, 47, 47));

        // Menambahkan action listener
        tombolSimpan.addActionListener(e -> simpanData());
        tombolReset.addActionListener(e -> bersihkanForm());
        tombolBatal.addActionListener(e -> dispose());

        // Menambahkan komponen ke panel
        infoPribadi.add(namaPanel);
        infoPribadi.add(nimPanel);
        infoPribadi.add(tanggalLahirPanel);
        infoPribadi.add(jenisKelaminPanel);
        infoPribadi.add(statusPanel);
        infoPribadi.add(pendidikanPanel);
        infoPribadi.add(alamatPanel);
        infoPribadi.add(Box.createVerticalStrut(10));
        infoPribadi.add(fasilitasPanel);
        infoPribadi.add(Box.createVerticalStrut(10));
        infoPribadi.add(hobiPanel);
        infoPribadi.add(Box.createVerticalStrut(10));
        infoPribadi.add(catatanPanel);

        kontakPanel.add(teleponPanel);
        kontakPanel.add(emailPanel);

        akademikPanel.add(jurusanPanel);
        akademikPanel.add(fakultasPanel);
        akademikPanel.add(semesterPanel);
        akademikPanel.add(ipkPanel);

        panelTombol.add(tombolSimpan);
        panelTombol.add(tombolReset);
        panelTombol.add(tombolBatal);

        // Menambahkan semua bagian ke panel utama
        panelUtama.add(infoPribadi);
        panelUtama.add(Box.createVerticalStrut(10));
        panelUtama.add(kontakPanel);
        panelUtama.add(Box.createVerticalStrut(10));
        panelUtama.add(akademikPanel);
        panelUtama.add(Box.createVerticalStrut(15));
        panelUtama.add(panelTombol);

        // Menambahkan panel utama ke dalam JScrollPane
        JScrollPane scrollPane = new JScrollPane(panelUtama);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);
    }



    private void simpanData() {
        if (namaField.getText().isEmpty() || nimField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nama dan NIM harus diisi!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get selected hobi
        StringBuilder hobi = new StringBuilder();
        for (String selectedHobi : hobiList.getSelectedValuesList()) {
            if (hobi.length() > 0) hobi.append(", ");
            hobi.append(selectedHobi);
        }

        // Get fasilitas
        StringBuilder fasilitas = new StringBuilder();
        if (asramaCheck.isSelected()) fasilitas.append("Asrama ");
        if (beasiswaCheck.isSelected()) fasilitas.append("Beasiswa ");
        if (organisasiCheck.isSelected()) fasilitas.append("Organisasi");

        DataMahasiswa mahasiswa = new DataMahasiswa(
                namaField.getText(),
                nimField.getText(),
                tanggalLahirField.getText(),
                lakiRadio.isSelected() ? "Laki-laki" : "Perempuan",
                (String) statusCombo.getSelectedItem(),
                (String) pendidikanCombo.getSelectedItem(),
                alamatArea.getText(),
                teleponField.getText(),
                emailField.getText(),
                jurusanField.getText(),
                fakultasField.getText(),
                fasilitas.toString(),
                hobi.toString(),
                semesterSlider.getValue(),
                (Double) ipkSpinner.getValue(),
                catatanArea.getText()
        );

        daftarMahasiswa.add(mahasiswa);

        JOptionPane.showMessageDialog(this,
                "Data berhasil disimpan!",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);

        bersihkanForm();
    }

    private void bersihkanForm() {
        namaField.setText("");
        nimField.setText("");
        tanggalLahirField.setText("");
        lakiRadio.setSelected(false);
        perempuanRadio.setSelected(false);
        statusCombo.setSelectedIndex(0);
        pendidikanCombo.setSelectedIndex(0);
        alamatArea.setText("");
        teleponField.setText("");
        emailField.setText("");
        jurusanField.setText("");
        fakultasField.setText("");
        asramaCheck.setSelected(false);
        beasiswaCheck.setSelected(false);
        organisasiCheck.setSelected(false);
        hobiList.clearSelection();
        semesterSlider.setValue(1);
        ipkSpinner.setValue(0.00);
        catatanArea.setText("");
    }

    private JPanel buatBagian(String judul, Font font) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        TitledBorder border = BorderFactory.createTitledBorder(judul);
        border.setTitleFont(font);
        border.setTitleColor(warnaAksen);
        panel.setBorder(border);
        return panel;
    }

    private JPanel buatPanelField(String labelText, JComponent field, Font font) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setPreferredSize(new Dimension(100, 25));
        panel.add(label);
        panel.add(field);
        return panel;
    }

    private void styleRadioButton(JRadioButton radioButton, Color color) {
        radioButton.setBackground(Color.WHITE);
        radioButton.setForeground(color);
        radioButton.setFocusPainted(false);
    }
    public static ArrayList<DataMahasiswa> getDataMahasiswa() {
        return daftarMahasiswa;
    }
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(100, 30));
    }

}