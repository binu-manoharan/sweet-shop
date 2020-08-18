package learn.binu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackSizeCalculator {
    /**
     * Finds the optimal number packs required to satisfy the given order, this minimises wastage and
     * then the number of packs
     *
     * @return a list of packs to place order for
     */
    public List<Pack> findOptimalPackSize(int[] packSizes, int orderSize) {
        final List<Pack> order = new ArrayList<>();
        final Integer smallestPackThatFullyContainsTheOrder = findSmallestPackThatFullyContainsTheOrder(packSizes, orderSize);

        if (smallestPackThatFullyContainsTheOrder != null) {
            order.add(new Pack(smallestPackThatFullyContainsTheOrder, orderSize, smallestPackThatFullyContainsTheOrder - orderSize));
        } else {

        }

        return order;
    }

    /**
     * Find the smallest pack size that fulfils the order completely. E.g. For a list with
     * pack size of [100, 200, 300] and an order of 150, this would be 200. Returns -1 if none of
     * them fully satisfy the order
     *
     * @return the smallest pack size in the list
     */
    public Integer findSmallestPackThatFullyContainsTheOrder(int[] packSizes, int orderSize) {
        Arrays.sort(packSizes);

        for (int size : packSizes) {
            if (orderSize < size) {
                return size;
            }
        }

        return -1;
    }

    /**
     * Get all pack sizes smaller than a particular pack
     */
    public int[] getRemainingPackSize(int[] packSizes, int packSize) {
        return Arrays.stream(packSizes).filter(pack -> pack < packSize).toArray();
    }
}
