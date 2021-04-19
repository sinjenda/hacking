package computer.program.logging;

public class User {
    final String name;
    int password;
    final PermissionLevel level;

    public User(String name, String password, PermissionLevel level) {
        this.name = name;
        this.password=password.hashCode();
        this.level = level;
    }

    public void setPassword(String oldPassword,String newPassword) {
        if (tryPassword(oldPassword)){
            password=newPassword.hashCode();
        }
    }
    public boolean tryPassword(String password){
        return password.hashCode()==this.password;
    }
}
