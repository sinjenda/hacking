package computer.networking.shopping;

import computer.program.logging.User;

import java.net.UnknownServiceException;

public abstract class Item {
    private int price=-1;
    private String name="";
    boolean enableShopping=false;

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void enable(int price, String name){
        this.price=price;
        this.name=name;
        enableShopping=true;
    }
    public void buy(User user){

    }
}
