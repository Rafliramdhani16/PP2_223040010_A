import javax.swing.*;

public class HelloWorldSwing {
    private static void createAndShowGUI() {
        // Membuat JFrame dengan judul "HelloWorldSwing"
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menambahkan JLabel dengan teks "Hello World"
        JLabel label = new JLabel("Hello World");
        frame.getContentPane().add(label);

        // Menyesuaikan ukuran frame sesuai dengan isinya
        frame.pack();

        // Menampilkan frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Memastikan GUI dijalankan di thread event-dispatching
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
