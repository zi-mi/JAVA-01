package ning.geek.gateway.inbound.okhttp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import ning.geek.gateway.filter.HeaderHttpRequestFilter;
import ning.geek.gateway.filter.HttpRequestFilter;
import ning.geek.gateway.outbound.okhttp.OkHttpOutboundHandler;

public class OkHttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(OkHttpInboundHandler.class);
    private final List<String> proxyServer;
    private OkHttpOutboundHandler handler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();
    
    public OkHttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        this.handler = new OkHttpOutboundHandler(this.proxyServer);
    }
    
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            log.info("channelRead流量接口请求开始，时间为{}", System.currentTimeMillis());
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
    
            handler.handle(fullRequest, ctx, filter);
    
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


}
