package computer;

import computer.program.logging.Passwd;
import computer.program.logging.PermissionLevel;
import computer.program.logging.ProgramFiles.Ls;
import computer.program.logging.User;
import main.Terminal;

import java.io.IOException;
import java.util.Objects;


public class Disk {
    Folder root=new Folder("/");

    public Disk() {

    }
    public void generateDefaultFiles(Computer c){
        User u=File.system;
        root.createFolder("root");

        root.createFolder("etc").addFile(new Passwd());


        root.createFolder("bin");
        Folder usrBin= root.createFolder("usr").createFolder("bin");
        Passwd p;
        try {
            p=((Passwd)root.getFile("/etc/passwd"));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        root.createFolder("home");
        createDefaultUsers(p);
        usrBin.addFile(new Terminal("Terminal", Objects.requireNonNull(p.login("guest", "")),c));



    }
    public void createDefaultUsers(Passwd p){
        p.addUser(new User("guest","", PermissionLevel.guest),root);
        p.addUser(new User("root", Passwd.generatePassword(), PermissionLevel.root),root);
    }
    public void createBinFiles(Folder root,Computer c){
        try {
            root.getFolder("/bin").addFile(new Ls(c));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
