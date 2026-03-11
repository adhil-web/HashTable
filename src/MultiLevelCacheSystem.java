import java.util.*;

public class MultiLevelCacheSystem {

    private HashMap<String, String> L1Cache = new HashMap<>();
    private HashMap<String, String> L2Cache = new HashMap<>();
    private HashMap<String, String> database = new HashMap<>();

    public MultiLevelCacheSystem() {

        database.put("user1", "Adhil");
        database.put("user2", "Rahul");
        database.put("user3", "Sana");
    }

    public String getData(String key) {

        if (L1Cache.containsKey(key)) {
            System.out.println("L1 Cache HIT");
            return L1Cache.get(key);
        }

        if (L2Cache.containsKey(key)) {

            System.out.println("L2 Cache HIT");

            String value = L2Cache.get(key);

            L1Cache.put(key, value);

            return value;
        }

        System.out.println("Cache MISS → Fetching from database");

        String value = database.get(key);

        if (value != null) {

            L2Cache.put(key, value);
            L1Cache.put(key, value);
        }

        return value;
    }

    public static void main(String[] args) {

        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        System.out.println(cache.getData("user1"));
        System.out.println(cache.getData("user1"));
        System.out.println(cache.getData("user2"));
    }
}