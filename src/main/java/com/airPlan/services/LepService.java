package com.airPlan.services;

import com.airPlan.entities.Lep;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class LepService {

    @Autowired
    private ManualService manualService;
    @Autowired
    private CodeListService codeListService;

    ArrayList<String> paths;
    ArrayList<String> filterRevisionDates = new ArrayList<>();


    public void populateRevisionDates(Lep lep) throws FileNotFoundException {

        ArrayList<String> filteredRevisionDates = new ArrayList<>();
        String[] rvParts = lep.getRevision_dates().split("-");

        for(String s: rvParts) {
            System.out.println(s);
        }
        System.out.println(rvParts);

        for (int i = 0; i < rvParts.length; i++) {
            System.out.println(rvParts[i]);
            String rvFinal = "";
            for (int j = 0; j < rvParts[i].length(); j++) {
                rvFinal+=rvParts[i].charAt(j);
                if(j == 7 || j == 8) {
                    rvFinal+=".................";
                }
            }
            this.filterRevisionDates.add(rvFinal);
        }


        for (String s : this.filterRevisionDates) {
            System.out.println(s);
        }

        createPage1(lep.getCdl_code(), lep.getMnl_name());
    }


    // Creating a PdfWriter
    public void createPage1(String code, String manualName) throws FileNotFoundException {

        String destPath = Paths.get("./manuals/test.pdf").toString();



        Paragraph paragraph1 = new Paragraph("LIST OF EFFECTIVE PAGES");

        PdfWriter pdfWriter = new PdfWriter(destPath);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);

        document.add(paragraph1.setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(18));

        for(String s: this.filterRevisionDates) {
            Paragraph temp = new Paragraph(s);
            document.add(temp.setTextAlignment(TextAlignment.CENTER).setFontSize(16));
        }

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        document.showTextAligned(new Paragraph(String.format("INTENTIONALLY BLANK")).setFontSize(14),
                300, 500, 2, TextAlignment.CENTER,
                VerticalAlignment.MIDDLE, 0);


        int n = pdfDocument.getNumberOfPages();
        for (int i = 1; i <= n; i++) {
            if(i%2==0) {
                document.showTextAligned(new Paragraph(String
                                .format("Page " + i )).setFontSize(14),
                        100, 30, i, TextAlignment.RIGHT,
                        VerticalAlignment.BOTTOM, 0);

                document.showTextAligned(new Paragraph(String.format("List of Effective Pages")),
                        50, 830, i, TextAlignment.LEFT,
                        VerticalAlignment.TOP, 0);

                document.showTextAligned(new Paragraph(String.format(manualName)).setFontSize(8),
                        560, 80, i, TextAlignment.LEFT,
                        VerticalAlignment.BOTTOM, 300);
            } else {
                document.showTextAligned(new Paragraph(String
                                .format("Page " + i )).setFontSize(14),
                        550, 30, i, TextAlignment.RIGHT,
                        VerticalAlignment.BOTTOM, 0);

                document.showTextAligned(new Paragraph(String.format("List of Effective Pages")),
                        560, 830, i, TextAlignment.RIGHT,
                        VerticalAlignment.TOP, 0);

                document.showTextAligned(new Paragraph(String.format(manualName)).setFontSize(8),
                        50, 80, i, TextAlignment.LEFT,
                        VerticalAlignment.TOP, 300);
            }
            document.showTextAligned(new Paragraph(String.format("0-LEP")).setBold().setFontSize(16),
                    300, 60, i, TextAlignment.CENTER,
                    VerticalAlignment.TOP, 0);

            document.showTextAligned(new Paragraph(String.format("Code " + code)).setFontSize(14),
                    300, 30, i, TextAlignment.CENTER,
                    VerticalAlignment.TOP, 0);


        }

        document.close();

        System.out.println("Paragraph added");
    }

}
