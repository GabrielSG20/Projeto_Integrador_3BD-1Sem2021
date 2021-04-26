package airPlan.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class General {
        
        private String flg_secundary;
        
        private String flg_secundary_id;
        
        private String cdl_section;
        
        private String cdl_sub_section;
        
        private String cdl_block;
        
        private String cdl_block_name;
        
        private String cdl_code;
        
        private String flg_tag;
        
        private String mnl_name;
        
		private General[] codelist = new General[5];
        
        public General(){

        }
        
        public Integer addLista(General [] general, ManualFlag[] manualFlagModel, CodeList[] codeListModel) {
        	int count = 3;
        	for(int i = 0; i<3;i++) {
        		if(general[i].getFlg_secundary().equals("")) {
        			--count;
        			continue;
        		} else {
        			manualFlagModel[i] = new ManualFlag(i,general[i].getFlg_secundary());
        		}
        		if(general[i].getCdl_section().equals("")) {
        			--count;
        			continue;
        		} else {
        			codeListModel[i] = new CodeList(i,general[i].getFlg_secundary(), general[i].getCdl_section(),
					general[i].getCdl_sub_section(), Integer.parseInt(general[i].getCdl_block()), general[i].getCdl_block_name(),
					Integer.parseInt(general[i].getCdl_code()));
        		}
        	}
        	return count;
        }
       

    }