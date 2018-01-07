package com.cs.cs213.androidchess91.Data;

import java.util.Comparator;

public class CBN implements Comparator<playBack> {
    @Override
    public int compare(playBack p1, playBack p2) {
        // for example - sort ascending by ID
        return p1.getName().compareTo(p2.getName());
    }
}
