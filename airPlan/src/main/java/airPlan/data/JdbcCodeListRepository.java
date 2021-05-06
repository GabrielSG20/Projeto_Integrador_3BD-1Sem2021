package airPlan.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import airPlan.model.CodeList;
import airPlan.repository.CodeListRepository;

@Repository
public class JdbcCodeListRepository implements CodeListRepository{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcCodeListRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public CodeList save(CodeList codeList) {
		if(codeList.getCdl_sub_section().equals("")) {
			jdbc.update(
					"insert into codelist (mnl_id, flg_secundary, "
					+ " cdl_section, cdl_block, cdl_block_name, cdl_code) values (?,?,?,?,?,?)",
					codeList.getMnl_id(), codeList.getFlg_secundary(),
					codeList.getCdl_section(),codeList.getCdl_block(), 
					codeList.getCdl_block_name(), codeList.getCdl_code()
					);
		} else {
			jdbc.update(
					"insert into codelist (mnl_id, flg_secundary, cdl_section,"
					+ " cdl_sub_section, cdl_block, cdl_block_name, cdl_code) values (?,?,?,?,?,?,?)",
					codeList.getMnl_id(), codeList.getFlg_secundary(),
					codeList.getCdl_section(), codeList.getCdl_sub_section(),
					codeList.getCdl_block(), codeList.getCdl_block_name(),
					codeList.getCdl_code()
					);
		}
		
		return codeList;
	}
	
	@Override
	public CodeList delete(CodeList codeList) {
		jdbc.update(
				"delete from codelist where mnl_id = (?) and flg_secundary = (?) and cdl_section = (?) and cdl_block = (?)",
				codeList.getMnl_id(), codeList.getFlg_secundary(),
				codeList.getCdl_section(), codeList.getCdl_block()
				);
		
		return codeList;
	}
	
	@Override
	public CodeList edit(CodeList codeList) {
		jdbc.update(
				"update codelist set cdl_sub_section = (?), cdl_block_name = (?), cdl_code = (?) where mnl_id = (?) "
				+ "and flg_secundary = (?) and cdl_section = (?) and cdl_block = (?)",
				codeList.getCdl_sub_section(),
				codeList.getCdl_block_name(),
				codeList.getCdl_code(), codeList.getMnl_id(), codeList.getFlg_secundary(),
				codeList.getCdl_section(), codeList.getCdl_block());
		
		return codeList;
	}
	
	public List<CodeList> list(){
		String sql = "select * from codelist";
		return jdbc.query(sql, (rs, rowNum) -> {
			CodeList codeList = new CodeList();
			codeList.setMnl_id(rs.getInt("mnl_id"));
			codeList.setFlg_secundary(rs.getString("flg_secundary"));
			codeList.setCdl_section(rs.getString("cdl_section"));
			codeList.setCdl_sub_section(rs.getString("cdl_sub_section"));
			codeList.setCdl_block(rs.getInt("cdl_block"));
			codeList.setCdl_block_name(rs.getString("cdl_block_name"));
			codeList.setCdl_code(rs.getInt("cdl_code"));
			
			return codeList;
		});
	}
	
	public List<CodeList> fitrar(String mnlId,String flgSecundary, String cdlBlock){
		if (flgSecundary.equals("") && cdlBlock.equals("")) {
			String sql = "select * from codelist where mnl_id = " + mnlId;
			return jdbc.query(sql, (rs, rowNum) -> {
				CodeList codeList = new CodeList();
				codeList.setMnl_id(rs.getInt("mnl_id"));
				codeList.setFlg_secundary(rs.getString("flg_secundary"));
				codeList.setCdl_section(rs.getString("cdl_section"));
				codeList.setCdl_sub_section(rs.getString("cdl_sub_section"));
				codeList.setCdl_block(rs.getInt("cdl_block"));
				codeList.setCdl_block_name(rs.getString("cdl_block_name"));
				codeList.setCdl_code(rs.getInt("cdl_code"));
				
				return codeList;
			});
			
		} else if(cdlBlock.equals("")){
			String sql = "select * from codelist where flg_secundary = " + flgSecundary + " and mnl_id = " + mnlId;
			return jdbc.query(sql, (rs, rowNum) -> {
				CodeList codeList = new CodeList();
				codeList.setMnl_id(rs.getInt("mnl_id"));
				codeList.setFlg_secundary(rs.getString("flg_secundary"));
				codeList.setCdl_section(rs.getString("cdl_section"));
				codeList.setCdl_sub_section(rs.getString("cdl_sub_section"));
				codeList.setCdl_block(rs.getInt("cdl_block"));
				codeList.setCdl_block_name(rs.getString("cdl_block_name"));
				codeList.setCdl_code(rs.getInt("cdl_code"));
				
				return codeList;
			});
		} else if (flgSecundary.equals("")){
			String sql = "select * from codelist where cdl_block = " + cdlBlock + " and mnl_id = " + mnlId;
			return jdbc.query(sql, (rs, rowNum) -> {
				CodeList codeList = new CodeList();
				codeList.setMnl_id(rs.getInt("mnl_id"));
				codeList.setFlg_secundary(rs.getString("flg_secundary"));
				codeList.setCdl_section(rs.getString("cdl_section"));
				codeList.setCdl_sub_section(rs.getString("cdl_sub_section"));
				codeList.setCdl_block(rs.getInt("cdl_block"));
				codeList.setCdl_block_name(rs.getString("cdl_block_name"));
				codeList.setCdl_code(rs.getInt("cdl_code"));
				
				return codeList;
			});
		} else {
			String sql = "select * from codelist where mnl_id = " + mnlId + " and flg_secundary = " + flgSecundary + 
					" and cdl_block = " + cdlBlock;
			return jdbc.query(sql, (rs, rowNum) -> {
				CodeList codeList = new CodeList();
				codeList.setMnl_id(rs.getInt("mnl_id"));
				codeList.setFlg_secundary(rs.getString("flg_secundary"));
				codeList.setCdl_section(rs.getString("cdl_section"));
				codeList.setCdl_sub_section(rs.getString("cdl_sub_section"));
				codeList.setCdl_block(rs.getInt("cdl_block"));
				codeList.setCdl_block_name(rs.getString("cdl_block_name"));
				codeList.setCdl_code(rs.getInt("cdl_code"));
				
				return codeList;
			});
		}
	}
}
