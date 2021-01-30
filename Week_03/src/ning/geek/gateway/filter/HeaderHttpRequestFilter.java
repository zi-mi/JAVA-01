package ning.geek.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        
    	fullRequest.headers().set("hello", "world");
        String token = fullRequest.headers().get("token");
        if (token != null && !"".equals(token)) {
        	fullRequest.headers().set("JSESSIONID", token);
        }
    }
}
