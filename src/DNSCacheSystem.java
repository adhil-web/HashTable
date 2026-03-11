import java.util.*;

class DNSEntry {

    String ipAddress;
    long expiryTime;

    public DNSEntry(String ipAddress, long ttl) {
        this.ipAddress = ipAddress;
        this.expiryTime = System.currentTimeMillis() + ttl;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}

public class DNSCacheSystem {

    private HashMap<String, DNSEntry> cache = new HashMap<>();

    public String resolve(String domain) {

        if (cache.containsKey(domain)) {

            DNSEntry entry = cache.get(domain);

            if (!entry.isExpired()) {
                System.out.println("Cache HIT");
                return entry.ipAddress;
            }

            System.out.println("Cache EXPIRED");
            cache.remove(domain);
        }

        System.out.println("Cache MISS → Querying upstream DNS");

        String newIP = queryUpstreamDNS(domain);

        cache.put(domain, new DNSEntry(newIP, 5000));

        return newIP;
    }

    private String queryUpstreamDNS(String domain) {

        return "172.217.14.206"; // simulated IP
    }

    public static void main(String[] args) throws InterruptedException {

        DNSCacheSystem dns = new DNSCacheSystem();

        System.out.println("IP: " + dns.resolve("google.com"));
        Thread.sleep(2000);

        System.out.println("IP: " + dns.resolve("google.com"));
        Thread.sleep(6000);

        System.out.println("IP: " + dns.resolve("google.com"));
    }
}