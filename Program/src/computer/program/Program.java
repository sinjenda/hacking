package computer.program;

import computer.Computer;
import computer.Game;
import computer.ProgramRegistry;
import computer.File;
import computer.program.logging.PermissionLevel;
import computer.program.logging.User;
import main.Terminal;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Program extends File implements Runnable {
    public static final User shop=new User("shop","", PermissionLevel.root);
    protected final Computer c;

    public Program(String name,Computer c) {
        super(name, system);
        this.c=c;
    }

    @Override
    public void buy(User user) {
        owner=user;
        super.buy(user);
    }

    public Program(String name, Computer c, int price) {
        super(name,shop);
        this.c=c;
        enable(price,name);
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
