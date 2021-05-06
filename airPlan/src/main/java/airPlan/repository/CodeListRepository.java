package airPlan.repository;

import java.util.List;

import airPlan.model.CodeList;

public interface CodeListRepository {
	
	CodeList save(CodeList codeList);
	CodeList delete(CodeList codeList);
	CodeList edit(CodeList codeList);
	List<CodeList> list();
	List<CodeList> fitrar(String mnlId,String flgSecundary, String cdlBlock);
}
