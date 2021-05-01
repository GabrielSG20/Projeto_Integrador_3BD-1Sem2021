package airPlan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
public class Manual {
		
	private int mnl_id;
	
	private String mnl_name;
        

	public Manual(String mnl_name) {
		this.mnl_name = mnl_name;
	}
}