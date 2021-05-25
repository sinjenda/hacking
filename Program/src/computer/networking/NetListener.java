package computer.networking;

public interface NetListener {
    boolean active();

    void process(Packet p);
    default Packet createPacket(Object[] data,String command){
        return new Packet(null,null,data,command,this);
    }

}
