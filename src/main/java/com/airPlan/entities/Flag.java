package com.airPlan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="flag")
public class Flag implements Serializable {

    /* poderia mudar flg_secundary_id
       para flg_secundary?*/
    @Id
    @Column(name = "flg_secundary")
    private String flg_secundary_id;

    private String flg_tag;

}
