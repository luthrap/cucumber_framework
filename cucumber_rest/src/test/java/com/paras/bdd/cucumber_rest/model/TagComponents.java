package com.paras.bdd.cucumber_rest.model;

public class TagComponents {
    private int id;
    private String name;

    @Override
    public String toString() {
        return id + "_" + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
