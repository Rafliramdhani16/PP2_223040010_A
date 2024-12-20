package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class AddStudentView extends JFrame {
    private JTextField txtNIM, txtNama, txtJurusan, txtAngkatan, txtEmail;
    private JButton btnSimpan, btnKembali;

    public AddStudentView() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        setTitle("Tambah Data Mahasiswa");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtNIM = createStyledTextField("Masukkan NIM");
        txtNama = createStyledTextField("Masukkan Nama Lengkap");
        txtJurusan = createStyledTextField("Masukkan Jurusan");
        txtAngkatan = createStyledTextField("Masukkan Angkatan");
        txtEmail = createStyledTextField("Masukkan Email");

        btnSimpan = createStyledButton("Simpan", new Color(79, 70, 229));
        btnKembali = createStyledButton("Kembali", new Color(239, 68, 68));
    }

    private JTextField createStyledTextField(String placeholder) {
        JTextField field = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !hasFocus()) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2.setColor(new Color(156, 163, 175));
                    g2.setFont(getFont());
                    FontMetrics fm = g2.getFontMetrics();
                    g2.drawString(placeholder, getInsets().left, (getHeight() - fm.getHeight()) / 2 + fm.getAscent());
                }
            }
        };

        field.setPreferredSize(new Dimension(300, 40));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
        field.setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(new CompoundBorder(
                        BorderFactory.createLineBorder(new Color(79, 70, 229)),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(new CompoundBorder(
                        BorderFactory.createLineBorder(new Color(209, 213, 219)),
                        BorderFactory.createEmptyBorder(5, 10, 5, 10)
                ));
            }
        });

        return field;
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(color.brighter());
                } else {
                    g2.setColor(color);
                }
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(120, 40));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(55, 65, 81));
        return label;
    }

    private void setupLayout() {
        JPanel mainContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(249, 250, 251),
                        0, getHeight(),
                        new Color(243, 244, 246)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainContainer.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel titleLabel = new JLabel("Tambah Data Mahasiswa");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(17, 24, 39));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;

        addFormRow(formPanel, "NIM", txtNIM, gbc);
        addFormRow(formPanel, "Nama Lengkap", txtNama, gbc);
        addFormRow(formPanel, "Jurusan", txtJurusan, gbc);
        addFormRow(formPanel, "Angkatan", txtAngkatan, gbc);
        addFormRow(formPanel, "Email", txtEmail, gbc);

        JPanel formWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        formWrapper.setOpaque(false);
        formWrapper.add(formPanel);
        contentPanel.add(formWrapper);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        buttonPanel.add(btnSimpan);
        buttonPanel.add(btnKembali);
        contentPanel.add(buttonPanel);

        mainContainer.add(contentPanel, BorderLayout.CENTER);
        setContentPane(mainContainer);
    }

    private void addFormRow(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc) {
        JLabel label = createStyledLabel(labelText);
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(label, gbc);

        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(field, gbc);
    }

    public String getNIM() { return txtNIM.getText(); }
    public String getNama() { return txtNama.getText(); }
    public String getJurusan() { return txtJurusan.getText(); }
    public String getAngkatan() { return txtAngkatan.getText(); }
    public String getEmail() { return txtEmail.getText(); }

    public void addSimpanListener(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }

    public void addKembaliListener(ActionListener listener) {
        btnKembali.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearFields() {
        txtNIM.setText("");
        txtNama.setText("");
        txtJurusan.setText("");
        txtAngkatan.setText("");
        txtEmail.setText("");
    }
}