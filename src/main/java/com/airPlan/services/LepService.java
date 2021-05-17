package com.airPlan.services;

import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.List;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.kernel.pdf.canvas.parser.listener.ITextExtractionStrategy;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.airPlan.entities.CodeList;
import java.nio.file.Files;
import java.util.HashMap;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import java.nio.file.Paths;
import java.io.IOException;
import com.airPlan.entities.Lep;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LepService
{
    @Autowired
    private ManualService manualService;
    @Autowired
    private CodeListService codeListService;
    ArrayList<String> filterRevisionDates;

    public LepService() {
        this.filterRevisionDates = new ArrayList<String>();
    }

    public void populateRevisionDates(final Lep lep) throws IOException {
        final String[] rvParts = lep.getRevision_dates().split("-");
        for (int i = 0; i < rvParts.length; ++i) {
            String rvFinal = "";
            for (int j = 0; j < rvParts[i].length(); ++j) {
                rvFinal += rvParts[i].charAt(j);
                if (j == 7 || j == 8) {
                    rvFinal += ".................";
                }
            }
            this.filterRevisionDates.add(rvFinal);
        }
        final Integer mnl_id = this.manualService.findManualByName(lep.getMnl_name());
        this.createPage1(lep.getCdl_code(), lep.getMnl_name(), lep.getFlg_tag(), mnl_id);
    }

    public void createPage1(final String code, final String manualName, final String flgTag, final Integer mnlId) throws IOException {
        List<CodeList> listCode1 = this.codeListService.filtroLep(mnlId, flgTag);
        String destPath = String.valueOf(Paths.get("./manuals/" + manualName.toUpperCase() + "-00-02c" + code + ".pdf"));
        Paragraph paragraph1 = new Paragraph("LIST OF EFFECTIVE PAGES");
        PdfWriter pdfWriter = new PdfWriter(destPath);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();
        final Document document = new Document(pdfDocument);
        document.add(paragraph1.setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(18.0f));
        for (final String s2 : this.filterRevisionDates) {
            final Paragraph temp = new Paragraph(s2);
            document.add(temp.setTextAlignment(TextAlignment.CENTER).setFontSize(16.0f));
        }
        // INTERFACE
        document.showTextAligned(new Paragraph(String
                        .format("Page " + 1)).setFontSize(14),
                550, 30, 1, TextAlignment.RIGHT,
                VerticalAlignment.BOTTOM, 0);

        document.showTextAligned(new Paragraph(String.format("List of Effective Pages")),
                560, 830, 1, TextAlignment.RIGHT,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String.format(manualName)).setFontSize(8),
                50, 80, 1, TextAlignment.LEFT,
                VerticalAlignment.TOP, 300);

        document.showTextAligned(new Paragraph(String.format("0-LEP")).setBold().setFontSize(16),
                300, 60, 1, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String.format("Code " + code)).setFontSize(14),
                300, 30, 1, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);


        // Create and select next page
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        document.showTextAligned(new Paragraph(String.format("INTENTIONALLY BLANK")).setFontSize(14),
                300, 500, 2, TextAlignment.CENTER,
                VerticalAlignment.MIDDLE, 0);

        // begin interface
        document.showTextAligned(new Paragraph(String
                        .format("Page " + 2)).setFontSize(14),
                100, 30, 2, TextAlignment.RIGHT,
                VerticalAlignment.BOTTOM, 0);

        document.showTextAligned(new Paragraph(String.format("List of Effective Pages")),
                50, 830, 2, TextAlignment.LEFT,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String.format(manualName)).setFontSize(8),
                560, 80, 2, TextAlignment.LEFT,
                VerticalAlignment.BOTTOM, 300);

        document.showTextAligned(new Paragraph(String.format("0-LEP")).setBold().setFontSize(16),
                300, 60, 2, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String.format("Code " + code)).setFontSize(14),
                300, 30, 2, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);

        // end interface



        HashMap<String, ArrayList> lepTable = new HashMap<>();
        ArrayList<String> coverList = new ArrayList<>();
        lepTable.put("01 Cover", coverList);
        ArrayList<String> tocList = new ArrayList<>();
        lepTable.put("03 Table of Contents", tocList);
        ArrayList<String> storyList = new ArrayList<>();
        lepTable.put("02 Story", storyList);
        ArrayList<String> chapterList = new ArrayList<>();
        lepTable.put("03 Chapter", chapterList);
        ArrayList<String> middleList = new ArrayList<>();
        lepTable.put("04 Middle", middleList);
        ArrayList<String> generalDataList = new ArrayList<>();
        lepTable.put("05 General Data", generalDataList);
        ArrayList<String> appendixList = new ArrayList<>();
        lepTable.put("AP01 Appendix", appendixList);
        ArrayList<String> supplementList = new ArrayList<>();
        lepTable.put("S03 Supplement", supplementList);
        try {
            Stream<Path> filepath = Files.walk(Paths.get("./manuals/"));
            try {
                filepath.forEach(s -> {
                    String tempStr = s.toString();
                    for (CodeList y: listCode1) {
                        if (tempStr.contains(y.getCdl_block_name()) && tempStr.contains(".pdf")) {
                            if (y.getCdl_code() < 10) {
                                if (tempStr.contains("01 Cover")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("01 Cover").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("03 Table of Contents")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("03 Table of Contents").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("02 Story")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("02 Story").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("03 Chapter")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("03 Chapter").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("04 Middle")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("04 Middle").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("05 General Data")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("05 General Data").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("AP01 Appendix")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("AP01 Appendix").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("S03 Supplement")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("S03 Supplement").add(tempStr);
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                else {
                                    continue;
                                }
                            }
                            else {
                                if (tempStr.contains("Cover")) {
                                    if (tempStr.contains("c" + y.getCdl_code())) {
                                        lepTable.get("01 Cover").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("03 Table of Contents")) {
                                    if (tempStr.contains("c" + y.getCdl_code())) {
                                        lepTable.get("03 Table of Contents").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("02 Story")) {
                                    if (tempStr.contains("c" + y.getCdl_code())) {
                                        lepTable.get("02 Story").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("03 Chapter")) {
                                    if (tempStr.contains("c" + y.getCdl_code())) {
                                        lepTable.get("03 Chapter").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("04 Middle")) {
                                    if (tempStr.contains("c" + y.getCdl_code())) {
                                        lepTable.get("04 Middle").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("05 General Data")) {
                                    if (tempStr.contains("c" + y.getCdl_code())) {
                                        lepTable.get("05 General Data").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("AP01 Appendix")) {
                                    if (tempStr.contains("c" + y.getCdl_code())) {
                                        lepTable.get("AP01 Appendix").add(tempStr);
                                        continue;
                                    }
                                }
                                if (tempStr.contains("S03 Supplement")) {
                                    if (tempStr.contains("c" + y.getCdl_code())) {
                                        lepTable.get("S03 Supplement").add(tempStr);
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                else {
                                    continue;
                                }
                            }
                        }
                        else if (tempStr.contains("03 Table of Contents") && y.getCdl_block_name().equals("TOC") && tempStr.contains(".pdf")) {
                            if (y.getCdl_code() < 10) {
                                if (tempStr.contains("03 Table of Contents")) {
                                    if (tempStr.contains("c0" + y.getCdl_code())) {
                                        lepTable.get("03 Table of Contents").add(tempStr);
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                else {
                                    continue;
                                }
                            }
                            else if (tempStr.contains("03 Table of Contents")) {
                                if (tempStr.contains("c" + y.getCdl_code())) {
                                    lepTable.get("03 Table of Contents").add(tempStr);
                                }
                                else {
                                    continue;
                                }
                            }
                            else {
                                continue;
                            }
                        }
                        else {
                            continue;
                        }
                    }
                    return;
                });
                if (filepath != null) {
                    filepath.close();
                }
            }
            catch (Throwable t) {
                if (filepath != null) {
                    try {
                        filepath.close();
                    }
                    catch (Throwable exception) {
                        t.addSuppressed(exception);
                    }
                }
                throw t;
            }
        }
        catch (IOException e) {
            throw new IOException("Directory Not Present!");
        }

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        float[] columnWidths = { 60.0f, 60.0f, 60.0f, 90.0f, 60.0f };
        Table table = new Table(columnWidths);
        table.addCell(new Cell().add("Block"));
        table.addCell(new Cell().add("Code"));
        table.addCell(new Cell().add("Page"));
        table.addCell(new Cell().add("    "));
        table.addCell(new Cell().add("Change"));
        if (lepTable.get("01 Cover") != null) {
            table.addCell(new Cell().add("0-TITLE"));
            table.addCell(new Cell().add("00"));
            table.addCell((Cell)new Cell().add("cover").setBold());
            table.addCell(new Cell().add("*"));
            table.addCell(new Cell().add("REVISION 06"));
        }
        for (int i = 1; i <= 4; ++i) {
            table.addCell(new Cell().add("0-LEP"));
            table.addCell(new Cell().add(code));
            table.addCell(new Cell().add(String.valueOf(i)));
            table.addCell(new Cell().add("*"));
            table.addCell(new Cell().add("REVISION 06"));
        }
        for (int j = 0; j < lepTable.get("03 Table of Contents").size(); ++j) {
            String path = String.valueOf(lepTable.get("03 Table of Contents").get(j));
            PdfReader pdfReader = new PdfReader(path);
            PdfDocument doc1 = new PdfDocument(pdfReader);
            for (int n2 = doc1.getNumberOfPages(), k = 1; k <= n2; ++k) {
                String text = PdfTextExtractor.getTextFromPage(doc1.getPage(k), new SimpleTextExtractionStrategy());
                String[] textParts = text.split("\n");
                ArrayList<String> textPartsf = new ArrayList<>();
                for (final String x1 : textParts) {
                    if (!x1.equals(" ") && !x1.equals("\\n")) {
                        textPartsf.add(x1);
                    }
                }
                String x2 = textPartsf.get(2) + textPartsf.get(3);
                String[] x3 = x2.split(" ");
                if (k % 2 == 0) {
                    final String block = x3[1];
                    final String code2 = x3[6];
                    final String page = x3[3] + " " + x3[4];
                    final String change = x3[7];
                    table.addCell(new Cell().add(block));
                    table.addCell(new Cell().add(code2));
                    table.addCell(new Cell().add(page));
                    table.addCell(new Cell().add(""));
                    if (change.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(change));
                    }
                }
                else {
                    final String block = x3[1];
                    final String code2 = x3[4];
                    final String page = x3[5] + " " + x3[6];
                    final String change = x3[2];
                    table.addCell(new Cell().add(block));
                    table.addCell(new Cell().add(code2));
                    table.addCell(new Cell().add(page));
                    table.addCell(new Cell().add(""));
                    if (change.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(change));
                    }
                }
            }
            pdfReader.close();
            doc1.close();
        }
        for (int j = 0; j < lepTable.get("02 Story").size(); ++j) {
            PdfReader pdfReader2 = new PdfReader(String.valueOf(lepTable.get("02 Story").get(j)));
            PdfDocument doc2 = new PdfDocument(pdfReader2);
            for (int n3 = doc2.getNumberOfPages(), l = 1; l <= n3; ++l) {
                String text2 = PdfTextExtractor.getTextFromPage(doc2.getPage(l), new SimpleTextExtractionStrategy());
                String[] textParts2 = text2.split("\n");
                ArrayList<String> textPartsf2 = new ArrayList<>();
                for (final String x4 : textParts2) {
                    if (!x4.equals(" ")) {
                        textPartsf2.add(x4);
                    }
                }
                String x5 = textPartsf2.get(0) + textPartsf2.get(1);
                String[] x6 = x5.split(" ");
                if (l % 2 == 0) {
                    if (x6.length == 9) {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7] + " " + x6[8];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                    else {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                }
                else if (x6.length == 9) {
                    final String block2 = x6[1];
                    final String code3 = x6[6];
                    final String page2 = x6[7] + " ";
                    final String change2 = x6[3] + " " + x6[4];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
                else if (x6.length == 8) {
                    final String block2 = x6[1];
                    final String code3 = x6[4];
                    final String page2 = x6[5] + " ";
                    final String change2 = x6[2];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
            }
            pdfReader2.close();
            doc2.close();
        }
        for (int j = 0; j < lepTable.get("03 Chapter").size(); ++j) {
            PdfReader pdfReader2 = new PdfReader(String.valueOf(lepTable.get("03 Chapter").get(j)));
            PdfDocument doc2 = new PdfDocument(pdfReader2);
            for (int n3 = doc2.getNumberOfPages(), l = 1; l <= n3; ++l) {
                String text2 = PdfTextExtractor.getTextFromPage(doc2.getPage(l), new SimpleTextExtractionStrategy());
                String[] textParts2 = text2.split("\n");
                ArrayList<String> textPartsf2 = new ArrayList<>();
                for (final String x4 : textParts2) {
                    if (!x4.equals(" ")) {
                        textPartsf2.add(x4);
                    }
                }
                String x5 = textPartsf2.get(0) + textPartsf2.get(1);
                String[] x6 = x5.split(" ");
                if (l % 2 == 0) {
                    if (x6.length == 9) {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7] + " " + x6[8];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                    else {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                }
                else if (x6.length == 9) {
                    final String block2 = x6[1];
                    final String code3 = x6[6];
                    final String page2 = x6[7] + " ";
                    final String change2 = x6[3] + " " + x6[4];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
                else if (x6.length == 8) {
                    final String block2 = x6[1];
                    final String code3 = x6[4];
                    final String page2 = x6[5] + " ";
                    final String change2 = x6[2];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
            }
            pdfReader2.close();
            doc2.close();
        }
        for (int j = 0; j < lepTable.get("04 Middle").size(); ++j) {
            final PdfReader pdfReader2 = new PdfReader(String.valueOf(lepTable.get("04 Middle").get(j)));
            final PdfDocument doc2 = new PdfDocument(pdfReader2);
            for (int n3 = doc2.getNumberOfPages(), l = 1; l <= n3; ++l) {
                final String text2 = PdfTextExtractor.getTextFromPage(doc2.getPage(l), (ITextExtractionStrategy)new SimpleTextExtractionStrategy());
                final String[] textParts2 = text2.split("\n");
                final ArrayList<String> textPartsf2 = new ArrayList<String>();
                for (final String x4 : textParts2) {
                    if (!x4.equals(" ")) {
                        textPartsf2.add(x4);
                    }
                }
                final String x5 = textPartsf2.get(0) + textPartsf2.get(1);
                final String[] x6 = x5.split(" ");
                if (l % 2 == 0) {
                    if (x6.length == 9) {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7] + " " + x6[8];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                    else {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                }
                else if (x6.length == 9) {
                    final String block2 = x6[1];
                    final String code3 = x6[6];
                    final String page2 = x6[7] + " ";
                    final String change2 = x6[3] + " " + x6[4];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
                else if (x6.length == 8) {
                    final String block2 = x6[1];
                    final String code3 = x6[4];
                    final String page2 = x6[5] + " ";
                    final String change2 = x6[2];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
            }
            pdfReader2.close();
            doc2.close();
        }
        for (int j = 0; j < lepTable.get("05 General Data").size(); ++j) {
            final PdfReader pdfReader2 = new PdfReader(String.valueOf(lepTable.get("05 General Data").get(j)));
            System.out.println(lepTable.get("05 General Data").get(j));
            final PdfDocument doc2 = new PdfDocument(pdfReader2);
            for (int n3 = doc2.getNumberOfPages(), l = 1; l <= n3; ++l) {
                final String text2 = PdfTextExtractor.getTextFromPage(doc2.getPage(l), new SimpleTextExtractionStrategy());
                final String[] textParts2 = text2.split("\n");
                final ArrayList<String> textPartsf2 = new ArrayList<>();
                for (final String x4 : textParts2) {
                    if (!x4.equals(" ")) {
                        textPartsf2.add(x4);
                    }
                }
                String x5;
                if (l % 2 == 0) {
                    if (textPartsf2.size() > 3) {
                        x5 = textPartsf2.get(2) + textPartsf2.get(3);
                    }
                    else {
                        x5 = textPartsf2.get(0) + textPartsf2.get(1);
                    }
                }
                else if (textPartsf2.size() > 3) {
                    x5 = textPartsf2.get(1) + textPartsf2.get(2);
                }
                else {
                    x5 = textPartsf2.get(0) + textPartsf2.get(1);
                }
                final String[] x6 = x5.split(" ");
                if (l % 2 == 0) {
                    if (x6.length == 9) {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7] + " " + x6[8];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                    else if (x6.length == 8) {
                        final String block2 = x6[1];
                        final String code3 = x6[5];
                        final String page2 = x6[6] + " ";
                        final String change2 = x6[3];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                }
                else if (x6.length == 9) {
                    final String block2 = x6[1];
                    final String code3 = x6[6];
                    final String page2 = x6[7] + " ";
                    final String change2 = x6[3] + " " + x6[6];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
                else if (x6.length == 8) {
                    final String block2 = x6[1];
                    final String code3 = x6[5];
                    final String page2 = x6[6] + " ";
                    final String change2 = x6[3];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
            }
            pdfReader2.close();
            doc2.close();
        }
        for (int j = 0; j < lepTable.get("AP01 Appendix").size(); ++j) {
            final PdfReader pdfReader2 = new PdfReader(String.valueOf(lepTable.get("AP01 Appendix").get(j)));
            final PdfDocument doc2 = new PdfDocument(pdfReader2);
            for (int n3 = doc2.getNumberOfPages(), l = 1; l <= n3; ++l) {
                final String text2 = PdfTextExtractor.getTextFromPage(doc2.getPage(l), new SimpleTextExtractionStrategy());
                final String[] textParts2 = text2.split("\n");
                final ArrayList<String> textPartsf2 = new ArrayList<>();
                for (final String x4 : textParts2) {
                    if (!x4.equals(" ")) {
                        textPartsf2.add(x4);
                    }
                }
                final String x5 = textPartsf2.get(1) + textPartsf2.get(2);
                final String[] x6 = x5.split(" ");
                if (l % 2 == 0) {
                    if (x6.length == 9) {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7] + " " + x6[8];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                    else {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                }
                else if (x6.length == 9) {
                    final String block2 = x6[1];
                    final String code3 = x6[6];
                    final String page2 = x6[7] + " ";
                    final String change2 = x6[3] + " " + x6[4];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
                else if (x6.length == 8) {
                    final String block2 = x6[1];
                    final String code3 = x6[4];
                    final String page2 = x6[5] + " ";
                    final String change2 = x6[2];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
            }
            pdfReader2.close();
            doc2.close();
        }
        for (int j = 0; j < lepTable.get("S03 Supplement").size(); ++j) {
            final PdfReader pdfReader2 = new PdfReader(String.valueOf(lepTable.get("S03 Supplement").get(j)));
            final PdfDocument doc2 = new PdfDocument(pdfReader2);
            for (int n3 = doc2.getNumberOfPages(), l = 1; l <= n3; ++l) {
                final String text2 = PdfTextExtractor.getTextFromPage(doc2.getPage(l), new SimpleTextExtractionStrategy());
                final String[] textParts2 = text2.split("\n");
                final ArrayList<String> textPartsf2 = new ArrayList<>();
                for (final String x4 : textParts2) {
                    if (!x4.equals(" ")) {
                        textPartsf2.add(x4);
                    }
                }
                final String x5 = textPartsf2.get(1) + textPartsf2.get(2);
                final String[] x6 = x5.split(" ");
                if (l % 2 == 0) {
                    if (x6.length == 9) {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7] + " " + x6[8];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                    else {
                        final String block2 = x6[1];
                        final String code3 = x6[6];
                        final String page2 = x6[3] + " ";
                        final String change2 = x6[7];
                        table.addCell(new Cell().add(block2));
                        table.addCell(new Cell().add(code3));
                        table.addCell(new Cell().add(page2 + l));
                        if (change2.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        }
                        else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change2));
                    }
                }
                else if (x6.length == 9) {
                    final String block2 = x6[1];
                    final String code3 = x6[6];
                    final String page2 = x6[7] + " ";
                    final String change2 = x6[3] + " " + x6[4];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
                else if (x6.length == 8) {
                    final String block2 = x6[1];
                    final String code3 = x6[4];
                    final String page2 = x6[5] + " ";
                    final String change2 = x6[2];
                    table.addCell(new Cell().add(block2));
                    table.addCell(new Cell().add(code3));
                    table.addCell(new Cell().add(page2 + l));
                    if (change2.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    }
                    else {
                        table.addCell(new Cell().add(""));
                    }
                    table.addCell(new Cell().add(change2));
                }
            }
            pdfReader2.close();
            doc2.close();
        }

        document.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER).setMarginBottom(50));
        // interface
        document.showTextAligned(new Paragraph(String
                        .format("Page " + 3 )).setFontSize(14),
                550, 30, 3, TextAlignment.RIGHT,
                VerticalAlignment.BOTTOM, 0);

        document.showTextAligned(new Paragraph(String.format("List of Effective Pages")),
                560, 830, 3, TextAlignment.RIGHT,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String.format(manualName)).setFontSize(8),
                50, 80, 3, TextAlignment.LEFT,
                VerticalAlignment.TOP, 300);

        document.showTextAligned(new Paragraph(String.format("0-LEP")).setBold().setFontSize(16),
                300, 60, 3, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String.format("Code " + code)).setFontSize(14),
                300, 30, 3, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);

        // begin interface
        document.showTextAligned(new Paragraph(String
                        .format("Page " + 4)).setFontSize(14),
                100, 30, 4, TextAlignment.RIGHT,
                VerticalAlignment.BOTTOM, 0);

        document.showTextAligned(new Paragraph(String.format("List of Effective Pages")),
                50, 830, 4, TextAlignment.LEFT,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String.format(manualName)).setFontSize(8),
                560, 80, 4, TextAlignment.LEFT,
                VerticalAlignment.BOTTOM, 300);

        document.showTextAligned(new Paragraph(String.format("0-LEP")).setBold().setFontSize(16),
                300, 60, 4, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);

        document.showTextAligned(new Paragraph(String.format("Code " + code)).setFontSize(14),
                300, 30, 4, TextAlignment.CENTER,
                VerticalAlignment.TOP, 0);

        pdfDocument.close();
        document.close();
    }
}
