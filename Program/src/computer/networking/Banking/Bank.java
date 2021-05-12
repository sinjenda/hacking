package computer.networking.Banking;

import java.util.ArrayList;

public final class Bank {
    // TODO: 11.05.2021 finish bank
    public static final Account drop=new Account(0,"void");
    private Bank(){
    }
    static ArrayList<Account>accounts=new ArrayList<>();
    static {
        accounts.add(drop);
    }

    public static void register(String userId){
        accounts.add(new Account(0,userId));
    }
    public static boolean transaction(String from,String to,int money){
        try {
            return ((Account)accounts.stream().filter(a->a.userId.equals(from)).toArray()[0]).transaction(money,(Account)accounts.stream().filter(a->a.userId.equals(to)));
        }
        catch (Exception e){
            return false;
        }
    }

}
