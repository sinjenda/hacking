package computer;

import computer.networking.Router;
import computer.networking.shopping.Shop;
import computer.program.Program;
import computer.program.logging.Passwd;
import computer.program.logging.programFiles.Decipher;
import computer.program.logging.programFiles.Nmap;
import computer.program.logging.programFiles.SshServer;

import java.io.IOException;

public class Game {
    public static Computer mainComputer;
    private static final Shop<Program>programShop=new Shop<>();
    public static void add(Program program){
        programShop.add(program);
    }
    static {
        Disk d=new Disk();
        MotherBoard board=new MotherBoard(new Cpu(10),new Ram(50),d);
        mainComputer=new Computer(board,new Router(Router.ethernet));
        d.generateDefaultFiles(mainComputer);
        try {
            d.createBinFiles(((Passwd)d.root.getFile("/etc/passwd")).getUsers().get(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initPrograms();
    }
    private static void initPrograms(){
        add(new Decipher(mainComputer));
        add(new SshServer(mainComputer));
        add(new Nmap(mainComputer));
    }
}
