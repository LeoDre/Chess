package com.cs.cs213.androidchess91.Data;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class playBackList implements Serializable {
    private static final long serialVersionUID = 5024004452512589713L;
    private ArrayList<playBack> al;
    public playBackList(){
        al = new ArrayList<playBack>();
    }
    public ArrayList<playBack> getList(){
        return al;
    }
    public static void writeData(Context parentActivity, playBackList l)throws IOException {
       // sg = new SaveGame(fileName, boardLayouts);

        String fileName = parentActivity.getFilesDir() + "/" + "chess91.dat";

        File f = new File(fileName);
        ObjectOutputStream oos;
        FileOutputStream fos = new FileOutputStream(fileName);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(l);
        oos.close();
        fos.close();
    }

    public static playBackList loadData(Context parentActivity)throws IOException, ClassNotFoundException {
        String fileName = parentActivity.getFilesDir() + "/" + "chess91.dat";

        File f = new File( fileName );
        if( !f.exists() ){
            return null;
        }

        ObjectInputStream ois;
        FileInputStream fis = new FileInputStream(fileName);
        ois = new ObjectInputStream(fis);
        playBackList l = (playBackList) ois.readObject();
        ois.close();
        fis.close();

        return l;
    }
}
