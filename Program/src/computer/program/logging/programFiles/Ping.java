package computer.program.logging.programFiles;

import computer.Computer;
import computer.networking.Packet;
import computer.networking.Port;
import computer.program.Program;
import main.Terminal;

import java.io.*;

public class Ping extends Program {
    Terminal t;
    String[] params;

    public Ping(Computer c) {
        super("ping", system, c,true);
    }

    @Override
    public void exec(String[] params, Terminal t) {
        this.t=t;
        this.params=params;
        super.exec(params, t);
    }

    @Override
    public void run() {
        try {
            PipedOutputStream pos=new PipedOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(pos);
            ObjectInputStream ois=new ObjectInputStream(new PipedInputStream(pos));
            c.sendPacket(new Packet(ois,oos,new Port(0,params[1]),c.getIp()));
            t.getWriter().println("reachable");
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e){
            t.getWriter().println("not reachable");
        }
    }
}
