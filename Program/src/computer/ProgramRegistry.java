package computer;

import computer.networking.Banking.BankGui;
import computer.networking.Banking.BankMain;
import computer.program.Program;
import computer.program.logging.programFiles.Ls;
import computer.program.logging.programFiles.Ping;
import computer.program.logging.programFiles.SshClient;
import main.Terminal;

import java.util.ArrayList;

public class ProgramRegistry {
    private static final ArrayList<Program> binPrograms = new ArrayList<>();
    private static final ArrayList<Program>exePrograms=new ArrayList<>();

    public static void addBin(Program p) {
        binPrograms.add(p);
    }
    public static void addExe(Program p){
        binPrograms.add(p);
    }
    public static ArrayList<Program>getExePrograms(){
        return exePrograms;
    }
    static {
        addBin(new Ls(Game.mainComputer));
        addExe(new Terminal(null,Game.mainComputer));
        addExe(new BankMain(Game.mainComputer));
        addBin(new Ping(Game.mainComputer));
        addBin(new SshClient(Game.mainComputer));
    }


    public static ArrayList<Program> getBinPrograms() {
        return binPrograms;
    }
}
