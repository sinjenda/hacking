package computer;

import computer.program.logging.Passwd;
import computer.program.logging.PermissionLevel;
import computer.program.logging.User;
import main.Terminal;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;


public class Disk {
    final Folder root=new Folder("/",File.system);
    private Computer c;
    public Disk() {

    }
    public void generateDefaultFiles(Computer c){
        this.c=c;
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



    }
    public void createDefaultUsers(Passwd p){
        p.addUser(new User("guest","", PermissionLevel.guest),root);
        p.addUser(new User("root", Passwd.generatePassword(), PermissionLevel.root),root);
    }
    public String generatePasswordWithChance(){
        if (new Random().nextFloat()>0.5){
            return "";
        }
        return Passwd.generatePassword();
    }
    public void createBinFiles(User setTo){
        generateExe(setTo);
        try {
            Folder bin=root.getFolder("/bin");
            ProgramRegistry.getBinPrograms().forEach(bin::addFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void generateExe(User setTo){
        try {
            Folder bin = root.getFolder("/usr/bin");
            ProgramRegistry.getExePrograms().forEach(a->{
                if (a instanceof Terminal){
                    ((Terminal)a).setLogged(setTo);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
