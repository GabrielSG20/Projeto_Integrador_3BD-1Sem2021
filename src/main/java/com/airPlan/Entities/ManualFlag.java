package com.airPlan.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "manual_flag")
public class ManualFlag implements Serializable {
    /* problema para salvar
     usa generated id de outra table*/
    @EmbeddedId
    private ManualFlagId manualFlagId;


}
