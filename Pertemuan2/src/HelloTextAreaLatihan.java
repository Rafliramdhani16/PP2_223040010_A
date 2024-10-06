import java.awt.event.*;
import javax.swing.*;

public class HelloTextAreaLatihan extends JFrame {

    public HelloTextAreaLatihan() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelInput = new JLabel("Masukkan Nama : ");
        labelInput.setBounds(130, 40, 400, 10);

        JTextField text = new JTextField();
        text.setBounds(130, 50, 100, 30);

        JLabel labelInput2 = new JLabel("Masukkan Nomor Telepon : ");
        labelInput2.setBounds(130, 85, 400, 10);

        JTextField text2 = new JTextField();
        text2.setBounds(130, 100, 100, 30);

        JButton button = new JButton("Klik");
        button.setBounds(130, 150, 100, 40);

        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(130,200,100,100);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = text.getText();
                String no = text2.getText();
                txtOutput.append("Nama : " + nama + "\n"+ "no : " + no);
                text.setText("");
            }
        });

        this.add(button);
        this.add(text);
        this.add(labelInput);
        this.add(text2);
        this.add(labelInput2);
        this.add(txtOutput);

        this.setSize(400, 500);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloTextAreaLatihan h = new HelloTextAreaLatihan();
                h.setVisible(true);
            }
        });
    }
}