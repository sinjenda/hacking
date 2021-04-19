package main;

import computer.*;
import computer.program.Program;
import computer.program.logging.Passwd;

public class Main {
    public static void main(String[] args) {
        Disk d=new Disk();
        MotherBoard board=new MotherBoard(new Cpu(15),new Ram(80),d);
        Computer c=new Computer(board);
        d.generateDefaultFiles(c);
        try {
            ((Program)c.getRoot().getFile("/usr/bin/Terminal")).exec();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
