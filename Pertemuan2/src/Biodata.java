import javax.swing.*;
import java.awt.*;

public class Biodata extends JFrame {
    private JTextField textNama, textHP;
    private JRadioButton male, female;
    private JCheckBox foreign;
    private JTextArea txtOutput;
    private JButton saveButton;

    public Biodata() {
        setTitle("Formulir Input Data");
        setSize(600, 400); // Adjust size to provide better spacing
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeComponents();
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void initializeComponents() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Label and Text Field for Name
        JLabel labelNama = new JLabel("Nama:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(labelNama, constraints);

        textNama = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(textNama, constraints);

        // Label and Text Field for Phone Number
        JLabel labelHP = new JLabel("Nomor HP:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(labelHP, constraints);

        textHP = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(textHP, constraints);

        // Label and Radio Buttons for Gender
        JLabel labelGender = new JLabel("Jenis Kelamin:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(labelGender, constraints);

        male = new JRadioButton("Laki-Laki");
        female = new JRadioButton("Perempuan");
        female.setSelected(true);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(male);
        genderPanel.add(female);
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(genderPanel, constraints);

        // Check Box for Foreign Citizen
        foreign = new JCheckBox("Warga Negara Asing");
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(foreign, constraints);

        // Save Button
        saveButton = new JButton("Simpan");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.WEST;
        saveButton.addActionListener(e -> saveData());
        add(saveButton, constraints);

        // Text Area for Output
        txtOutput = new JTextArea(5, 20);
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        add(scrollPane, constraints);
    }

    private void saveData() {
        String nama = textNama.getText();
        String hp = textHP.getText();
        String gender = male.isSelected() ? "Laki-Laki" : "Perempuan";
        String statusWNA = foreign.isSelected() ? "Ya" : "Bukan";

        String result = "Nama          : " + nama + "\n" +
                "Nomor HP      : " + hp + "\n" +
                "Jenis Kelamin : " + gender + "\n" +
                "WNA           : " + statusWNA + "\n" +
                "===============================";

        txtOutput.setText(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Biodata().setVisible(true);
        });
    }
}
