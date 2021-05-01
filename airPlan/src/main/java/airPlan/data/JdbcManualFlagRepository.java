package airPlan.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import airPlan.model.ManualFlag;
import airPlan.repository.ManualFlagRepository;

@Repository
public class JdbcManualFlagRepository implements ManualFlagRepository{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcManualFlagRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public ManualFlag save(ManualFlag manualFlag) {
		
		boolean x = jdbc.queryForObject("SELECT EXISTS(SELECT 1 FROM manual_flag WHERE mnl_id = ?)", Boolean.class, manualFlag.getMnl_id());
		boolean y = jdbc.queryForObject("select exists(select 1 from manual_flag where flg_secundary = ?)", Boolean.class, manualFlag.getFlg_secundary_id());
		boolean u = jdbc.queryForObject("select exists(select 1 from flag where flg_secundary = ?)", Boolean.class, manualFlag.getFlg_secundary_id());
		boolean i = jdbc.queryForObject("select exists(select 1 from manual where mnl_id = ?)", Boolean.class, manualFlag.getFlg_secundary_id());
		if(!x && !y || !u || !i) {
			jdbc.update(
					"insert into manual_flag (mnl_id, flg_secundary) values (?,?)",
					manualFlag.getMnl_id(), manualFlag.getFlg_secundary_id());
		
		}
		return manualFlag;
		
	
	}
}
