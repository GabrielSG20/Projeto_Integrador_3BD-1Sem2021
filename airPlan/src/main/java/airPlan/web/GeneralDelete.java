package airPlan.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import airPlan.data.JdbcCodeListRepository;
import airPlan.data.JdbcManualRepository;
import airPlan.data.SpringJdbcConfig;
import airPlan.model.CodeList;
import airPlan.model.General;
import airPlan.model.Manual;

public class GeneralDelete {
	
	public static void delete(General general) {
		SpringJdbcConfig jdbcConfig = new SpringJdbcConfig();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcConfig.mysqlDataSource());
		JdbcManualRepository manualRepository = new JdbcManualRepository(jdbcTemplate);
		JdbcCodeListRepository codeListRepository = new JdbcCodeListRepository(jdbcTemplate);
		
		Manual manualModel = new Manual(general.getMnl_name());
		manualRepository.findIdManual(manualModel);
		
		CodeList codeListModel = new CodeList(manualModel.getMnl_id(),general.getFlg_secundary(), general.getCdl_section(),
				 Integer.parseInt(general.getCdl_block()));

		
		/* DEBUG */
		Logger loggerCodeList = LoggerFactory.getLogger(CodeList.class);
		loggerCodeList.debug(codeListModel.toString());
		loggerCodeList.info(codeListModel.toString());
		
		
		codeListRepository.delete(codeListModel);
		
	}
}