package com.airplan.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "codelist")
public class CodeListModel {
        @Id
        private Long mnl_id;

        private String flg_secundary;

        private Long cdl_section;

        private String cdl_sub_section;

        private Long cdl_block;

        private String cdl_block_name;

        private Long cdl_code;

        private Long cdl_rev_number;

        private Date cdl_rev_date;

        private Long emp_id;

        private String cdl_arquive_url;

        public CodeListModel(){

        }
    }
