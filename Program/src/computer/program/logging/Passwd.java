package computer.program.logging;



import computer.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Passwd extends File {
    ArrayList<User>users=new ArrayList<>();
    public Passwd() {
        super("passwd",system);
    }

    public String generatePassword(){
        ArrayList<String>passwords=new ArrayList<>();
        try {
            Scanner scnr=new Scanner(new java.io.File(getClass().getResource("/computer/program/logging/passwords").getFile()));
            while (scnr.hasNext()){
                passwords.add(scnr.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return passwords.get(ThreadLocalRandom.current().nextInt(0,passwords.size()));
    }

    public String createString(){
        StringBuilder ret= new StringBuilder();
        for (User u:users){
            ret.append(u.name).append(":").append(u.password).append("\n");
        }
        return ret.toString();
    }






}
