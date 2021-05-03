package computer.networking;

import computer.Computer;
import org.jetbrains.annotations.Nullable;

public interface NetListener {
    boolean active();

    void process(Packet p);

    int port();

}
