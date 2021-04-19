package computer;

public class Computer {
    MotherBoard board;

    public Computer(MotherBoard board) {
        this.board = board;
    }
    public Folder getRoot(){
        return board.disk.root;
    }
}
