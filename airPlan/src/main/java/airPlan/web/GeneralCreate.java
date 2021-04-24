package airPlan.web;

import org.springframework.jdbc.core.JdbcTemplate;

import airPlan.data.JdbcCodeListRepository;
import airPlan.data.JdbcManualFlagRepository;
import airPlan.data.JdbcManualRepository;
import airPlan.data.SpringJdbcConfig;
import airPlan.model.CodeList;
import airPlan.model.Flag;
import airPlan.model.General;
import airPlan.model.Manual;
import airPlan.model.ManualFlag;
import airPlan.data.JdbcFlagRepository;

public class GeneralCreate {
	
	public static void create(General general) {
		SpringJdbcConfig jdbcConfig = new SpringJdbcConfig();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcConfig.mysqlDataSource());
		JdbcManualRepository manualRepository = new JdbcManualRepository(jdbcTemplate);
		JdbcFlagRepository flagRepository = new JdbcFlagRepository(jdbcTemplate);
		JdbcManualFlagRepository manualFlagRepository = new JdbcManualFlagRepository(jdbcTemplate);
		JdbcCodeListRepository codeListRepository = new JdbcCodeListRepository(jdbcTemplate);
		
		
		
		
		Manual manualModel = new Manual(general.getMnl_name());
		manualRepository.save(manualModel);
		System.out.println(manualModel);
		manualRepository.findIdManual(manualModel);
		
		
		String flgSecundaryId = general.getFlg_secundary_id();
		
		if(flgSecundaryId.length() > 1) {
			if(flgSecundaryId.contains(",")) {
				String[] flgSecundaryIdParts = flgSecundaryId.split(",");
				String tag = general.getFlg_tag();
				String[] tagParts = tag.split(",");
				
				for(int i=0;i<flgSecundaryIdParts.length;i++) {
					Flag flagModel = new Flag(flgSecundaryIdParts[i], tagParts[i]);
					flagRepository.save(flagModel);
					ManualFlag manualFlagModel = new ManualFlag(manualModel.getMnl_id(),flgSecundaryIdParts[i]);
					manualFlagRepository.save(manualFlagModel);
					System.out.println(flagModel);
				}
				
			} else {
				Flag flagModel = new Flag(general.getFlg_secundary_id(), general.getFlg_tag());
				flagRepository.save(flagModel);
				ManualFlag manualFlagModel = new ManualFlag(manualModel.getMnl_id(),general.getFlg_secundary());
				manualFlagRepository.save(manualFlagModel);
				System.out.println(flagModel);
			}
		}
		
		String flgSecundary = general.getFlg_secundary();
		
		if(flgSecundary.length() > 1) {
			if(flgSecundary.contains(",")) {
				String[] flgSecundaryParts = flgSecundary.split(",");
				
				for(int i=0;i<flgSecundaryParts.length;i++) {
					CodeList codeListModel = new CodeList(manualModel.getMnl_id(),flgSecundaryParts[i], general.getCdl_section(),
							general.getCdl_sub_section(), Integer.parseInt(general.getCdl_block()), general.getCdl_block_name(),
							Integer.parseInt(general.getCdl_code()));
					codeListRepository.save(codeListModel);
					System.out.println(codeListModel);
				}
			} else {
				CodeList codeListModel = new CodeList(manualModel.getMnl_id(),general.getFlg_secundary(), general.getCdl_section(),
						general.getCdl_sub_section(), Integer.parseInt(general.getCdl_block()), general.getCdl_block_name(),
						Integer.parseInt(general.getCdl_code()));
				codeListRepository.save(codeListModel);
			}
		}
	}
}
