package airPlan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@AllArgsConstructor
@Getter
@Setter
@Data
public class ManualFlag {
    
    private String mnl_name;
    
    private String flg_secundary;
    
    public ManualFlag(){

    }
}