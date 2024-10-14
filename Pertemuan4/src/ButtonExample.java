import javax.swing.*;
import java.awt.event.*;

public class ButtonExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Button Example");
        JButton button = new JButton("Click Me");

        // Menambahkan ActionListener ke JButton
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
            }
        });

        button.setBounds(50, 50, 150, 30); // Mengatur posisi dan ukuran tombol
        frame.add(button); // Menambahkan tombol ke JFrame

        frame.setSize(300, 200); // Mengatur ukuran JFrame
        frame.setLayout(null); // Mengatur layout menjadi null (absolute positioning)
        frame.setVisible(true); // Menampilkan JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Menutup program saat JFrame ditutup
    }
}
