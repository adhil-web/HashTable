import java.util.*;

public class UsernameAvailabilityChecker {

    private HashMap<String, Integer> users = new HashMap<>();
    private HashMap<String, Integer> attempts = new HashMap<>();

    public UsernameAvailabilityChecker() {

        users.put("john_doe", 1);
        users.put("admin", 2);
        users.put("alice123", 3);
    }

    public boolean checkAvailability(String username) {

        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        return !users.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        suggestions.add(username + "1");
        suggestions.add(username + "2");
        suggestions.add(username + "_official");

        return suggestions;
    }

    public static void main(String[] args) {

        UsernameAvailabilityChecker checker = new UsernameAvailabilityChecker();

        String username = "john_doe";

        if (checker.checkAvailability(username)) {
            System.out.println("Username available");
        } else {
            System.out.println("Username taken");
            System.out.println("Suggestions: " + checker.suggestAlternatives(username));
        }
    }
}