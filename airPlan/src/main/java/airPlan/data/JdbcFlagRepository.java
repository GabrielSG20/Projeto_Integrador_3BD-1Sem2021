package airPlan.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import airPlan.model.Flag;
import airPlan.repository.FlagRepository;

@Repository
public class JdbcFlagRepository implements FlagRepository{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcFlagRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Flag save(Flag flag) {
		
		boolean x = jdbc.queryForObject("SELECT EXISTS(SELECT 1 FROM flag WHERE flg_secundary = ?)", Boolean.class, flag.getFlg_secundary_id());
		if(!x) {
			jdbc.update(
					"insert into flag (flg_secundary, flg_tag) values (?, ?)",
					flag.getFlg_secundary_id(), flag.getFlg_tag());
		}
		
		return flag;
	}
	
	@Override
	public Flag editTag(Flag flag) {
		jdbc.update(
				"update flag set flg_tag = (?) where flg_secundary = (?)",
				flag.getFlg_tag(), flag.getFlg_secundary_id());
		
		return flag;
	}
	
}
