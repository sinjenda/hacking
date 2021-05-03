package computer.networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public record Packet(ObjectInputStream input, ObjectOutputStream output,Port port) {

}
