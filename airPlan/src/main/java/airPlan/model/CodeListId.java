package airPlan.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class CodeListId {
		
		@Id
		private ManualFlag mnl_id;
		@Id
	    private String flg_secundary;
        @Id
        private String cdl_section;
        @Id
        private Integer cdl_block;       
}
