package org.example.model;

import java.util.Objects;

public class Currency implements Comparable<Currency> {
    private String name;
    private String code;

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }

    @Override
    public int compareTo(Currency o) {
        return this.code.compareTo(o.code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency currency)) return false;
        return getCode().equalsIgnoreCase(((Currency) o).getCode());
    }

}
