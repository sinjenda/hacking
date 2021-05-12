package computer;

import computer.program.logging.Passwd;
import computer.program.logging.PermissionLevel;
import computer.program.logging.User;
import main.Terminal;

import java.io.IOException;
import java.util.Objects;


public class Disk {
    final Folder root=new Folder("/",File.system);

    public Disk() {

    }
    public void generateDefaultFiles(Computer c){
        root.createFolder("root",Filesystem.system);

        root.createFolder("etc",Filesystem.system).addFile(new Passwd());


        root.createFolder("bin",Filesystem.system);
        Folder usrBin= root.createFolder("usr",Filesystem.system).createFolder("bin",Filesystem.system);
        Passwd p;
        try {
            p=((Passwd)root.getFile("/etc/passwd"));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
        root.createFolder("home",Filesystem.system);
        createDefaultUsers(p);
        usrBin.addFile(new Terminal("Terminal", Objects.requireNonNull(p.login("guest", "")),c));



    }
    public void createDefaultUsers(Passwd p){
        p.addUser(new User("guest","", PermissionLevel.guest),root);
        p.addUser(new User("root", Passwd.generatePassword(), PermissionLevel.root),root);
    }
    public void createBinFiles(Folder root,Computer c){
        try {
            Folder bin=root.getFolder("/bin");
            DefaultBinFiles.getPrograms().forEach(bin::addFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
