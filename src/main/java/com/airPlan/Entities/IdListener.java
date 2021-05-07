package com.airPlan.Entities;

import javax.persistence.PostPersist;

public class IdListener {

    @PostPersist
    public Integer manualIdPersist(Manual manual) {

        return manual.getMnl_id();

    }
}
