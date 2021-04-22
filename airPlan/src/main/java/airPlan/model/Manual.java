package airPlan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Data
public class Manual {
		
	private int mnl_id;
	
	private String mnl_name;
        
	public Manual() {
		
	}

	public Manual(String mnl_name) {
		this.mnl_name = mnl_name;
	}
}