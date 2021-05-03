package computer;

import computer.program.logging.PermissionLevel;
import computer.program.logging.User;

public abstract class File extends Filesystem {


    public File(String name,User owner) {
        super(name, owner);
    }

    @Override
    public abstract String toString();

    public boolean isAllowed(User user) {
        return !user.getLevel().equals(PermissionLevel.root) || !getOwner().getLevel().equals(PermissionLevel.guest) || user.equals(getOwner())||(getOwner().equals(registered)&&user.getLevel().equals(PermissionLevel.registered));
    }

}
