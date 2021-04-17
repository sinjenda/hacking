package computer;

public abstract class Filesystem {
    public abstract boolean isFile();
    String name;

    public Filesystem(String name) {
        this.name = name;
    }
}
