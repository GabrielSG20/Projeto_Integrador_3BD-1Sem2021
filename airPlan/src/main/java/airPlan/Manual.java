package airPlan;

import org.springframework.jdbc.core.RowCallbackHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Data
public class Manual {
		
	private String mnl_name;
        
	public Manual() {
		
	}
}