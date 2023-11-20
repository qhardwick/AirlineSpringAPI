package com.revature.constants;

import java.io.Serializable;

public enum AircraftType implements Serializable {

    BOEING737_700("737"),
    BOEING737_800("738"),
    BOEING737_900("739");

    public final String code;

    private AircraftType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }

}

