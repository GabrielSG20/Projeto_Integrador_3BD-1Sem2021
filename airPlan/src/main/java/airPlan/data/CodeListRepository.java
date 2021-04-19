package airPlan.data;

import airPlan.CodeList;

public interface CodeListRepository {
	
	CodeList save(CodeList codeList);
	CodeList delete(CodeList codeList);
	CodeList edit(CodeList codeList);
}
