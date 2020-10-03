package com.mamalimomen.base.controllers.utilities;

public enum PersistenceUnit {
    UNIT_ONE("persistence-unit-one");

    private String unitName;

    PersistenceUnit(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return this.unitName;
    }
}
