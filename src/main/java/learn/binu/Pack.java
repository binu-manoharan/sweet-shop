package learn.binu;

import java.util.Objects;

public class Pack {
    private int capacity;
    private int quantity;
    private int wastage;

    public Pack(int capacity, int quantity, int wastage) {
        this.capacity = capacity;
        this.quantity = quantity;
        this.wastage = wastage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pack pack = (Pack) o;
        return capacity == pack.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }

    public int getCapacity() {
        return capacity;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getWastage() {
        return wastage;
    }

    @Override
    public String toString() {
        return "[" + quantity + " x " + capacity + "]";
    }
}
