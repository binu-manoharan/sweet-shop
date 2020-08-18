package learn.binu;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class PackSizeCalculatorTest {

    private PackSizeCalculator packSizeCalculator;

    @Before
    public void setUp() {
        packSizeCalculator = new PackSizeCalculator(Main.PACK_SIZE);
    }

    @Test
    public void should_find_the_smallest_pack_to_put_order_quantity_of_1() {
        final List<Pack> optimalPackSize = packSizeCalculator.findOptimalPackSize(1);
        assertThat(optimalPackSize.size(), is(1));

        final Pack singlePack = optimalPackSize.get(0);
        assertThat(singlePack.getCapacity(), is(250));
        assertThat(singlePack.getQuantity(), is(1));
        assertThat(singlePack.getWastage(), is(249));
    }
}
