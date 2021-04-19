package computer;

import computer.program.logging.PermissionLevel;
import computer.program.logging.User;

public abstract class File extends Filesystem {
    public static User system=new User("System","", PermissionLevel.root);

    User owner;

    public File(String name,User owner) {
        super(name);
        this.owner=owner;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean isFile() {
        return false;
    }
}
