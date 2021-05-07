package com.airPlan.entities;

import javax.persistence.PostPersist;

public class IdListener {

    @PostPersist
    public Integer manualIdPersist(Manual manual) {

        return manual.getMnl_id();

    }
}
