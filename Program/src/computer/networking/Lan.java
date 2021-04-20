package computer.networking;

import java.util.ArrayList;

public class Lan {
    final ArrayList<NetworkPart>devices=new ArrayList<>();
    public void connect(NetworkPart device){
        devices.add(device);
    }

}
