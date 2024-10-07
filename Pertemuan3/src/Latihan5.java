import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Latihan5 extends JFrame {
    private JTextField namaField, nomorHPField;
    private JRadioButton lakiLakiRadio, perempuanRadio;
    private JCheckBox wnaCheckbox;
    private JLabel outputLabel;

    public Latihan5() {
        setTitle("Form Biodata");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel titleLabel = new JLabel("Form Biodata");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        addFormField(panel, "Nama:", namaField = new JTextField(15), 1);
        addFormField(panel, "Jenis Kelamin:", createGenderPanel(), 2);
        addFormField(panel, "Nomor HP:", nomorHPField = new JTextField(15), 3);

        wnaCheckbox = new JCheckBox("Warga Negara Asing");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(wnaCheckbox, gbc);

        JButton simpanButton = new JButton("Simpan");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(simpanButton, gbc);

        // Create a panel for the output with a white background
        JPanel outputPanel = new JPanel();
        outputPanel.setBackground(Color.WHITE);
        outputPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        outputPanel.setPreferredSize(new Dimension(350, 100));

        outputLabel = new JLabel("Output akan ditampilkan di sini");
        outputLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        outputPanel.add(outputLabel);

        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(outputPanel, gbc);

        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOutput();
            }
        });

        add(panel);
    }

    private void addFormField(JPanel panel, String labelText, Component component, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(component, gbc);
    }

    private JPanel createGenderPanel() {
        JPanel genderPanel = new JPanel();
        lakiLakiRadio = new JRadioButton("Laki-Laki");
        perempuanRadio = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(lakiLakiRadio);
        genderGroup.add(perempuanRadio);
        genderPanel.add(lakiLakiRadio);
        genderPanel.add(perempuanRadio);
        lakiLakiRadio.setSelected(true);
        return genderPanel;
    }

    private void updateOutput() {
        StringBuilder output = new StringBuilder("<html>");
        output.append("Nama: ").append(namaField.getText()).append("<br>");
        output.append("Jenis Kelamin: ").append(lakiLakiRadio.isSelected() ? "Laki-Laki" : "Perempuan").append("<br>");
        output.append("Nomor HP: ").append(nomorHPField.getText()).append("<br>");
        output.append("Status: ").append(wnaCheckbox.isSelected() ? "WNA" : "WNI").append("</html>");
        outputLabel.setText(output.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Latihan5 form = new Latihan5();
            form.setVisible(true);
        });
    }
}
