package com.cs.cs213.androidchess91.Data;


import java.io.Serializable;
import java.util.ArrayList;

public class playBack implements Serializable {
    private static final long serialVersionUID = 8559011478590691714L;
    ArrayList<String> al;
    String name;
    String date;
    public playBack(String name){
        this.name = name;
        al = new ArrayList<String>();
    }
    public void add(String s){
        al.add(s);
    }
    public String getName(){
        return name;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public void setName(String s){
        name = s;
    }
    public void remove(int index){
        al.remove(index);
    }
    public ArrayList<String> getList(){
        return al;
    }
    public String toString(){
        String s = "";
        s = name+" --- "+date;
        return s;
    }

}
