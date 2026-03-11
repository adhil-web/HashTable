import java.util.*;

class ClientRequest {

    int requestCount;
    long startTime;

    public ClientRequest() {
        this.requestCount = 0;
        this.startTime = System.currentTimeMillis();
    }
}

public class RateLimiterSystem {

    private HashMap<String, ClientRequest> clientRequests = new HashMap<>();

    private static final int LIMIT = 5;
    private static final long WINDOW = 60000; // 1 minute

    public boolean checkRateLimit(String clientId) {

        clientRequests.putIfAbsent(clientId, new ClientRequest());

        ClientRequest request = clientRequests.get(clientId);

        long currentTime = System.currentTimeMillis();

        if (currentTime - request.startTime > WINDOW) {

            request.requestCount = 0;
            request.startTime = currentTime;
        }

        if (request.requestCount < LIMIT) {

            request.requestCount++;

            System.out.println("Request allowed. Remaining: " + (LIMIT - request.requestCount));
            return true;

        } else {

            System.out.println("Rate limit exceeded. Try later.");
            return false;
        }
    }

    public static void main(String[] args) {

        RateLimiterSystem limiter = new RateLimiterSystem();

        for (int i = 1; i <= 7; i++) {

            System.out.println("Request " + i);
            limiter.checkRateLimit("client123");
        }
    }
}