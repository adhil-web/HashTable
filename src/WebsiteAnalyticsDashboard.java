import java.util.*;

public class WebsiteAnalyticsDashboard {

    private HashMap<String, Integer> pageViews = new HashMap<>();
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(String url, String userId, String source) {

        // count page views
        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        // track unique visitors
        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        // track traffic sources
        trafficSources.put(source, trafficSources.getOrDefault(source, 0) + 1);
    }

    public void showDashboard() {

        System.out.println("\n=== Top Pages ===");

        for (String page : pageViews.keySet()) {

            int views = pageViews.get(page);
            int unique = uniqueVisitors.get(page).size();

            System.out.println(page + " → " + views + " views (" + unique + " unique)");
        }

        System.out.println("\n=== Traffic Sources ===");

        for (String source : trafficSources.keySet()) {

            System.out.println(source + " → " + trafficSources.get(source) + " visits");
        }
    }

    public static void main(String[] args) {

        WebsiteAnalyticsDashboard dashboard = new WebsiteAnalyticsDashboard();

        dashboard.processEvent("/article/breaking-news", "user1", "google");
        dashboard.processEvent("/article/breaking-news", "user2", "facebook");
        dashboard.processEvent("/sports/championship", "user3", "direct");
        dashboard.processEvent("/sports/championship", "user1", "google");

        dashboard.showDashboard();
    }
}