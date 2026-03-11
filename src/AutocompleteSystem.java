import java.util.*;

public class AutocompleteSystem {

    private HashMap<String, Integer> queryFrequency = new HashMap<>();

    public void addQuery(String query) {

        queryFrequency.put(query,
                queryFrequency.getOrDefault(query, 0) + 1);
    }

    public List<String> search(String prefix) {

        List<String> results = new ArrayList<>();

        for (String query : queryFrequency.keySet()) {

            if (query.startsWith(prefix)) {
                results.add(query);
            }
        }

        results.sort((a, b) ->
                queryFrequency.get(b) - queryFrequency.get(a));

        if (results.size() > 5) {
            return results.subList(0, 5);
        }

        return results;
    }

    public static void main(String[] args) {

        AutocompleteSystem system = new AutocompleteSystem();

        system.addQuery("java tutorial");
        system.addQuery("javascript guide");
        system.addQuery("java download");
        system.addQuery("java tutorial");
        system.addQuery("java interview questions");

        System.out.println("Suggestions for 'jav':");
        System.out.println(system.search("jav"));
    }
}