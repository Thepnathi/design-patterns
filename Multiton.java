import java.util.HashMap;
import java.util.Map;

class ServerDetails {
    private String name;
    private String ipAddress;

    public ServerDetails(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
    }
}

class BackendServer {
    // Immutable 
    private static Map<String, ServerDetails> serverPool = Map.of(
                                                         "1", new ServerDetails("Server 1", "121.100"),
                                                         "2", new ServerDetails("Server 2", "121.101")); 

    private static 

}

https://www.codeproject.com/Articles/1178694/Singleton-and-Multiton-Pattern

public class Multiton {
    
}
