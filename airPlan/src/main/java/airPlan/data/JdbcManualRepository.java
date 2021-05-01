package airPlan.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import airPlan.model.Manual;
import airPlan.repository.ManualRepository;

@Repository
public class JdbcManualRepository implements ManualRepository{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcManualRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Manual save(Manual manual) {
		
		boolean x = jdbc.queryForObject("SELECT EXISTS(SELECT 1 FROM manual WHERE mnl_name = ?)", Boolean.class, manual.getMnl_name());
		if(!x) {
			jdbc.update(
					"insert into manual (mnl_name) values (?)",
					manual.getMnl_name());
		}
		
		return manual;
	}
	
	public void findIdManual(Manual manual) {
		int id = jdbc.queryForObject("SELECT mnl_id FROM manual WHERE mnl_name = ?", Integer.class, manual.getMnl_name());
		manual.setMnl_id(id);
	}
}
