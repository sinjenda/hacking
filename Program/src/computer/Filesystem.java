package computer;

public abstract class Filesystem {
    public abstract boolean isFile();

    public String getName() {
        return name;
    }

    String name;

    public Filesystem(String name) {
        this.name = name;
    }
}
