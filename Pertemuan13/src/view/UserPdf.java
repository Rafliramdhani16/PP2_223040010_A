package view;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import model.User;

import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class UserPdf {
    public void exportPdf(List<User> users) {
        String filePath = "users.pdf";
        Document document = new Document(PageSize.A4);

        try {
            File file = new File(filePath);

            // Cek apakah file PDF sedang dibuka
            if(file.exists() && !file.canWrite()) {
                JOptionPane.showMessageDialog(null,
                        "PDF sedang dibuka di program lain. Mohon tutup terlebih dahulu.");
                return;
            }

            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Setup table
            PdfPTable table = new PdfPTable(3); // 3 kolom
            table.setWidthPercentage(100);

            // Header
            table.addCell("ID");
            table.addCell("Name");
            table.addCell("Email");

            // Isi data
            for (User user : users) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getName());
                table.addCell(user.getEmail());
            }

            document.add(new Paragraph("User List"));
            document.add(table);
            document.close();

            // Buka PDF setelah dibuat
            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error membuat PDF: " + e.getMessage());
        }
    }
}