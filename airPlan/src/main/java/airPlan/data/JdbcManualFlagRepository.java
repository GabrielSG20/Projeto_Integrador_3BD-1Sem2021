package airPlan.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import airPlan.model.ManualFlag;

@Repository
public class JdbcManualFlagRepository implements ManualFlagRepository{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcManualFlagRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public ManualFlag save(ManualFlag manualFlag) {
		
		boolean x = jdbc.queryForObject("SELECT EXISTS(SELECT 1 FROM manual_flag WHERE mnl_name = ?)", Boolean.class, manualFlag.getMnl_name());
		System.out.println(x);
		boolean y = jdbc.queryForObject("select exists(select 1 from manual_flag where flg_secundary = ?)", Boolean.class, manualFlag.getFlg_secundary());
		System.out.println(y);
		if(!x && !y) {
			jdbc.update(
					"insert into manual_flag (mnl_name, flg_secundary) values (?,?)",
					manualFlag.getMnl_name(), manualFlag.getFlg_secundary());
		
		}
		return manualFlag;
		
	
	}
}
