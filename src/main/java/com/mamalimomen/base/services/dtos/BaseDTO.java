package com.mamalimomen.base.services.dtos;

import java.io.Serializable;

public class BaseDTO<PK extends Number> implements Serializable {
    private PK id;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }
}