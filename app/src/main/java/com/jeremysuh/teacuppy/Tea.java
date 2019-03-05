package com.jeremysuh.teacuppy;

public class Tea {

    private String name;
    private int brew_temperature;
    private int brew_time;
    private String description;
    private int caffeine;
    private int calories;

    private int id;

    public Tea(String name, int temperature, int brew_time, String description, int caffeine, int id, int calories){

        this.name = name;
        this.brew_temperature = temperature;
        this.brew_time = brew_time;
        this.description = description;
        this.id = id;
        this.caffeine = caffeine;
        this.calories = calories;

    }

    public void set_name(String n){
        name = n;
    }
    public String get_name(){
        return name;
    }

    public void set_temperature(int t){
        brew_temperature = t;
    }
    public int get_temperature(){
        return brew_temperature;
    }

    public void set_brew_time(int n){
        brew_time = n;
    }
    public int get_brew_time(){
        return brew_time;
    }

    public void set_caffeine(int n){
        caffeine = n;
    }
    public int get_caffeine(){
        return caffeine;
    }

    public void set_calories(int n){
        calories = n;
    }
    public int get_calories(){
        return calories;
    }

    public void set_description(String n){
        description = n;
    }
    public String get_description(){
        return description;
    }
}
