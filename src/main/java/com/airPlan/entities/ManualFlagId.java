package com.airPlan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class ManualFlagId implements Serializable {



    private Integer mnl_id;



    private String flg_secundary;

}
