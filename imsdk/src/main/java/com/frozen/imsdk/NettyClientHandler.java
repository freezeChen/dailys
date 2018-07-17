package com.frozen.imsdk;

import android.util.Log;

import com.frozen.daily.imsdk.R;
import com.frozen.imsdk.model.IMConnect;

import java.util.Observable;
import java.util.Observer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundBuffer;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private NettyClient nettyClient = null;
    private Observable observable = null;

    public NettyClientHandler(NettyClient nettyClient, Observable observable) {
        super();
        this.nettyClient = nettyClient;
        this.observable = observable;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("read");
        ByteBuf strMsg = (ByteBuf) msg;
        byte[] decode = MessageDecoder.decode(strMsg.copy());


        System.out.println(new String(decode));
//        byte[] bytes = new byte[strMsg.readableBytes()];
//        strMsg.readBytes(bytes);
//
//
//        System.out.println("read : " + new String(bytes));
//

        super.channelRead(ctx, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ClientHandler" + "-------重连回调------");
        observable.notifyObservers(new IMConnect(IMConnect.CONNECT_RECONNECT));
        nettyClient.setConnectState(NettyClient.DISCONNECTION);
        nettyClient.connect();
        super.channelInactive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandl" + "registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {


        observable.notifyObservers(new IMConnect(IMConnect.CONNECT_SUCCESS));
        System.out.println("NettyClientHandler" + "=====连接成功回调=====");
        nettyClient.setConnectState(NettyClient.CONNECTED);
        super.channelActive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        observable.notifyObservers(new IMConnect(IMConnect.CONNECT_FAILED));
        System.out.println("NettyClientHandl" + "网络异常!");
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}