package computer;

import computer.program.logging.User;

import java.io.IOException;
import java.util.ArrayList;

public class Folder extends Filesystem{

    final ArrayList<Filesystem> subFiles=new ArrayList<>();

    public ArrayList<Filesystem> getFilesInDirectory(){
        return subFiles;
    }

    public File getFile(String path) throws IOException {
        //return (File) subFiles.stream().filter(a->a.name.equals(path)).toArray()[0];
        if (path.startsWith("/"))
            path=path.replaceFirst("/","");
        String[]files=path.split("/");
        ArrayList<Filesystem> current=subFiles;
        for (String s:files){
            for (Filesystem sys:current){
                if (sys.name.equals(s)){
                    try {
                        current=((Folder)sys).subFiles;
                    }catch (ClassCastException e){
                        return (File) sys;
                    }
                }
            }
        }
        throw new IOException("file not found");
    }

    public Folder getFolder(String path) throws IOException {
        if (path.startsWith("/"))
            path=path.replaceFirst("/","");
        String[]files=path.split("/");
        for (String s:files){
            for (Filesystem sys: subFiles){
                if (sys.name.equals(s)){
                    return (Folder) sys;
                }
            }
        }
        throw new IOException("file not found");
    }

    public Folder(String name, User owner) {
        super(name, owner);
    }
    public Folder createFolder(String name,User owner){
        Folder f=new Folder(name,owner);
        subFiles.add(f);
        return f;
    }
    public void addFile(File file){
        subFiles.add(file);
    }


}
