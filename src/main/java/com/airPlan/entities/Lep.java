package com.airPlan.entities;




import com.airPlan.services.CodeListService;
import com.airPlan.services.ManualService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lep{

    private String mnl_name;
    private String flg_tag;
    private String cdl_code;
    private String revision_dates;
    private String cdl_code_list;



    // Main Method



}

/*    ArrayList<String> filteredRevisionDates = new ArrayList<>();
        for (int i = 0; i < rvParts.length; i++) {
        for (int j = 0; j < rvParts[i].length(); i++) {
        String temp = String.valueOf(rvParts[i].charAt(j));
        if (temp.matches("-?\\d+(\\.\\d+)?")) {
        String[] tempParts = rvParts[i].split(temp);
        String revisionFinal = String.join(".................",
        tempParts[0], temp, tempParts[1]);
        filteredRevisionDates.add(revisionFinal);
        }
        }
        }


        for (String s : filteredRevisionDates) {
        System.out.println(s);
        }
        }*/

   /* public void populatePaths() throws IOException {
        String[] tag = this.flg_tag.split("-");
        try (Stream<Path> filepath
                     = Files.walk(Paths.get("./manuals/"))) {
            filepath.forEach(s -> {
                if (s.toString().contains("c" + cdl_code) || s.toString().contains("c" + tag)) {
                    this.paths.add(s.toString());
                }
            });

            for (String s : this.paths) {
                System.out.println(s);
            }
        }

        // if no such directory exists throw an exception.
        catch (IOException e) {
            throw new IOException("Directory Not Present!");
        }
    }
}
    public static void main(String[] args) throws IOException, DocumentException {

        ArrayList<String> arr = new ArrayList<>();
        // create try-catch block and provide
        // the directory path
        try (Stream<Path> filepath
                     = Files.walk(Paths.get("./manuals/")))

        {
            // print the name of directories and files with
            // entire path
            filepath.forEach(s-> {
                if(s.toString().contains("c01")){
                    arr.add(s.toString());
                }
            });

            for(String s: arr) {
                System.out.println(s);
            }
        }

        // if no such directory exists throw an exception.
        catch (IOException e)
        {
            throw new IOException("Directory Not Present!");
        }

        // Creating a PdfWriter
        String destPath = Paths.get("./manuals/test.pdf").toString();

        String paraText = ("                                       LIST OF EFFECTIVE PAGES");
        Paragraph paragraph1 = new Paragraph(paraText);
        PdfWriter pdfWriter = new PdfWriter(destPath);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);

        document.add(paragraph1.setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(18));

        document.showTextAligned(new Paragraph(String
                        .format("0-LEP")).setBold().setFontSize(16),
                300, 60, 1, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String
                                .format("List of Effective Pages")),
                                560, 830, 1, TextAlignment.RIGHT,
                                VerticalAlignment.TOP, 0);

        int n = pdfDocument.getNumberOfPages();
        for (int i = 1; i <= n; i++)
        {

            document.showTextAligned(new Paragraph(String
                            .format("Page " + i )).setFontSize(14),
                    560, 30, i, TextAlignment.RIGHT,
                    VerticalAlignment.BOTTOM, 0);
        }

        document.close();

        System.out.println("Paragraph added");
    }
}*/
