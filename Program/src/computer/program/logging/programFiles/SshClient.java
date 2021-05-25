package computer.program.logging.programFiles;

import computer.Computer;
import computer.networking.NetListener;
import computer.networking.Packet;
import computer.networking.PacketHelper;
import computer.networking.Port;
import computer.program.Program;
import main.Terminal;

import java.util.Objects;
import java.util.UUID;

public class SshClient extends Program {
    // TODO: 25.05.2021 merge with terminal
    Terminal terminal;
    private NetListener listener;
    UUID id;
    private void reset(){
        listener=new NetListener() {
            final boolean active=false;
            @Override
            public boolean active() {
                return active;
            }

            @Override
            public void process(Packet p) {
                PacketHelper helper=new PacketHelper(p);
                switch (p.command()){
                    case "ret":
                        helper.print(terminal.getWriter());
                    case "connect":
                        id=helper.as();
                }
            }

        };
    }

    @Override
    public void exec(String[] params, Terminal t) {
        reset();
        terminal=t;
        super.exec(params, t);
        String[]s=params[1].split(" ");
        String port="22";
        if (s.length>1)
            port=s[1];
        Packet p=new Packet(new Port(Integer.parseInt(port),s[0]),c.getIp(),new Object[]{s[2],s[3]},"connect",listener);
        Objects.requireNonNull(c.sendPacket(p)).process(p);
    }

    public SshClient(Computer c) {
        super("ssh-client", c,50);
    }

    @Override
    public void run() {

    }
}