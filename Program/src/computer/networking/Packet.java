package computer.networking;

public record Packet(Port port,String source,Object[] data,String command,NetListener listener) {

}
