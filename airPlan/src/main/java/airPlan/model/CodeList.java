package airPlan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Data
@ToString
public class CodeList {
	    
		private int mnl_id;
		
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
        
        public CodeList(int mnl_id,String flg_secundary,String cdl_section,int cdl_block){
        		
        	this.mnl_id=mnl_id;
        	this.flg_secundary=flg_secundary;
        	this.cdl_section=cdl_section;
        	this.cdl_block=cdl_block;


        }
        
        public CodeList(String cdl_sub_section,String cdl_block_name,int cdl_code,int mnl_id,String flg_secundary, String cdl_section,int cdl_block){
        	this.cdl_sub_section=cdl_sub_section;
        	this.cdl_block_name=cdl_block_name;
        	this.cdl_code=cdl_code;
        	this.mnl_id=mnl_id;
        	this.flg_secundary=flg_secundary;
        	this.cdl_section=cdl_section;
        	this.cdl_block=cdl_block;


        }
}