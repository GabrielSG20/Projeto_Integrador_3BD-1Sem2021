package com.airplan.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class CodeListModel {
        private Long mnl_id;
        private String flg_secundary;
        private Long cdl_section;
        private String cdl_sub_section;
        private Long cdl_block;
        private String cdl_block_name;
        private Long cdl_code;
        private Long cdl_rev_number;
        private Date cdl_rev_date;
        private Long cdl_user;
        private String cdl_arquive_url;

    }

