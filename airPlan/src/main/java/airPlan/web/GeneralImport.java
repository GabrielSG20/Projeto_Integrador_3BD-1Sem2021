package airPlan.web;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;

import airPlan.CodeList;
import airPlan.Flag;
import airPlan.General;
import airPlan.Manual;
import airPlan.ManualFlag;
import airPlan.data.JdbcCodeListRepository;
import airPlan.data.JdbcFlagRepository;
import airPlan.data.JdbcManualFlagRepository;
import airPlan.data.JdbcManualRepository;
import airPlan.data.SpringJdbcConfig;

public class GeneralImport {
	
	public static void importCodeList(String[] args) {
		getCellData();
	}
	
	public static void getCellData() {
		
		try {
			String excelPath = "./uploads/Codelist.xlsx";
			System.out.println(excelPath);
			XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
			XSSFSheet sheet = workbook.getSheet("Jedi Codex");
			int n = getRowCount();
			DataFormatter formatter1 = new DataFormatter();
			ArrayList<String> tags = new ArrayList<>();
			
			System.out.println(formatter1.formatCellValue(sheet.getRow(1).getCell(7)));
			System.out.println(formatter1.formatCellValue(sheet.getRow(1).getCell(8)));
			System.out.println(formatter1.formatCellValue(sheet.getRow(1).getCell(9)));
			String tagName1 = formatter1.formatCellValue(sheet.getRow(1).getCell(7));
			String tagName2 = formatter1.formatCellValue(sheet.getRow(1).getCell(8));
			String tagName3 = formatter1.formatCellValue(sheet.getRow(1).getCell(9));
			String[] parts = tagName1.split(" - ");
			tags.add(parts[1]);
			String[] parts2 = tagName2.split(" - ");
			tags.add(parts2[1]);
			String[] parts3 = tagName3.split(" - ");
			tags.add(parts3[1]);
		
			for(int i=2; i<3; i++) {
				
				for(int j=1; j<2; j++) {
					
					DataFormatter formatter = new DataFormatter();
					
					if(formatter.formatCellValue(sheet.getRow(i).getCell(7)).equals("1")) {
						
						
						System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(6)));
						System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(1)));
						System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(2)));
						System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(4)));
						System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(5)));
						System.out.println(tags.get(0));
						General general = new General(formatter.formatCellValue(sheet.getRow(i).getCell(6)),
								formatter.formatCellValue(sheet.getRow(i).getCell(1)),
								formatter.formatCellValue(sheet.getRow(i).getCell(2)),
								Integer.parseInt(formatter.formatCellValue(sheet.getRow(i).getCell(3))),
								formatter.formatCellValue(sheet.getRow(i).getCell(4)),
								Integer.parseInt(formatter.formatCellValue(sheet.getRow(i).getCell(5))),
								tags.get(0), "abc-129");
						
						/* Insert no Banco de Dados */
						Manual manualModel = new Manual(general.getMnl_name());
						
						ManualFlag manualFlagModel = new ManualFlag(general.getMnl_name(), general.getFlg_secundary());
						
						CodeList codeListModel = new CodeList(general.getMnl_name(),general.getFlg_secundary(), general.getCdl_section(),
																		general.getCdl_sub_section(), general.getCdl_block(), general.getCdl_block_name(),
																		general.getCdl_code());
						
						Flag flagModel = new Flag(general.getFlg_secundary(), general.getFlg_tag());
						
						System.out.println(manualModel);
						System.out.println(manualFlagModel);
						System.out.println(codeListModel);
						System.out.println(flagModel);
						SpringJdbcConfig jdbcConfig = new SpringJdbcConfig();
						JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcConfig.mysqlDataSource());
						JdbcManualRepository manualRepository = new JdbcManualRepository(jdbcTemplate);
						JdbcFlagRepository flagRepository = new JdbcFlagRepository(jdbcTemplate);
						JdbcManualFlagRepository manualFlagRepository = new JdbcManualFlagRepository(jdbcTemplate);
						JdbcCodeListRepository codeListRepository = new JdbcCodeListRepository(jdbcTemplate);
						

						manualRepository.save(manualModel);
						flagRepository.save(flagModel);
						manualFlagRepository.save(manualFlagModel);
						codeListRepository.save(codeListModel);
					}
					
					/*Object value = formatter.formatCellValue(sheet.getRow(i).getCell(j));
					General general = new General(formatter.formatCellValue(sheet.getRow(i).getCell(6)),
							formatter.formatCellValue(sheet.getRow(i).getCell(1)),
							formatter.formatCellValue(sheet.getRow(i).getCell(2)),
							formatter.formatCellValue(sheet.getRow(i).getCell(3)),
							formatter.formatCellValue(sheet.getRow(i).getCell(4)),
							formatter.formatCellValue(sheet.getRow(i).getCell(5)),
							formatter.formatCellValue(sheet.getRow(i).getCell(7)),);
					System.out.printf("%15s", value);*/
				}
				System.out.println("ok");
				workbook.close();
				
			}
		}catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static int getRowCount() {
		
		try {
			String excelPath = "./uploads/Codelist.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
			XSSFSheet sheet = workbook.getSheet("Jedi Codex");
			int rowCount = sheet.getPhysicalNumberOfRows();
			return rowCount;
		
		}catch(Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return 0;
		
	}
}
