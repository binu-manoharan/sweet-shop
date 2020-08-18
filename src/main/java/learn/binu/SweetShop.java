package learn.binu;

import java.util.List;

public class SweetShop {

    private final PackSizeCalculator packSizeCalculator;

    public SweetShop(int[] packSize) {
        packSizeCalculator = new PackSizeCalculator(packSize);
    }

    public List<Pack> placeOrder(int orderSize) {
        return packSizeCalculator.findOptimalPackSize(orderSize);
    }
}
