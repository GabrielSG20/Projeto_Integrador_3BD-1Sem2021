package airPlan.web;

import java.sql.SQLException;

import javax.sql.DataSource;

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

public class GeneralCreate {
	
	public static void create(General general) {
		
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
}
