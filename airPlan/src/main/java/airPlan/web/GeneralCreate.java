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
		manualRepository.findIdManual(manualModel);
		
		Flag flagModel = new Flag(general.getFlg_secundary(), general.getFlg_tag());
		flagRepository.save(flagModel);
		
		ManualFlag manualFlagModel = new ManualFlag(manualModel.getMnl_id(),general.getFlg_secundary());
		manualFlagRepository.save(manualFlagModel);
		
		CodeList codeListModel = new CodeList(manualModel.getMnl_id(),general.getFlg_secundary(), general.getCdl_section(),
														general.getCdl_sub_section(), general.getCdl_block(), general.getCdl_block_name(),
														general.getCdl_code());
		codeListRepository.save(codeListModel);
		
		System.out.println(manualModel);
		System.out.println(manualFlagModel);
		System.out.println(codeListModel);
		System.out.println(flagModel);
	}
}
