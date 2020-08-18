package learn.binu;

import java.util.Scanner;

public class Main {

    public static int[] PACK_SIZES = new int[]{250, 500, 1000, 2000, 5000};
    private static Scanner scanner = new Scanner(System.in);
    private static SweetShop sweetShop = new SweetShop();

    public static void main(String[] args) {
        final String order = getOrderSize();
        final int orderSize = Integer.parseInt(order);

        sweetShop.placeOrder(PACK_SIZES, orderSize);
    }

    private static String getOrderSize() {
        System.out.println("Please enter the desired size of the order: ");
        return scanner.nextLine();
    }
}
