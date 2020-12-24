package ru.job4j.nonblocking;

import java.util.Objects;

public class Base {
    private int id;
    private int version;
    private String name;

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Base(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && version == base.version
                && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name);
    }

    @Override
    public String toString() {
        return "Base{"
                + "id=" + id
                + ", version=" + version
                + ", name='" + name + '\''
                + '}';
    }
}
