package learn.binu;

import java.util.List;
import java.util.Scanner;

public class Main {

    // List of available pack sizes to place order for
    public static int[] PACK_SIZES = {250, 500, 1000, 2000, 5000};
    private static SweetShop sweetShop = new SweetShop();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int orderSize = getOrderSize();
        final List<Pack> packs = sweetShop.placeOrder(PACK_SIZES, orderSize);
        printOrder(packs);
    }

    private static void printOrder(List<Pack> packs) {
        System.out.println("Generated Order with the below details");
        for (Pack pack : packs) {
            System.out.println(pack);
        }
    }

    private static int getOrderSize() {
        System.out.println("Please enter the desired size of the order: ");
        int orderSize = 0;

        try {
            final String userInput = scanner.nextLine();
            orderSize = Integer.parseInt(userInput);
        } catch(NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            System.exit(1);
        }

        return orderSize;
    }
}
