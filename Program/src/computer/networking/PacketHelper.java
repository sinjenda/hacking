package computer.networking;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class PacketHelper implements Iterator<Object> , Closeable,Iterable<Object> {
    private Object[] readValue;
    private final ArrayList<Object>writeValue=new ArrayList<>();
    private boolean closed=false;
    private int where=0;
    private String command=null;
    private Packet packet;

    public PacketHelper(Packet packet) {
        this.readValue = packet.data();
        this.packet=packet;
    }

    @Override
    public boolean hasNext() {
        return where <= readValue.length;
    }

    @Override
    public String next() {
        ensureOpen();
        where +=1;
        return (String) readValue[where-1];
    }
    @SuppressWarnings("unchecked")
    public <T>T as(){
        ensureOpen();
        where +=1;
        T ret;
        try {
            ret=(T) readValue[where-1];
        }
        catch (ClassCastException e){
            throw new RuntimeException(e);
        }
        return ret;
    }
    private void ensureOpen(){
        if (closed){
            throw new IllegalStateException("closed");
        }
    }

    @Override
    public void close() {
        readValue=null;
        closed=true;
    }


    public void write(String value){
        writeValue.add(value);
    }
    public void write(java.util.UUID object){
        writeValue.add(object);
    }
    public void writeCommand(String command){
        this.command=command;
    }
    public void send(NetListener sender,NetListener receiver){
        receiver.process(sender.createPacket(writeValue.toArray(),command));
    }
    public String readCommand(){
        return packet.command();
    }


    @NotNull
    @Override
    public Iterator<Object> iterator() {
        return this;
    }
    public void print(PrintStream stream){
        for (Object o:this){
            if (o instanceof String)
                stream.println(o);
        }
    }
}
