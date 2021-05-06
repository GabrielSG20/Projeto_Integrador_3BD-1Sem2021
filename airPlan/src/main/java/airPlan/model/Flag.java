package airPlan.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Flag {
   
	@Id
    private String flg_secundary_id;
    
    private String flg_tag;
    
}