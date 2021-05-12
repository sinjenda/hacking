package computer.program.logging.programFiles;

import computer.Computer;
import computer.Filesystem;
import computer.Folder;
import computer.networking.NetListener;
import computer.networking.Packet;
import computer.program.Program;
import computer.program.logging.User;
import main.Terminal;

import java.io.IOException;

public class SshServer extends Program implements NetListener {
    boolean active = true;

    public SshServer(Computer computer, User owner) {
        super("ssh-server", owner, computer,false);
    }

    @Override
    public boolean active() {
        return active;
    }

    @Override
    public void process(Packet p) {
        try {

            if (active()) {
                p.output().writeUTF("already in use");
            } else {
                active = true;
                switch (p.input().readUTF()) {
                    case "ls":
                        try {
                            Folder current;
                            current = super.c.getRoot().getFolder(p.input().readUTF());
                            StringBuilder name = new StringBuilder();
                            for (Filesystem sys : current.getFilesInDirectory()) {
                                name.append(sys.getName()).append(" ");
                            }
                            p.output().writeUTF(name.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "run":
                        try {
                            ((Program) c.getRoot().getFile(p.input().readUTF())).exec((String[]) p.input().readObject(), (Terminal) p.input().readObject());
                            p.output().writeUTF("done");
                        } catch (IOException e) {
                            e.printStackTrace();
                            p.output().writeUTF(e.getMessage() + " occurred");
                        }
                }
                active = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        active = false;
    }


}
