package computer.networking.shopping;

import computer.networking.Banking.Bank;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Shop<Type> {
    HashMap<Properties, Type> items = new HashMap<>();

    public @Nullable Type buy(String itemName, String user) {
        Object[] result = items.keySet().stream().filter(a -> a.name().equals(itemName)).toArray();
        if (result.length > 0) {
            if (Bank.transaction(user, "void", ((Properties) result[0]).price()))
                return items.get(result[0]);
        }
        return null;
    }
}
