package computer.networking;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class Router extends NetworkPart {
    private final String ip = NetworkPart.generateIp();

    private final HashMap<Integer, Port> redirecting = new HashMap<>();
    public static NetworkPart ethernet = new Ethernet();

    public Router(NetworkPart behind) {
        super(behind);
    }

    public void addRedirect(int source, Port target) {
        redirecting.put(source, target);
    }



    @Override
    public String getIp() {
        return ip;
    }


    @Override
    public @Nullable NetListener listenerAt(int port) {
        return null;
    }
}
