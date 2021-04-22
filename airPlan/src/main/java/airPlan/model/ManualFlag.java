package airPlan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@AllArgsConstructor
@Getter
@Setter
@Data
public class ManualFlag {
    
    private int mnl_id;
    
    private String flg_secundary_id;
    
    public ManualFlag(){

    }
}