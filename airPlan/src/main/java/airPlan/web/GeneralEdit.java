package airPlan.web;

import org.springframework.jdbc.core.JdbcTemplate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import airPlan.data.JdbcCodeListRepository;
import airPlan.data.JdbcFlagRepository;
import airPlan.data.JdbcManualRepository;
import airPlan.data.SpringJdbcConfig;
import airPlan.model.CodeList;
import airPlan.model.Flag;
import airPlan.model.General;
import airPlan.model.Manual;

public class GeneralEdit {
	
	public static void edit(General general) {
		SpringJdbcConfig jdbcConfig = new SpringJdbcConfig();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(jdbcConfig.mysqlDataSource());
		JdbcManualRepository manualRepository = new JdbcManualRepository(jdbcTemplate);
		JdbcCodeListRepository codeListRepository = new JdbcCodeListRepository(jdbcTemplate);
		JdbcFlagRepository flagRepository = new JdbcFlagRepository(jdbcTemplate);
		
		Manual manualModel = new Manual(general.getMnl_name());
		manualRepository.findIdManual(manualModel);
		
		
		CodeList codeListModel = new CodeList(manualModel.getMnl_id(),general.getFlg_secundary(), general.getCdl_section(),
														general.getCdl_sub_section(), Integer.parseInt(general.getCdl_block()), general.getCdl_block_name(),
														Integer.parseInt(general.getCdl_code()));
		
		Flag flagmodel = new Flag(general.getFlg_secundary(), general.getFlg_tag());
		
		/* DEBUG */
		Logger logger = LoggerFactory.getLogger(CodeList.class);
		logger.debug(codeListModel.toString());
		logger.info(codeListModel.toString());
		
		codeListRepository.edit(codeListModel);
		flagRepository.editTag(flagmodel);
	}
}