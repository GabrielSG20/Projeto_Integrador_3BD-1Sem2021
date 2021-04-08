package com.airplan.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateCodeModel {
    private String primaryKey;
    private String secondKey;
    private String tag;
    private String sectionNumber;
    private String subSection;
    private String blockNumber;
    private String blockName;
    private String code;

}
