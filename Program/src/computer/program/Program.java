package computer.program;

import computer.File;
import computer.program.logging.User;

import java.util.ArrayList;

public abstract class Program extends File implements Runnable{
    ArrayList<Port>openPorts=new ArrayList<>();

    public Program(String name, User owner) {
        super(name,owner);
    }
    public void addPort(int num){
        openPorts.add(new Port(num));
    }
    public void exec(){
        Thread t=new Thread(this);
        t.start();
    }

    @Override
    public abstract void run();
}
