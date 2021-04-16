package api.service;


import org.springframework.stereotype.Service;

import api.model.CodeListModel;
import api.model.FlagModel;

import api.model.General;
import api.model.ManualFlagModel;
import api.model.ManualModel;

@Service
public class GeneralService {
	
	public static void create(General general) {
		
		ManualModel manualModel = new ManualModel();
		manualModel.setMnl_name(general.getMnl_name());
		
		ManualFlagModel manualFlagModel = new ManualFlagModel(manualModel.getMnl_id(), general.getFlg_secundary());
		
		CodeListModel codeListModel = new CodeListModel((long)manualModel.getMnl_id(), general.getFlg_secundary(), general.getCdl_section(),
														general.getCdl_sub_section(), general.getCdl_block(), general.getCdl_block_name(),
														general.getCdl_code(), (long)1);
		
		FlagModel flagModel = new FlagModel(general.getFlg_tag(), general.getFlg_secundary());
		
		System.out.println(manualModel);
		System.out.println(manualFlagModel);
		System.out.println(codeListModel);
		System.out.println(flagModel);
		
		ManualService manualService = new ManualService();
		
		manualService.createManual(manualModel);
		
		ManualFlagService manualFlagService = new ManualFlagService();
		
		manualFlagService.createManualFlag(manualFlagModel);
		
		CodeListService codeListService = new CodeListService();
		
		codeListService.createCodelist(codeListModel);
		
		FlagService flagService = new FlagService();
		
		flagService.createFlag(flagModel);
		
	
	}
}
