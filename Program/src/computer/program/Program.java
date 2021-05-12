package computer.program;

import computer.Computer;
import computer.DefaultBinFiles;
import computer.File;
import computer.program.logging.User;
import main.Terminal;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Program extends File implements Runnable {

    protected final Computer c;

    public Program(String name, User owner,Computer c,boolean isDefault) {
        super(name, owner);
        if (isDefault){
            DefaultBinFiles.add(this);
        }
        this.c=c;
    }

    public Program(String name, User owner, Computer c) {
        this(name,owner,c,false);
    }

    public void exec(String[] params,Terminal t) {
        Thread t1;
        Terminal t2 = null;
        if (this instanceof Terminal){
            t2=new Terminal((Terminal) this);
            t1=new Thread(t2);
        }
        else
            t1 = new Thread(this);

        t1.start();
        System.out.println(t2);
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i != ThreadLocalRandom.current().nextInt(80, 80000); i++) {
            ret.append(ThreadLocalRandom.current().nextInt(0, 9));
        }
        return ret.toString();
    }



    @Override
    public abstract void run();
}
