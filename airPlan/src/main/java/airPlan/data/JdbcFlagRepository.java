package airPlan.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import airPlan.Flag;

@Repository
public class JdbcFlagRepository implements FlagRepository{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcFlagRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Flag save(Flag flag) {
		
		boolean x = jdbc.queryForObject("SELECT EXISTS(SELECT 1 FROM flag WHERE flg_secundary = ?)", Boolean.class, flag.getFlg_secundary());
		System.out.println(x);
		if(!x) {
			jdbc.update(
					"insert into flag (flg_secundary, flg_tag) values (?, ?)",
					flag.getFlg_secundary(), flag.getFlg_tag());
		}
		
		return flag;
	}
	
	
}
