package computer.program.logging;



import computer.File;
import computer.Folder;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Passwd extends File {
    final ArrayList<User>users=new ArrayList<>();

    public ArrayList<User> getUsers() {
        return users;
    }

    public Passwd() {
        super("passwd",system);
    }

    public static String generatePassword(){
        ArrayList<String>passwords=new ArrayList<>();
        try {
            Scanner scnr=new Scanner(new java.io.File(Objects.requireNonNull(Passwd.class.getResource("/computer/program/logging/passwords")).getFile()));
            while (scnr.hasNextLine()){
                passwords.add(scnr.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return passwords.get(ThreadLocalRandom.current().nextInt(0,passwords.size()));
    }

    public static String generateUserName(){
        ArrayList<String>passwords=new ArrayList<>();
        try {
            Scanner scnr=new Scanner(new java.io.File(Objects.requireNonNull(Passwd.class.getResource("/computer/program/logging/usernames")).getFile()));
            while (scnr.hasNextLine()){
                passwords.add(scnr.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return passwords.get(ThreadLocalRandom.current().nextInt(0,passwords.size()));
    }

    public void addUser(User user, Folder root){
        user.accepted=true;
        try {
            root.getFolder("/home").createFolder(user.getName(),registered);
        }catch (IOException e){
            e.printStackTrace();
        }
        users.add(user);
    }
    public @Nullable User login(String name, String password){
        User u= (User) users.stream().filter(a->a.name.equals(name)).toArray()[0];
        if (u.accepted) {
            if (u.tryPassword(password)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder ret= new StringBuilder();
        for (User u:users){
            ret.append(u.name).append(":").append(u.password).append("\n");
        }
        return ret.toString();
    }






}
