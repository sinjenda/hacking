package computer.networking.shopping;

import computer.networking.Banking.Bank;
import computer.program.logging.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Shop<Type extends Item> implements Collection<Type> {
    ArrayList<Type> items = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public @Nullable Type buy(String itemName, String user, User userReal) {
        Object[] result = items.stream().filter(a -> a.getName().equals(itemName)).toArray();
        if (result.length > 0) {
            if (Bank.transaction(user, "void", ((Type) result[0]).getPrice()))
                ((Type)result[0]).buy(userReal);
                return (Type) result[0];
        }
        return null;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return items.contains(o);
    }

    @NotNull
    @Override
    public Iterator<Type> iterator() {
        return items.iterator();
    }

    @NotNull
    @Override
    public Object @NotNull [] toArray() {
        return items.toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @NotNull
    @Override
    public <T> T @NotNull [] toArray(@NotNull T @NotNull [] a) {
        return items.toArray(a);
    }

    @Override
    public boolean add(Type type) {
        if (type.enableShopping)
            return items.add(type);
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return items.remove(o);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return items.contains(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Type> c) {
        return items.addAll(c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return items.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public void clear() {
        items.clear();
    }
}
