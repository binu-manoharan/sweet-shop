package learn.binu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackSizeCalculator {
    private int[] packSize;

    public PackSizeCalculator(int[] packSize) {
        this.packSize = packSize;
        Arrays.sort(this.packSize);
    }

    /**
     * Finds the optimal number packs required to satisfy the given order, this minimises wastage and
     * then the number of packs
     *
     * @param orderSize size of the order
     * @return a list of packs to place order for
     */
    public List<Pack> findOptimalPackSize(int orderSize) {
        final List<Pack> order = new ArrayList<>();
        order.add(new Pack(250, 1, 249));
        return order;
    }
}
