package com.airplan.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "flags")
    private Set<ManualModel> manuais;
    
    public FlagModel(){

    }
}
