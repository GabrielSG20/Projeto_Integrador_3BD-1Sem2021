package airPlan.web;

import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	public static boolean create(General general) {
		ManualFlag[] manualflags = new ManualFlag[5];
		CodeList[] codelists = new CodeList[5];
		
		int n = general.addLista(general.getCodelist(), manualflags, codelists);
		
		SpringJdbcConfig jdbcConfig = new SpringJdbcConfig();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcConfig.mysqlDataSource());
		JdbcManualRepository manualRepository = new JdbcManualRepository(jdbcTemplate);
		JdbcFlagRepository flagRepository = new JdbcFlagRepository(jdbcTemplate);
		JdbcManualFlagRepository manualFlagRepository = new JdbcManualFlagRepository(jdbcTemplate);
		JdbcCodeListRepository codeListRepository = new JdbcCodeListRepository(jdbcTemplate);
		
		/* DEBUG */
		checkDebug(general, general.toString());
		
		Manual manualModel = new Manual(general.getMnl_name());
		manualRepository.save(manualModel);
		/* DEBUG */
		checkDebug(manualModel, manualModel.toString());
		
		manualRepository.findIdManual(manualModel);
		
		String flgSecundaryId = general.getFlg_secundary_id();
		
		if(flgSecundaryId.length() > 1) {
			if(flgSecundaryId.contains(",")) {
				String[] flgSecundaryIdParts = flgSecundaryId.split(",");
				String tag = general.getFlg_tag();
				String[] tagParts = tag.split(",");
				
				for(int i=0;i<flgSecundaryIdParts.length;i++) {
					Flag flagModel = new Flag(flgSecundaryIdParts[i], tagParts[i]);
					/* DEBUG */
					checkDebug(flagModel, flagModel.toString());
					flagRepository.save(flagModel);
					
					ManualFlag manualFlagModel = new ManualFlag(manualModel.getMnl_id(),flgSecundaryIdParts[i]);
					/* DEBUG */
					checkDebug(manualFlagModel, manualFlagModel.toString());
					manualFlagRepository.save(manualFlagModel);
				}
				
			} else {
				Flag flagModel = new Flag(general.getFlg_secundary_id(), general.getFlg_tag());
				/* DEBUG */
				checkDebug(flagModel, flagModel.toString());
				flagRepository.save(flagModel);
				
				ManualFlag manualFlagModel = new ManualFlag(manualModel.getMnl_id(),general.getFlg_secundary());
				/* DEBUG */
				checkDebug(manualFlagModel, manualFlagModel.toString());
				manualFlagRepository.save(manualFlagModel);
			}
		}
		
		for(int j = 0; j < n; j++) {

			manualflags[j].setMnl_id(manualModel.getMnl_id());
			codelists[j].setMnl_id(manualModel.getMnl_id());
				
			String flgSecundary = manualflags[j].getFlg_secundary_id();
				
			if(flgSecundary.length() > 1) {
				if(flgSecundary.contains(",")) {
					String[] flgSecundaryParts = flgSecundary.split(",");
						
					for(int i=0;i<flgSecundaryParts.length;i++) {
						codelists[j].setFlg_secundary(flgSecundaryParts[i]);
							
						/* DEBUG */
						checkDebug(codelists[j], codelists[j].toString());
							
						codeListRepository.save(codelists[j]);
					}
				} else {
					/* DEBUG */
					checkDebug(codelists[j], codelists[j].toString());
						
					codeListRepository.save(codelists[j]);
				}
			}
		}
		
		return true;
	}
	
	public static void checkDebug(Object obj, String data) {
		
		Logger logger= LoggerFactory.getLogger(Object.class);
		logger.debug(data);
		logger.info(data);
		
	}
}
