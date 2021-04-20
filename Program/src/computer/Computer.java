package computer;

import computer.networking.NetworkPart;

public class Computer implements NetworkPart{
    final MotherBoard board;
    final String ip;

    public Computer(MotherBoard board) {
        this.board = board;
        ip= NetworkPart.GenerateIp();
    }
    public Folder getRoot(){
        return board.disk().root;
    }

    @Override
    public String getIp() {
        return ip;
    }
}
