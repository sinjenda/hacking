package computer.program.logging.programFiles;

import computer.Computer;
import computer.Filesystem;
import computer.Folder;
import computer.networking.NetListener;
import computer.networking.Packet;
import computer.networking.PacketHelper;
import computer.networking.ServiceHelper;
import computer.program.Program;
import computer.program.logging.Passwd;

import java.io.IOException;
import java.util.UUID;

public class SshServer extends Program implements NetListener {
    boolean active = true;
    ServiceHelper service=new ServiceHelper();

    public SshServer(Computer computer) {
        super("ssh-server", computer);
    }

    @Override
    public boolean active() {
        return active;
    }

    @Override
    public void process(Packet p) {
        try {
            PacketHelper iterator=new PacketHelper(p);
            if (active()) {
                p.listener().process(createPacket(new Object[]{"already in use"},"ret"));
            } else {
                active = true;
                switch (p.command()) {
                    case "ls":
                        try {
                            Folder current;
                            current = c.getRoot().getFolder(iterator.next());
                            StringBuilder name = new StringBuilder();
                            for (Filesystem sys : current.getFilesInDirectory()) {
                                name.append(sys.getName()).append(" ");
                            }
                            iterator.write(name.toString());
                            iterator.writeCommand("ret");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "run":
                        try {
                            ((Program) c.getRoot().getFile(iterator.next())).exec(iterator.as(), iterator.as());
                            iterator.write("done");
                        } catch (IOException e) {
                            e.printStackTrace();
                            iterator.write(e.getMessage() + " occurred");
                        }
                        iterator.writeCommand("ret");
                    case "connect":
                        UUID id=service.newConnection();
                        service.authorize(id,((Passwd)c.getRoot().getFile("/etc/passwd")).login(iterator.next(),iterator.next()).getLevel());
                        iterator.write(id);
                        iterator.writeCommand("connect");
                }
                active = false;
            }
            iterator.send(this,p.listener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        active = false;
    }


}
