package learn.binu;

import java.util.List;

/**
 * A sweet shop that is ready to get their orders going.
 */
public class SweetShop {

    private final PackSizeCalculator packSizeCalculator = new PackSizeCalculator();

    public List<Pack> placeOrder(int[] packSize, int orderSize) {

        return packSizeCalculator.findOptimalPackSize(packSize, orderSize);
    }
}
