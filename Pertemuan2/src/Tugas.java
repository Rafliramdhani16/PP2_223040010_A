import javax.swing.*;
import java.awt.*;

public class Tugas extends JFrame {

    private JTextField textNama, textHP;
    private JRadioButton male, female;
    private JCheckBox foreign, agreementCheckBox;
    private ButtonGroup genderGroup;
    private JTextArea txtOutput;
    private JList<String> hobbyList;
    private JSlider ageSlider;
    private JPasswordField passwordField;
    private JSpinner heightSpinner;

    public Tugas() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());
        this.setTitle("Enhanced Aplikasi Input Data");

        createMenu();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createInputPanel());
        mainPanel.add(createHobbyPanel());
        mainPanel.add(createAgePanel());
        mainPanel.add(createPasswordPanel());
        mainPanel.add(createHeightPanel());
        mainPanel.add(createButtonPanel());
        mainPanel.add(createOutputPanel());

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        textNama = new JTextField();
        textHP = new JTextField();
        male = new JRadioButton("Laki-laki");
        female = new JRadioButton("Perempuan");
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        foreign = new JCheckBox("Warga Negara Asing");

        panel.add(new JLabel("Nama:"));
        panel.add(textNama);
        panel.add(new JLabel("Nomor HP:"));
        panel.add(textHP);
        panel.add(new JLabel("Jenis Kelamin:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(male);
        genderPanel.add(female);
        panel.add(genderPanel);
        panel.add(new JLabel("Kewarganegaraan:"));
        panel.add(foreign);

        return panel;
    }

    private JPanel createHobbyPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Hobi"));

        String[] hobbies = {"Membaca", "Olahraga", "Bermain Game", "Memancing", "Memasak"};
        hobbyList = new JList<>(hobbies);
        hobbyList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panel.add(new JScrollPane(hobbyList), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAgePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Umur"));

        ageSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);
        ageSlider.setMajorTickSpacing(20);
        ageSlider.setMinorTickSpacing(5);
        ageSlider.setPaintTicks(true);
        ageSlider.setPaintLabels(true);
        panel.add(ageSlider, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Password"));

        passwordField = new JPasswordField();
        panel.add(passwordField, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createHeightPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tinggi Badan (cm)"));

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(170, 100, 250, 1);
        heightSpinner = new JSpinner(spinnerModel);
        panel.add(heightSpinner, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        agreementCheckBox = new JCheckBox("Saya menyetujui syarat dan ketentuan yang berlaku");
        JButton saveButton = new JButton("Simpan");
        saveButton.addActionListener(e -> saveData());

        panel.add(agreementCheckBox);
        panel.add(saveButton);

        return panel;
    }

    private JPanel createOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Output"));

        txtOutput = new JTextArea(10, 40);
        txtOutput.setEditable(false);
        panel.add(new JScrollPane(txtOutput), BorderLayout.CENTER);

        return panel;
    }

    private void saveData() {
        if (agreementCheckBox.isSelected()) {
            StringBuilder output = new StringBuilder();
            output.append("Nama          : ").append(textNama.getText()).append("\n");
            output.append("Nomor HP      : ").append(textHP.getText()).append("\n");
            output.append("Jenis Kelamin : ").append(male.isSelected() ? "Laki-laki" : "Perempuan").append("\n");
            output.append("WNA           : ").append(foreign.isSelected() ? "Ya" : "Bukan").append("\n");
            output.append("Hobi          : ").append(String.join(", ", hobbyList.getSelectedValuesList())).append("\n");
            output.append("Umur          : ").append(ageSlider.getValue()).append(" tahun\n");
            output.append("Tinggi Badan  : ").append(heightSpinner.getValue()).append(" cm\n");

            txtOutput.setText(output.toString());
        } else {
            txtOutput.setText("Anda tidak mencentang kotak persetujuan");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tugas app = new Tugas();
            app.setVisible(true);
        });
    }
}
