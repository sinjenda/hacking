package main;

import computer.*;

public class Main {
    public static void main(String[] args) {
        Disk d=new Disk();
        MotherBoard board=new MotherBoard(new Cpu(15),new Ram(80),d);
        Computer c=new Computer(board);
        d.generateDefaultFiles(c);
        d.createBinFiles(c.getRoot(), c);
        try {
            Terminal t=((Terminal)c.getRoot().getFile("/usr/bin/Terminal"));
            t.exec(new String[]{},null);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
