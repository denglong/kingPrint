package com.family_doctor.test_demo.model;

public class BlueDivice {
    public String name;
    public String address;
    public int state;

    public BlueDivice() {
        name = "";
        address = "";
        state = 0;
    }

    public BlueDivice(String name, String address, int state) {
        this.name = name;
        this.address = address;
        this.state = state;
    }

    @Override
    public String toString() {
        return "BlueDevice{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", state=" + state +
                '}';
    }
}
