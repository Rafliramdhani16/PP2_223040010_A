package view;

import model.Student;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class EditStudentView extends JFrame {
    private JTextField txtNIMCari;
    private JTextField txtNIM, txtNama, txtJurusan, txtAngkatan, txtEmail;
    private JButton btnCari, btnUpdate, btnKembali;
    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public EditStudentView() {
        initComponents();
        setupTable();
        setupLayout();
        setupSearchListener();
    }

    private void initComponents() {
        setTitle("Edit Data Mahasiswa");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtNIMCari = createStyledTextField("Cari mahasiswa...");
        txtNIM = createStyledTextField("NIM");
        txtNama = createStyledTextField("Nama Lengkap");
        txtJurusan = createStyledTextField("Jurusan");
        txtAngkatan = createStyledTextField("Angkatan");
        txtEmail = createStyledTextField("Email");

        txtNIM.setEditable(false);
        txtNIM.setBackground(new Color(243, 244, 246));

        btnCari = createStyledButton("Cari", new Color(79, 70, 229));
        btnUpdate = createStyledButton("Update", new Color(16, 185, 129));
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

        field.setPreferredSize(new Dimension(250, 35));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(Color.WHITE);
        field.setBorder(new CompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                if (field.isEditable()) {
                    field.setBorder(new CompoundBorder(
                            BorderFactory.createLineBorder(new Color(79, 70, 229)),
                            BorderFactory.createEmptyBorder(5, 10, 5, 10)
                    ));
                }
            }
            public void focusLost(FocusEvent evt) {
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

        button.setPreferredSize(new Dimension(120, 35));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private void setupTable() {
        String[] columns = {"NIM", "Nama", "Jurusan", "Angkatan", "Email"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(79, 70, 229, 50));
        table.setSelectionForeground(Color.BLACK);
        table.setShowGrid(true);
        table.setGridColor(new Color(229, 231, 235));
        table.setIntercellSpacing(new Dimension(0, 0));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(243, 244, 246));
        header.setForeground(new Color(31, 41, 55));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        table.getColumnModel().getColumn(0).setPreferredWidth(100);  // NIM
        table.getColumnModel().getColumn(1).setPreferredWidth(200);  // Nama
        table.getColumnModel().getColumn(2).setPreferredWidth(150);  // Jurusan
        table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Angkatan
        table.getColumnModel().getColumn(4).setPreferredWidth(200);  // Email

        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
    }

    private void setupSearchListener() {
        txtNIMCari.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterData();
            }
        });
    }

    private void filterData() {
        String searchText = txtNIMCari.getText().trim().toLowerCase();
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            RowFilter<DefaultTableModel, Object> rowFilter = new RowFilter<DefaultTableModel, Object>() {
                @Override
                public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                    for (int i = 0; i < entry.getValueCount(); i++) {
                        if (entry.getStringValue(i).toLowerCase().contains(searchText)) {
                            return true;
                        }
                    }
                    return false;
                }
            };
            sorter.setRowFilter(rowFilter);
        }
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
        mainContainer.setLayout(new BorderLayout(20, 20));
        mainContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setOpaque(false);

        JPanel titleSearchPanel = new JPanel(new BorderLayout());
        titleSearchPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Edit Data Mahasiswa");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);
        JLabel searchLabel = new JLabel("Cari:");
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchPanel.add(searchLabel);
        searchPanel.add(txtNIMCari);
        searchPanel.add(btnCari);

        titleSearchPanel.add(titleLabel, BorderLayout.WEST);
        titleSearchPanel.add(searchPanel, BorderLayout.EAST);

        topPanel.add(titleSearchPanel);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel formTitle = new JLabel("Data Mahasiswa");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        formTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(formTitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel fieldsPanel = new JPanel(new GridBagLayout());
        fieldsPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;

        fieldsPanel.add(createFormField("NIM", txtNIM), gbc);
        fieldsPanel.add(createFormField("Nama Lengkap", txtNama), gbc);
        fieldsPanel.add(createFormField("Jurusan", txtJurusan), gbc);
        fieldsPanel.add(createFormField("Angkatan", txtAngkatan), gbc);
        fieldsPanel.add(createFormField("Email", txtEmail), gbc);

        formPanel.add(fieldsPanel);
        formPanel.add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnKembali);
        formPanel.add(buttonPanel);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, formPanel, tablePanel);
        splitPane.setDividerLocation(400);
        splitPane.setOpaque(false);
        splitPane.setBorder(null);

        mainContainer.add(topPanel, BorderLayout.NORTH);
        mainContainer.add(splitPane, BorderLayout.CENTER);

        setContentPane(mainContainer);
    }

    private JPanel createFormField(String labelText, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);

        return panel;
    }

    public String getNIMCari() { return txtNIMCari.getText(); }
    public String getNIM() { return txtNIM.getText(); }
    public String getNama() { return txtNama.getText(); }
    public String getJurusan() { return txtJurusan.getText(); }
    public String getAngkatan() { return txtAngkatan.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public JTable getTable() { return table; }

    public void setNIM(String nim) {
        txtNIM.setText(nim);
    }

    public void setNama(String nama) {
        txtNama.setText(nama);
    }
    public void setJurusan(String jurusan) { txtJurusan.setText(jurusan); }
    public void setAngkatan(String angkatan) { txtAngkatan.setText(angkatan); }
    public void setEmail(String email) { txtEmail.setText(email); }

    public void addTableSelectionListener(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }

    public void addCariListener(ActionListener listener) {
        btnCari.addActionListener(listener);
    }

    public void addUpdateListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addKembaliListener(ActionListener listener) {
        btnKembali.addActionListener(listener);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearFields() {
        txtNIMCari.setText("");
        txtNIM.setText("");
        txtNama.setText("");
        txtJurusan.setText("");
        txtAngkatan.setText("");
        txtEmail.setText("");
        table.clearSelection();
    }

    public void refreshTable(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student student : students) {
            Object[] row = {
                    student.getNim(),
                    student.getNama(),
                    student.getJurusan(),
                    student.getAngkatan(),
                    student.getEmail()
            };
            tableModel.addRow(row);
        }
    }

}