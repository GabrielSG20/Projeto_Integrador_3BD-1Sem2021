package com.airplan.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "flag")
public class FlagModel {
    @Id
    @Column(name = "flg_secundary")
    private String flg_secundary;
    
    @Column(name = "flg_tag")
    private String flg_tag;

    public FlagModel(){

    }
}
