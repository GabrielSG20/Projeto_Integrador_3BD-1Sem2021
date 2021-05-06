package airPlan.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Manual")
public class Manual {
	
	@Id @GeneratedValue
	private Integer mnl_id;
	
	private String mnl_name;
        

	public Manual(String mnl_name) {
		this.mnl_name = mnl_name;
	}
}