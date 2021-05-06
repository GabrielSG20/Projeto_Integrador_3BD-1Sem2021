package airPlan.services;

import airPlan.model.CodeList;
import airPlan.model.Flag;
import airPlan.model.General;
import airPlan.model.Manual;
import airPlan.model.ManualFlag;
import airPlan.repository.CodeListRepository;
import airPlan.repository.FlagRepository;
import airPlan.repository.ManualFlagRepository;
import airPlan.repository.ManualRepository;

public class CodeListService {
	
	
	public static Manual saveManual(General general) {
		
		Manual manual = new Manual(general.getMnl_name());
		
		return manual;
	}
	
	public static Flag saveFlag(General general) {
		Flag flag = new Flag(general.getFlg_secundary_id(), general.getFlg_tag());
		
		return flag;
	}
	
	public static ManualFlag saveManualFlag(Manual manual, Flag flag) {
		ManualFlag manualFlag = new ManualFlag(manual, flag);
		
		return manualFlag;
	}
	
	public static CodeList saveCodeList(ManualFlag manualFlag, General general) {
		CodeList codeList = new CodeList(manualFlag, manualFlag, general.getCdl_section(), general.getCdl_sub_section(), Integer.parseInt(general.getCdl_block()),
										general.getCdl_block_name(), Integer.parseInt(general.getCdl_code()));
		
		return codeList;
	}
}
