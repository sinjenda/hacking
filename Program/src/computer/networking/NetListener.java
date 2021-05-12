package computer.networking;

public interface NetListener {
    boolean active();

    void process(Packet p);

}
