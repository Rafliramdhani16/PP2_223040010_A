package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoadingView extends JFrame {
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private JButton startButton;

    public LoadingView() {
        setTitle("Loading Status");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status Label
        statusLabel = new JLabel("Tekan tombol untuk mulai tugas berat", JLabel.CENTER);

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        // Start Button
        startButton = new JButton("Mulai");

        // Add components to frame
        add(statusLabel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }

    public void addStartButtonListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgress(int value) {
        progressBar.setValue(value);
    }
}