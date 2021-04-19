package airPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class Flag {
   
    private String flg_secundary;
    
    private String flg_tag;
    
    public Flag(){

    }
}