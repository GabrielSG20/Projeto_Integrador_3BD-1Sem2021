package airPlan.web;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;

import airPlan.data.JdbcCodeListRepository;
import airPlan.data.JdbcFlagRepository;
import airPlan.data.JdbcManualFlagRepository;
import airPlan.data.JdbcManualRepository;
import airPlan.data.SpringJdbcConfig;
import airPlan.model.CodeList;
import airPlan.model.Flag;
import airPlan.model.Manual;
import airPlan.model.ManualFlag;

public class GeneralImport {
	
	public static void importCodeList(String[] args) {
		
	}
	
	public static void getCellData(String manualName) {
		
		try {
			String excelPath = "./uploads/Codelist.xlsx";
			System.out.println(excelPath);
			XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
			XSSFSheet sheet = workbook.getSheet("Jedi Codex");
			int n = getRowCount();
			DataFormatter formatter1 = new DataFormatter();
			ArrayList<String> tags = new ArrayList<>();
			
			SpringJdbcConfig jdbcConfig = new SpringJdbcConfig();
			JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcConfig.mysqlDataSource());
			JdbcManualRepository manualRepository = new JdbcManualRepository(jdbcTemplate);
			JdbcFlagRepository flagRepository = new JdbcFlagRepository(jdbcTemplate);
			JdbcManualFlagRepository manualFlagRepository = new JdbcManualFlagRepository(jdbcTemplate);
			JdbcCodeListRepository codeListRepository = new JdbcCodeListRepository(jdbcTemplate);
			
			
			/* Manual Name */
			Manual manualModel = new Manual(manualName);
			manualRepository.save(manualModel);
			manualRepository.findIdManual(manualModel);
			System.out.println(manualModel);
			
			/* check se esta lendo excel */
			System.out.println(formatter1.formatCellValue(sheet.getRow(1).getCell(7)));
			System.out.println(formatter1.formatCellValue(sheet.getRow(1).getCell(8)));
			System.out.println(formatter1.formatCellValue(sheet.getRow(1).getCell(9)));
			
			
			String tagName1 = formatter1.formatCellValue(sheet.getRow(1).getCell(7));
			String tagName2 = formatter1.formatCellValue(sheet.getRow(1).getCell(8));
			String tagName3 = formatter1.formatCellValue(sheet.getRow(1).getCell(9));
			
			
			String[] parts = tagName1.split(" - ");
			Flag flagModel = new Flag("-"+parts[0], parts[1]);
			flagRepository.save(flagModel);
			ManualFlag manualFlagModel = new ManualFlag(manualModel.getMnl_id(),"-"+parts[0]);
			manualFlagRepository.save(manualFlagModel);
			tags.add(parts[1]);
			
			
			String[] parts2 = tagName2.split(" - ");
			Flag flagModel2 = new Flag("-"+parts2[0], parts2[1]);
			flagRepository.save(flagModel2);
			ManualFlag manualFlagModel2 = new ManualFlag(manualModel.getMnl_id(),"-"+parts2[0]);
			manualFlagRepository.save(manualFlagModel2);
			tags.add(parts2[1]);
			
			
			String[] parts3 = tagName3.split(" - ");
			Flag flagModel3 = new Flag("-"+parts3[0], parts3[1]);
			flagRepository.save(flagModel3);
			ManualFlag manualFlagModel3 = new ManualFlag(manualModel.getMnl_id(),"-"+parts3[0]);
			manualFlagRepository.save(manualFlagModel3);
			tags.add(parts3[1]);
		
			for(int i=2; i<n; i++) {
				DataFormatter formatter = new DataFormatter();
					
				if(formatter.formatCellValue(sheet.getRow(i).getCell(7)).equals("1")) {
					System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(6)));
					System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(1)));
					System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(2)));
					System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(4)));
					System.out.println(formatter.formatCellValue(sheet.getRow(i).getCell(5)));
					System.out.println(tags.get(0));
					CodeList codeListModel = new CodeList(manualModel.getMnl_id(), "-"+parts[0], formatter.formatCellValue(sheet.getRow(i).getCell(1)),
							formatter.formatCellValue(sheet.getRow(i).getCell(2)), Integer.parseInt(formatter.formatCellValue(sheet.getRow(i).getCell(3))),
							formatter.formatCellValue(sheet.getRow(i).getCell(4)),
							Integer.parseInt(formatter.formatCellValue(sheet.getRow(i).getCell(5))));
						
						/* Insert no Banco de Dados */
					
					codeListRepository.save(codeListModel);
						
					System.out.println(manualModel);
					System.out.println(manualFlagModel);
					System.out.println(codeListModel);
					System.out.println(flagModel);
					
				} if(formatter.formatCellValue(sheet.getRow(i).getCell(8)).equals("1")) {
					CodeList codeListModel = new CodeList(manualModel.getMnl_id(), "-"+parts2[0], formatter.formatCellValue(sheet.getRow(i).getCell(1)),
							formatter.formatCellValue(sheet.getRow(i).getCell(2)), Integer.parseInt(formatter.formatCellValue(sheet.getRow(i).getCell(3))),
							formatter.formatCellValue(sheet.getRow(i).getCell(4)),
							Integer.parseInt(formatter.formatCellValue(sheet.getRow(i).getCell(5))));
						
						/* Insert no Banco de Dados */
					
					codeListRepository.save(codeListModel);
					
				} if(formatter.formatCellValue(sheet.getRow(i).getCell(9)).equals("1")) {
					CodeList codeListModel = new CodeList(manualModel.getMnl_id(), "-"+parts3[0], formatter.formatCellValue(sheet.getRow(i).getCell(1)),
							formatter.formatCellValue(sheet.getRow(i).getCell(2)), Integer.parseInt(formatter.formatCellValue(sheet.getRow(i).getCell(3))),
							formatter.formatCellValue(sheet.getRow(i).getCell(4)),
							Integer.parseInt(formatter.formatCellValue(sheet.getRow(i).getCell(5))));
						
						/* Insert no Banco de Dados */
					
					codeListRepository.save(codeListModel);
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
