package computer;

import computer.networking.NetListener;
import computer.networking.NetworkPart;
import org.jetbrains.annotations.Nullable;

public class Computer extends NetworkPart{
    final MotherBoard board;
    final String ip;

    public Computer(MotherBoard board, NetworkPart part) {
        super(part);
        this.board = board;
        ip= NetworkPart.generateIp();
    }
    public Folder getRoot(){
        return board.disk().root;
    }

    @Override
    public String getIp() {
        return ip;
    }



    @Override
    public @Nullable NetListener listenerAt(int port) {
        return null;
    }
}
