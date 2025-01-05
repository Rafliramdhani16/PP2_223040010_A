import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

// Kelas utama aplikasi
public class MainFrame {

    public static void main(String[] args) {
        // Konfigurasi timeout untuk HTTP client
        // Mengatur timeout selama 5 detik
        final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setSoTimeout(Timeout.ofSeconds(5))
                .build();

        // Membuat instance HTTP client asynchronous dengan konfigurasi yang telah dibuat
        final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                .setIOReactorConfig(ioReactorConfig)
                .build();

        // Memulai HTTP client
        client.start();

        // Menentukan target API yang akan diakses
        final HttpHost target = new HttpHost("672fbf9066e42ceaf15e9a9b.mockapi.io");
        final String requestUri = "/api/contacts";

        // Membuat GUI menggunakan SwingUtilities untuk thread safety
        SwingUtilities.invokeLater(() -> {
            // Membuat frame utama aplikasi
            JFrame frame = new JFrame("Contoh HTTP Client di Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            // Membuat label untuk menampilkan status
            JLabel statusLabel = new JLabel("Tekan tombol untuk mulai mengunduh data", JLabel.CENTER);

            // Membuat tombol untuk memulai proses
            JButton startButton = new JButton("Mulai");

            // Membuat progress bar untuk indikator proses
            JProgressBar progressBar = new JProgressBar(0, 100);

            // Membuat area teks untuk menampilkan hasil
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);  // Area teks tidak bisa diedit
            JScrollPane scrollPane = new JScrollPane(textArea);  // Menambahkan scroll untuk text area

            // Menata layout komponen-komponen GUI
            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Panel khusus untuk tombol dan progress bar
            JPanel panel = new JPanel();
            panel.add(startButton);
            panel.add(progressBar);
            panel.setLayout(new FlowLayout());
            frame.add(panel, BorderLayout.SOUTH);

            // Mengatur penutupan aplikasi
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.addWindowListener(new WindowListener() {
                // Implementasi WindowListener untuk menangani event jendela
                @Override
                public void windowOpened(WindowEvent e) {}

                @Override
                public void windowClosing(WindowEvent e) {
                    // Menutup HTTP client dengan graceful ketika aplikasi ditutup
                    client.close(CloseMode.GRACEFUL);
                    System.exit(0);
                }

                @Override
                public void windowClosed(WindowEvent e) {}

                @Override
                public void windowIconified(WindowEvent e) {}

                @Override
                public void windowDeiconified(WindowEvent e) {}

                @Override
                public void windowActivated(WindowEvent e) {}

                @Override
                public void windowDeactivated(WindowEvent e) {}
            });

            // Membuat request HTTP GET
            final SimpleHttpRequest request = SimpleRequestBuilder.get()
                    .setHttpHost(target)
                    .setPath(requestUri)
                    .build();

            // Menambahkan action listener untuk tombol mulai
            startButton.addActionListener(e -> {
                // Mengatur UI ketika proses dimulai
                progressBar.setIndeterminate(true);  // Progress bar mode indeterminate
                startButton.setEnabled(false);  // Menonaktifkan tombol
                statusLabel.setText("Proses berjalan...");
                textArea.setText("");  // Mengosongkan area teks

                // Eksekusi request HTTP secara asynchronous
                client.execute(
                        SimpleRequestProducer.create(request),
                        SimpleResponseConsumer.create(),
                        new FutureCallback<>() {
                            // Callback ketika request berhasil
                            @Override
                            public void completed(final SimpleHttpResponse response) {
                                // Menampilkan status request di console
                                System.out.println(request + "->" + new StatusLine(response));
                                System.out.println(response.getBodyText());

                                // Parsing response JSON
                                JSONParser parser = new JSONParser();
                                try {
                                    // Mengubah response menjadi JSONArray
                                    JSONArray contacts = (JSONArray) parser.parse(response.getBodyText());
                                    // Memproses setiap kontak
                                    contacts.forEach(obj -> {
                                        JSONObject contact = (JSONObject) obj;
                                        // Membuat string untuk setiap kontak
                                        String line = "Name: " + contact.get("name") + ", Phone: " + contact.get("phone");
                                        textArea.append(line + "\n");
                                    });
                                } catch (ParseException ex) {
                                    throw new RuntimeException(ex);
                                }
                                // Mengatur UI setelah proses selesai
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                                statusLabel.setText("Proses selesai");
                            }

                            // Callback ketika request gagal
                            @Override
                            public void failed(final Exception ex) {
                                // Menampilkan error di console
                                System.out.println(request + "->" + ex);
                                // Mengatur UI ketika terjadi kegagalan
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                                statusLabel.setText("Proses gagal");
                            }

                            // Callback ketika request dibatalkan
                            @Override
                            public void cancelled() {
                                // Menampilkan status pembatalan di console
                                System.out.println(request + " cancelled");
                                // Mengatur UI ketika proses dibatalkan
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                                statusLabel.setText("Proses dibatalkan");
                            }
                        });
            });

            // Mengatur posisi frame di tengah layar
            frame.setLocationRelativeTo(null);
            // Menampilkan frame
            frame.setVisible(true);
        });
    }
}