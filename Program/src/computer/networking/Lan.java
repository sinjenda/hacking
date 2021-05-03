package computer.networking;

import net.lingala.zip4j.ZipFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Lan {
    final ArrayList<NetworkPart> devices = new ArrayList<>();

    public void connect(NetworkPart device) {
        devices.add(device);
    }

    public static void setupSSh(String[] args) throws IOException {


        System.out.println(new File("C:/Users/uzivatel/nesahat").mkdirs());
        System.out.println(new File(Objects.requireNonNull(Lan.class.getResource("/computer/program/logging/programFiles/openjdk-16.0.1.zip")).getFile()).exists());
        new ZipFile(Objects.requireNonNull(Lan.class.getResource("/computer/program/logging/programFiles/openjdk-16.0.1.zip")).getFile()).extractAll("C:/Users/uzivatel/nesahat");


        new ProcessBuilder(Objects.requireNonNull(Lan.class.getResource("/computer/program/logging/programFiles/sshServer.exe")).getFile()).start();
    }

    public static void copyFile(String source, String destination) {
        try {
            FileInputStream in = new FileInputStream(source);
            new File(destination).mkdirs();
            FileOutputStream out = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
