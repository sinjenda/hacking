package computer.networking;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class NetworkPart {
    final String ip = generateIp();
    private final ArrayList<NetworkPart> below = new ArrayList<>();

    private NetworkPart getBehind() {
        return behind;
    }

    protected NetworkPart(@Nullable NetworkPart behind) {
        this.behind = behind;
        if (behind != null)
            behind.addBelow(this);
    }

    public abstract String getIp();


    public final void sendPacketUp(Packet packet, String destIp) {
        if (getBehind() == Router.ethernet) {
            Objects.requireNonNull(Objects.requireNonNull(getBehind().getBelow(destIp)).listenerAt(packet.port().number())).process(packet);
        } else {
            getBehind().sendPacketUp(packet, destIp);
        }
    }

    public @Nullable NetworkPart getBelow(String ip) {
        ArrayList<Router> routers = new ArrayList<>();
        for (NetworkPart part : below) {
            if (part.getIp().equals(ip)) {
                return part;
            }
            if (part instanceof Router) {
                routers.add((Router) part);
            }
        }
        for (Router r : routers) {
            if (r.getBelow(ip) != null)
                return r.getBelow(ip);
        }
        return null;
    }




    private void addBelow(NetworkPart part) {
        below.add(part);
    }

    protected final NetworkPart behind;

    private static final ArrayList<String> ips = new ArrayList<>();

    public static String generateIp() {
        String ip = ThreadLocalRandom.current().nextInt(10, 255) + "." + ThreadLocalRandom.current().nextInt(10, 255) + "." + ThreadLocalRandom.current().nextInt(10, 255) + "." + ThreadLocalRandom.current().nextInt(10, 255);
        if (ips.stream().filter(a -> a.equals(ip)).toArray().length == 0) {
            ips.add(ip);
            return ip;
        } else return generateIp();
    }

    @Nullable
    public abstract NetListener listenerAt(int port);


    public static class Ethernet extends NetworkPart {

        public Ethernet() {
            super(null);
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
}
