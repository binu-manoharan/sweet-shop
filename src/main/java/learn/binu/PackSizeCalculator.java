package learn.binu;

import java.util.*;

public class PackSizeCalculator {
    /**
     * Finds the optimal number packs required to satisfy the given order, this minimises wastage and
     * then the number of packs
     *
     * @return a list of packs to place order for
     */
    public List<Pack> findOptimalPackSize(int[] packSizes, int orderSize) {

        final List<List<Pack>> orders = new ArrayList<>();
        findAllPossibleOrders(orders, new ArrayList<>(), packSizes, orderSize);

        return getOrderWithLeastWastage(orders);
    }

     /**
     * Recursively populates the list of possible orders
      *
     * @param orders the list of orders to populate
     * @param currentOrder used to keep track of previous packs for an order
     * @param packSizes available pack sizes
     * @param orderSize orderSize to cater for
     */
    private void findAllPossibleOrders(List<List<Pack>> orders, List<Pack> currentOrder, int[] packSizes, int orderSize) {
        final int smallestPackThatFullyContainsTheOrder = findSmallestPackThatFullyContainsTheOrder(packSizes, orderSize);

        if (smallestPackThatFullyContainsTheOrder == -1) {
            // orderSize exceeds what can be filled by the biggest pack, find the biggest pack and add order/biggestPack to the order
            // and recursively evaluate what is remaining of the order.

            final int biggestPackSize = getBiggestPackSize(packSizes);
            if (biggestPackSize != -1) {
                final int numberPackQuantity = orderSize / biggestPackSize;
                final int orderToPackage = orderSize % biggestPackSize;

                // Add this order to currentOrder so as to keep track of it during recursion.
                final ArrayList<Pack> packs = new ArrayList<>(currentOrder);
                packs.add(new Pack(biggestPackSize, numberPackQuantity, 0));

                findAllPossibleOrders(orders, packs, packSizes, orderToPackage);
            }
        } else {
            // There is a pack that can cater for this order fully, it is a potential order.
            final List<Pack> order = new ArrayList<>(currentOrder);
            order.add(new Pack(smallestPackThatFullyContainsTheOrder, orderSize, smallestPackThatFullyContainsTheOrder - orderSize));
            orders.add(order);

            // Also, recursively call the function to ensure an order with lower sized packs to be able to evaluate
            // wastage later.
            final int[] remainingPackSize = getRemainingPackSize(packSizes, smallestPackThatFullyContainsTheOrder);
            if (remainingPackSize.length > 0) {
                findAllPossibleOrders(orders, currentOrder, remainingPackSize, orderSize);
            }
        }
    }

    /**
     * Find and return the order with least wastage
     */
    public List<Pack> getOrderWithLeastWastage(List<List<Pack>> orders) {
        orders.sort((order1, order2) -> {
            final int order1Wastage = order1.stream()
                    .map(Pack::getWastage)
                    .mapToInt(Integer::intValue)
                    .sum();

            final int order2Wastage = order2.stream()
                    .map(Pack::getWastage)
                    .mapToInt(Integer::intValue)
                    .sum();

            return order1Wastage - order2Wastage;
        });

        return orders.size() > 0 ? orders.get(0) : Collections.emptyList();
    }

    /**
     * Find the smallest pack size that fulfils the order completely. E.g. For a list with
     * pack size of [100, 200, 300] and an order of 150, this would be 200. Returns -1 if none of
     * them fully satisfy the order
     *
     * @return the smallest pack size in the list
     */
    public int findSmallestPackThatFullyContainsTheOrder(int[] packSizes, int orderSize) {
        Arrays.sort(packSizes);

        for (int size : packSizes) {
            if (orderSize <= size) {
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

    /**
     * Get the biggest pack size
     */
    public int getBiggestPackSize(int[] packSizes) {
        final OptionalInt max = Arrays.stream(packSizes).max();
        if (max.isPresent()) {
            return max.getAsInt();
        }
        else {
            return -1;
        }
    }
}
