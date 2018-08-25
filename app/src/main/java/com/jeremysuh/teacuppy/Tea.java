package com.jeremysuh.teacuppy;

public class Tea {

    private String name;
    private String brew_temperature;

    public Tea(String name, String temperature){

        this.name = name;
        this.brew_temperature = temperature;

    }

    public void set_name(String n){
        name = n;
    }

    public String get_name(){
        return name;
    }

    public void set_temperature(String t){
        brew_temperature = t;
    }

    public String get_temperature(){
        return brew_temperature;
    }
}
