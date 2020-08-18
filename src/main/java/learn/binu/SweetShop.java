package learn.binu;

import java.util.List;

public class SweetShop {

    final PackSizeCalculator packSizeCalculator = new PackSizeCalculator();

    public List<Pack> placeOrder(int[] packSize, int orderSize) {

        return packSizeCalculator.findOptimalPackSize(packSize, orderSize);
    }
}
