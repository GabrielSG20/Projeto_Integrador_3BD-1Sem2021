package com.airPlan.entities;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "codelist")
public class CodeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdl_id;

    private Integer mnl_id;

    private String flg_secundary;

    private String cdl_section;

    private Integer cdl_block_number;

    private String cdl_sub_section;

    private String cdl_block_name;

    private Integer cdl_code;

    public CodeList(Integer mnl_id, String flg_secundary, String cdl_section, Integer cdl_block_number,
                    String cdl_sub_section, String cdl_block_name, Integer cdl_code) {

        this.mnl_id = mnl_id;
        this.flg_secundary = flg_secundary;
        this.cdl_section = cdl_section;
        this.cdl_block_number = cdl_block_number;
        this.cdl_sub_section = cdl_sub_section;
        this.cdl_block_name = cdl_block_name;
        this.cdl_code = cdl_code;
    }
}
