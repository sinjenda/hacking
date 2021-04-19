package computer.program;

import computer.Computer;
import computer.File;
import computer.program.logging.User;
import main.Terminal;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Program extends File implements Runnable {
    ArrayList<Port> openPorts = new ArrayList<>();

    protected Computer c;

    public Program(String name, User owner,Computer c) {
        super(name, owner);
        this.c=c;
    }

    public void addPort(int num) {
        openPorts.add(new Port(num));
    }

    public void exec(String[] params,Terminal t) {
        Thread t1;
        if (this instanceof Terminal){
            t1=new Thread(new Terminal((Terminal) this));
        }
        else
            t1 = new Thread(this);

        t1.start();
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
