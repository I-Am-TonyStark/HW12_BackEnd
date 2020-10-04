package com.mamalimomen.base.services.dtos;

import java.io.Serializable;

public abstract class BaseDTO<PK extends Number> implements Serializable {

    private static final long serialVersionUID = -4940234245804900900L;

    private PK id;

    private boolean deleted = false;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}