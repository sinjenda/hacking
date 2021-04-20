package computer;

import computer.program.logging.User;

public abstract class File extends Filesystem {


    public File(String name,User owner) {
        super(name, owner);
    }

    @Override
    public abstract String toString();

}
