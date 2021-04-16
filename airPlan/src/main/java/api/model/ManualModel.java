package api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "manual")
@Component
public class ManualModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mnl_id")
	private int mnl_id;
	
    @Column(name = "mnl_name")
	private String mnl_name;
	
	public ManualModel() {
		
	}
}
