package com.airplan.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "manual_flag")
public class ManualFlagModel {
	@Id
	private int mnl_id;
	private String flg_secundary;
	
	public ManualFlagModel() {
		
	}
}
