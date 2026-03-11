import java.util.*;

public class FraudDetectionSystem {

    public static boolean detectFraud(int[] transactions, int suspiciousAmount) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < transactions.length; i++) {

            int complement = suspiciousAmount - transactions[i];

            if (map.containsKey(complement)) {

                System.out.println("Fraud detected!");
                System.out.println("Transactions: " + complement + " + " + transactions[i]);
                return true;
            }

            map.put(transactions[i], i);
        }

        System.out.println("No suspicious transactions found.");
        return false;
    }

    public static void main(String[] args) {

        int[] transactions = {120, 450, 300, 200, 150};

        int suspiciousAmount = 500;

        detectFraud(transactions, suspiciousAmount);
    }
}