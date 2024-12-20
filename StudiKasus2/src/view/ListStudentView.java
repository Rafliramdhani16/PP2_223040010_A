package view;

import model.Student;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

public class ListStudentView extends JFrame {
    private JTextField txtSearch;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnHapus, btnRefresh, btnKembali;
    private TableRowSorter<DefaultTableModel> sorter;

    public ListStudentView() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        setTitle("Data Mahasiswa");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        txtSearch = createStyledTextField("Cari Mahasiswa...");

        btnHapus = createStyledButton("Hapus", new Color(239, 68, 68));    // Red
        btnRefresh = createStyledButton("Refresh", new Color(79, 70, 229)); // Indigo
        btnKembali = createStyledButton("Kembali", new Color(107, 114, 128)); // Gray

        setupTable();
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

        field.setPreferredSize(new Dimension(300, 35));
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

        txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });
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


        JPanel topPanel = new JPanel(new BorderLayout(15, 0));
        topPanel.setOpaque(false);


        JLabel titleLabel = new JLabel("Daftar Mahasiswa");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(17, 24, 39));


        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);
        searchPanel.add(new JLabel(new ImageIcon("path/to/search-icon.png"))); // Optional: Add search icon
        searchPanel.add(txtSearch);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnHapus);
        buttonPanel.add(btnKembali);

        mainContainer.add(topPanel, BorderLayout.NORTH);
        mainContainer.add(tablePanel, BorderLayout.CENTER);
        mainContainer.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainContainer);
    }

    public JTable getTable() {
        return table;
    }

    public void addHapusListener(ActionListener listener) {
        btnHapus.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addKembaliListener(ActionListener listener) {
        btnKembali.addActionListener(listener);
    }

    public void showMessage(String message) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(this, message);
    }

    public int showConfirmDialog(String message) {
        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Segoe UI", Font.PLAIN, 14));
        return JOptionPane.showConfirmDialog(this, message, "Konfirmasi",
                JOptionPane.YES_NO_OPTION);
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