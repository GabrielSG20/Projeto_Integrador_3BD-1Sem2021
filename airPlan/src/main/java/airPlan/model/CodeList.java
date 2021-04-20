package airPlan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class CodeList {
	    
		private String mnl_name;
		
	    private String flg_secundary;
        
        private String cdl_section;
        
        private String cdl_sub_section;
        
        private int cdl_block;
        
        private String cdl_block_name;
        
        private int cdl_code;
        
        /*
        private UserModel emp_id;
		*/
        public CodeList(){

        }
}