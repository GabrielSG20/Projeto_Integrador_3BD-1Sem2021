package com.airPlan.services;

import com.airPlan.entities.Lep;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

@Service
public class LepService {

    @Autowired
    private ManualService manualService;
    @Autowired
    private CodeListService codeListService;


    ArrayList<String> filterRevisionDates = new ArrayList<>();


    public void populateRevisionDates(Lep lep) throws IOException {


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

        createPage1(lep.getCdl_code(), lep.getMnl_name(), lep.getCdl_code_list());
    }


    // Creating LEP
    public void createPage1(String code, String manualName, String codeList) throws IOException {

        String destPath = Paths.get("./manuals/test.pdf").toString();

        Paragraph paragraph1 = new Paragraph("LIST OF EFFECTIVE PAGES");

        PdfWriter pdfWriter = new PdfWriter(destPath);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        Document document = new Document(pdfDocument);

        document.add(paragraph1.setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(18));

        for (String s : this.filterRevisionDates) {
            Paragraph temp = new Paragraph(s);
            document.add(temp.setTextAlignment(TextAlignment.CENTER).setFontSize(16));
        }

        // interface
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
        // end of interface

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

        String[] codeList1 = codeList.split("-");
        ArrayList<String> paths = new ArrayList<>();

        for (String code1 : codeList1) {
            try (Stream<Path> filepath
                         = Files.walk(Paths.get("./manuals/"))) {
                filepath.forEach(s -> {
                    String tempStr = s.toString();
                    if (tempStr.contains("c" + code1) && tempStr.contains(".pdf")) {
                        paths.add(s.toString());
                    }
                });

            } catch (IOException e) {
                throw new IOException("Directory Not Present!");
            }
        }

        for(String b: paths) {
            System.out.println(b);
        }
        HashMap<String, String> lepTable = new HashMap<>();
        lepTable.put("01 Cover", null);
        lepTable.put("03 Table of Contents", null);
        lepTable.put("02 Story", null);
        lepTable.put("03 Chapter", null);
        lepTable.put("04 Middle", null);
        lepTable.put("05 General Data", null);
        lepTable.put("AP01 Appendix", null);
        lepTable.put("S03 Supplement", null);

        for (String s : paths) {
            if (s.contains("01 Cover")) {
                lepTable.replace("01 Cover", s);
            } else if (s.contains("03 Table of Contents") && lepTable.get("03 Table of Contents") == null) {
                lepTable.replace("03 Table of Contents", s);
            } else if (s.contains("02 Story")) {
                lepTable.replace("02 Story", s);
            } else if (s.contains("03 Chapter")) {
                lepTable.replace("03 Chapter", s);
            } else if (s.contains("04 Middle")) {
                lepTable.replace("04 Middle", s);
            } else if (s.contains("05 General Data")) {
                lepTable.replace("05 General Data", s);
            } else if (s.contains("AP01 Appendix")) {
                lepTable.replace("AP01 Appendix", s);
            } else if (s.contains("S03 Supplement")) {
                lepTable.replace("S03 Supplement", s);
            }
        }

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));

        float[] columnWidths = {80F, 80F, 80F, 140F, 80F};
        Table table = new Table(columnWidths);

        table.addCell(new Cell().add("Block"));
        table.addCell(new Cell().add("Code"));
        table.addCell(new Cell().add("Page"));
        table.addCell(new Cell().add("    "));
        table.addCell(new Cell().add("Change"));

        if (lepTable.get("01 Cover") != null) {
            table.addCell(new Cell().add("0-TITLE"));
            table.addCell(new Cell().add("00"));
            table.addCell(new Cell().add("cover").setBold());
            table.addCell(new Cell().add("*"));
            table.addCell(new Cell().add("REVISION 06"));
        }

        for (int i = 1; i <= 3; i++) {
            table.addCell(new Cell().add("0-LEP"));
            table.addCell(new Cell().add(code));
            table.addCell(new Cell().add(String.valueOf(i)));
            table.addCell(new Cell().add("*"));
            table.addCell(new Cell().add("REVISION 06"));
        }

        if (lepTable.get("03 Table of Contents") != null) {
            PdfReader pdfReader = new PdfReader(lepTable.get("03 Table of Contents"));
            PdfDocument doc1 = new PdfDocument(pdfReader);
            int n2 = doc1.getNumberOfPages();
            for (int i = 1; i <= n2; i++) {
                String text = PdfTextExtractor.getTextFromPage(doc1.getPage(i), new SimpleTextExtractionStrategy());
                String[] textParts = text.split("\n");
                ArrayList<String> textPartsf = new ArrayList<>();
                for (String x1 : textParts) {
                    if (!x1.equals(" ") && !x1.equals("\\n")) {
                        textPartsf.add(x1);
                    }
                }

                String x3 = textPartsf.get(2) + textPartsf.get(3);
                String[] x4 = x3.split(" ");

                if(i%2==0) {
                    String block = x4[1];
                    String code1 = x4[6];
                    String page = x4[3]+" "+x4[4];
                    String change = x4[7];

                    table.addCell(new Cell().add(block));
                    table.addCell(new Cell().add(code1));
                    table.addCell(new Cell().add(page));
                    table.addCell(new Cell().add(""));
                    if(change.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    } else {
                        table.addCell(new Cell().add(change));
                    }
                } else {

                    String block = x4[1];
                    String code1 = x4[4];
                    String page = x4[5]+" "+x4[6];
                    String change = x4[2];

                    table.addCell(new Cell().add(block));
                    table.addCell(new Cell().add(code1));
                    table.addCell(new Cell().add(page));
                    table.addCell(new Cell().add(""));
                    if(change.equals("REVISION 06")) {
                        table.addCell(new Cell().add("*"));
                    } else {
                        table.addCell(new Cell().add(change));
                    }
                }

            }
            pdfReader.close();
            doc1.close();
        }

        if (lepTable.get("02 Story") != null) {
            PdfReader pdfReader = new PdfReader(lepTable.get("02 Story"));
            PdfDocument doc1 = new PdfDocument(pdfReader);
            int n2 = doc1.getNumberOfPages();
            for (int i = 1; i <= n2; i++) {
                String text = PdfTextExtractor.getTextFromPage(doc1.getPage(i), new SimpleTextExtractionStrategy());
                String[] textParts = text.split("\n");
                ArrayList<String> textPartsf = new ArrayList<>();
                for (String x1 : textParts) {
                    if (!x1.equals(" ")) {
                        textPartsf.add(x1);
                    }
                }
                String x3 = textPartsf.get(0) + textPartsf.get(1);
                String[] x4 = x3.split(" ");
                if (i % 2 == 0) {
                    if (x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7] + " " + x4[8];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        String block = x4[1];
                        String code1 = x4[5] + " " + x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    }
                } else {
                    if (x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[7] + " ";
                        String change = x4[3] + " " + x4[4];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));
                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        if (x4.length == 8) {
                            String block = x4[1];
                            String code1 = x4[4];
                            String page = x4[5] + " ";
                            String change = x4[2];

                            table.addCell(new Cell().add(block));
                            table.addCell(new Cell().add(code1));
                            table.addCell(new Cell().add(page + i));
                            if (change.equals("REVISION 06")) {
                                table.addCell(new Cell().add("*"));
                            } else {
                                table.addCell(new Cell().add(""));
                            }
                            table.addCell(new Cell().add(change));
                        }
                    }
                }
            }
            pdfReader.close();
            doc1.close();
        }

            if(lepTable.get("03 Chapter")!=null) {
                PdfReader pdfReader = new PdfReader(lepTable.get("03 Chapter"));
                PdfDocument doc1 = new PdfDocument(pdfReader);
                int n2 = doc1.getNumberOfPages();
                for (int i = 1; i <= n2; i++) {
                    String text = PdfTextExtractor.getTextFromPage(doc1.getPage(i), new SimpleTextExtractionStrategy());
                    String[] textParts = text.split("\n");
                    ArrayList<String> textPartsf = new ArrayList<>();
                    for (String x1 : textParts) {
                        if (!x1.equals(" ")) {
                            textPartsf.add(x1);
                        }
                    }
                    String x3 = textPartsf.get(0) + textPartsf.get(1);
                    String[] x4 = x3.split(" ");
                    if(i%2 == 0) {
                        if(x4.length == 9) {
                            String block = x4[1];
                            String code1 = x4[6];
                            String page = x4[3] + " ";

                            String change = x4[7] + " " + x4[8];

                            table.addCell(new Cell().add(block));
                            table.addCell(new Cell().add(code1));
                            table.addCell(new Cell().add(page + i));

                            if (change.equals("REVISION 06")) {
                                table.addCell(new Cell().add("*"));
                            } else {
                                table.addCell(new Cell().add(""));
                            }
                            table.addCell(new Cell().add(change));
                        } else {
                            String block = x4[1];
                            String code1 = x4[6];
                            String page = x4[3] + " ";

                            String change = x4[7];

                            table.addCell(new Cell().add(block));
                            table.addCell(new Cell().add(code1));
                            table.addCell(new Cell().add(page + i));

                            if (change.equals("REVISION 06")) {
                                table.addCell(new Cell().add("*"));
                            } else {
                                table.addCell(new Cell().add(""));
                            }
                            table.addCell(new Cell().add(change));
                        }
                    } else {
                        if(x4.length == 9) {
                            String block = x4[1];
                            String code1 = x4[6];
                            String page = x4[7] + " ";
                            String change = x4[3] + " " + x4[4];

                            table.addCell(new Cell().add(block));
                            table.addCell(new Cell().add(code1));
                            table.addCell(new Cell().add(page + i));
                            if (change.equals("REVISION 06")) {
                                table.addCell(new Cell().add("*"));
                            } else {
                                table.addCell(new Cell().add(""));
                            }
                            table.addCell(new Cell().add(change));
                        } else {
                            if(x4.length == 8) {
                                String block = x4[1];
                                String code1 = x4[4];
                                String page = x4[5] + " ";
                                String change = x4[2];

                                table.addCell(new Cell().add(block));
                                table.addCell(new Cell().add(code1));
                                table.addCell(new Cell().add(page + i));
                                if (change.equals("REVISION 06")) {
                                    table.addCell(new Cell().add("*"));
                                } else {
                                    table.addCell(new Cell().add(""));
                                }
                                table.addCell(new Cell().add(change));
                            }
                        }
                    }
            }
            pdfReader.close();
            doc1.close();
        }

        if(lepTable.get("04 Middle")!=null) {
            PdfReader pdfReader = new PdfReader(lepTable.get("04 Middle"));
            PdfDocument doc1 = new PdfDocument(pdfReader);
            int n2 = doc1.getNumberOfPages();
            for (int i = 1; i <= n2; i++) {
                String text = PdfTextExtractor.getTextFromPage(doc1.getPage(i), new SimpleTextExtractionStrategy());
                String[] textParts = text.split("\n");
                ArrayList<String> textPartsf = new ArrayList<>();
                for (String x1 : textParts) {
                    if (!x1.equals(" ")) {
                        textPartsf.add(x1);
                    }
                }
                String x3 = textPartsf.get(0) + textPartsf.get(1);
                String[] x4 = x3.split(" ");
                if(i%2 == 0) {
                    if(x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7] + " " + x4[8];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    }
                } else {
                    if(x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[7] + " ";
                        String change = x4[3] + " " + x4[4];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));
                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        if(x4.length == 8) {
                            String block = x4[1];
                            String code1 = x4[4];
                            String page = x4[5] + " ";
                            String change = x4[2];

                            table.addCell(new Cell().add(block));
                            table.addCell(new Cell().add(code1));
                            table.addCell(new Cell().add(page + i));
                            if (change.equals("REVISION 06")) {
                                table.addCell(new Cell().add("*"));
                            } else {
                                table.addCell(new Cell().add(""));
                            }
                            table.addCell(new Cell().add(change));
                        }
                    }
                }
            }
            pdfReader.close();
            doc1.close();
        }

        if(lepTable.get("05 General Data")!=null) {
            PdfReader pdfReader = new PdfReader(lepTable.get("05 General Data"));
            PdfDocument doc1 = new PdfDocument(pdfReader);
            int n2 = doc1.getNumberOfPages();
            for (int i = 1; i <= n2; i++) {
                String text = PdfTextExtractor.getTextFromPage(doc1.getPage(i), new SimpleTextExtractionStrategy());
                String[] textParts = text.split("\n");
                ArrayList<String> textPartsf = new ArrayList<>();
                for (String x1 : textParts) {
                    if (!x1.equals(" ")) {
                        textPartsf.add(x1);
                    }
                }
                String x3 = textPartsf.get(0) + textPartsf.get(1);
                String[] x4 = x3.split(" ");
                if(i%2 == 0) {
                    if(x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7] + " " + x4[8];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    }
                } else {
                    if(x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[7] + " ";
                        String change = x4[3] + " " + x4[4];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));
                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        if(x4.length == 8) {
                            String block = x4[1];
                            String code1 = x4[4];
                            String page = x4[5] + " ";
                            String change = x4[2];

                            table.addCell(new Cell().add(block));
                            table.addCell(new Cell().add(code1));
                            table.addCell(new Cell().add(page + i));
                            if (change.equals("REVISION 06")) {
                                table.addCell(new Cell().add("*"));
                            } else {
                                table.addCell(new Cell().add(""));
                            }
                            table.addCell(new Cell().add(change));
                        }
                    }
                }
            }
            pdfReader.close();
            doc1.close();
        }
        // "AP01 Appendix"
        if(lepTable.get("AP01 Appendix")!=null) {
            PdfReader pdfReader = new PdfReader(lepTable.get("AP01 Appendix"));
            PdfDocument doc1 = new PdfDocument(pdfReader);
            int n2 = doc1.getNumberOfPages();
            for (int i = 1; i <= n2; i++) {
                String text = PdfTextExtractor.getTextFromPage(doc1.getPage(i), new SimpleTextExtractionStrategy());
                String[] textParts = text.split("\n");
                ArrayList<String> textPartsf = new ArrayList<>();
                for (String x1 : textParts) {
                    if (!x1.equals(" ")) {
                        textPartsf.add(x1);
                    }
                }

                for(String x2: textPartsf) {
                    System.out.println(x2);
                }
                String x3 = textPartsf.get(1) + textPartsf.get(2);
                String[] x4 = x3.split(" ");

                if(i%2 == 0) {
                    if(x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7] + " " + x4[8];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    }
                } else {
                    if(x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[7] + " ";
                        String change = x4[3] + " " + x4[4];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));
                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        if(x4.length == 8) {
                            String block = x4[1];
                            String code1 = x4[4];
                            String page = x4[5] + " ";
                            String change = x4[2];

                            table.addCell(new Cell().add(block));
                            table.addCell(new Cell().add(code1));
                            table.addCell(new Cell().add(page + i));
                            if (change.equals("REVISION 06")) {
                                table.addCell(new Cell().add("*"));
                            } else {
                                table.addCell(new Cell().add(""));
                            }
                            table.addCell(new Cell().add(change));
                        }
                    }
                }
            }
            pdfReader.close();
            doc1.close();
        }
        if(lepTable.get("S03 Supplement")!=null) {
            PdfReader pdfReader = new PdfReader(lepTable.get("S03 Supplement"));
            PdfDocument doc1 = new PdfDocument(pdfReader);
            int n2 = doc1.getNumberOfPages();
            System.out.println("PAGE NUMBER " + n2);
            for (int i = 1; i <= n2; i++) {
                String text = PdfTextExtractor.getTextFromPage(doc1.getPage(i), new SimpleTextExtractionStrategy());
                String[] textParts = text.split("\n");
                ArrayList<String> textPartsf = new ArrayList<>();
                for (String x1 : textParts) {
                    if (!x1.equals(" ")) {
                        textPartsf.add(x1);
                    }
                }

                for(String x2: textPartsf) {
                    System.out.println(x2);
                }
                String x3 = textPartsf.get(1) + textPartsf.get(2);
                String[] x4 = x3.split(" ");

                if(i%2 == 0) {
                    if(x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7] + " " + x4[8];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[3] + " ";

                        String change = x4[7];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));

                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    }
                } else {
                    if(x4.length == 9) {
                        String block = x4[1];
                        String code1 = x4[6];
                        String page = x4[7] + " ";
                        String change = x4[3] + " " + x4[4];

                        table.addCell(new Cell().add(block));
                        table.addCell(new Cell().add(code1));
                        table.addCell(new Cell().add(page + i));
                        if (change.equals("REVISION 06")) {
                            table.addCell(new Cell().add("*"));
                        } else {
                            table.addCell(new Cell().add(""));
                        }
                        table.addCell(new Cell().add(change));
                    } else {
                        if(x4.length == 8) {
                            String block = x4[1];
                            String code1 = x4[4];
                            String page = x4[5] + " ";
                            String change = x4[2];

                            table.addCell(new Cell().add(block));
                            table.addCell(new Cell().add(code1));
                            table.addCell(new Cell().add(page + i));
                            if (change.equals("REVISION 06")) {
                                table.addCell(new Cell().add("*"));
                            } else {
                                table.addCell(new Cell().add(""));
                            }
                            table.addCell(new Cell().add(change));
                        }
                    }
                }
            }
            pdfReader.close();
            doc1.close();
        }

        document.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));

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

        pdfDocument.close();
        document.close();

        System.out.println("Paragraph added");
    }

}
