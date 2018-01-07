package com.cs.cs213.androidchess91.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class CBD implements Comparator<playBack> {
    @Override
    public int compare(playBack p1, playBack p2){
        String date = p1.getDate();
        String date2 = p2.getDate();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(date);
            d2 = format.parse(date2);
        } catch (ParseException e) {

        }

        return d1.compareTo(d2);
    }
}
