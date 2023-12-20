package com.dadry.spygame.roles;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    SPY("Шпигун"),
    PLAYER("Гравець");

    private static final Map<String, Role> values = new HashMap<>();

    static {
        for (Role role: values()) {
            values.put(role.localName, role);
        }
    }

    private final String localName;

    Role(String localName) {
        this.localName = localName;
    }
}
