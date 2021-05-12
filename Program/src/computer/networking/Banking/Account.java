package computer.networking.Banking;

public class Account {
    int money;

    public Account(int money, String userId) {
        this.money = money;
        this.userId = userId;
    }

    String userId;
    boolean transaction(int money,Account userIdTo){
        if (this.money>money){
            this.money-=money;
            userIdTo.money+=money;
            return true;
        }
        return false;
    }
}
