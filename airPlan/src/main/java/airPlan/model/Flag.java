package airPlan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class Flag {
   
    private String flg_secundary_id;
    
    private String flg_tag;
    
    public Flag(){

    }
}