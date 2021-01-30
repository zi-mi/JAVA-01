package ning.geek.gateway;

import java.util.Arrays;

import ning.geek.gateway.inbound.okhttp.OkHttpInboundServer;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";
    public final static String SERVERS = "http://localhost:8801,http://localhost:8802";
    public final static int PROXY_PORT = 8888;
    		
    public static void main(String[] args) {

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        OkHttpInboundServer server = new OkHttpInboundServer(PROXY_PORT, Arrays.asList(SERVERS.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + PROXY_PORT + " for server:" + SERVERS);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
