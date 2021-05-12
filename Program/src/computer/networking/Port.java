package computer.networking;

import computer.networking.NetworkPart;

public record Port(int number,String dest) {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Port && ((Port) obj).number() == number();
    }
}
