package airPlan.web;

import airPlan.model.CodeList;
import airPlan.model.Flag;
import airPlan.model.General;
import airPlan.model.Manual;
import airPlan.model.ManualFlag;

public class GeneralCreate {
	
	public Manual createManual(General general) {
		Manual manual = new Manual(general.getMnl_name());
		
		return manual;
	}
	
	public Flag createFlag(General general) {
		Flag flag = new Flag(general.getFlg_secundary_id(), general.getFlg_tag());
		
		return flag;
	}
	
	public ManualFlag createManualFlag(Manual manual, Flag flag) {
		ManualFlag manualFlag = new ManualFlag(manual, flag);
		
		return manualFlag;
	}
	
	public CodeList createCodeList(ManualFlag manualFlag, General general) {
		CodeList codeList = new CodeList(manualFlag, manualFlag, general.getCdl_section(), general.getCdl_sub_section(), Integer.parseInt(general.getCdl_block()),
										general.getCdl_block_name(), Integer.parseInt(general.getCdl_code()));
		
		return codeList;
	}
	
}
