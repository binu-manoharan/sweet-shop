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
        packSizeCalculator = new PackSizeCalculator(Main.PACK_SIZES);
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

    @Test
    public void should_find_smallest_pack_that_fulfil_the_order() {
        assertThat(
                packSizeCalculator.findSmallestPackThatFullyContainsTheOrder(Main.PACK_SIZES, 1),
                is(250)
        );

        assertThat(
                packSizeCalculator.findSmallestPackThatFullyContainsTheOrder(Main.PACK_SIZES, 450),
                is(500)
        );

        assertThat(
                packSizeCalculator.findSmallestPackThatFullyContainsTheOrder(Main.PACK_SIZES, 750),
                is(1000)
        );

        assertThat(
                packSizeCalculator.findSmallestPackThatFullyContainsTheOrder(Main.PACK_SIZES, 1500),
                is(2000)
        );

        assertThat(
                packSizeCalculator.findSmallestPackThatFullyContainsTheOrder(Main.PACK_SIZES, 2500),
                is(5000)
        );

        assertThat(
                packSizeCalculator.findSmallestPackThatFullyContainsTheOrder(Main.PACK_SIZES, 5500),
                is(-1)
        );
    }
}
