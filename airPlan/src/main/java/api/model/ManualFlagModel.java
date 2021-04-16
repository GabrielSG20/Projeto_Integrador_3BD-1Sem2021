package api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "manual_flag")
@Component
public class ManualFlagModel {
	@Id
	private int mnl_id;
	private String flg_secundary;
	
	public ManualFlagModel() {
		
	}
}
