package computer;

import computer.program.logging.PermissionLevel;
import computer.program.logging.User;

public abstract class Filesystem {
    public static final User system=new User("System","", PermissionLevel.root);
    public static final User registered=new User(null,null,PermissionLevel.registered);


    public User getOwner() {
        return owner;
    }

    final User owner;

    public String getName() {
        return name;
    }

    final String name;

    public Filesystem(String name,User owner) {
        this.owner = owner;
        this.name = name;
    }
}
