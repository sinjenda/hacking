package computer.networking;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Router extends NetworkPart implements NetListener {

    private final HashMap<String, Port> redirecting = new HashMap<>();
    public static final NetworkPart ethernet = new Ethernet();

    public Router(NetworkPart behind) {
        super(behind);
        addRedirect("all", new Port(8080, getIp()));
    }

    @Override
    public boolean processPacket(Packet packet) {
        if (redirecting.containsKey(packet.source()) && redirecting.get(packet.port().dest()).equals(packet.port())) {
            return super.processPacket(packet);
        }
        if (redirecting.get("all").equals(packet.port())) {
            return super.processPacket(packet);
        }
        return true;
    }

    public void addRedirect(String source, Port target) {
        redirecting.put(source, target);
    }


    @Override
    public @Nullable NetListener listenerAt(int port) {
        if (port == 8080) {
            return this;
        }
        return null;
    }

    boolean active = false;

    @Override
    public boolean active() {
        return active;
    }

    @Override
    public void process(Packet p) {
        try {
            PacketHelper helper=new PacketHelper(p);
            if (!active) {
                active = true;
                switch (helper.readCommand()) {
                    case "remove":
                        redirecting.clear();
                        addRedirect("all", new Port(8080, getIp()));
                    case "add":
                        addRedirect(helper.next(), new Port(helper.as(), helper.next()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


