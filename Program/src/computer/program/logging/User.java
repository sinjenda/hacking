package computer.program.logging;

public class User {
    final String name;

    public String getPassword() {
        return password;
    }

    String password;

    public PermissionLevel getLevel() {
        return level;
    }

    final PermissionLevel level;
    boolean accepted=false;

    public User(String name, String password, PermissionLevel level) {
        this.name = name;
        this.password=password;
        this.level = level;
    }

    public void setPassword(String oldPassword,String newPassword) {
        if (tryPassword(oldPassword)){
            password=newPassword;
        }
    }
    public boolean tryPassword(String password){
        return password.hashCode()==this.password.hashCode();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(password.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return ((User) obj).name.equals(name);
        }
        return false;
    }
}
