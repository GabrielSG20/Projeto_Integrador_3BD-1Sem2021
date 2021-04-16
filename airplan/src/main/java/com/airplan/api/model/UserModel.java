package com.airplan.api.model;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class UserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int emp_id; 
	
	@Column(name = "emp_name")
	private String emp_name;
	
	@Column(name = "emp_password")
	private String emp_password; 
	
	@Column(name = "typ_id")
	private int typ_id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "emp_id")
	private Set<CodeListModel> documentos;
	
	public UserModel() {
		
	}
}
