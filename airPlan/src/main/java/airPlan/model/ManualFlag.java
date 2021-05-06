package airPlan.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "manual_flag")
@IdClass(ManualFlagId.class)
public class ManualFlag {
    
	@Id
    private Manual mnl_id;
    @Id
    private Flag flg_secundary_id;
    
}