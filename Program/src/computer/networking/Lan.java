package computer.networking;

import java.util.ArrayList;

public class Lan {
    ArrayList<NetworkPart>devices=new ArrayList<>();
    public void connect(NetworkPart device){
        devices.add(device);
    }
}
