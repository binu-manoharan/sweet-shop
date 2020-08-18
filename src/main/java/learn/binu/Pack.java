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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getWastage() {
        return wastage;
    }

    public void setWastage(int wastage) {
        this.wastage = wastage;
    }
}
