package com.example.ISA.tim6.ENUMS;

import java.util.Arrays;

public enum Role {
    KORISNIK("korisnik"),
    ADMINISTRATOR_CENTRA_I_MEDICINSKO_OSOBLJE("administrator_centra_i_medicinsko_osoblje"),
    ADMINISTRATOR_SISTEMA("administrator_sistema");

    private String value;

    private Role(String value) {
        this.value = value;
    }

    public static Role fromValue(String value) {
        for (Role category : values()) {
            if (category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }
}
