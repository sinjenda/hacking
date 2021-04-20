package computer.networking;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public interface NetworkPart {
    String getIp();
    ArrayList<String>ips=new ArrayList<>();
    static String GenerateIp(){
        String ip=ThreadLocalRandom.current().nextInt(10,255)+"."+ThreadLocalRandom.current().nextInt(10,255)+"."+ThreadLocalRandom.current().nextInt(10,255)+"."+ThreadLocalRandom.current().nextInt(10,255);
        if (ips.stream().filter(a->a.equals(ip)).toArray().length==0){
            return ip;
        }
        else return GenerateIp();
    }

}
