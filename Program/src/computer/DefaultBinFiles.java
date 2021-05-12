package computer;

import computer.program.Program;

import java.util.ArrayList;

public class DefaultBinFiles {
    private static final ArrayList<Program>programs=new ArrayList<>();
    public static void add(Program p){
        programs.add(p);
    }

    public static ArrayList<Program> getPrograms() {
        return programs;
    }
}
