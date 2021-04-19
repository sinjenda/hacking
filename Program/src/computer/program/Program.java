package computer.program;

import computer.File;
import computer.program.logging.User;
import main.Terminal;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Program extends File implements Runnable {
    ArrayList<Port> openPorts = new ArrayList<>();

    public Program(String name, User owner) {
        super(name, owner);
    }

    public void addPort(int num) {
        openPorts.add(new Port(num));
    }

    public void exec() {
        Thread t;
        if (this instanceof Terminal){
            t=new Thread(new Terminal((Terminal) this));
        }
        else
            t = new Thread(this);

        t.start();
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
