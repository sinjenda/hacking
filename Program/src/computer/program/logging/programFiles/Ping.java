package computer.program.logging.programFiles;

import computer.Computer;
import computer.networking.Packet;
import computer.networking.Port;
import computer.program.Program;
import main.Terminal;

import java.util.Objects;

public class Ping extends Program {
    Terminal t;
    String[] params;

    public Ping(Computer c) {
        super("ping", c);
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
            Packet p=new Packet(new Port(0,params[1]),c.getIp(),null,null,null);
            Objects.requireNonNull(c.sendPacket(p)).process(p);
            t.getWriter().println("reachable");
        } catch (RuntimeException e){
            t.getWriter().println("not reachable");
        }
    }
}
