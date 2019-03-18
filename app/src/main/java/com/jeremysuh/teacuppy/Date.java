package com.jeremysuh.teacuppy;


// date class; used when storing data to the database for user stats
public class Date {

    private String date;
    private int cups;
    private int calories;
    private int caffeine;

    private int id;

    public Date(){};

    public Date(String date, int cups, int calories, int caffine){

        this.date = date;
        this.cups = cups;
        this.calories = calories;
        this.caffeine = caffine;

    }

    public void set_date(String n){
        date = n;
    }
    public void set_cups(int n){
        cups = n;
    }
    public void set_calories(int n){
        calories = n;
    }
    public void set_caffeine(int n){
        caffeine = n;
    }

    public String get_date(){
        return date;
    }
    public int get_cups(){
        return cups;
    }
    public int get_caffeine(){
        return caffeine;
    }
    public int get_calories(){
        return calories;
    }

}
