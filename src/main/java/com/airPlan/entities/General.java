package com.airPlan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class General {

    private String flg_secundary;

    private String flg_secundary_id;

    private String cdl_section;

    private String cdl_sub_section;

    private String cdl_block_number;

    private String cdl_block_name;

    private String cdl_code;

    private String flg_tag;

    private String mnl_name;

    private General[] codelist = new General[3];

    public Integer addLista(General[] general, CodeList[] codeListModel) {
        int count = 3;
        for (int i = 0; i < 3; i++) {
            if (general[i].getCdl_section().equals("")) {
                --count;
                continue;
            } else {
                codeListModel[i] = new CodeList(i, general[i].getFlg_secundary(), general[i].getCdl_section(),
                        Integer.parseInt(general[i].getCdl_block_number()), general[i].getCdl_sub_section(),
                        general[i].getCdl_block_name(), Integer.parseInt(general[i].getCdl_code()));
            }
        }
        return count;
    }
}

