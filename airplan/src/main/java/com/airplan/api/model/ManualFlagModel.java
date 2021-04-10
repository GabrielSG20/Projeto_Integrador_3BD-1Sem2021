package com.airplan.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ManualFlagModel {
	private String mnl_id;
	private Long flg_secundary;
}
