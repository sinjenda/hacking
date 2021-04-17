package computer;

import java.util.ArrayList;

public class Folder extends Filesystem{

    ArrayList<Filesystem> subFiles=new ArrayList<>();

    @Override
    public boolean isFile() {
        return true;
    }
    public boolean isExecutable(){
        return false;
    }
    public File getFile(String path){
        return (File) subFiles.stream().filter(a->a.name.equals(path)).toArray()[0];
    }

    public Folder getFolder(String path){
        return (Folder) subFiles.stream().filter(a->a.name.equals(path)).toArray()[0];
    }

    public Folder(String name) {
        super(name);
    }
}
