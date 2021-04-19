package computer.program.logging.ProgramFiles;

import computer.Computer;
import computer.Filesystem;
import computer.Folder;
import computer.program.Program;
import main.Terminal;

import java.io.IOException;
import java.util.Scanner;

public class Ls extends Program {
    String[] params;
    Terminal.Console console;
    public Ls(Computer c) {
        super("ls",system,c);
    }

    @Override
    public void run() {
        Scanner scnr=new Scanner(params[1]);
        String pa="";
        if (scnr.hasNext()){
            pa=scnr.next();
        }
        try {
            Folder current;
            if (pa.startsWith("/")) {
                current = c.getRoot().getFolder(params[1]);
            }
            else {
                current=c.getRoot().getFolder(params[0]+params[1]);
            }
            StringBuilder name=new StringBuilder();
            for (Filesystem sys:current.getFilesInDirectory()){
                name.append(sys.getName()).append(" ");
            }
            console.println(name.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void exec(String[] params, Terminal t) {
        this.params=params;
        console=t.getWriter();
        super.exec(params,t);
    }


}
