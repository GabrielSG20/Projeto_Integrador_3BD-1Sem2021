package airPlan.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "codelist")
@IdClass(CodeListId.class)
public class CodeList {
		
		@Id
		private ManualFlag mnl_id;
		@Id
	    private ManualFlag flg_secundary;
        @Id
        private String cdl_section;
        private String cdl_sub_section;
        @Id
        private Integer cdl_block;
        
        private String cdl_block_name;
        
        private Integer cdl_code;
        
        
        public CodeList(ManualFlag mnl_id,ManualFlag flg_secundary,String cdl_section,int cdl_block){
        		
        	this.mnl_id=mnl_id;
        	this.flg_secundary=flg_secundary;
        	this.cdl_section=cdl_section;
        	this.cdl_block=cdl_block;
        }
        

}