package computer.program.logging.programFiles;

import computer.Computer;
import computer.program.Program;
import computer.program.logging.Passwd;
import computer.program.logging.User;
import main.Terminal;
import me.tongfei.progressbar.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Decipher extends Program {
    String name;
    Terminal.Console console;
    final Computer c;

    @Override
    public void exec(String[] params, Terminal t) {
        name=Terminal.pathBuilder(params[0],params[1]);
        console=t.getWriter();
        super.exec(params, t);
    }

    public Decipher( User owner, Computer c) {
        super("decipher", owner, c);
        this.c=c;
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        ProgressBar bar=Terminal.createProgressBar("decipher",console);
        while(bar.getCurrent()!=bar.getMax()){
            try {
                Thread.sleep(400);
                bar.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            for (int i=0;i!=4;i++){
                bar.stepTo(100);
                Thread.sleep(200);
            }

            ArrayList<User>users=((Passwd)c.getRoot().getFile(name)).getUsers();
            if (users.size()==1){
                console.println("\nfound password for user "+users.get(0).getName()+": "+users.get(0).getPassword());
            }
            console.println("\nfound password for user "+users.get(ThreadLocalRandom.current().nextInt(0,users.size()-1)).getName()+": "+users.get(ThreadLocalRandom.current().nextInt(0,users.size()-1)).getPassword());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
