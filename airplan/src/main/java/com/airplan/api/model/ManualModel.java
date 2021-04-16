package com.airplan.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "manual")
public class ManualModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mnl_id")
	private int mnl_id;
	
    @Column(name = "mnl_name")
	private String mnl_name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "manual_flag",
	    joinColumns = {@JoinColumn (name = "mnl_id")},
	    inverseJoinColumns = {@JoinColumn (name = "flg_secundary")})
    private Set<FlagModel> flags;
	
	public ManualModel() {
		
	}
}
