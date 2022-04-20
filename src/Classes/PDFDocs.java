/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

/**
 *
 * @author OSBAL
 */
public class PDFDocs {

    Font fuente = FontFactory.getFont(FontFactory.HELVETICA);

    //ESTA CLASE SE INVOCA PARA IMPRIMIR UNA NOTA DE VENTA
    public void PrintTicket(String[] idv_usr_tot, String printer) {
//        String[] datos_imp = new ConfigDat().getDatTicket();
        String ruta = "docs\\Tickets_Compras\\" + idv_usr_tot[0] + ".pdf";
        try {
//            switch (datos_imp[1]) {
//                case "1":
//            CreatePDFLetterSale(ruta, PageSize.LETTER, TableLetterSale(idv_usr_tot, productos, cantidad, precio_un, p_tot), TableLetterFooterSale(idv_usr_tot));
            CreatePDFLetterSale(ruta, PageSize.LETTER, TableLetterFooterSale(idv_usr_tot));
            PrintDocument(ruta, printer);
//                    break;
//                case "2":
//                    CreatePDFMiniPrintSale(ruta, new Rectangle(200, 792), TableMiniprintSale(idv_usr_tot, productos, cantidad, precio_un, p_tot));
//                    PrintDocument(ruta, printer);
//                    break;
//                case "3":
//                    CreatePDFMiniPrintSale(ruta, new Rectangle(150, 792), TableMiniprintSale(idv_usr_tot, productos, cantidad, precio_un, p_tot));
//                    PrintDocument(ruta, printer);
//                    break;
//            }
        } catch (Exception e) {
            int resp = JOptionPane.showConfirmDialog(null, "<html><h1>¡OOPS NO SE PUDO IMPRIMIR TICKET!</h1><font SIZE=5><p>¿Desea imprimirlo como PDF?</font></html>", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                PrintDocument(ruta, "Imprimir como PDF");
            }
//            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //CREAR DOCUMENTO PDF VENTA TAMAÑO CARTA EN Register_Cash_v1.0.1\docs\Tickets_Compras
//    public void CreatePDFLetterSale(String ruta, Rectangle tamaño, PdfPTable tabla, PdfPTable tableFooter) {
    public void CreatePDFLetterSale(String ruta, Rectangle tamaño, PdfPTable tableFooter) {
        try {
            int margenLeft = 36;
            int margenRigth = 36;
            int margenTop = 18;
            int margenBottom = 18;
            Document doc = new Document(tamaño, margenLeft, margenRigth, margenTop, margenBottom);
            PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();
            doc.add(TableLetterHeaderSale());
            doc.add(new Paragraph(" "));
//            doc.add(tabla);
            tableFooter.setTotalWidth(doc.right(doc.rightMargin()));
            tableFooter.writeSelectedRows(0, -1, margenLeft, tableFooter.getTotalHeight() + doc.bottom(doc.bottomMargin()), pdfw.getDirectContent());
            doc.add(new Paragraph(" "));
            doc.close();
        } catch (Exception e) {
            int resp = JOptionPane.showConfirmDialog(null, "<html><h1>¡OOPS NO SE PUDO IMPRIMIR TICKET!</h1><font SIZE=5><p>¿Desea imprimirlo como PDF?</font></html>", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                PrintDocument(ruta, "Imprimir como PDF");
            }
//            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //CREAR DOCUMENTO PDF VENTA TAMAÑO MINIPRINT EN Register_Cash_v1.0.1\docs\Tickets_Compras
    public void CreatePDFMiniPrintSale(String ruta, Rectangle tamaño, PdfPTable tabla) {
        try {
            int margenLeft = 10;
            int margenRigth = 10;
            int margenTop = 10;
            int margenBottom = 10;
            Document doc = new Document(tamaño, margenLeft, margenRigth, margenTop, margenBottom);
            PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();
            doc.add(TableMiniprintHeaderSale());
            doc.add(new Paragraph(" "));
            doc.add(tabla);
            doc.add(new Paragraph(" "));
            doc.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //PARTES TABLA TAMAÑO CARTA VENTA
    public PdfPTable TableLetterHeaderSale() {
        PdfPTable table = new PdfPTable(4);
        int tamaño = 9;
        table.setWidthPercentage(100);
        PdfPCell celda = new PdfPCell();
        String[] cont = new DatEmpresa().getDatEmpresa();
        try {

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);//SEPARADOR

            //ENCABEZADO
            File f = null;
            if (new File("iconos\\LOGO.png").exists()) {
                f = new File("iconos\\LOGO.png");
            } else {
                f = new File("iconos\\LOGO.png");
            }
            com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance(f.getAbsolutePath());
            imagen.scaleToFit(150, 75);
            imagen.setAlignment(Element.ALIGN_CENTER);
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(2);
            celda.addElement(imagen);
            table.addCell(celda);// LOGO  

            celda = new PdfPCell();
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setColspan(3);
            Paragraph p = new Paragraph(cont[1] + "\n", FontFactory.getFont(fuente.toString(), tamaño + 15));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);

            String text = ""
                    + "RFC: " + cont[2] + "\n"
                    + "ENCARGADO: " + cont[3] + "\n"
                    + "DIRECCION: " + cont[4] + "\n"
                    + "TELEFONO: " + cont[5] + "\n\n";
            p = new Paragraph(text, FontFactory.getFont(fuente.toString(), 6));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);//DATOS EMPRESA

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);//SEPARADOR
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return table;
    }

    public PdfPTable TableLetterSale(String[] idv_usr_total, String[] productos, String[] cantidad, String[] precio_un, String[] p_tot) {
        PdfPTable table = new PdfPTable(4);
        int tamaño = 9;
        table.setWidthPercentage(100);
        PdfPCell celda = new PdfPCell();
        String[] cont = new DatEmpresa().getDatEmpresa();
        try {
            Paragraph p = new Paragraph(cont[1] + "\n", FontFactory.getFont(fuente.toString(), tamaño + 15));

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);//SEPARADOR

            p = new Paragraph("PRODUCTOS PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño + 3));
            p.setAlignment(Element.ALIGN_CENTER);
            celda = new PdfPCell();
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(4);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);//SEPARADOR

            table.addCell(new Phrase("Producto", FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(new Phrase("Cantidad", FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(new Phrase("Precio", FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(new Phrase("Total", FontFactory.getFont(fuente.toString(), tamaño)));
            for (int i = 0; i < productos.length; i++) {
                table.addCell(new Phrase(productos[i], FontFactory.getFont(fuente.toString(), tamaño)));
                table.addCell(new Phrase(cantidad[i], FontFactory.getFont(fuente.toString(), tamaño)));
                table.addCell(new Phrase(precio_un[i], FontFactory.getFont(fuente.toString(), tamaño)));
                table.addCell(new Phrase("$###,###.###".format(p_tot[i]), FontFactory.getFont(fuente.toString(), tamaño)));
            }

            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(3);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase("Total: " + idv_usr_total[2], FontFactory.getFont(fuente.toString(), tamaño)));
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);//SEPARADOR
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return table;
    }

    public PdfPTable TableLetterFooterSale(String[] idv_usr_total) {
        PdfPTable table = new PdfPTable(4);
//        String[] datos_imp = new ConfigDat().getDatTicket();
        int tamaño = 9;
        Paragraph p = new Paragraph();
        table.setWidthPercentage(100);

        try {
            PdfPCell celda = new PdfPCell();
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);//SEPARADOR

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(2);
            table.addCell(celda);

            p = new Paragraph("Fecha: " + new SimpleDateFormat("dd-MM-YYYY").format(new Date()) + ", " + new SimpleDateFormat("HH:mm").format(new Date())
                    + "\nLe atendio: " + idv_usr_total[1]
                    + "\nForma de pago: EFECTIVO", FontFactory.getFont(fuente.toString(), tamaño));
            celda = new PdfPCell(p);
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);

            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(new Barcodes().ImgBC128(idv_usr_total[0]));
            celda.addElement(image);
            p = new Paragraph("Folio: " + idv_usr_total[0], FontFactory.getFont(fuente.toString(), tamaño + 3));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph("PIE DE PAGINA", FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(4);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);//SEPARADOR

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return table;
    }

    // TABLA TAMAÑO MINIPRINT VENTA
    public PdfPTable TableMiniprintHeaderSale() {
        PdfPTable table = new PdfPTable(4);
//        String[] datos_imp = new ConfigDat().getDatTicket();
        String[] cont = new DatEmpresa().getDatEmpresa();
        table.setWidthPercentage(100);
        PdfPCell celda = new PdfPCell();
        Paragraph p = new Paragraph();
        int tamaño = 6;
        int border = 0;
        try {
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            File f = null;
            if (new File("iconos\\LOGO.png").exists()) {
                f = new File("iconos\\LOGO.png");
            } else {
                f = new File("iconos\\LOGO.png");
            }
            com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance(f.getAbsolutePath());
            imagen.scaleToFit(70, 35);
            imagen.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(imagen);
            celda.setColspan(4);
            celda.setBorder(border);
            table.addCell(celda);//logo 

            celda = new PdfPCell();
            String text = ""
                    + "RFC:" + cont[2] + "\n"
                    + "ENCARGADO:" + cont[3] + "\n"
                    + "DIRECCION:" + cont[4] + "\n"
                    + "TELEFONO:" + cont[5] + "\n\n";
            p = new Paragraph(text, FontFactory.getFont(fuente.toString(), tamaño));
            celda.setColspan(4);
            celda.setBorder(border);
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);//DATOS EMPRESA
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return table;
    }

    public PdfPTable TableMiniprintSale(String[] idv_usr_total, String[] productos, String[] cantidad, String[] precio_un, String[] p_tot) {
        PdfPTable table = new PdfPTable(4);
//        String[] datos_imp = new ConfigDat().getDatTicket();
        String[] cont = new DatEmpresa().getDatEmpresa();
        table.setWidthPercentage(100);
        PdfPCell celda = new PdfPCell();
        Paragraph p = new Paragraph();
        int tamaño = 6;
        int border = 0;
        try {
            celda = new PdfPCell();
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph("PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(4);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell();
            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph("PROD", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("CANT", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("P_UNIT", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);
            celda = new PdfPCell(new Phrase("TOT_UNIT", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(celda);

            for (int i = 0; i < productos.length; i++) {
                celda = new PdfPCell(new Phrase(productos[i].toUpperCase(), FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);
                celda = new PdfPCell(new Phrase(cantidad[i], FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);
                celda = new PdfPCell(new Phrase(precio_un[i], FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);
                celda = new PdfPCell(new Phrase(p_tot[i], FontFactory.getFont(fuente.toString(), tamaño)));
                celda.setBorder(border);
                celda.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(celda);
            }//añadir productos

            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(3);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase("Total: " + idv_usr_total[2], FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);

            p = new Paragraph("FECHA: " + new SimpleDateFormat("dd-MM-YYYY").format(new Date()) + ", " + new SimpleDateFormat("HH:mm").format(new Date())
                    + "\nLE ATENDIO: " + idv_usr_total[1].toUpperCase()
                    + "\nFORMA DE PAGO: EFECTIVO", FontFactory.getFont(fuente.toString(), tamaño));
            celda = new PdfPCell(p);
            celda.setColspan(4);
            celda.setBorder(border);
            p.setAlignment(Element.ALIGN_CENTER);
            table.addCell(celda);

            celda = new PdfPCell();
            celda.setColspan(4);
            celda.setBorder(border);
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(new Barcodes().ImgBC128(idv_usr_total[0]));
            celda.addElement(image);
            p = new Paragraph("FOLIO: " + idv_usr_total[0], FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.addElement(p);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);

            celda = new PdfPCell();
            p = new Paragraph("DATTIKET[2]".toUpperCase(), FontFactory.getFont(fuente.toString(), tamaño));
            p.setAlignment(Element.ALIGN_CENTER);
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celda.addElement(p);
            celda.setColspan(4);
            celda.setBorder(border);
            table.addCell(celda);

            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
            celda.setColspan(4);
            celda.setBorder(border);
            celda.setBackgroundColor(BaseColor.GRAY);
            table.addCell(celda);

            celda = new PdfPCell(new Paragraph(" "));
            celda.setColspan(4);
            celda.setBorder(2);
            table.addCell(celda);//CORTAR

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return table;
    }

    //ESTA CLASE SE INVOCA PARA IMPRIMIR UN ESTADO DE RESULTADOS
    public void PrintEdoResTicket(String[] id_usr_date_hour, String printer) {
//        String[] datos_imp = new ConfigDat().getDatTicket();
        String ruta = "docs\\EdoRes\\" + id_usr_date_hour[0] + ".pdf";
        try {

//            switch (datos_imp[1]) {
//                case "1":
            CreatePDFLetterEdoRes(ruta, PageSize.LETTER, TableLetterEdoRes(), TableLetterFooterEdoRes(id_usr_date_hour));
            PrintDocument(ruta, printer);
//                    break;
//                case "2":
//                    CreatePDFMiniPrintEdoRes(ruta, new Rectangle(200, 792), TableMiniprintEdoRes(id_usr_date_hour));
//                    PrintDocument(ruta, printer);
//                    break;
//                case "3":
//                    CreatePDFMiniPrintEdoRes(ruta, new Rectangle(150, 792), TableMiniprintEdoRes(id_usr_date_hour));
//                    PrintDocument(ruta, printer);
//                    break;
//            }
        } catch (Exception e) {
            int resp = JOptionPane.showConfirmDialog(null, "<html><h1>¡OOPS NO SE PUDO IMPRIMIR TICKET!</h1><font SIZE=5><p>¿Desea imprimirlo como PDF?</font></html>", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                PrintDocument(ruta, "Imprimir como PDF");
            }
//            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //PARTES TABLA TAMAÑO CARTA ESTADO DE RESULTADOS
    public PdfPTable TableLetterEdoRes() {
        PdfPTable table = new PdfPTable(7);
        int tamaño = 9;
        table.setWidthPercentage(100);
        PdfPCell celda = new PdfPCell();
        String[] cont = new DatEmpresa().getDatEmpresa();
//        String[] prod_vendidos = new EdoRes().genRegistrosEdoRes();
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        try {
//            Paragraph p = new Paragraph(cont[1] + "\n", FontFactory.getFont(fuente.toString(), tamaño + 15));
//
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);//SEPARADOR
//
//            p = new Paragraph("ESTADO DE RESULTADOS", FontFactory.getFont(fuente.toString(), tamaño + 3));
//            p.setAlignment(Element.ALIGN_CENTER);
//            celda = new PdfPCell();
//            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            celda.addElement(p);
//            celda.setColspan(7);
//            table.addCell(celda);
//
//            p = new Paragraph("PRODUCTOS VENDIDOS", FontFactory.getFont(fuente.toString(), tamaño + 3));
//            p.setAlignment(Element.ALIGN_CENTER);
//            celda = new PdfPCell();
//            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            celda.addElement(p);
//            celda.setColspan(7);
//            table.addCell(celda);
//
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);//SEPARADOR
//
//            table.addCell(new Phrase("PRODUCTO", FontFactory.getFont(fuente.toString(), tamaño)));
//            table.addCell(new Phrase("CANTIDAD", FontFactory.getFont(fuente.toString(), tamaño)));
//            table.addCell(new Phrase("PRECIO", FontFactory.getFont(fuente.toString(), tamaño)));
//            table.addCell(new Phrase("TOTAL", FontFactory.getFont(fuente.toString(), tamaño)));
//            table.addCell(new Phrase("VENDEDOR", FontFactory.getFont(fuente.toString(), tamaño)));
//            table.addCell(new Phrase("FECHA VENTA", FontFactory.getFont(fuente.toString(), tamaño)));
//            table.addCell(new Phrase("HORA VENTA", FontFactory.getFont(fuente.toString(), tamaño)));
//
//            for (int i = 0; i < prod_vendidos.length; i++) {
//                String[] prod_vendido = prod_vendidos[i].split(",");
//                for (int j = 0; j < prod_vendido.length; j++) {
//                    if (j == 2 || j == 3) {
//                        prod_vendido[j] = format.format(Double.parseDouble(prod_vendido[j]));
//                    }
//                }
//                for (int j = 0; j < prod_vendido.length; j++) {
//                    table.addCell(new Phrase(prod_vendido[j], FontFactory.getFont(fuente.toString(), tamaño)));
//                }
//            }
//            celda = new PdfPCell(new Paragraph(" "));
//            celda.setColspan(5);
//            table.addCell(celda);
//            celda = new PdfPCell(new Phrase("TOTAL DEL DIA: " + new EdoRes().getTotalRegEdoRes(prod_vendidos), FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(2);
//            table.addCell(celda);
//
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);//SEPARADOR

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return table;
    }

    public PdfPTable TableLetterFooterEdoRes(String[] id_usr_date_hour) {
        PdfPTable table = new PdfPTable(7);
        int tamaño = 9;
        Paragraph p = new Paragraph();
        table.setWidthPercentage(100);
        String texto = "Este documento fue realizado por el usuario " + id_usr_date_hour[1] + " el día " + id_usr_date_hour[2] + " a las " + id_usr_date_hour[3] + " hrs";
        try {
//            PdfPCell celda = new PdfPCell();
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);//SEPARADOR
//
//            celda = new PdfPCell();
//            p = new Paragraph(texto, FontFactory.getFont(fuente.toString(), tamaño));
//            p.setAlignment(Element.ALIGN_CENTER);
//            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            celda.addElement(p);
//            celda.setColspan(7);
//            table.addCell(celda);
//
//            celda = new PdfPCell();
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);//SEPARADOR
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
//        }
//        return table;
//    }
//
//    //TABLA TAMAÑO MINIPRINT ESTADO DE RESULTADOS
//    public PdfPTable TableMiniprintEdoRes(String[] id_usr_date_hour) {
//        PdfPTable table = new PdfPTable(7);
//        int tamaño = 4;
//        int border = 0;
//        table.setWidthPercentage(100);
//        PdfPCell celda = new PdfPCell();
//        Paragraph p = new Paragraph();
//        String[] cont = new DatEmpresa().getDatEmpresa();
//        String[] prod_vendidos = new EdoRes().genRegistrosEdoRes();
//        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
//        String texto = "Este documento fue realizado por el usuario " + id_usr_date_hour[1] + " el día " + id_usr_date_hour[2] + " a las " + id_usr_date_hour[3] + " hrs";
//        try {
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBorder(border);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);
//
//            celda = new PdfPCell();
//            File f = null;
//            if (new File("iconos\\LOGO.png").exists()) {
//                f = new File("iconos\\LOGO.png");
//            } else {
//                f = new File("iconos\\LOGO.png");
//            }
//            com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance(f.getAbsolutePath());
//            imagen.scaleToFit(70, 35);
//            imagen.setAlignment(Element.ALIGN_CENTER);
//            celda.addElement(imagen);
//            celda.setColspan(7);
//            celda.setBorder(border);
//            table.addCell(celda);//logo 
//
//            celda = new PdfPCell();
//            String text = ""
//                    + "RFC:" + cont[2] + "\n"
//                    + "ENCARGADO:" + cont[3] + "\n"
//                    + "DIRECCION:" + cont[4] + "\n"
//                    + "TELEFONO:" + cont[5] + "\n\n";
//            p = new Paragraph(text, FontFactory.getFont(fuente.toString(), tamaño));
//            celda.setColspan(7);
//            celda.setBorder(border);
//            p.setAlignment(Element.ALIGN_CENTER);
//            celda.addElement(p);
//            table.addCell(celda);//DATOS EMPRESA
//
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBorder(border);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);
//
//            celda = new PdfPCell();
//            p = new Paragraph("ESTADO DE RESULTADOS", FontFactory.getFont(fuente.toString(), tamaño));
//            p.setAlignment(Element.ALIGN_CENTER);
//            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            celda.addElement(p);
//            celda.setColspan(7);
//            celda.setBorder(border);
//            table.addCell(celda);
//
//            celda = new PdfPCell();
//            p = new Paragraph("PRODUCTOS VENDIDOS DEL DIA", FontFactory.getFont(fuente.toString(), tamaño));
//            p.setAlignment(Element.ALIGN_CENTER);
//            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            celda.addElement(p);
//            celda.setColspan(7);
//            celda.setBorder(border);
//            table.addCell(celda);
//
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBorder(border);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);
//
//            table.addCell(new Phrase("PRODUCTo", FontFactory.getFont(fuente.toString(), tamaño - 1)));
//            table.addCell(new Phrase("CANTIDAD", FontFactory.getFont(fuente.toString(), tamaño - 1)));
//            table.addCell(new Phrase("PRECIO", FontFactory.getFont(fuente.toString(), tamaño - 1)));
//            table.addCell(new Phrase("TOTAL", FontFactory.getFont(fuente.toString(), tamaño - 1)));
//            table.addCell(new Phrase("VENDEDOR", FontFactory.getFont(fuente.toString(), tamaño - 1)));
//            table.addCell(new Phrase("FECHA VENTA", FontFactory.getFont(fuente.toString(), tamaño - 1)));
//            table.addCell(new Phrase("HORA VENTA", FontFactory.getFont(fuente.toString(), tamaño - 1)));
//
//            for (int i = 0; i < prod_vendidos.length; i++) {
//                String[] prod_vendido = prod_vendidos[i].split(",");
//                for (int j = 0; j < prod_vendido.length; j++) {
//                    if (j == 2 || j == 3) {
//                        prod_vendido[j] = format.format(Double.parseDouble(prod_vendido[j]));
//                    }
//                }
//                for (int j = 0; j < prod_vendido.length; j++) {
//                    table.addCell(new Phrase(prod_vendido[j], FontFactory.getFont(fuente.toString(), tamaño - 1)));
//                }
//            }
//
//            celda = new PdfPCell(new Paragraph(" "));
//            celda.setColspan(5);
//            celda.setBorder(border);
//            table.addCell(celda);
//
//            celda = new PdfPCell(new Phrase("TOTAL DEL DIA: " + new EdoRes().getTotalRegEdoRes(prod_vendidos), FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(2);
//            celda.setBorder(border);
//            table.addCell(celda);
//
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBorder(border);
//            celda.setBackgroundColor(BaseColor.WHITE);
//            table.addCell(celda);
//            table.addCell(celda);
//
//            celda = new PdfPCell();
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBorder(border);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);//SEPARADOR
//
//            celda = new PdfPCell();
//            p = new Paragraph(texto, FontFactory.getFont(fuente.toString(), tamaño));
//            p.setAlignment(Element.ALIGN_CENTER);
//            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
//            celda.addElement(p);
//            celda.setColspan(7);
//            celda.setBorder(border);
//            table.addCell(celda);
//
//            celda = new PdfPCell();
//            celda = new PdfPCell(new Phrase(" ", FontFactory.getFont(fuente.toString(), tamaño)));
//            celda.setColspan(7);
//            celda.setBorder(border);
//            celda.setBackgroundColor(BaseColor.GRAY);
//            table.addCell(celda);//SEPARADOR
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        return table;
    }

    //CREAR DOCUMENTO PDF ESTADO DE RESULTADOS TAMAÑO CARTA EN Register_Cash_v1.0.1\docs\EdoRes
    public void CreatePDFLetterEdoRes(String ruta, Rectangle tamaño, PdfPTable tabla, PdfPTable tableFooter) {
        try {
            int margenLeft = 36;
            int margenRigth = 36;
            int margenTop = 18;
            int margenBottom = 18;
            Document doc = new Document(tamaño, margenLeft, margenRigth, margenTop, margenBottom);
            PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();
            doc.add(TableLetterHeaderSale());
            doc.add(new Paragraph(" "));
            doc.add(tabla);
            doc.add(new Paragraph(" "));
            tableFooter.setTotalWidth(doc.right(doc.rightMargin()));
            tableFooter.writeSelectedRows(0, -1, margenLeft, tableFooter.getTotalHeight() + doc.bottom(doc.bottomMargin()), pdfw.getDirectContent());
            doc.add(new Paragraph(" "));
            doc.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //CREAR DOCUMENTO PDF ESTADO DE RESULTADOS TAMAÑO MINIPRINTER EN Register_Cash_v1.0.1\docs\EdoRes
    public void CreatePDFMiniPrintEdoRes(String ruta, Rectangle tamaño, PdfPTable tabla) {
        try {
            int margenLeft = 10;
            int margenRigth = 10;
            int margenTop = 10;
            int margenBottom = 10;
            Document doc = new Document(tamaño, margenLeft, margenRigth, margenTop, margenBottom);
            PdfWriter pdfw = PdfWriter.getInstance(doc, new FileOutputStream(ruta));
            doc.open();
            doc.add(tabla);
            doc.add(new Paragraph(" "));
            doc.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //IMPRIMIR DOCUMENTO EN FISICO
    public void PrintTicket(String ruta, String printer) {
        try {
            File arch = new File(ruta);
            PDDocument document = PDDocument.load(arch);
            PrintService myPrintService = this.getPrintDevice(printer);
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPageable(new PDFPageable(document));
            printerJob.setPrintService(myPrintService);
            printerJob.print();
            JOptionPane.showMessageDialog(null, "<html><h1>ARCHIVO IMPRESO CORRECTAMENTE</h1><font SIZE=5><p> Clic para cerrar…</font></html>", "¡Terminado!", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            int resp = JOptionPane.showConfirmDialog(null, "<html><h1>¡OOPS NO SE PUDO IMPRIMIR TICKET!</h1><font SIZE=5><p>¿Desea imprimirlo como PDF?</font></html>", "ADVERTENCIA", JOptionPane.YES_NO_OPTION);
            if (resp == 0) {
                PrintDocument(ruta, "Imprimir como PDF");
            }
//            JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //ESCOGER TIPO DE IMPRESION
    public void PrintDocument(String ruta, String impresora) {
        switch (impresora) {
            case "Imprimir como PDF":
                DigitalPrint(ruta);
                break;
            default:
                PrintTicket(ruta, impresora);
                break;
        }
    }

    //ESCOGER RUTA DE COPIADO DE ARCHIVO IMPRESO COMO PDF
    public void DigitalPrint(String ruta) {
        Path origen = FileSystems.getDefault().getPath(ruta);
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de PDF", "PDF", "pdf");
        chooser.setFileFilter(filtro);
        chooser.setSelectedFile(new File(new File(ruta).getName()));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                Path destino = FileSystems.getDefault().getPath(chooser.getSelectedFile().getAbsolutePath() + ".pdf");
                if (new File(destino.toString()).exists()) {
                    if (JOptionPane.showConfirmDialog(null, "Este archivo ya existe... \ndesea reemplazarlo?", "¡Atencion!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                        JOptionPane.showMessageDialog(null, "<html><h1>ARCHIVO GUARDADO CORRECTAMENTE</h1><font SIZE=5><p> Clic para cerrar…</font></html>", "¡Terminado!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
                    JOptionPane.showMessageDialog(null, "<html><h1>ARCHIVO GUARDADO CORRECTAMENTE</h1><font SIZE=5><p> Clic para cerrar…</font></html>", "¡Terminado!", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (HeadlessException | IOException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e, "¡ERROR!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //OBTENER SERVICIO DE IMPRESION BUSCANDOLO POR NOMBRE
    public PrintService getPrintDevice(String nombre) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService impresora : printServices) {
            if (impresora.getName().contentEquals(nombre)) {
                return impresora;
            }
        }
        return null;
    }
}
