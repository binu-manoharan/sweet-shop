package learn.binu;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class PackSizeCalculatorTest {

    private PackSizeCalculator packSizeCalculator;
    private final int[] customPackSize1 = {1, 2, 3};

    @Before
    public void setUp() {
        packSizeCalculator = new PackSizeCalculator();
    }

    @Test
    public void should_find_the_smallest_pack_to_put_order_quantity_of_1() {
        final List<Pack> optimalPackSize = packSizeCalculator.findOptimalPackSize(Main.PACK_SIZES, 1);
        assertThat(optimalPackSize.size(), is(1));

        assertPack(optimalPackSize.get(0), 250, 1, 249);
    }

    @Test
    public void should_find_the_smallest_pack_to_put_order_quantity_of_250() {
        final List<Pack> optimalPackSize = packSizeCalculator.findOptimalPackSize(Main.PACK_SIZES, 250);
        assertThat(optimalPackSize.size(), is(1));

        assertPack(optimalPackSize.get(0), 250, 1, 0);
    }

    @Test
    public void should_find_the_smallest_pack_to_put_order_quantity_of_251() {
        final List<Pack> optimalPackSize = packSizeCalculator.findOptimalPackSize(Main.PACK_SIZES, 251);
        assertThat(optimalPackSize.size(), is(1));

        assertPack(optimalPackSize.get(0), 500, 1, 249);
    }

    @Test
    public void should_find_the_smallest_pack_to_put_order_quantity_of_501() {
        final List<Pack> optimalPackSize = packSizeCalculator.findOptimalPackSize(Main.PACK_SIZES, 501);
        assertThat(optimalPackSize.size(), is(2));

        assertPack(optimalPackSize.get(0), 500, 1, 0);
        assertPack(optimalPackSize.get(1), 250, 1, 249);
    }

    @Test
    public void should_find_the_smallest_pack_to_put_order_quantity_of_12001() {
        final List<Pack> optimalPackSize = packSizeCalculator.findOptimalPackSize(Main.PACK_SIZES, 12001);
        assertThat(optimalPackSize.size(), is(3));

        assertPack(optimalPackSize.get(0), 5000, 2, 0);
        assertPack(optimalPackSize.get(1), 2000, 1, 0);
        assertPack(optimalPackSize.get(2), 250, 1, 249);
    }

    @Test
    public void should_find_the_smallest_pack_to_put_order_quantity_of_14999() {
        final List<Pack> optimalPackSize = packSizeCalculator.findOptimalPackSize(Main.PACK_SIZES, 14999);
        assertThat(optimalPackSize.size(), is(1));

        assertPack(optimalPackSize.get(0), 5000, 3, 1);
    }

    @Test
    public void should_find_the_smallest_pack_to_put_order_quantity_of_874() {
        final List<Pack> optimalPackSize = packSizeCalculator.findOptimalPackSize(Main.PACK_SIZES, 749);
        assertThat(optimalPackSize.size(), is(2));
        assertPack(optimalPackSize.get(0), 500, 1, 0);
        assertPack(optimalPackSize.get(1), 250, 1, 1);
    }

    @Test
    public void should_work_with_various_pack_sizes() {
        final List<Pack> optimalPackSize1 = packSizeCalculator.findOptimalPackSize(customPackSize1, 16);
        assertThat(optimalPackSize1.size(), is(2));
        assertPack(optimalPackSize1.get(0), 3, 5, 0);
        assertPack(optimalPackSize1.get(1), 1, 1, 0);

        final List<Pack> optimalPackSize2 = packSizeCalculator.findOptimalPackSize(customPackSize1, 2);
        assertThat(optimalPackSize2.size(), is(1));
        assertPack(optimalPackSize2.get(0), 2, 1, 0);
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

    @Test
    public void should_return_array_with_smaller_pack_size() {
        assertArrayEquals(packSizeCalculator.getRemainingPackSize(Main.PACK_SIZES, 100), new int[]{});
        assertArrayEquals(packSizeCalculator.getRemainingPackSize(Main.PACK_SIZES, 500), new int[]{250});
        assertArrayEquals(packSizeCalculator.getRemainingPackSize(Main.PACK_SIZES, 1000), new int[]{250, 500});
        assertArrayEquals(packSizeCalculator.getRemainingPackSize(Main.PACK_SIZES, 2000), new int[]{250, 500, 1000});
        assertArrayEquals(packSizeCalculator.getRemainingPackSize(Main.PACK_SIZES, 5000), new int[]{250, 500, 1000, 2000});
    }

    private void assertPack(Pack pack, int expectedCapacity, int expectedQuantity, int expectedWastage) {
        assertThat(pack.getCapacity(), is(expectedCapacity));
        assertThat(pack.getQuantity(), is(expectedQuantity));
        assertThat(pack.getWastage(), is(expectedWastage));
    }
}
