import java.util.*;

public class FlashSaleInventoryManager {

    private HashMap<String, Integer> inventory = new HashMap<>();
    private Queue<Integer> waitingList = new LinkedList<>();

    public FlashSaleInventoryManager() {

        // product stock
        inventory.put("IPHONE15_256GB", 5);
    }

    public void checkStock(String productId) {

        int stock = inventory.getOrDefault(productId, 0);

        System.out.println(productId + " → " + stock + " units available");
    }

    public void purchaseItem(String productId, int userId) {

        int stock = inventory.getOrDefault(productId, 0);

        if (stock > 0) {

            inventory.put(productId, stock - 1);

            System.out.println("User " + userId + " purchased successfully. Remaining: " + (stock - 1));

        } else {

            waitingList.add(userId);

            System.out.println("Stock finished. User " + userId + " added to waiting list position #" + waitingList.size());
        }
    }

    public static void main(String[] args) {

        FlashSaleInventoryManager manager = new FlashSaleInventoryManager();

        manager.checkStock("IPHONE15_256GB");

        manager.purchaseItem("IPHONE15_256GB", 101);
        manager.purchaseItem("IPHONE15_256GB", 102);
        manager.purchaseItem("IPHONE15_256GB", 103);
        manager.purchaseItem("IPHONE15_256GB", 104);
        manager.purchaseItem("IPHONE15_256GB", 105);
        manager.purchaseItem("IPHONE15_256GB", 106);
    }
}