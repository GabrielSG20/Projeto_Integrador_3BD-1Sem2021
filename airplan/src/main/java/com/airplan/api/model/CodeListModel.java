package com.airplan.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "codelist")
public class CodeListModel {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "cdl_id")
	    private int cdl_id;

		@Column(name = "mnl_id")
        private ManualModel mnl_id;
        
        @Column(name = "flg_secundary")
        private FlagModel flg_secundary;
        
        @Column(name = "cdl_section")
        private int cdl_section;
        
        @Column(name = "cdl_sub_section")
        private String cdl_sub_section;
        
        @Column(name = "cdl_block")
        private int cdl_block;
        
        @Column(name = "cdl_block_name")
        private String cdl_block_name;
        
        @Column(name = "cdl_code")
        private int cdl_code;
        
        @Column(name = "emp_id")
        private int emp_id;

        public CodeListModel(){

        }
    }
