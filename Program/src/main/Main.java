package main;

import computer.*;

public class Main {
    public static void main(String[] args) {
        try {
            Terminal t=((Terminal)Game.mainComputer.getRoot().getFile("/usr/bin/Terminal"));
            t.exec(new String[]{},null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
