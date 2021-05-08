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

    private Integer cdl_block;

    private String cdl_sub_section;

    private String cdl_block_name;

    private Integer cdl_code;
}
