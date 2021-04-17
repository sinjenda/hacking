package computer;

public class File extends Filesystem{

    public File(String name) {
        super(name);
    }

    @Override
    public boolean isFile() {
        return false;
    }
}
