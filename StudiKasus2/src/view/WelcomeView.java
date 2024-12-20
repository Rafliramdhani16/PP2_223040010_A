package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class WelcomeView extends JFrame {
    private JButton btnTambah, btnEdit, btnLihat;

    public WelcomeView() {
        initComponents();
        setupLayout();
    }

    private void initComponents() {
        setTitle("Sistem Informasi Akademik Mahasiswa");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        btnTambah = createStyledButton("Tambah Data Mahasiswa", new Color(79, 70, 229));
        btnEdit = createStyledButton("Edit Data Mahasiswa", new Color(67, 56, 202));
        btnLihat = createStyledButton("Lihat Data Mahasiswa", new Color(99, 102, 241));
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
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(300, 60));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(0, 0, 4, 0),
                        BorderFactory.createEmptyBorder(4, 4, 4, 4)
                ));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            }
        });

        return button;
    }

    private void setupLayout() {
        JPanel mainContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0,
                        new Color(79, 70, 229, 50),
                        w, h,
                        new Color(67, 56, 202, 30)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        mainContainer.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        JLabel welcomeLabel = new JLabel("Sistem Informasi Akademik");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        welcomeLabel.setForeground(new Color(30, 41, 59));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Manajemen Data Mahasiswa");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(new Color(71, 85, 105));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(welcomeLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        titlePanel.add(subtitleLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        btnTambah.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLihat.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(btnTambah);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(btnEdit);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(btnLihat);

        contentPanel.add(titlePanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        contentPanel.add(buttonPanel);

        mainContainer.add(contentPanel, BorderLayout.CENTER);
        setContentPane(mainContainer);
    }

    public void addTambahListener(ActionListener listener) {
        btnTambah.addActionListener(listener);
    }

    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addLihatListener(ActionListener listener) {
        btnLihat.addActionListener(listener);
    }
}