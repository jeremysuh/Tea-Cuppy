package com.jeremysuh.teacuppy;

public class Tea {

    private String name;
    private String brew_temperature;
    private int brew_time;
    private String description;

    private int id;

    public Tea(String name, String temperature, int brew_time, String description, int id){

        this.name = name;
        this.brew_temperature = temperature;
        this.brew_time = brew_time;
        this.description = description;
        this.id = id;

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

    public void set_brew_time(int n){
        brew_time = n;
    }
    public int get_brew_time(){
        return brew_time;
    }

    public void set_description(String n){
        description = n;
    }
    public String get_description(){
        return description;
    }
}
